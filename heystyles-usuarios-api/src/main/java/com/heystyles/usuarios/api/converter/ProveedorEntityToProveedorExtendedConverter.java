package com.heystyles.usuarios.api.converter;

import com.heystyles.common.service.ConverterService;
import com.heystyles.usuarios.api.entity.ProveedorEntity;
import com.heystyles.usuarios.core.domain.CuentaBanco;
import com.heystyles.usuarios.core.domain.Persona;
import com.heystyles.usuarios.core.domain.Proveedor;
import com.heystyles.usuarios.core.domain.ProveedorExtended;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProveedorEntityToProveedorExtendedConverter
        implements Converter<ProveedorEntity, ProveedorExtended> {

    @Autowired
    private ConverterService converterService;

    @Override
    public ProveedorExtended convert(ProveedorEntity entity) {
        ProveedorExtended bean = new ProveedorExtended();
        bean.setProveedor(converterService.convertTo(entity, Proveedor.class));
        bean.setContactos(converterService.convertTo(entity.getContactos(), Persona.class));
        bean.setCuentasBanco(converterService.convertTo(entity.getCuentasBanco(), CuentaBanco.class));
        return bean;
    }
}
