package com.heystyles.usuarios.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.usuarios.core.domain.Proveedor;
import com.heystyles.usuarios.core.domain.ProveedorExtended;

public interface ProveedorService extends Service<Proveedor, Long> {

    Long insert(ProveedorExtended request);

    void update(ProveedorExtended request);

    Proveedor getProveedor(Long proveedorId);

    ProveedorExtended getProveedorExtended(Long proveedorId);

    void activarProveedor(Long proveedorId);

}
