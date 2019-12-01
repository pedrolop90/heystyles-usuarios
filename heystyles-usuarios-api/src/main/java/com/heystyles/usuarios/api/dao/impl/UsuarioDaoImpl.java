package com.heystyles.usuarios.api.dao.impl;

import com.heystyles.usuarios.api.config.AuditConfig;
import com.heystyles.usuarios.api.dao.UsuarioCustomDao;
import com.heystyles.usuarios.api.entity.UsuarioEntity;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional(readOnly = true)
public class UsuarioDaoImpl implements UsuarioCustomDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<UsuarioEntity> findByExcluyendoCargoGerenteAndUsuarioActivo(String numeroDocumentoUsuarioActivo) {
        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(UsuarioEntity.class);
        criteria.createAlias(UsuarioEntity.Attributes.PERSONA, UsuarioEntity.Attributes.PERSONA);
        criteria.createAlias(UsuarioEntity.Attributes.CARGO,
                UsuarioEntity.Attributes.CARGO);
        if (numeroDocumentoUsuarioActivo != null
                && !numeroDocumentoUsuarioActivo.equalsIgnoreCase(AuditConfig.USUARIO_DEFECTO)) {
            criteria.add(Restrictions.not(Restrictions.eq(UsuarioEntity.Attributes.NUMERO_DOCUMENTO, numeroDocumentoUsuarioActivo)));
        }
        criteria.add(Restrictions.gt(UsuarioEntity.Attributes.CARGO_NIVEL, 0L));
        return criteria.list();
    }
}
