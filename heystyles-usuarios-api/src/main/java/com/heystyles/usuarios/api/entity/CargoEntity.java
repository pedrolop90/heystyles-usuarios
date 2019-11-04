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
@Table(name = "cargo")
@Where(clause = "s_delete = 0")
public class CargoEntity extends AuditableEntity<Long> implements SoftDeletable {

    public interface Attributes extends AuditableEntity.Attributes {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "nivel", nullable = false)
    private Long nivel;

    @Column(name = "id_security", nullable = false)
    private Long idSecurity;

    @Column(name = "s_delete", nullable = false)
    private boolean delete;

    @Column(name = "fecha_limite_pago")
    private Long fechaLimitePago;

    @CreatedDate
    @Column(name = "created_date")
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date")
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
