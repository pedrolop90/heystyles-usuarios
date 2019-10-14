package com.heystyles.usuarios.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.usuarios.core.domain.Cargo;

public class CargoResponse extends ObjectResponse<Cargo> {

    public CargoResponse() {
    }

    public CargoResponse(Cargo cargo) {
        super(cargo);
    }
}
