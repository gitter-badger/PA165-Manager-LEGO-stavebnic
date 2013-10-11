package cz.muni.fi.PA165.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.lang.reflect.ParameterizedType;

/**
 * general class for AbstractDao, specific entity AbstractDao classes subclass this class
 * this class contains basic CRUD operations
 *
 * @param <E> generic type for instance of entity class
 * @author jirikrepl
 * @author Martin Rumanek
 */
public abstract class AbstractDao<E> {

    protected Class<E> entityClass;
    @PersistenceContext
    protected EntityManager entityManager;

    /**
     * costructor uses reflection to get proper entity class
     */
    public AbstractDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class) genericSuperclass.getActualTypeArguments()[0];

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("TestPU");
        entityManager = emf.createEntityManager();
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void close() {
        entityManager.close();
    }

    /**
     * persists an entity
     *
     * @param entity some entity to be persisted
     */
    public void store(E entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity is NULL");
        }
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    /**
     * remove entity from context
     *
     * @param entity entity which has to be removed
     */
    public void delete(E entity) {
        entityManager.remove(entity);
    }

    /**
     * find entity by given primary key (id)
     *
     * @param id find an instance of entity by its id
     * @return entity which was found
     */
    public E retrieveById(Long id) {
        return entityManager.find(entityClass, id);
    }
}