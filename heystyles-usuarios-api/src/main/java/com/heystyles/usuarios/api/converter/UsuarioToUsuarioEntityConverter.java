package com.heystyles.usuarios.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.common.service.ConverterService;
import com.heystyles.usuarios.api.dao.CargoDao;
import com.heystyles.usuarios.api.dao.PersonaDao;
import com.heystyles.usuarios.api.dao.UsuarioDao;
import com.heystyles.usuarios.api.entity.CargoEntity;
import com.heystyles.usuarios.api.entity.PersonaEntity;
import com.heystyles.usuarios.api.entity.UsuarioEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.core.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class UsuarioToUsuarioEntityConverter implements Converter<Usuario, UsuarioEntity> {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private CargoDao cargoDao;

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private ConverterService converterService;

    @Autowired
    private MessageSource messageSource;

    @Override
    public UsuarioEntity convert(Usuario bean) {
        UsuarioEntity entity = null;
        if (bean.getId() == null) {
            entity = new UsuarioEntity();
            PersonaEntity personaEntity = Optional.ofNullable(
                    personaDao.findByNumeroDocumento(bean.getNumeroDocumento())).orElse(new PersonaEntity());
            entity.setPersona(personaEntity);
        }
        else {
            entity = Optional.ofNullable(usuarioDao.findOne(bean.getId()))
                    .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                            messageSource.getMessage(MessageKeys.USUARIO_NOT_FOUND,
                                    new String[]{String.valueOf(bean.getId())},
                                    getLocale())));
        }
        CargoEntity cargoEntity = Optional.ofNullable(cargoDao.findOne(bean.getCargoId()))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(messageSource.getMessage(
                        MessageKeys.CARGO_NOT_FOUND, new String[]{String.valueOf(bean.getCargoId())}, getLocale())));
        entity.setCargo(cargoEntity);
        entity.setEstado(bean.getEstado());

        entity.setIdPersona(bean.getIdPersona());
        entity.setNombres(bean.getNombres());
        entity.setApellidos(bean.getApellidos());
        entity.setNumeroDocumento(bean.getNumeroDocumento());
        entity.setEmail(bean.getEmail());
        entity.setFechaNacimiento(bean.getFechaNacimiento());
        entity.setTipoDocumento(bean.getTipoDocumento());
        entity.setTelefono(bean.getTelefono());
        if (bean.getFotografiaId() != null) {
            entity.setFotografiaId(bean.getFotografiaId());
        }

        return entity;
    }
}
