package com.heystyles.usuarios.api.entity;

import com.heystyles.common.persistence.LocalDateTimeAttributeConverter;
import com.heystyles.common.types.SoftDeletable;
import com.heystyles.usuarios.core.domain.TipoCuentaBanco;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "cuenta_banco")
@Where(clause = "s_delete = 0")
public class CuentaBancoEntity extends com.heystyles.common.types.AuditableEntity<Long> implements SoftDeletable {

    public interface Attributes extends com.heystyles.common.types.Entity.Attributes {
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
