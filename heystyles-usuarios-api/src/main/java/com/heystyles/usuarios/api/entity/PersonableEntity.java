package com.heystyles.usuarios.api.entity;

import com.heystyles.common.persistence.LocalDateTimeAttributeConverter;
import com.heystyles.common.types.AuditableEntity;
import com.heystyles.usuarios.core.domain.TipoDocumento;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PersonableEntity extends AuditableEntity<Long> implements PersonableCommon {

    public interface Attributes extends AuditableEntity.Attributes {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_PERSONA", nullable = false)
    private PersonaEntity personaEntity = new PersonaEntity();

    @CreatedDate
    @NotNull
    @Column(name = "CREATED_DATE")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "UPDATED_DATE")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime updatedDate;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public PersonaEntity getPersonaEntity() {
        return personaEntity;
    }

    public void setPersonaEntity(PersonaEntity personaEntity) {
        this.personaEntity = personaEntity;
    }

    @Override
    public String getNombres() {
        return personaEntity.getNombres();
    }

    @Override
    public void setNombres(String nombres) {
        personaEntity.setNombres(nombres);
    }

    @Override
    public String getApellidos() {
        return personaEntity.getApellidos();
    }

    @Override
    public void setApellidos(String apellidos) {
        personaEntity.setApellidos(apellidos);
    }

    @Override
    public String getNombreCompleto() {
        return personaEntity.getNombreCompleto();
    }

    @Override
    public void setNombreCompleto(String nombreCompleto) {
        personaEntity.setNombreCompleto(nombreCompleto);
    }

    @Override
    public String getNumeroDocumento() {
        return personaEntity.getNumeroDocumento();
    }

    @Override
    public void setNumeroDocumento(String numeroDocumento) {
        personaEntity.setNumeroDocumento(numeroDocumento);
    }

    @Override
    public String getEmail() {
        return personaEntity.getEmail();
    }

    @Override
    public void setEmail(String email) {
        personaEntity.setEmail(email);
    }

    @Override
    public String getIdSecurity() {
        return personaEntity.getIdSecurity();
    }

    @Override
    public void setIdSecurity(String idSecurity) {
        personaEntity.setIdSecurity(idSecurity);
    }

    @Override
    public LocalDateTime getFechaNacimiento() {
        return personaEntity.getFechaNacimiento();
    }

    @Override
    public void setFechaNacimiento(LocalDateTime fechaNacimiento) {
        personaEntity.setFechaNacimiento(fechaNacimiento);
    }

    @Override
    public TipoDocumento getTipoDocumento() {
        return personaEntity.getTipoDocumento();
    }

    @Override
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        personaEntity.setTipoDocumento(tipoDocumento);
    }

    @Override
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    @Override
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }
}
