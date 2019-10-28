package com.heystyles.usuarios.api.converter;

import com.heystyles.seguridad.cliente.RolClient;
import com.heystyles.usuarios.api.entity.CargoEntity;
import com.heystyles.usuarios.core.domain.CargoExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CargoEntityToCargoExtendedConverter implements Converter<CargoEntity, CargoExtended> {

    @Autowired
    private RolClient rolClient;

    @Override
    public CargoExtended convert(CargoEntity entity) {
        CargoExtended bean = new CargoExtended();
        bean.setId(entity.getId());
        bean.setNivel(entity.getNivel());
        bean.setNombre(entity.getNombre());
        bean.setIdSecurity(entity.getIdSecurity());
        bean.setPermisos(rolClient.getPermisos(entity.getIdSecurity()));
        return bean;
    }
}
