package com.heystyles.usuarios.api.validator;

import com.heystyles.common.validation.ValidationError;
import com.heystyles.common.validation.Validator;
import com.heystyles.common.validation.groups.Insert;
import com.heystyles.common.validation.groups.Update;
import com.heystyles.usuarios.api.dao.UsuarioDao;
import com.heystyles.usuarios.api.entity.UsuarioEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.core.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class UsuarioUniqueValidator implements Validator<Usuario> {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<ValidationError> validate(Usuario usuario) {
        List<ValidationError> errors = new ArrayList<>();
        UsuarioEntity usuarioNumeroDocumento = usuarioDao.findByPersonaNumeroDocumento(usuario.getNumeroDocumento());
        if (usuarioNumeroDocumento != null && !Objects.equals(usuarioNumeroDocumento.getId(), usuario.getId())) {
            errors.add(new ValidationError("NumeroDocumento", messageSource.getMessage(
                    MessageKeys.PERSONA_NUMERO_DOCUMENTO_DUPLICATED,
                    new String[]{String.valueOf(usuario.getNumeroDocumento())}, getLocale())));
        }
        UsuarioEntity usuarioEmail = usuarioDao.findByPersonaEmail(usuario.getEmail());
        if (usuarioEmail != null && !Objects.equals(usuarioEmail.getId(), usuario.getId())) {
            errors.add(new ValidationError("Email", messageSource.getMessage(
                    MessageKeys.PERSONA_EMAIL_DUPLICATED,
                    new String[]{String.valueOf(usuario.getEmail())}, getLocale())));
        }
        return errors;
    }

    @Override
    public List<Class<?>> getScopes() {
        return Arrays.asList(Insert.class, Update.class);
    }
}
