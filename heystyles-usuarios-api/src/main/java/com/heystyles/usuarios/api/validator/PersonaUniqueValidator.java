package com.heystyles.usuarios.api.validator;

import com.heystyles.common.validation.ValidationError;
import com.heystyles.common.validation.Validator;
import com.heystyles.common.validation.groups.Insert;
import com.heystyles.common.validation.groups.Update;
import com.heystyles.usuarios.api.dao.PersonaDao;
import com.heystyles.usuarios.api.entity.PersonaEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.core.domain.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class PersonaUniqueValidator implements Validator<Persona> {

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<ValidationError> validate(Persona persona) {
        List<ValidationError> errors = new ArrayList<>();
        PersonaEntity personaNumeroDocumento = personaDao.findByNumeroDocumento(persona.getNumeroDocumento());
        if (personaNumeroDocumento != null && !Objects.equals(persona.getId(), personaNumeroDocumento.getId())) {
            errors.add(new ValidationError("NumeroDocumento", messageSource.getMessage(
                    MessageKeys.PERSONA_NUMERO_DOCUMENTO_DUPLICATED,
                    new String[]{String.valueOf(persona.getNumeroDocumento())}, getLocale())));
        }
        PersonaEntity personaEmail = personaDao.findByEmail(persona.getEmail());
        if (personaEmail != null && !Objects.equals(persona.getId(), personaEmail.getId())) {
            errors.add(new ValidationError("Email", messageSource.getMessage(
                    MessageKeys.PERSONA_EMAIL_DUPLICATED,
                    new String[]{String.valueOf(persona.getEmail())}, getLocale())));
        }
        return errors;
    }

    @Override
    public List<Class<?>> getScopes() {
        return Arrays.asList(Insert.class, Update.class);
    }
}
