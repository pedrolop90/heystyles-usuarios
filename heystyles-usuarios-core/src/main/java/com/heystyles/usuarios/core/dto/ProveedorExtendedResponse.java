package com.heystyles.usuarios.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.usuarios.core.domain.ProveedorExtended;

public class ProveedorExtendedResponse extends ObjectResponse<ProveedorExtended> {

    public ProveedorExtendedResponse() {
        super();
    }

    public ProveedorExtendedResponse(ProveedorExtended proveedorExtended) {
        super(proveedorExtended);
    }

}
