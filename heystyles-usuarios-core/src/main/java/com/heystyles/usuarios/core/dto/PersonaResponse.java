package com.heystyles.usuarios.core.dto;

import com.heystyles.common.types.ObjectResponse;
import com.heystyles.usuarios.core.domain.Persona;

public class PersonaResponse extends ObjectResponse<Persona> {

    public PersonaResponse() {
    }

    public PersonaResponse(Persona persona) {
        super(persona);
    }
}
