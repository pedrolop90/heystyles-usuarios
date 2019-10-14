package com.heystyles.usuarios.api.service.impl;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.usuarios.api.dao.UsuarioDao;
import com.heystyles.usuarios.api.entity.UsuarioEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.api.service.UsuarioService;
import com.heystyles.usuarios.core.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Service
public class UsuarioServiceImpl extends PersonableServiceImpl<Usuario, UsuarioEntity, Long> implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    protected CrudRepository<UsuarioEntity, Long> getDao() {
        return usuarioDao;
    }

    @Override
    public Usuario getUsuario(Long usuarioId) {
        return Optional.ofNullable(findById(usuarioId)).orElseThrow(() ->
                APIExceptions.objetoNoEncontrado(messageSource.getMessage(MessageKeys.USUARIO_NOT_FOUND,
                        new String[]{String.valueOf(usuarioId)}, getLocale())));
    }
}
