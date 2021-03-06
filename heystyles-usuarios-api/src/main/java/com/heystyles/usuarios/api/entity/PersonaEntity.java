package com.heystyles.usuarios.api.entity;

import com.heystyles.common.persistence.LocalDateAttributeConverter;
import com.heystyles.common.persistence.LocalDateTimeAttributeConverter;
import com.heystyles.common.types.AuditableWithAuthorEntity;
import com.heystyles.usuarios.core.domain.TipoDocumento;
import org.hibernate.annotations.Formula;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "persona")
public class PersonaEntity extends AuditableWithAuthorEntity<Long> implements Personable {

    public interface Attributes extends AuditableWithAuthorEntity.Attributes {
        String PERSONA = "persona";
        String TIPO_IDENTIFICACION = "tipoIdentificacion";
        String NUMERO_DOCUMENTO = "numeroDocumento";
        String NOMBRES = "nombres";
        String APELLIDOS = "apellidos";
        String NOMBRE_COMPLETO = "nombreCompleto";
        String EMAIL = "email";
        String GENERO = "genero";
        String DIRECCION = "direccion";
        String TELEFONO = "telefono";
        String CELULAR = "celular";
        String FECHA_NACIMIENTO = "fechaNacimiento";
        String NACIONALIDAD = "nacionalidad";
        String ID_SECURITY = "idSecurity";
        String FOTOGRAFIA_ID = "fotografiaId";
        String UBICACION_EXPEDICION = "ubicacionExpedicion";
        String UBICACION_EXPEDICION_ID = "ubicacionExpedicionId";
        String FECHA_EXPEDICION = "fechaExpedicion";
        String BARRIO_RESIDENCIA = "barrioResidencia";
        String REGIMEN_SALUD = "regimenSalud";
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Formula(value = "CONCAT(nombres, ' ', apellidos)")
    private String nombreCompleto;

    @Column(name = "numero_documento", nullable = false)
    private String numeroDocumento;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "id_security")
    private Long idSecurity;

    @Column(name = "fecha_nacimiento", nullable = false)
    @Convert(converter = LocalDateAttributeConverter.class)
    private LocalDate fechaNacimiento;

    @Column(name = "tipo_documento", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TipoDocumento tipoDocumento;

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

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "fotografia_id")
    private Long fotografiaId;

    public PersonaEntity() {
    }

    public PersonaEntity(Long id) {
        setId(id);
    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long aLong) {
        this.id = id;
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
    public Long getIdPersona() {
        return this.id;
    }

    @Override
    public void setIdPersona(Long idPersona) {
        this.id = idPersona;
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

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
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

    @Override
    public Long getIdSecurity() {
        return idSecurity;
    }

    @Override
    public void setIdSecurity(Long idSecurity) {
        this.idSecurity = idSecurity;
    }

    @Override
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public Long getFotografiaId() {
        return fotografiaId;
    }

    @Override
    public void setFotografiaId(Long fotografiaId) {
        this.fotografiaId = fotografiaId;
    }
}
