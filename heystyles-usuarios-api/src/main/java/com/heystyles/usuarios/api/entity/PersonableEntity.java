package com.heystyles.usuarios.api.entity;

import com.heystyles.common.persistence.LocalDateTimeAttributeConverter;
import com.heystyles.common.types.AuditableWithAuthorEntity;
import com.heystyles.usuarios.core.domain.TipoDocumento;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "s_delete = 0")
public abstract class PersonableEntity extends AuditableWithAuthorEntity<Long> implements Personable {

    public interface Attributes extends AuditableWithAuthorEntity.Attributes {
        String PERSONA = "persona";
        String PERSONA_ID = PERSONA + "." + PersonaEntity.Attributes.ID;
        String TIPO_IDENTIFICACION = PERSONA + "." + PersonaEntity.Attributes.TIPO_IDENTIFICACION;
        String NUMERO_DOCUMENTO = PERSONA + "." + PersonaEntity.Attributes.NUMERO_DOCUMENTO;
        String NOMBRES = PERSONA + "." + PersonaEntity.Attributes.NOMBRES;
        String APELLIDOS = PERSONA + "." + PersonaEntity.Attributes.APELLIDOS;
        String NOMBRE_COMPLETO = PERSONA + "." + PersonaEntity.Attributes.NOMBRE_COMPLETO;
        String EMAIL = PERSONA + "." + PersonaEntity.Attributes.EMAIL;
        String GENERO = PERSONA + "." + PersonaEntity.Attributes.GENERO;
        String DIRECCION = PERSONA + "." + PersonaEntity.Attributes.DIRECCION;
        String TELEFONO = PERSONA + "." + PersonaEntity.Attributes.TELEFONO;
        String CELULAR = PERSONA + "." + PersonaEntity.Attributes.CELULAR;
        String FECHA_NACIMIENTO = PERSONA + "." + PersonaEntity.Attributes.FECHA_NACIMIENTO;
        String NACIONALIDAD = PERSONA + "." + PersonaEntity.Attributes.NACIONALIDAD;
        String ID_SECURITY = PERSONA + "." + PersonaEntity.Attributes.ID_SECURITY;
        String FOTOGRAFIA = PERSONA + "." + PersonaEntity.Attributes.FOTOGRAFIA_ID;
        String UBICACION_EXPEDICION = PERSONA + "." + PersonaEntity.Attributes.UBICACION_EXPEDICION;
        String FECHA_EXPEDICION = PERSONA + "." + PersonaEntity.Attributes.FECHA_EXPEDICION;
        String BARRIO_RESIDENCIA = PERSONA + "." + PersonaEntity.Attributes.BARRIO_RESIDENCIA;
        String REGIMEN_SALUD = PERSONA + "." + PersonaEntity.Attributes.REGIMEN_SALUD;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, optional = false,
            cascade = {
                    CascadeType.MERGE,
                    CascadeType.REFRESH,
                    CascadeType.DETACH,
                    CascadeType.PERSIST
            })
    @JoinColumn(name = "id_persona")
    private PersonaEntity persona = new PersonaEntity();

    @CreatedDate
    @Column(name = "created_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime createdDate;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @LastModifiedDate
    @Column(name = "updated_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime updatedDate;

    @LastModifiedBy
    @Column(name = "updated_by")
    private String updatedBy;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long getIdPersona() {
        return persona.getId();
    }

    @Override
    public void setIdPersona(Long idPersona) {
        persona.setId(idPersona);
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
    public Long getIdSecurity() {
        return persona.getIdSecurity();
    }

    @Override
    public void setIdSecurity(Long idSecurity) {
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
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public String getTelefono() {
        return persona.getTelefono();
    }

    @Override
    public void setTelefono(String telefono) {
        persona.setTelefono(telefono);
    }

    @Override
    public Long getFotografiaId() {
        return persona.getFotografiaId();
    }

    @Override
    public void setFotografiaId(Long fotografiaId) {
        persona.setFotografiaId(fotografiaId);
    }
}
