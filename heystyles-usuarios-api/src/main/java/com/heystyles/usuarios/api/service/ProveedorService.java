package com.heystyles.usuarios.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.usuarios.core.domain.Proveedor;

public interface ProveedorService extends Service<Proveedor, Long> {

    Proveedor getProveedor(Long proveedorId);

}
