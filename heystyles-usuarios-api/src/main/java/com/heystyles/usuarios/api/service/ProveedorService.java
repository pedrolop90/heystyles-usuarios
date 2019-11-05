package com.heystyles.usuarios.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.usuarios.core.domain.Proveedor;
import com.heystyles.usuarios.core.domain.ProveedorExtended;

public interface ProveedorService extends Service<Proveedor, Long> {

    Long insert(ProveedorExtended request);

    ProveedorExtended getProveedor(Long proveedorId);

}
