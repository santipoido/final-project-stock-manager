package com.tpfinal.stockmanager.service.interfaces;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, ID> {
    List<T> findAll();
    Optional<T> findOptionalById(ID id);
    T findById(ID id); // Lanza excepción si no se encuentra
    T create(T entity);
    T update(ID id, T entity);
    void delete(ID id);
}

//Interfaz generica que permite obligar a los objetos a implementar un CRUD básico
