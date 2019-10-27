package com.heystyles.usuarios.api.entity;

import com.heystyles.common.types.SoftDeletable;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
@Where(clause = "S_DELETE = 0")
public class UsuarioEntity extends PersonableEntity implements SoftDeletable {

    public interface Attributes extends PersonableEntity.Attributes {
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ID_CARGO", nullable = false)
    private CargoEntity cargo;

    @Column(name = "S_DELETE", nullable = false)
    private boolean delete;

    public CargoEntity getCargo() {
        return cargo;
    }

    public void setCargo(CargoEntity cargo) {
        this.cargo = cargo;
    }

    @Override
    public void markAsDeleted() {
        delete = true;
    }
}
