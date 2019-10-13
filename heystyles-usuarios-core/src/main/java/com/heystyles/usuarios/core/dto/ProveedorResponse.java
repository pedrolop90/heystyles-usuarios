package com.heystyles.usuarios.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.usuarios.core.domain.Proveedor;

public class ProveedorResponse extends ObjectResponse<Proveedor> {

    public ProveedorResponse() {
    }

    public ProveedorResponse(Proveedor proveedor) {
        super(proveedor);
    }
}
