package com.heystyles.usuarios.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.usuarios.api.dao.CargoDao;
import com.heystyles.usuarios.api.entity.CargoEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.api.service.CargoService;
import com.heystyles.usuarios.core.domain.Cargo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class CargoServiceImpl
        extends ServiceImpl<Cargo, CargoEntity, Long> implements CargoService {

    @Autowired
    private CargoDao cargoDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<CargoEntity, Long> getDao() {
        return cargoDao;
    }

    @Override
    public Cargo getCargo(Long cargoId) {
        return Optional.ofNullable(findById(cargoId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(messageSource.getMessage(
                        MessageKeys.CARGO_NOT_FOUND, new String[]{String.valueOf(cargoId)}, getLocale())));
    }
}
