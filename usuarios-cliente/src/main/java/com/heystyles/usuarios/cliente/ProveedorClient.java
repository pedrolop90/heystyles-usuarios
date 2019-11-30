package com.heystyles.usuarios.cliente;

import com.heystyles.usuarios.core.domain.Proveedor;

public interface ProveedorClient {

    Proveedor findProveedorById(Long proveedorId);

}
