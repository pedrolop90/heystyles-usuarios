package com.heystyles.usuarios.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.usuarios.core.domain.Cargo;

public interface CargoService extends Service<Cargo, Long> {

    Cargo getCargo(Long cargoId);
}
