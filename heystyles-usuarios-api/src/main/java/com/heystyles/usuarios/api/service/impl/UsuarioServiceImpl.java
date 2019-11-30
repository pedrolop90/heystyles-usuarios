package com.heystyles.usuarios.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.file.cliente.FileClient;
import com.heystyles.usuarios.api.dao.CargoDao;
import com.heystyles.usuarios.api.dao.UsuarioDao;
import com.heystyles.usuarios.api.entity.CargoEntity;
import com.heystyles.usuarios.api.entity.UsuarioEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.api.service.UsuarioService;
import com.heystyles.usuarios.core.domain.Usuario;
import com.heystyles.usuarios.core.domain.UsuarioExtended;
import com.heystyles.usuarios.core.dto.UsuarioRequest;
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
public class UsuarioServiceImpl
        extends PersonableServiceImpl<Usuario, UsuarioEntity, Long> implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private CargoDao cargoDao;

    @Autowired
    private FileClient fileClient;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<UsuarioEntity, Long> getDao() {
        return usuarioDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long insert(UsuarioRequest request) {
        Usuario usuario = request.getUsuario();

        Long id = insert(usuario);

        if (request.getFotografia() != null) {
            Long fotografiaId = fileClient.save(request.getFotografia());
            usuario.setId(id);
            usuario.setFotografiaId(fotografiaId);
            super.update(usuario);
        }

        return id;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(UsuarioRequest request) {
        Usuario usuario = request.getUsuario();

        update(usuario);

        if (request.getFotografia() != null) {
            Long fotografiaId = fileClient.save(request.getFotografia());
            usuario.setFotografiaId(fotografiaId);
            super.update(usuario);
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Long insert(Usuario bean) {
        Long id = super.insert(bean);
        Long rolId = cargoDao.findOne(bean.getCargoId()).getIdSecurity();
        registerUser(bean.getNumeroDocumento(), rolId);
        return id;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void update(Usuario bean) {
        Usuario oldPersonal = getUsuario(bean.getId());
        Usuario newPersonal = bean;

        CargoEntity cargoOld = Optional.ofNullable(
                cargoDao.findOne(oldPersonal.getCargoId()))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(
                                MessageKeys.CARGO_NOT_FOUND,
                                new String[]{String.valueOf(oldPersonal.getCargoId())}, getLocale())));

        super.update(bean);
        CargoEntity cargoNew = cargoDao.findOne(newPersonal.getCargoId());
        updateUserRol(newPersonal.getNumeroDocumento(), cargoOld.getIdSecurity(), cargoNew.getIdSecurity());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void delete(Long usuarioId) {
        Usuario usuario = getUsuario(usuarioId);
        super.delete(usuarioId);
        Optional.ofNullable(cargoDao.findOne(usuario.getCargoId()))
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

    @Override
    public Usuario getUsuarioByNumeroDocumento(String numeroDocumento) {
        return Optional.ofNullable(
                getConverterService().convertTo(
                        usuarioDao.findByPersonaNumeroDocumento(numeroDocumento), Usuario.class))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(
                                MessageKeys.USUARIO_NUMERO_DOCUMENTO_NOT_FOUND,
                                new String[]{String.valueOf(numeroDocumento)}, getLocale())));
    }

    @Override
    public UsuarioExtended getUsuarioExtended(Long usuarioId) {
        UsuarioEntity usuarioEntity = Optional.ofNullable(usuarioDao.findOne(usuarioId))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(
                                MessageKeys.USUARIO_NOT_FOUND,
                                new String[]{String.valueOf(usuarioId)}, getLocale())));
        return getConverterService().convertTo(usuarioEntity, UsuarioExtended.class);
    }
}
