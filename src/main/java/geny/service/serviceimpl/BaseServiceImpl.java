package geny.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import geny.persistence.dao.BaseDao;
import geny.service.serviceintf.BaseService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dat on 1/15/2018.
 */

@Service("baseService")
public class BaseServiceImpl<T, K extends Serializable> implements BaseService<T, K> {

    private BaseDao<T, K> baseDao;

    public BaseServiceImpl() {}

    public BaseServiceImpl(BaseDao<T, K> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public List<T> findAll() {
        return baseDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void persist(final T object) {
        baseDao.persist(object);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public T find(final K key) {
        return baseDao.find(key);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public T merge(final T object) {
        return baseDao.merge(object);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void remove(final T object) {
        baseDao.remove(object);
    }
}
