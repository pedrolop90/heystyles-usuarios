package com.heystyles.usuarios.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.usuarios.api.dao.CargoDao;
import com.heystyles.usuarios.api.entity.CargoEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.core.domain.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class CargoToCargoEntityConverter implements Converter<Cargo, CargoEntity> {

    @Autowired
    private CargoDao cargoDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public CargoEntity convert(Cargo bean) {
        CargoEntity entity = null;
        if (bean.getId() == null) {
            entity = new CargoEntity();
        }
        else {
            entity = Optional.ofNullable(cargoDao.findOne(bean.getId())).orElseThrow(() ->
                    APIExceptions.objetoNoEncontrado(messageSource.getMessage(MessageKeys.CARGO_NOT_FOUND,
                            new String[]{String.valueOf(bean.getId())}, getLocale())));
        }
        entity.setNombre(bean.getNombre());
        entity.setNivel(1L);
        entity.setIdSecurity(bean.getIdSecurity());
        return entity;
    }
}
