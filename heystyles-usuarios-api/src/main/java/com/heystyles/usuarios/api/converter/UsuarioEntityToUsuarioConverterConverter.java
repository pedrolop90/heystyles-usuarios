package com.heystyles.usuarios.api.converter;

import com.heystyles.usuarios.api.entity.UsuarioEntity;
import com.heystyles.usuarios.core.domain.Usuario;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEntityToUsuarioConverterConverter extends PersonableEntityToPersonaConverter<UsuarioEntity, Usuario>
        implements Converter<UsuarioEntity, Usuario> {
    @Override
    public Usuario convert(UsuarioEntity entity) {
        Usuario bean = new Usuario();
        bean.setId(entity.getId());
        bean.setCargoId(entity.getCargo().getId());
        bean.setEstado(entity.getEstado());
        convertPersona(entity, bean);
        return bean;
    }
}
