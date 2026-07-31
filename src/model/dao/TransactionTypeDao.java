package model.dao;

import model.entities.TransactionType;

import java.util.List;

public interface TransactionTypeDao {

        void insert(TransactionType obj);

        void update(TransactionType obj);

        void deleteById(Integer id);

        TransactionType findById(Integer id);

        List<TransactionType> findAll();
    }
