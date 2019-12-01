package com.heystyles.usuarios.api.converter;

import com.heystyles.common.service.ConverterService;
import com.heystyles.usuarios.api.entity.UsuarioEntity;
import com.heystyles.usuarios.core.domain.Cargo;
import com.heystyles.usuarios.core.domain.Usuario;
import com.heystyles.usuarios.core.dto.UsuarioCargoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEntityToUsuarioCargoDtoConverter implements Converter<UsuarioEntity, UsuarioCargoDto> {

    @Autowired
    private ConverterService converterService;

    @Override
    public UsuarioCargoDto convert(UsuarioEntity entity) {
        UsuarioCargoDto dto = new UsuarioCargoDto();
        dto.setUsuario(converterService.convertTo(entity, Usuario.class));
        dto.setCargo(converterService.convertTo(entity.getCargo(), Cargo.class));
        return dto;
    }
}
