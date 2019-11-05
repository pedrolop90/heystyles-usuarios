package com.heystyles.usuarios.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.usuarios.core.domain.ProveedorPersona;

public class ProveedorPersonaResponse extends ObjectResponse<ProveedorPersona> {

    public ProveedorPersonaResponse() {
        super();
    }

    public ProveedorPersonaResponse(ProveedorPersona proveedorPersona) {
        super(proveedorPersona);
    }

}
