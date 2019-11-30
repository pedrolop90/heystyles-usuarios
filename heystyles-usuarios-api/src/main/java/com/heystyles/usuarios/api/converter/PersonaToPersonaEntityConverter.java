package com.heystyles.usuarios.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.usuarios.api.dao.PersonaDao;
import com.heystyles.usuarios.api.entity.PersonaEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.core.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class PersonaToPersonaEntityConverter implements Converter<Persona, PersonaEntity> {

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public PersonaEntity convert(Persona bean) {
        PersonaEntity entity = null;
        if (bean.getId() == null) {
            entity = new PersonaEntity();
            entity.setId(bean.getId());
        }
        else {
            entity = Optional.ofNullable(personaDao.getOne(bean.getId())).orElseThrow(() ->
                    APIExceptions.objetoNoEncontrado(messageSource.getMessage(MessageKeys.PERSONA_NOT_FOUND,
                            new String[]{String.valueOf(bean.getId())}, getLocale())));
        }
        entity.setNombres(bean.getNombres());
        entity.setApellidos(bean.getApellidos());
        entity.setNumeroDocumento(bean.getNumeroDocumento());
        entity.setEmail(bean.getEmail());
        entity.setFechaNacimiento(bean.getFechaNacimiento());
        entity.setTipoDocumento(bean.getTipoDocumento());
        entity.setTelefono(bean.getTelefono());
        if (bean.getFotografiaId() != null) {
            entity.setFotografiaId(bean.getFotografiaId());
        }

        return entity;
    }
}
