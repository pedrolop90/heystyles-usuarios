package com.heystyles.usuarios.api.converter;

import com.heystyles.usuarios.api.entity.UsuarioEntity;
import com.heystyles.usuarios.core.domain.Usuario;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEntityToUsuarioConverter extends PersonableEntityToPersona<UsuarioEntity, Usuario>
        implements Converter<UsuarioEntity, Usuario> {
    @Override
    public Usuario convert(UsuarioEntity entity) {
        Usuario bean = new Usuario();
        bean.setCargoId(entity.getCargo().getId());
        convertPersona(entity, bean);
        return bean;
    }
}
