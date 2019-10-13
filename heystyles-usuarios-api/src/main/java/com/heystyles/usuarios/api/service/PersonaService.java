package com.heystyles.usuarios.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.usuarios.core.domain.Persona;

public interface PersonaService extends Service<Persona, Long> {

    Persona getPersona(Long personaId);

}
