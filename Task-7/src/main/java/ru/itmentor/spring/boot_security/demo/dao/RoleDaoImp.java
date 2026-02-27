package ru.itmentor.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.itmentor.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class RoleDaoImp implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Optional<Role> findByName(String name) {
        try {
            return Optional.ofNullable(entityManager.createQuery("select r from Role r where r.name = :name", Role.class)
                    .setParameter("name", name)
                    .getSingleResult());
        }catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Role> getRoles() {
        return entityManager.createQuery("from Role").getResultList();
    }
}
