package org.jherman.numberApp.jsf.repositories;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import org.jherman.numberApp.jsf.entities.Number;

import java.util.List;

@RequestScoped
public class NumberRepositoryImpl implements DAORepository<Number> {

    @Inject
    private EntityManager em;

    @Override
    public List<Number> listar() {
        return em.createQuery("select n from Number n", Number.class).getResultList();
    }

    @Override
    public Number porId(Long id) {
        return em.find(Number.class, id);
    }

    @Override
    public void guardar(Number number) {
        if (number.getId() != null && number.getId() > 0) {
            em.merge(number);
        } else {
            em.persist(number);
        }
    }

    @Override
    public void eliminar(Long id) {
        em.remove(porId(id));
    }
}
