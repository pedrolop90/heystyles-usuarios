package com.heystyles.usuarios.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.usuarios.api.dao.ProveedorDao;
import com.heystyles.usuarios.api.entity.ProveedorEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.api.service.ProveedorService;
import com.heystyles.usuarios.core.domain.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class ProveedorServiceImpl extends ServiceImpl<Proveedor, ProveedorEntity, Long> implements ProveedorService {

    @Autowired
    private ProveedorDao proveedorDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<ProveedorEntity, Long> getDao() {
        return proveedorDao;
    }

    @Override
    public Proveedor getProveedor(Long proveedorId) {
        return Optional.ofNullable(findById(proveedorId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.PROVEEDOR_NOT_FOUND,
                                new String[]{String.valueOf(proveedorId)}, getLocale())));
    }
}
