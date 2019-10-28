package com.heystyles.usuarios.api.converter;

import com.heystyles.usuarios.api.entity.CuentaBancoEntity;
import com.heystyles.usuarios.core.domain.CuentaBanco;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CuentaBancoEntityToCuentaBancoConverter implements Converter<CuentaBancoEntity, CuentaBanco> {
    @Override
    public CuentaBanco convert(CuentaBancoEntity entity) {
        CuentaBanco bean = new CuentaBanco();
        bean.setId(entity.getId());
        bean.setNombreBanco(entity.getNombreBanco());
        bean.setNumeroCuenta(entity.getNumeroCuenta());
        bean.setTipoCuenta(entity.getTipoCuenta());
        bean.setProveedorId(entity.getProveedor().getId());
        return bean;
    }
}
