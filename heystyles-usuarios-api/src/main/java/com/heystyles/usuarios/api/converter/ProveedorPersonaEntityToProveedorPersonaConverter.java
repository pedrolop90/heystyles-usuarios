package com.heystyles.usuarios.api.converter;

import com.heystyles.common.service.ConverterService;
import com.heystyles.usuarios.api.entity.ProveedorPersonaEntity;
import com.heystyles.usuarios.core.domain.Persona;
import com.heystyles.usuarios.core.domain.ProveedorPersona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProveedorPersonaEntityToProveedorPersonaConverter
        implements Converter<ProveedorPersonaEntity, ProveedorPersona> {

    @Autowired
    private ConverterService converterService;

    @Override
    public ProveedorPersona convert(ProveedorPersonaEntity entity) {
        ProveedorPersona bean = new ProveedorPersona();
        bean.setId(entity.getId());
        bean.setPersona(converterService.convertTo(entity.getPersona(), Persona.class));
        bean.setProveedorId(entity.getProveedor().getId());
        return bean;
    }
}
