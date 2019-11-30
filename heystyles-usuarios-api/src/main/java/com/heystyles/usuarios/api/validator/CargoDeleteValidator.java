package com.heystyles.usuarios.api.validator;

import com.heystyles.common.validation.ValidationError;
import com.heystyles.common.validation.Validator;
import com.heystyles.common.validation.groups.Delete;
import com.heystyles.usuarios.api.dao.UsuarioDao;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.core.domain.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class CargoDeleteValidator implements Validator<Cargo> {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<ValidationError> validate(Cargo cargo) {
        List<ValidationError> errors = new ArrayList<>();
        Long cantidadUsuarios = usuarioDao.countByCargoId(cargo.getId());
        if (cantidadUsuarios > 0) {
            errors.add(new ValidationError("Cargo", messageSource.getMessage(
                    MessageKeys.CARGO_USUARIO_REFERENCIADOS_NOT_DELETE,
                    new String[]{String.valueOf(cantidadUsuarios), String.valueOf(cargo.getId())}, getLocale())));
        }
        return errors;
    }

    @Override
    public List<Class<?>> getScopes() {
        return Arrays.asList(Delete.class);
    }
}
