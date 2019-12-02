package com.heystyles.usuarios.api.dao.impl;

import com.heystyles.common.types.Page;
import com.heystyles.usuarios.api.dao.ProveedorCustomDaoImpl;
import com.heystyles.usuarios.api.entity.ProveedorEntity;
import com.heystyles.usuarios.core.filter.ProveedorFilter;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional(readOnly = true)
public class ProveedorDaoImpl implements ProveedorCustomDaoImpl {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<ProveedorEntity> getPage(ProveedorFilter filter) {
        Session session = entityManager.unwrap(org.hibernate.Session.class);

        Criteria totalCriteria = session.createCriteria(ProveedorEntity.class);

        Criteria pageCriteria = session.createCriteria(ProveedorEntity.class);

        applyWhere(totalCriteria, filter);
        applyWhere(pageCriteria, filter);

        applySort(totalCriteria, filter);
        applySort(pageCriteria, filter);

        queryCount(totalCriteria);
        queryPage(pageCriteria, filter.getPageNumber(), filter.getPageSize());

        Long total = (Long) totalCriteria.uniqueResult();
        List<ProveedorEntity> entities = pageCriteria.list();

        return new Page<>(total, entities);
    }

    private void applyWhere(Criteria criteria, ProveedorFilter filter) {
        if (filter.getEstado() != null) {
            criteria.add(Restrictions.eq(ProveedorEntity.Attributes.ESTADO, filter.getEstado()));
        }
        if (filter.getNombre() != null) {
            criteria.add(Restrictions.ilike(
                    ProveedorEntity.Attributes.NOMBRE, filter.getNombre(), MatchMode.ANYWHERE));
        }
    }

    private void applySort(Criteria criteria, ProveedorFilter filter) {
        if (filter.getNombreAscending() != null) {
            if (filter.getNombreAscending()) {
                criteria.addOrder(Order.asc(ProveedorEntity.Attributes.NOMBRE));
            }
            else {
                criteria.addOrder(Order.desc(ProveedorEntity.Attributes.NOMBRE));
            }
        }
        if (filter.getEstadoAscending() != null) {
            if (filter.getEstadoAscending()) {
                criteria.addOrder(Order.asc(ProveedorEntity.Attributes.ESTADO));
            }
            else {
                criteria.addOrder(Order.desc(ProveedorEntity.Attributes.ESTADO));
            }
        }
    }

    private void queryCount(Criteria criteria) {
        criteria.setProjection(Projections.count(ProveedorEntity.Attributes.ID));
    }

    private void queryPage(Criteria criteria, Integer pageNumber, Integer pageSize) {
        if (pageNumber != null && pageSize != null) {
            criteria.setFirstResult(pageNumber * pageSize);
            criteria.setMaxResults(pageSize);
        }
    }
}
