package com.heystyles.usuarios.api.dao;

import com.heystyles.usuarios.api.entity.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorDao extends JpaRepository<ProveedorEntity, Long> {
}
