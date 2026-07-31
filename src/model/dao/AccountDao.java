package model.dao;

import model.entities.Account;

import java.util.List;

public interface AccountDao {

        void insert(Account obj);

        void update(Account obj);

        void deleteById(Integer id);

        Account findById(Integer id);

        List<Account> findAll();
}

