package com.heystyles.usuarios.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.usuarios.core.domain.CuentaBanco;

import java.util.List;

public interface CuentaBancoService extends Service<CuentaBanco, Long> {

    void uppsert(Long proveedorId, List<CuentaBanco> cuentasBanco);

    List<CuentaBanco> findByProveedoId(Long proveedorId);

}
