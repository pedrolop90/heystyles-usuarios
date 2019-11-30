package com.heystyles.usuarios.api.dao;

import com.heystyles.usuarios.api.entity.CargoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoDao extends JpaRepository<CargoEntity, Long> {

    CargoEntity findByNombre(String nombre);

    List<CargoEntity> findByNivelGreaterThanEqual(Long nivel);

}
