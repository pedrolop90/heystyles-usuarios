package com.heystyles.usuarios.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.common.types.Estado;
import com.heystyles.common.types.Page;
import com.heystyles.usuarios.api.dao.ProveedorDao;
import com.heystyles.usuarios.api.entity.ProveedorEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.api.service.CuentaBancoService;
import com.heystyles.usuarios.api.service.ProveedorPersonaService;
import com.heystyles.usuarios.api.service.ProveedorService;
import com.heystyles.usuarios.core.domain.Proveedor;
import com.heystyles.usuarios.core.domain.ProveedorExtended;
import com.heystyles.usuarios.core.dto.ProveedorListResponse;
import com.heystyles.usuarios.core.filter.ProveedorFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class ProveedorServiceImpl
        extends ServiceImpl<Proveedor, ProveedorEntity, Long> implements ProveedorService {

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
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(ProveedorExtended request) {
        update(request.getProveedor());
        cuentaBancoService.upsert(request.getProveedor().getId(), request.getCuentasBanco());
        proveedorPersonaService.upsert(request.getProveedor().getId(), request.getContactos());
    }

    @Override
    public void delete(Long proveedorId) {
        Proveedor proveedor = getProveedor(proveedorId);
        proveedor.setEstado(Estado.INACTIVO);
        super.update(proveedor);
    }

    @Override
    public Proveedor getProveedor(Long proveedorId) {
        return Optional.ofNullable(findById(proveedorId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.PROVEEDOR_NOT_FOUND,
                                new String[]{String.valueOf(proveedorId)}, getLocale())));
    }

    @Override
    public ProveedorExtended getProveedorExtended(Long proveedorId) {
        ProveedorEntity proveedorEntity = Optional.ofNullable(proveedorDao.findOne(proveedorId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.PROVEEDOR_NOT_FOUND,
                                new String[]{String.valueOf(proveedorId)}, getLocale())));
        return getConverterService().convertTo(proveedorEntity, ProveedorExtended.class);
    }

    @Override
    public void activarProveedor(Long proveedorId) {
        Proveedor proveedor = getProveedor(proveedorId);
        proveedor.setEstado(Estado.ACTIVO);
        super.update(proveedor);
    }

    @Override
    public ProveedorListResponse filter(ProveedorFilter filter) {
        filter = Optional.ofNullable(filter).orElse(new ProveedorFilter());
        Page<ProveedorEntity> page = proveedorDao.getPage(filter);
        return new ProveedorListResponse(
                page.getTotalElements(),
                getConverterService().convertTo(page.getContent(), Proveedor.class)
        );
    }
}
