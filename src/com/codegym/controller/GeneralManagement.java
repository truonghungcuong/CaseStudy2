package com.codegym.controller;

public interface GeneralManagement<T> {
    void displayAll();

    void addNew(T t);

    void update(int index, T t);

    boolean delete(int index);

    T getById(int index);
}
