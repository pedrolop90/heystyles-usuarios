package com.heystyles.usuarios.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.usuarios.core.domain.Cargo;
import com.heystyles.usuarios.core.dto.CargoExtended;

import java.util.List;

public interface CargoService extends Service<Cargo, Long> {

    Long insert(CargoExtended request);

    void update(CargoExtended request);

    CargoExtended getCargoExtended(Long cargoId);

    Cargo getCargo(Long cargoId);

    List<Cargo> getCargos();
}
