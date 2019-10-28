package com.heystyles.usuarios.api.converter;

import com.heystyles.usuarios.api.entity.Personable;
import com.heystyles.usuarios.core.domain.Persona;

public class PersonableEntityToPersonaConverter<T extends Personable, D extends Persona> {

    public void convertPersona(T entity, D bean) {
        bean.setNombres(entity.getNombres());
        bean.setApellidos(entity.getApellidos());
        bean.setNumeroDocumento(entity.getNumeroDocumento());
        bean.setFechaNacimiento(entity.getFechaNacimiento());
        bean.setEmail(entity.getEmail());
        bean.setTipoDocumento(entity.getTipoDocumento());
        bean.setTelefono(entity.getTelefono());
    }

}
