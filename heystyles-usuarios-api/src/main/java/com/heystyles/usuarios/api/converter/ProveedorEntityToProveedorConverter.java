package com.heystyles.usuarios.api.converter;

import com.heystyles.usuarios.api.entity.ProveedorEntity;
import com.heystyles.usuarios.core.domain.Proveedor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProveedorEntityToProveedorConverter implements Converter<ProveedorEntity, Proveedor> {
    @Override
    public Proveedor convert(ProveedorEntity entity) {
        Proveedor bean = new Proveedor();
        bean.setId(entity.getId());
        bean.setNombre(entity.getNombre());
        bean.setDescripcion(entity.getDescripcion());
        bean.setTelefono(entity.getTelefono());
        bean.setDireccion(entity.getDireccion());
        bean.setEmail(entity.getEmail());
        bean.setFechaLimitePago(entity.getFechaLimitePago());
        bean.setEstado(entity.getEstado());
        return bean;
    }
}
