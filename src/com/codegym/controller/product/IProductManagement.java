package com.codegym.controller.product;

import com.codegym.controller.ReadFile;
import com.codegym.controller.WriteFile;
import com.codegym.model.Product;

public interface IProductManagement extends ReadFile, WriteFile {
    void displayAll();

    void addNew(Product product);

    void update(int index, Product product);

    void delete(int index);

    Product getById(int index);
}
