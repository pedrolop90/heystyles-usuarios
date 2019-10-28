package com.heystyles.usuarios.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.usuarios.core.domain.Proveedor;
import com.heystyles.usuarios.core.domain.ProveedorExtended;
import com.heystyles.usuarios.core.dto.ProveedorRequest;

public interface ProveedorService extends Service<Proveedor, Long> {

    Long insert(ProveedorRequest request);

    void update(Long proveedorId, ProveedorRequest request);

    ProveedorExtended getProveedor(Long proveedorId);

}
