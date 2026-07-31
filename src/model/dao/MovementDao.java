package model.dao;

import model.entities.Movement;

import java.util.List;

public interface MovementDao {

    void insert(Movement obj);
    void update(Movement obj);
    void deleteById(Integer id);
    Movement findById(Integer id);
    List<Movement> findAll();
}
