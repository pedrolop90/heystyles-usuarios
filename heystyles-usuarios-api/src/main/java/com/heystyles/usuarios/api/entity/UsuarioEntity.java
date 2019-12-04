package com.heystyles.usuarios.api.entity;

import com.heystyles.common.types.Estado;
import com.heystyles.common.types.SoftDeletable;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

    @Column(name = "estado")
    @Enumerated(value = EnumType.STRING)
    private Estado estado;

    @Column(name = "s_delete", nullable = false)
    private boolean delete;

    public CargoEntity getCargo() {
        return cargo;
    }

    public void setCargo(CargoEntity cargo) {
        this.cargo = cargo;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Override
    public void markAsDeleted() {
        delete = true;
    }
}
