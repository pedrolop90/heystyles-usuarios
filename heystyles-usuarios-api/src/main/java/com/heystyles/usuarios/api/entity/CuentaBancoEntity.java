package com.heystyles.usuarios.api.entity;

import com.heystyles.common.persistence.LocalDateTimeAttributeConverter;
import com.heystyles.common.types.AuditableWithAuthorEntity;
import com.heystyles.common.types.SoftDeletable;
import com.heystyles.usuarios.core.domain.TipoCuentaBanco;
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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "cuenta_banco")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "s_delete = 0")
public class CuentaBancoEntity extends AuditableWithAuthorEntity<Long> implements SoftDeletable {

    public interface Attributes extends AuditableWithAuthorEntity.Attributes {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_proveedor", nullable = false)
    private ProveedorEntity proveedor;

    @Column(name = "nombre_banco", nullable = false)
    private String nombreBanco;

    @Column(name = "tipo_cuenta", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private TipoCuentaBanco tipoCuenta;

    @Column(name = "numero_cuenta", nullable = false)
    private String numeroCuenta;


    @Column(name = "s_delete", nullable = false)
    private boolean delete;

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

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public ProveedorEntity getProveedor() {
        return proveedor;
    }

    public void setProveedor(ProveedorEntity proveedor) {
        this.proveedor = proveedor;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public TipoCuentaBanco getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuentaBanco tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
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
