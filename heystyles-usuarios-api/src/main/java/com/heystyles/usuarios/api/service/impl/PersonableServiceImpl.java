package com.heystyles.usuarios.api.service.impl;

import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.common.types.DomainBean;
import com.heystyles.common.types.Entity;
import com.heystyles.seguridad.cliente.UserCliente;
import com.heystyles.usuarios.api.dao.PersonaDao;
import com.heystyles.usuarios.api.entity.PersonaEntity;
import com.heystyles.usuarios.api.service.PersonableService;
import domain.UserAuth0;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.UUID;

public abstract class PersonableServiceImpl<D extends DomainBean<ID>, T extends Entity<ID>, ID extends Serializable>
        extends ServiceImpl<D, T, ID> implements PersonableService<D, ID> {

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private UserCliente userCliente;

    @Override
    public void registerUser(String numeroDocumento, String rolId) {
        PersonaEntity personaEntity = personaDao.findByNumeroDocumento(numeroDocumento);

        if (personaEntity.getIdSecurity() == null) {
            String password = UUID.randomUUID().toString().substring(0, 8);
            UserAuth0 userAuth0 = new UserAuth0();
            userAuth0.setUserName(personaEntity.getNumeroDocumento());
            userAuth0.setNombre(personaEntity.getNombres());
            userAuth0.setApellido(personaEntity.getApellidos());
            userAuth0.setEmail(personaEntity.getEmail());
            userAuth0.setPassword(password);
            userAuth0.setRoleIdSecurity(rolId);
            String idSecurity = userCliente.createUser(userAuth0);
            personaEntity.setIdSecurity(idSecurity);
            personaDao.save(personaEntity);
        }
        else {
            /**
             * esta parta esta dispuesta para la implementacion, si una misma persona
             * tiene diferentes roles.
             */
        }
    }
}
