package com.heystyles.usuarios.api.converter;

import com.heystyles.common.exception.APIExceptions;
import com.heystyles.usuarios.api.dao.CuentaBancoDao;
import com.heystyles.usuarios.api.dao.ProveedorDao;
import com.heystyles.usuarios.api.entity.CuentaBancoEntity;
import com.heystyles.usuarios.api.entity.ProveedorEntity;
import com.heystyles.usuarios.api.message.MessageKeys;
import com.heystyles.usuarios.core.domain.CuentaBanco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.springframework.context.i18n.LocaleContextHolder.getLocale;

@Component
public class CuentaBancoToCuentaBancoEntityConverter implements Converter<CuentaBanco, CuentaBancoEntity> {

    @Autowired
    private CuentaBancoDao cuentaBancoDao;

    @Autowired
    private ProveedorDao proveedorDao;

    @Autowired
    private MessageSource messageSource;

    @Override
    public CuentaBancoEntity convert(CuentaBanco bean) {
        CuentaBancoEntity entity = null;
        if (bean.getId() == null) {
            entity = new CuentaBancoEntity();
        }
        else {
            entity = Optional.ofNullable(cuentaBancoDao.findOne(bean.getId()))
                    .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                            messageSource.getMessage(MessageKeys.CUENTA_BANCO_NOT_FOUND,
                                                     new String[]{String.valueOf(bean.getId())}, getLocale())));
        }
        entity.setNombreBanco(bean.getNombreBanco());
        entity.setNumeroCuenta(bean.getNumeroCuenta());
        entity.setTipoCuenta(bean.getTipoCuenta());
        ProveedorEntity proveedorEntity = Optional.ofNullable(proveedorDao.findOne(bean.getProveedorId()))
                .orElseThrow(() -> APIExceptions.objetoNoEncontrado(
                        messageSource.getMessage(MessageKeys.PROVEEDOR_NOT_FOUND,
                                                 new String[]{String.valueOf(bean.getProveedorId())}, getLocale())));
        entity.setProveedor(proveedorEntity);
        return entity;
    }
}
