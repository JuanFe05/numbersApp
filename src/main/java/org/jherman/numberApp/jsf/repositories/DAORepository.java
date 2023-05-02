package org.jherman.numberApp.jsf.repositories;

import java.util.List;
public interface DAORepository<T> {

    List<T> listar();
    T porId(Long id);
    void guardar(T t);
    void eliminar(Long id);

}
