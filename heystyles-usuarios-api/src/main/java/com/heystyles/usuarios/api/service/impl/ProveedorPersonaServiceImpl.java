package com.heystyles.usuarios.api.service.impl;

import com.heystyles.common.service.ConverterService;
import com.heystyles.usuarios.api.dao.ProveedorDao;
import com.heystyles.usuarios.api.dao.ProveedorPersonaDao;
import com.heystyles.usuarios.api.entity.PersonaEntity;
import com.heystyles.usuarios.api.entity.ProveedorEntity;
import com.heystyles.usuarios.api.entity.ProveedorPersonaEntity;
import com.heystyles.usuarios.api.service.ProveedorPersonaService;
import com.heystyles.usuarios.core.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProveedorPersonaServiceImpl implements ProveedorPersonaService {

    @Autowired
    private ProveedorPersonaDao proveedorPersonaDao;

    @Autowired
    private ProveedorDao proveedorDao;

    @Autowired
    private ConverterService converterService;

    @Override
    public void uppsert(Long proveedorId, List<Persona> contactos) {
        if (contactos == null || contactos.size() == 0) {
            return;
        }
        List<ProveedorPersonaEntity> existing = proveedorPersonaDao.findByProveedorId(proveedorId);

        ProveedorEntity proveedorEntity = proveedorDao.findOne(proveedorId);
        List<ProveedorPersonaEntity> toDelete = new ArrayList<>();
        List<ProveedorPersonaEntity> toSave = new ArrayList<>();

        Set<Long> oldPersonasIds = existing
                .stream()
                .map(e -> e.getPersona().getId())
                .collect(Collectors.toSet());

        Set<Long> newPersonasIds = contactos
                .stream()
                .map(Persona::getId)
                .collect(Collectors.toSet());

        existing.stream()
                .filter(p -> !newPersonasIds.contains(p.getPersona().getId()))
                .forEach(p -> toDelete.add(p));

        newPersonasIds.stream()
                .filter(l -> !oldPersonasIds.contains(l))
                .forEach(l -> {
                    ProveedorPersonaEntity entity = new ProveedorPersonaEntity();
                    entity.setProveedor(proveedorEntity);
                    entity.setPersona(new PersonaEntity(l));
                    toSave.add(entity);
                });

        proveedorPersonaDao.delete(toDelete);
        proveedorPersonaDao.save(toSave);
    }

    @Override
    public List<Persona> findContactosByProveedor(Long proveedorId) {
        return converterService.convertTo(
                proveedorPersonaDao.findByProveedorId(proveedorId)
                        .stream()
                        .map(ProveedorPersonaEntity::getPersona)
                        .collect(Collectors.toList()), Persona.class);
    }
}
