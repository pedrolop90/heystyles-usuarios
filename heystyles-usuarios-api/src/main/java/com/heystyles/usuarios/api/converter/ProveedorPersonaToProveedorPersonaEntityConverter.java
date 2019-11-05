package com.heystyles.usuarios.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.ConverterService;
import com.heystyles.usuarios.api.dao.ProveedorDao;
import com.heystyles.usuarios.api.dao.ProveedorPersonaDao;
import com.heystyles.usuarios.api.entity.PersonaEntity;
import com.heystyles.usuarios.api.entity.ProveedorEntity;
import com.heystyles.usuarios.api.entity.ProveedorPersonaEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.core.domain.ProveedorPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class ProveedorPersonaToProveedorPersonaEntityConverter
        implements Converter<ProveedorPersona, ProveedorPersonaEntity> {

    @Autowired
    private ProveedorPersonaDao proveedorPersonaDao;

    @Autowired
    private ConverterService converterService;

    @Autowired
    private ProveedorDao proveedorDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public ProveedorPersonaEntity convert(ProveedorPersona bean) {
        ProveedorPersonaEntity entity = null;
        if (bean.getId() == null) {
            entity = new ProveedorPersonaEntity();
        }
        else {
            entity = Optional.ofNullable(proveedorPersonaDao.findOne(bean.getId()))
                    .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                            messageSource.getMessage(MessageKeys.PROVEEDOR_PERSONA_NOT_FOUND,
                                                     new String[]{String.valueOf(bean.getId())}, getLocale())));
        }
        entity.setPersona(converterService.convertTo(bean.getPersona(), PersonaEntity.class));
        ProveedorEntity proveedorEntity = Optional.ofNullable(proveedorDao.findOne(bean.getProveedorId()))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.PROVEEDOR_NOT_FOUND,
                                                 new String[]{String.valueOf(bean.getProveedorId())}, getLocale())));
        entity.setProveedor(proveedorEntity);
        return entity;
    }
}
