package com.heystyles.usuarios.api.entity;

import com.heystyles.usuarios.core.domain.TipoDocumento;

import java.time.LocalDate;

public interface Personable {

    Long getIdPersona();

    void setIdPersona(Long id);

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

    Long getIdSecurity();

    void setIdSecurity(Long idSecurity);

    LocalDate getFechaNacimiento();

    void setFechaNacimiento(LocalDate fechaNacimiento);

    TipoDocumento getTipoDocumento();

    void setTipoDocumento(TipoDocumento tipoDocumento);

    String getTelefono();

    void setTelefono(String telefono);

    Long getFotografiaId();

    void setFotografiaId(Long fotografiaId);
}
