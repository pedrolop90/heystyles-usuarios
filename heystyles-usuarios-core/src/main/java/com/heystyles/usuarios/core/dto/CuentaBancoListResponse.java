package com.heystyles.usuarios.core.dto;

import com.heystyles.common.types.ListResponse;
import com.heystyles.usuarios.core.domain.CuentaBanco;

import java.util.List;

public class CuentaBancoListResponse extends ListResponse<CuentaBanco> {

    public CuentaBancoListResponse() {
        super();
    }

    public CuentaBancoListResponse(List<CuentaBanco> cuentasBanco) {
        super(cuentasBanco);
    }

}
