package com.heystyles.usuarios.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.usuarios.api.dao.ProveedorDao;
import com.heystyles.usuarios.api.entity.ProveedorEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.api.service.CuentaBancoService;
import com.heystyles.usuarios.api.service.ProveedorPersonaService;
import com.heystyles.usuarios.api.service.ProveedorService;
import com.heystyles.usuarios.core.domain.Proveedor;
import com.heystyles.usuarios.core.domain.ProveedorExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class ProveedorServiceImpl extends ServiceImpl<Proveedor, ProveedorEntity, Long> implements ProveedorService {

    @Autowired
    private ProveedorDao proveedorDao;

    @Autowired
    private ProveedorPersonaService proveedorPersonaService;

    @Autowired
    private CuentaBancoService cuentaBancoService;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<ProveedorEntity, Long> getDao() {
        return proveedorDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long insert(ProveedorExtended request) {
        Long id = super.insert(request.getProveedor());
        cuentaBancoService.upsert(id, request.getCuentasBanco());
        proveedorPersonaService.upsert(id, request.getContactos());
        return id;
    }

    @Override
    public ProveedorExtended getProveedor(Long proveedorId) {
        ProveedorEntity proveedorEntity = Optional.ofNullable(proveedorDao.findOne(proveedorId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.PROVEEDOR_NOT_FOUND,
                                                 new String[]{String.valueOf(proveedorId)}, getLocale())));
        return getConverterService().convertTo(proveedorEntity, ProveedorExtended.class);
    }
}
