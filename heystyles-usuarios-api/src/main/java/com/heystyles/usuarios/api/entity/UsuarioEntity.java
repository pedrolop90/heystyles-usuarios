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
@Table(name = "usuario")
@Where(clause = "s_delete = 0")
public class UsuarioEntity extends PersonableEntity implements SoftDeletable {

    public interface Attributes extends PersonableEntity.Attributes {
        String CARGO = "cargo";
        String CARGO_ID = CARGO + "." + CargoEntity.Attributes.ID;
        String CARGO_NIVEL = CARGO + "." + CargoEntity.Attributes.NIVEL;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_cargo", nullable = false)
    private CargoEntity cargo;

    @Column(name = "s_delete", nullable = false)
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
