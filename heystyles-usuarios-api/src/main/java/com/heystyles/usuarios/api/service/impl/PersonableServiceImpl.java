package com.heystyles.usuarios.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.common.types.DomainBean;
import com.heystyles.common.types.Entity;
import com.heystyles.seguridad.cliente.UserCliente;
import com.heystyles.usuarios.api.dao.PersonaDao;
import com.heystyles.usuarios.api.entity.PersonaEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.api.service.PersonableService;
import domain.EstadoUser;
import domain.UserAuth0;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

public abstract class PersonableServiceImpl<D extends DomainBean<ID>, T extends Entity<ID>, ID extends Serializable>
        extends ServiceImpl<D, T, ID> implements PersonableService<D, ID> {

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private UserCliente userClient;

    @Autowired
    private MessageSource messageSource;

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
            String idSecurity = userClient.createUser(userAuth0);
            personaEntity.setIdSecurity(idSecurity);
            personaDao.save(personaEntity);
        }
        else {
            userClient.assignRolToUser(rolId, personaEntity.getIdSecurity());
        }
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserRol(String numeroDocumento, String rolLastId, String rolNewId) {
        PersonaEntity entity = Optional.ofNullable(personaDao.findByNumeroDocumento(numeroDocumento))
                .orElseThrow(() -> APIExceptions
                        .objetoNoEncontrado(messageSource.getMessage(
                                MessageKeys.PERSONA_NUMERO_DOCUMENTO_NOT_FOUND,
                                new String[]{String.valueOf(numeroDocumento)}, getLocale())));

        if (entity.getIdSecurity() != null) {
            userClient.updateRolUser(entity.getIdSecurity(), rolLastId, rolNewId);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public EstadoUser removeRolToUser(String numeroDocumento, String rolId) {
        PersonaEntity entity = Optional.ofNullable(personaDao.findByNumeroDocumento(numeroDocumento))
                .orElseThrow(() -> APIExceptions
                        .objetoNoEncontrado(messageSource.getMessage(MessageKeys.PERSONA_NUMERO_DOCUMENTO_NOT_FOUND,
                                                                     new String[] {String.valueOf(numeroDocumento)}, getLocale())));

        if (entity.getIdSecurity() != null) {
            return userClient.removeRolToUser(entity.getIdSecurity(), rolId);
        }
        return EstadoUser.REMOVIDO_ROL;
    }
}
