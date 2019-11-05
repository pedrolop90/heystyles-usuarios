package com.heystyles.usuarios.api.entity;

import com.heystyles.common.persistence.LocalDateTimeAttributeConverter;
import com.heystyles.common.types.AuditableWithAuthorEntity;
import com.heystyles.common.types.SoftDeletable;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "proveedor")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "s_delete = 0")
public class ProveedorEntity extends AuditableWithAuthorEntity<Long> implements SoftDeletable {

    public interface Attributes extends AuditableWithAuthorEntity.Attributes {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "s_delete", nullable = false)
    private boolean delete;

    @Column(name = "fecha_limite_pago")
    private Long fechaLimitePago;

    @NotNull
    @CreatedDate
    @Column(name = "created_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime createdDate;

    @NotNull
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

    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    private List<ProveedorPersonaEntity> contactos;

    @OneToMany(mappedBy = "proveedor", fetch = FetchType.LAZY)
    private List<CuentaBancoEntity> cuentasBanco;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public Long getFechaLimitePago() {
        return fechaLimitePago;
    }

    public void setFechaLimitePago(Long fechaLimitePago) {
        this.fechaLimitePago = fechaLimitePago;
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
    public String getCreatedBy() {
        return createdBy;
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
    public String getUpdatedBy() {
        return updatedBy;
    }

    @Override
    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Override
    public void markAsDeleted() {
        delete = true;
    }

    public List<ProveedorPersonaEntity> getContactos() {
        return contactos;
    }

    public void setContactos(List<ProveedorPersonaEntity> contactos) {
        this.contactos = contactos;
    }

    public List<CuentaBancoEntity> getCuentasBanco() {
        return cuentasBanco;
    }

    public void setCuentasBanco(List<CuentaBancoEntity> cuentasBanco) {
        this.cuentasBanco = cuentasBanco;
    }
}
