package com.heystyles.usuarios.api.service;

import com.heystyles.common.service.Service;
import com.heystyles.common.types.DomainBean;

public interface PersonableService<D extends DomainBean<ID>, ID>
        extends Service<D, ID> {

    void registerUser(String numeroDocumento, String rolId);

}
