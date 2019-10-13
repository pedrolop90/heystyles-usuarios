package com.heystyles.usuarios.api.dao;

import com.heystyles.usuarios.api.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaDao extends JpaRepository<PersonaEntity, Long> {
}
