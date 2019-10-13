package com.heystyles.usuarios.core.dto;

import com.heystyles.common.types.ListResponse;
import com.heystyles.usuarios.core.domain.Proveedor;

import java.util.List;

public class ProveedorListResponse extends ListResponse<Proveedor> {

    public ProveedorListResponse() {
    }

    public ProveedorListResponse(List<Proveedor> proveedores) {
        super(proveedores);
    }

    public ProveedorListResponse(Long count, List<Proveedor> proveedores) {
        super(proveedores, count);
    }
}
