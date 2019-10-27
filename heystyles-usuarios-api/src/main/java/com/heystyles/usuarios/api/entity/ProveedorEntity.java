package com.heystyles.usuarios.api.entity;

import com.heystyles.common.persistence.LocalDateTimeAttributeConverter;
import com.heystyles.common.types.AuditableEntity;
import com.heystyles.common.types.SoftDeletable;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "PROVEEDOR")
@Where(clause = "S_DELETE = 0")
public class ProveedorEntity extends AuditableEntity<Long> implements SoftDeletable {

    public interface Attributes extends AuditableEntity.Attributes {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "NOMBRE", nullable = false)
    private String nombre;

    @Column(name = "DESCRIPCION", nullable = false)
    private String descripcion;

    @Column(name = "TELEFONO", nullable = false)
    private String telefono;

    @Column(name = "DIRECCION")
    private String direccion;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "S_DELETE", nullable = false)
    private boolean delete;

    @Column(name = "FECHA_LIMITE_PAGO")
    private Long fechaLimitePago;

    @CreatedDate
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
    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public void markAsDeleted() {
        delete = true;
    }
}
