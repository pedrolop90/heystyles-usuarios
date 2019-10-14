package com.heystyles.usuarios.api.dao;

import com.heystyles.usuarios.api.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDao extends JpaRepository<UsuarioEntity, Long> {
}
