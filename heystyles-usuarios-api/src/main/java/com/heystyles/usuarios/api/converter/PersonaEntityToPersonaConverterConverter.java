package com.heystyles.usuarios.api.converter;

import com.heystyles.usuarios.api.entity.PersonaEntity;
import com.heystyles.usuarios.core.domain.Persona;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonaEntityToPersonaConverterConverter
        extends PersonableEntityToPersonaConverter<PersonaEntity, Persona>
        implements Converter<PersonaEntity, Persona> {
    @Override
    public Persona convert(PersonaEntity entity) {
        Persona bean = new Persona();
        bean.setId(entity.getId());
        convertPersona(entity, bean);
        return bean;
    }
}
