package com.heystyles.usuarios.api.dao;

import com.heystyles.usuarios.api.entity.ProveedorPersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProveedorPersonaDao
        extends JpaRepository<ProveedorPersonaEntity, Long> {

    List<ProveedorPersonaEntity> findByProveedorId(Long proveedorId);
}
