package com.cg.service;

import java.util.List;

public interface IGeneralService<E, T> {

    List<E> findAll();
    List<E> findAllExceptSender(T t);

    E findById(T t);

    void create(E e);

    void update(T t, E e);

    void removeById(T t);
}
