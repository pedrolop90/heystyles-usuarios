package com.heystyles.usuarios.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.usuarios.api.dao.PersonaDao;
import com.heystyles.usuarios.api.entity.PersonaEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.api.service.PersonaService;
import com.heystyles.usuarios.core.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class PersonaServiceImpl
        extends PersonableServiceImpl<Persona, PersonaEntity, Long> implements PersonaService {

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<PersonaEntity, Long> getDao() {
        return personaDao;
    }

    @Override
    public Persona getPersona(Long personaId) {
        return Optional.ofNullable(super.findById(personaId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(messageSource.getMessage(
                        MessageKeys.PERSONA_NOT_FOUND,
                        new String[]{String.valueOf(personaId)}, getLocale())));

    }

    @Override
    public Persona getPersonaByNumeroDocumento(String numeroDocumento) {
        PersonaEntity personaEntity = personaDao.findByNumeroDocumento(numeroDocumento);
        if (personaEntity != null) {
            return getConverterService().convertTo(personaEntity, Persona.class);
        }
        return null;
    }
}
