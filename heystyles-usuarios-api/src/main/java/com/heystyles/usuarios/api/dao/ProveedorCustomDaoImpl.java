package com.heystyles.usuarios.api.dao;

import com.heystyles.common.types.Page;
import com.heystyles.usuarios.api.entity.ProveedorEntity;
import com.heystyles.usuarios.core.filter.ProveedorFilter;

public interface ProveedorCustomDaoImpl {

    Page<ProveedorEntity> getPage(ProveedorFilter filter);

}
