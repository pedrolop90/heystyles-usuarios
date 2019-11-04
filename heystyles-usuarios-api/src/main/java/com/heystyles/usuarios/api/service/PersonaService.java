package com.heystyles.usuarios.api.service;

import com.heystyles.usuarios.core.domain.Persona;

public interface PersonaService extends PersonableService<Persona, Long> {

    Persona getPersona(Long personaId);

    Persona getPersonaByNumeroDocumento(String numeroDocumento);

}
