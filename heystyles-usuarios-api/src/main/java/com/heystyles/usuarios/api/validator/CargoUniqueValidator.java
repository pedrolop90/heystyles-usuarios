package com.heystyles.usuarios.api.validator;

import com.heystyles.common.validation.ValidationError;
import com.heystyles.common.validation.Validator;
import com.heystyles.common.validation.groups.Insert;
import com.heystyles.common.validation.groups.Update;
import com.heystyles.usuarios.api.dao.CargoDao;
import com.heystyles.usuarios.api.entity.CargoEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.core.domain.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class CargoUniqueValidator implements Validator<Cargo> {

    @Autowired
    private CargoDao cargoDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public List<ValidationError> validate(Cargo cargo) {
        List<ValidationError> errors = new ArrayList<>();
        CargoEntity cargoNombre = cargoDao.findByNombre(cargo.getNombre());
        if (cargoNombre != null && !Objects.equals(cargoNombre.getNombre(), cargo.getNombre())) {
            errors.add(new ValidationError("Nombre", messageSource.getMessage(MessageKeys.CARGO_NOMBRE_DUPLICATED,
                                                                              new String[]{String.valueOf(cargo.getNombre())}, getLocale())));

        }
        return errors;
    }

    @Override
    public List<Class<?>> getScopes() {
        return Arrays.asList(Insert.class, Update.class);
    }
}
