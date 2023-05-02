package org.jherman.numberApp.jsf.services;

import jakarta.ejb.Local;
import org.jherman.numberApp.jsf.entities.Number;

import java.util.List;
import java.util.Optional;

@Local
public interface NumberService {

    List<Number> listar();
    Optional<Number> porId(Long id);
    void guardar(Number number);
    void eliminar(Long id);

}
