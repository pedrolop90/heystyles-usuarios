package com.heystyles.usuarios.api.converter;

import com.heystyles.usuarios.api.entity.CargoEntity;
import com.heystyles.usuarios.core.domain.Cargo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CargoEntityToCargoConverter implements Converter<CargoEntity, Cargo> {
    @Override
    public Cargo convert(CargoEntity entity) {
        Cargo cargo = new Cargo();
        cargo.setNombre(entity.getNombre());
        cargo.setNivel(entity.getNivel());
        cargo.setIdSecurity(entity.getIdSecurity());
        return cargo;
    }
}
