package com.example.springBootdemo2.dao;

import com.example.springBootdemo2.model.Role;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> findAll() {
        return entityManager.createQuery("Select r from Role r", Role.class).getResultList();
    }


    @Override
    public Role getByName(String name) {
        return entityManager.createQuery("Select r from Role r where r.name=:name", Role.class)
                .setParameter("name", name)
                .getSingleResult();
    }

    @Override
    public void add(Role role) {
        entityManager.persist(role);
    }

    @Override
    public Role getById(int id) {
        TypedQuery<Role> query = entityManager.createQuery("select r from Role r where r.id =:role_id", Role.class);
        query.setParameter("role_id", id);
        return query.getResultList().stream().findAny().orElse(null);
    }

//    @Override
//    public Set<Role> getRolesByName(Set<Role> roles) {
//        Set<Role> userRoles = new HashSet<>();
//        for (Role role : roles) {
//            userRoles.add(getByName(role.getName()));
//        }
//        return userRoles;
//    }
}
