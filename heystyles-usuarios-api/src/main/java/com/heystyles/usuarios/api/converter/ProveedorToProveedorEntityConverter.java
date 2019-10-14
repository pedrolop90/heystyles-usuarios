package com.heystyles.usuarios.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.usuarios.api.dao.ProveedorDao;
import com.heystyles.usuarios.api.entity.ProveedorEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.core.domain.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class ProveedorToProveedorEntityConverter implements Converter<Proveedor, ProveedorEntity> {

    @Autowired
    private ProveedorDao proveedorDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ProveedorEntity convert(Proveedor bean) {
        ProveedorEntity entity = null;
        if (bean.getId() == null) {
            entity = new ProveedorEntity();
        }
        else {
            entity = Optional.ofNullable(proveedorDao.findOne(bean.getId())).orElseThrow(() ->
                    APIExceptions.objetoNoEncontrado(messageSource.getMessage(MessageKeys.PROVEEDOR_NOT_FOUND,
                            new String[]{String.valueOf(bean.getId())}, getLocale())));
        }
        entity.setNombre(bean.getNombre());
        entity.setDescripcion(bean.getDescripcion());
        entity.setTelefono(bean.getTelefono());
        entity.setDireccion(bean.getDireccion());
        entity.setEmail(bean.getEmail());
        entity.setFechaLimitePago(bean.getFechaLimitePago());
        return entity;
    }
}
