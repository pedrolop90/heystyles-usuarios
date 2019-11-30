package com.heystyles.usuarios.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heystyles.common.types.DomainBean;
import org.hibernate.validator.constraints.NotBlank;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Cargo extends DomainBean<Long> {

    private Long id;

    @NotBlank
    private String nombre;
    private Long nivel = 1L;

    private Long idSecurity;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getNivel() {
        return nivel;
    }

    public void setNivel(Long nivel) {
        this.nivel = nivel;
    }

    public Long getIdSecurity() {
        return idSecurity;
    }

    public void setIdSecurity(Long idSecurity) {
        this.idSecurity = idSecurity;
    }
}
