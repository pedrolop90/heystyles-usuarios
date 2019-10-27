package com.heystyles.usuarios.api.entity;

import com.heystyles.common.persistence.LocalDateTimeAttributeConverter;
import com.heystyles.common.types.AuditableEntity;
import com.heystyles.usuarios.core.domain.TipoDocumento;
import org.hibernate.annotations.Where;
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
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@Where(clause = "S_DELETE = 0")
public abstract class PersonableEntity extends AuditableEntity<Long> implements Personable {

    public interface Attributes extends AuditableEntity.Attributes {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ID_PERSONA", nullable = false)
    private PersonaEntity persona = new PersonaEntity();

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

    public PersonaEntity getPersona() {
        return persona;
    }

    public void setPersona(PersonaEntity persona) {
        this.persona = persona;
    }

    @Override
    public String getNombres() {
        return persona.getNombres();
    }

    @Override
    public void setNombres(String nombres) {
        persona.setNombres(nombres);
    }

    @Override
    public String getApellidos() {
        return persona.getApellidos();
    }

    @Override
    public void setApellidos(String apellidos) {
        persona.setApellidos(apellidos);
    }

    @Override
    public String getNombreCompleto() {
        return persona.getNombreCompleto();
    }

    @Override
    public void setNombreCompleto(String nombreCompleto) {
        persona.setNombreCompleto(nombreCompleto);
    }

    @Override
    public String getNumeroDocumento() {
        return persona.getNumeroDocumento();
    }

    @Override
    public void setNumeroDocumento(String numeroDocumento) {
        persona.setNumeroDocumento(numeroDocumento);
    }

    @Override
    public String getEmail() {
        return persona.getEmail();
    }

    @Override
    public void setEmail(String email) {
        persona.setEmail(email);
    }

    @Override
    public String getIdSecurity() {
        return persona.getIdSecurity();
    }

    @Override
    public void setIdSecurity(String idSecurity) {
        persona.setIdSecurity(idSecurity);
    }

    @Override
    public LocalDate getFechaNacimiento() {
        return persona.getFechaNacimiento();
    }

    @Override
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        persona.setFechaNacimiento(fechaNacimiento);
    }

    @Override
    public TipoDocumento getTipoDocumento() {
        return persona.getTipoDocumento();
    }

    @Override
    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        persona.setTipoDocumento(tipoDocumento);
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

    @Override
    public String getTelefono() {
        return persona.getTelefono();
    }

    @Override
    public void setTelefono(String telefono) {
        persona.setTelefono(telefono);
    }
}
