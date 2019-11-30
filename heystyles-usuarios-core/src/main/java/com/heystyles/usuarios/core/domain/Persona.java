package com.heystyles.usuarios.core.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.heystyles.common.types.DomainBean;
import com.heystyles.common.util.Constants;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Persona extends DomainBean<Long> {

    private Long idPersona;

    @NotBlank
    private String nombres;

    @NotBlank
    private String apellidos;

    @NotBlank
    private String numeroDocumento;

    @NotBlank
    @Email
    private String email;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.FORMAT_DATE)
    private LocalDate fechaNacimiento;

    @NotNull
    private TipoDocumento tipoDocumento;

    private String telefono;

    private Long fotografiaId;

    @Override
    public Long getId() {
        return idPersona;
    }

    @Override
    public void setId(Long id) {
        this.idPersona = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Long getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Long idPersona) {
        this.idPersona = idPersona;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Long getFotografiaId() {
        return fotografiaId;
    }

    public void setFotografiaId(Long fotografiaId) {
        this.fotografiaId = fotografiaId;
    }
}
