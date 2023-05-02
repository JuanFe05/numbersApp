package org.jherman.numberApp.jsf.services;

import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import org.jherman.numberApp.jsf.entities.Number;
import org.jherman.numberApp.jsf.repositories.DAORepository;

import java.util.List;
import java.util.Optional;

@Stateless
public class NumberServiceImpl implements NumberService {

    @Inject
    private DAORepository<Number> repository;

    @Override
    public List<Number> listar() {
        return repository.listar();
    }

    @Override
    public Optional<Number> porId(Long id) {
        return Optional.ofNullable(repository.porId(id));
    }

    @Override
    public void guardar(Number number) {
        repository.guardar(number);
    }

    @Override
    public void eliminar(Long id) {
        repository.eliminar(id);
    }
}
