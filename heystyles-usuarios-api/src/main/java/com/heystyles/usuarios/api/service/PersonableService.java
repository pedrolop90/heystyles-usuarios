package com.heystyles.usuarios.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.common.types.DomainBean;
import domain.EstadoUser;

public interface PersonableService<D extends DomainBean<ID>, ID>
        extends Service<D, ID> {

    void registerUser(String numeroDocumento, Long rolId);

    void updateUserRol(String numeroDocumento, Long rolLastId, Long rolNewId);

    EstadoUser removeRolToUser(String numeroDocumento, Long rolId);
}
