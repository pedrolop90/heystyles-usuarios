package com.heystyles.usuarios.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.usuarios.api.dao.CargoDao;
import com.heystyles.usuarios.api.dao.UsuarioDao;
import com.heystyles.usuarios.api.entity.CargoEntity;
import com.heystyles.usuarios.api.entity.UsuarioEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.api.service.CargoService;
import com.heystyles.usuarios.api.service.UsuarioService;
import com.heystyles.usuarios.core.domain.Usuario;
import domain.EstadoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class UsuarioServiceImpl extends PersonableServiceImpl<Usuario, UsuarioEntity, Long> implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private CargoDao cargoDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<UsuarioEntity, Long> getDao() {
        return usuarioDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long insert(Usuario bean) {
        Long id = super.insert(bean);
        Long rolId = cargoService.getCargo(bean.getCargoId()).getIdSecurity();
        registerUser(bean.getNumeroDocumento(), rolId);
        return id;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(Long id, Usuario bean) {
        Usuario oldPersonal = getUsuario(id);
        Usuario newPersonal = bean;
        newPersonal.setId(id);

        CargoEntity cargoOld = Optional.ofNullable(
                cargoDao.findOne(oldPersonal.getCargoId()))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(
                                MessageKeys.CARGO_NOT_FOUND,
                                new String[]{String.valueOf(oldPersonal.getCargoId())}, getLocale())));

        super.update(id, bean);
        CargoEntity cargoNew = cargoDao.findOne(newPersonal.getCargoId());
        updateUserRol(newPersonal.getNumeroDocumento(), cargoOld.getIdSecurity(), cargoNew.getIdSecurity());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Long usuarioId) {
        Usuario usuario = getUsuario(usuarioId);
        super.delete(usuarioId);
        Optional.ofNullable(cargoService.getCargo(usuario.getCargoId()))
                .map(cargo -> removeRolToUser(usuario.getNumeroDocumento(), cargo.getIdSecurity()))
                .filter(estadoUser -> estadoUser.compareTo(EstadoUser.USER_ELIMINADO) == 0)
                .ifPresent(estadoUser -> {
                    UsuarioEntity entity = getDao().findOne(usuarioId);
                    entity.setIdSecurity(null);
                    getDao().save(entity);
                });

    }

    @Override
    public Usuario getUsuario(Long usuarioId) {
        return Optional.ofNullable(findById(usuarioId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(
                                MessageKeys.USUARIO_NOT_FOUND,
                                new String[]{String.valueOf(usuarioId)}, getLocale())));
    }
}
