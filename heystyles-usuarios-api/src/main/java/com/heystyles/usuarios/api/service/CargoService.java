package com.heystyles.usuarios.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.usuarios.core.domain.Cargo;
import com.heystyles.usuarios.core.domain.CargoExtended;
import com.heystyles.usuarios.core.dto.CargoRequest;

public interface CargoService extends Service<Cargo, Long> {

    Long insert(CargoRequest request);

    void update(Long idCargo, CargoRequest request);

    CargoExtended getCargo(Long cargoId);
}
