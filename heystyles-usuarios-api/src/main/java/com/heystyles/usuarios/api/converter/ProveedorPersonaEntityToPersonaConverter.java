package com.heystyles.usuarios.api.converter;

import com.heystyles.common.service.ConverterService;
import com.heystyles.usuarios.api.entity.ProveedorPersonaEntity;
import com.heystyles.usuarios.core.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProveedorPersonaEntityToPersonaConverter implements Converter<ProveedorPersonaEntity, Persona> {

    @Autowired
    private ConverterService converterService;

    @Override
    public Persona convert(ProveedorPersonaEntity entity) {
        return converterService.convertTo(entity.getPersona(), Persona.class);
    }
}
