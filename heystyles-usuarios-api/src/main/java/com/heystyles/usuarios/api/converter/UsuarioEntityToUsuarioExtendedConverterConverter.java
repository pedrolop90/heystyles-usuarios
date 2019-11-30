package com.heystyles.usuarios.api.converter;

import com.heystyles.common.service.ConverterService;
import com.heystyles.file.cliente.FileClient;
import com.heystyles.usuarios.api.entity.UsuarioEntity;
import com.heystyles.usuarios.core.domain.Usuario;
import com.heystyles.usuarios.core.domain.UsuarioExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsuarioEntityToUsuarioExtendedConverterConverter
        extends PersonableEntityToPersonaConverter<UsuarioEntity, Usuario>
        implements Converter<UsuarioEntity, UsuarioExtended> {

    @Autowired
    private ConverterService converterService;

    @Autowired
    private FileClient fileClient;

    @Override
    public UsuarioExtended convert(UsuarioEntity entity) {
        UsuarioExtended bean = new UsuarioExtended();
        bean.setUsuario(converterService.convertTo(entity, Usuario.class));
        bean.setFotografia(fileClient.getFile(entity.getFotografiaId()));
        return bean;
    }
}
