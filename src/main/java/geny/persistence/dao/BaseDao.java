package geny.persistence.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dat on 11/14/2017.
 */

public interface BaseDao<T, K extends Serializable> {
    List<T> findAll();
    void persist(final T object);
    T find(final K key);
    T merge(final T object);
    void remove(final T object);
}
