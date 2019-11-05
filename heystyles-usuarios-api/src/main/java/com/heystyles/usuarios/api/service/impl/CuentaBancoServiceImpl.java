package com.heystyles.usuarios.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.usuarios.api.dao.CuentaBancoDao;
import com.heystyles.usuarios.api.dao.ProveedorDao;
import com.heystyles.usuarios.api.entity.CuentaBancoEntity;
import com.heystyles.usuarios.api.entity.ProveedorEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.api.service.CuentaBancoService;
import com.heystyles.usuarios.core.domain.CuentaBanco;
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
public class CuentaBancoServiceImpl
        extends ServiceImpl<CuentaBanco, CuentaBancoEntity, Long> implements CuentaBancoService {

    @Autowired
    private CuentaBancoDao cuentaBancoDao;

    @Autowired
    private ProveedorDao proveedorDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<CuentaBancoEntity, Long> getDao() {
        return cuentaBancoDao;
    }

    @Override
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public void upsert(Long proveedorId, List<CuentaBanco> cuentasBanco) {
        if (cuentasBanco == null) {
            return;
        }
        List<CuentaBancoEntity> existing = cuentaBancoDao.findByProveedorId(proveedorId);

        ProveedorEntity proveedorEntity = proveedorDao.findOne(proveedorId);
        List<CuentaBancoEntity> toDelete = new ArrayList<>();
        List<CuentaBancoEntity> toSave = new ArrayList<>();

        Set<Long> oldPersonasIds = existing
                .stream()
                .map(e -> e.getId())
                .collect(Collectors.toSet());

        existing.stream()
                .filter(p -> !cuentasBanco.contains(p.getId()))
                .forEach(p -> toDelete.add(p));

        cuentasBanco.stream()
                .filter(l -> !oldPersonasIds.contains(l.getId()))
                .forEach(l -> {
                    CuentaBancoEntity entity = getConverterService().convertTo(l, CuentaBancoEntity.class);
                    entity.setProveedor(proveedorEntity);
                    toSave.add(entity);
                });

        cuentaBancoDao.delete(toDelete);
        cuentaBancoDao.save(toSave);
    }

    @Override
    public List<CuentaBanco> findByProveedoId(Long proveedorId) {
        return getConverterService().convertTo(cuentaBancoDao.findByProveedorId(proveedorId), CuentaBanco.class);
    }

    @Override
    public CuentaBanco getCuentaBanco(Long cuentaBancoId) {
        return Optional.ofNullable(findById(cuentaBancoId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.CUENTA_BANCO_NOT_FOUND,
                                                 new String[]{String.valueOf(cuentaBancoId)}, getLocale())));
    }
}
