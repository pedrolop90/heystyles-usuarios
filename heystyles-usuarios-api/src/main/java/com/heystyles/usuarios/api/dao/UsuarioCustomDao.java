package com.heystyles.usuarios.api.dao;

import com.heystyles.usuarios.api.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioCustomDao {

    List<UsuarioEntity> findByExcluyendoCargoGerenteAndUsuarioActivo(String numeroDocumentoUsuarioActivo);

}
