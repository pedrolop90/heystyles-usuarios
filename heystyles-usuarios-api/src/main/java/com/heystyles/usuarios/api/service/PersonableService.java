package com.heystyles.usuarios.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.common.types.DomainBean;
import domain.EstadoUser;

public interface PersonableService<D extends DomainBean<ID>, ID>
        extends Service<D, ID> {

    void registerUser(String numeroDocumento, String rolId);

    void updateUserRol(String numeroDocumento, String rolLastId, String rolNewId);

    EstadoUser removeRolToUser(String numeroDocumento, String rolId);
}
