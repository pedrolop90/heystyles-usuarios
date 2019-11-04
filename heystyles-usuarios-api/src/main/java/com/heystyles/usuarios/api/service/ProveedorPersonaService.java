package com.heystyles.usuarios.api.service;

import com.heystyles.usuarios.core.domain.Persona;

import java.util.List;

public interface ProveedorPersonaService {

    void upsert(Long proveedorId, List<Persona> contactos);

    List<Persona> findContactosByProveedor(Long proveedorId);

}
