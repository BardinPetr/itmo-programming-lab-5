package ru.bardinpetr.itmo.lab5.server.dao.sync;

import ru.bardinpetr.itmo.lab5.models.data.collection.IKeyedEntity;
import ru.bardinpetr.itmo.lab5.models.data.collection.IOwnedEntity;
import ru.bardinpetr.itmo.lab5.server.dao.ICollectionDAO;

import java.lang.reflect.Proxy;

public class SynchronizedDAOFactory {
    @SuppressWarnings("unchecked")
    public static <K, V extends IKeyedEntity<K> & IOwnedEntity> ICollectionDAO<K, V> wrap(ICollectionDAO<K, V> original) {
        return (ICollectionDAO<K, V>) Proxy.newProxyInstance(
                ICollectionDAO.class.getClassLoader(),
                new Class[]{ICollectionDAO.class},
                new SynchronizedDAOInvocationHandler<>(original)
        );
    }
}
