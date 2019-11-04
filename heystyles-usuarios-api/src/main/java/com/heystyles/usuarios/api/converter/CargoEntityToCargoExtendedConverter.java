package com.heystyles.usuarios.api.converter;

import com.heystyles.common.service.ConverterService;
import com.heystyles.seguridad.cliente.RolClient;
import com.heystyles.usuarios.api.entity.CargoEntity;
import com.heystyles.usuarios.core.domain.Cargo;
import com.heystyles.usuarios.core.dto.CargoExtended;
import domain.PermisoAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CargoEntityToCargoExtendedConverter implements Converter<CargoEntity, CargoExtended> {

    @Autowired
    private RolClient rolClient;

    @Autowired
    private ConverterService converterService;

    @Override
    public CargoExtended convert(CargoEntity entity) {
        CargoExtended bean = new CargoExtended();
        bean.setCargo(converterService.convertTo(entity, Cargo.class));
        bean.setPermisos(
                rolClient.getPermisos(entity.getIdSecurity())
                        .stream()
                        .map(PermisoAuth0::getId)
                        .collect(Collectors.toList())
        );
        return bean;
    }
}
