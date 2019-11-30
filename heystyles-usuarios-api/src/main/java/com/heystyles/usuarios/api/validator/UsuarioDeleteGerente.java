package com.heystyles.usuarios.api.validator;

import com.heystyles.common.validation.ValidationError;
import com.heystyles.common.validation.Validator;
import com.heystyles.usuarios.api.dao.CargoDao;
import com.heystyles.usuarios.api.dao.UsuarioDao;
import com.heystyles.usuarios.api.entity.CargoEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.core.domain.Usuario;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class UsuarioDeleteGerente implements Validator<Usuario> {

    @Autowired
    private CargoDao cargoDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<ValidationError> validate(Usuario usuario) {
        List<ValidationError> errors = new ArrayList<>();
        CargoEntity cargoEntity = cargoDao.findOne(usuario.getCargoId());
        if (cargoEntity != null && cargoEntity.getNivel() == 0) {
            errors.add(new ValidationError(
                    "cargo",
                    messageSource.getMessage(MessageKeys.USUARIO_GERENTE_NOT_DELETE,
                            null, getLocale())
            ));
        }
        return errors;
    }

    @Override
    public List<Class<?>> getScopes() {
        return Arrays.asList(Delete.class);
    }
}
