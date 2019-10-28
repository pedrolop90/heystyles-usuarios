package com.heystyles.usuarios.api.dao;

import com.heystyles.usuarios.api.entity.CuentaBancoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaBancoDao
        extends JpaRepository<CuentaBancoEntity, Long> {

    List<CuentaBancoEntity> findByProveedorId(Long proveedorId);

}
