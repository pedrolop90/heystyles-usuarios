package com.heystyles.usuarios.core.dto;

import com.heystyles.common.types.ListResponse;
import com.heystyles.usuarios.core.domain.Cargo;

import java.util.List;

public class CargoListResponse extends ListResponse<Cargo> {

    public CargoListResponse() {
    }

    public CargoListResponse(List<Cargo> cargos) {
        super(cargos);
    }

    public CargoListResponse(Long count, List<Cargo> cargos) {
        super(cargos, count);
    }
}
