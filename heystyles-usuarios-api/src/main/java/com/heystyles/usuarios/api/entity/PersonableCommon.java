package com.heystyles.usuarios.api.entity;

import com.heystyles.usuarios.core.domain.TipoDocumento;

import java.time.LocalDateTime;

public interface PersonableCommon {

    String getNombres();

    void setNombres(String nombres);

    String getApellidos();

    void setApellidos(String apellidos);

    String getNombreCompleto();

    void setNombreCompleto(String nombreCompleto);

    String getNumeroDocumento();

    void setNumeroDocumento(String numeroDocumento);

    String getEmail();

    void setEmail(String email);

    String getIdSecurity();

    void setIdSecurity(String idSecurity);

    LocalDateTime getFechaNacimiento();

    void setFechaNacimiento(LocalDateTime fechaNacimiento);

    TipoDocumento getTipoDocumento();

    void setTipoDocumento(TipoDocumento tipoDocumento);

}
