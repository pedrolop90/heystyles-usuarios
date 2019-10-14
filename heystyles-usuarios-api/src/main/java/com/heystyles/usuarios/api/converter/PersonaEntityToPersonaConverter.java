package com.heystyles.usuarios.api.converter;

import com.heystyles.usuarios.api.entity.PersonaEntity;
import com.heystyles.usuarios.core.domain.Persona;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonaEntityToPersonaConverter implements Converter<PersonaEntity, Persona> {
    @Override
    public Persona convert(PersonaEntity entity) {
        Persona bean = new Persona();
        bean.setId(entity.getId());
        bean.setNombres(entity.getNombres());
        bean.setApellidos(entity.getApellidos());
        bean.setNumeroDocumento(entity.getNumeroDocumento());
        bean.setEmail(entity.getEmail());
        bean.setFechaNacimiento(entity.getFechaNacimiento());
        bean.setTipoDocumento(entity.getTipoDocumento());
        return bean;
    }
}
