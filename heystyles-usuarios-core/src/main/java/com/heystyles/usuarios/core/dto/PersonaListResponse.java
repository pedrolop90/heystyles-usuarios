package com.heystyles.usuarios.core.dto;

import com.heystyles.common.types.ListResponse;
import com.heystyles.usuarios.core.domain.Persona;

import java.util.List;

public class PersonaListResponse extends ListResponse<Persona> {

    public PersonaListResponse() {
    }

    public PersonaListResponse(List<Persona> personas) {
        super(personas);
    }

    public PersonaListResponse(Long count, List<Persona> personas) {
        super(personas, count);
    }
}
