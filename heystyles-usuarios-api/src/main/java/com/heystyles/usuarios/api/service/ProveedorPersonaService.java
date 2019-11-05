package com.heystyles.usuarios.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.usuarios.core.domain.Persona;
import com.heystyles.usuarios.core.domain.ProveedorPersona;

import java.util.List;

public interface ProveedorPersonaService extends Service<ProveedorPersona, Long> {

    void upsert(Long proveedorId, List<Persona> contactos);

    List<Persona> findContactosByProveedor(Long proveedorId);

    ProveedorPersona getProveedorPersona(Long proveedorPersonaId);

}
