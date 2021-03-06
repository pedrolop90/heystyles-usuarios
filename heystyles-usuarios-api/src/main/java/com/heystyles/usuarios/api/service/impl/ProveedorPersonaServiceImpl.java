package com.heystyles.usuarios.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.ConverterService;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.usuarios.api.dao.ProveedorDao;
import com.heystyles.usuarios.api.dao.ProveedorPersonaDao;
import com.heystyles.usuarios.api.entity.PersonaEntity;
import com.heystyles.usuarios.api.entity.ProveedorEntity;
import com.heystyles.usuarios.api.entity.ProveedorPersonaEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.api.service.ProveedorPersonaService;
import com.heystyles.usuarios.core.domain.Persona;
import com.heystyles.usuarios.core.domain.ProveedorPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class ProveedorPersonaServiceImpl
        extends ServiceImpl<ProveedorPersona, ProveedorPersonaEntity, Long> implements ProveedorPersonaService {

    @Autowired
    private ProveedorPersonaDao proveedorPersonaDao;

    @Autowired
    private ProveedorDao proveedorDao;

    @Autowired
    private ConverterService converterService;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<ProveedorPersonaEntity, Long> getDao() {
        return proveedorPersonaDao;
    }

    @Override
    public void upsert(Long proveedorId, List<Persona> contactos) {
        if (contactos == null) {
            return;
        }
        List<ProveedorPersonaEntity> existing = proveedorPersonaDao.findByProveedorId(proveedorId);

        ProveedorEntity proveedorEntity = proveedorDao.findOne(proveedorId);
        List<ProveedorPersonaEntity> toDelete = new ArrayList<>();
        List<ProveedorPersonaEntity> toSave = new ArrayList<>();


        Set<Long> contactosRequest =  contactos
                .stream()
                .map(e -> e.getId())
                .collect(Collectors.toSet());

        Set<Long> oldPersonasIds = existing
                .stream()
                .map(e -> e.getPersona().getId())
                .collect(Collectors.toSet());

        existing.stream()
                .filter(p -> !contactosRequest.contains(p.getPersona().getId()))
                .forEach(p -> toDelete.add(p));

        contactos.stream()
                .filter(l -> !oldPersonasIds.contains(l.getId()))
                .forEach(l -> {
                    ProveedorPersonaEntity entity = new ProveedorPersonaEntity();
                    entity.setProveedor(proveedorEntity);
                    entity.setPersona(converterService.convertTo(l, PersonaEntity.class));
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

    @Override
    public ProveedorPersona getProveedorPersona(Long proveedorPersonaId) {
        return Optional.ofNullable(findById(proveedorPersonaId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.PROVEEDOR_PERSONA_NOT_FOUND,
                                                 new String[]{String.valueOf(proveedorPersonaId)},
                                                 getLocale())));
    }
}
