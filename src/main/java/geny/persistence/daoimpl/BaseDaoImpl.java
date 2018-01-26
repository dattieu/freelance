package geny.persistence.daoimpl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import geny.persistence.dao.BaseDao;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * Created by dat on 1/15/2018.
 */

@SuppressWarnings("unchecked")
@Repository("baseDao")
public abstract class BaseDaoImpl<T, K extends Serializable> implements BaseDao<T, K> {

    @Autowired
    private SessionFactory sessionFactory;

    protected Class<? extends T> classType;

    public BaseDaoImpl() {
        ParameterizedType paramType = (ParameterizedType) getClass().getGenericSuperclass();
        classType = (Class<? extends T>) paramType.getActualTypeArguments()[0];
    }

    protected final Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    protected final Criteria getCriteria() {
        return getSession().createCriteria(classType);
    }

    @Override
    public final List<T> findAll() {
        return getCriteria().list();
    }

    @Override
    public final void persist(final T object) {
        getSession().persist(object);
    }

    @Override
    public final T find(final K key) {
        return (T) getSession().get(classType, key);
    }

    @Override
    public final T merge(final T object) {
        return (T) getSession().merge(object);
    }

    @Override
    public final void remove(final T object) {
        getSession().delete(object);
    }
}
