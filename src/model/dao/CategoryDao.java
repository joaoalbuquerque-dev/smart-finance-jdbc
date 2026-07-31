package model.dao;

import model.entities.Category;

import java.util.List;

public interface CategoryDao {

    void insert(Category obj);
    void update(Category obj);
    void deleteById(Integer id);
    Category findById(Integer id);
    List<Category> findAll();
}
