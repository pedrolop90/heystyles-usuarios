package com.heystyles.usuarios.cliente;

import com.heystyles.usuarios.core.domain.ProveedorExtended;

public interface ProveedorClient {

    ProveedorExtended findProveedorById(Long proveedorId);

}
