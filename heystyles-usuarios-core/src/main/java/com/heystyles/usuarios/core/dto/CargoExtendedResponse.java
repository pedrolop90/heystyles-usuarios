package com.heystyles.usuarios.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.usuarios.core.domain.CargoExtended;

public class CargoExtendedResponse extends ObjectResponse<CargoExtended> {

    public CargoExtendedResponse() {
        super();
    }

    public CargoExtendedResponse(CargoExtended cargoExtended) {
        super(cargoExtended);
    }
}
