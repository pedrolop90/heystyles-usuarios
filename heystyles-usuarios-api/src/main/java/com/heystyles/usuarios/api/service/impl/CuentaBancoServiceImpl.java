package com.heystyles.usuarios.api.service.impl;

import com.heystyles.common.service.impl.ServiceImpl;
import com.heystyles.usuarios.api.dao.CuentaBancoDao;
import com.heystyles.usuarios.api.entity.CuentaBancoEntity;
import com.heystyles.usuarios.api.service.CuentaBancoService;
import com.heystyles.usuarios.core.domain.CuentaBanco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaBancoServiceImpl
        extends ServiceImpl<CuentaBanco, CuentaBancoEntity, Long> implements CuentaBancoService {

    @Autowired
    private CuentaBancoDao cuentaBancoDao;

    @Override
    protected CrudRepository<CuentaBancoEntity, Long> getDao() {
        return cuentaBancoDao;
    }

    @Override
    //@Transactional(propagation = Propagation.REQUIRES_NEW)
    public void uppsert(Long proveedorId, List<CuentaBanco> cuentasBanco) {
        if (cuentasBanco != null) {
            cuentasBanco.forEach(cuentaBanco -> {
                cuentaBanco.setProveedorId(proveedorId);
                if (cuentaBanco.getId() == null) {
                    insert(cuentaBanco);
                }
                else {
                    update(cuentaBanco);
                }
            });
        }
    }

    @Override
    public List<CuentaBanco> findByProveedoId(Long proveedorId) {
        return getConverterService().convertTo(cuentaBancoDao.findByProveedorId(proveedorId), CuentaBanco.class);
    }
}
