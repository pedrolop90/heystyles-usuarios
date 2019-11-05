package com.heystyles.usuarios.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.usuarios.core.domain.CuentaBanco;

public class CuentaBancoResponse extends ObjectResponse<CuentaBanco> {

    public CuentaBancoResponse() {
        super();
    }

    public CuentaBancoResponse(CuentaBanco cuentaBanco) {
        super(cuentaBanco);
    }

}
