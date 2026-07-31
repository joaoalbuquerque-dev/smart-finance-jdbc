package model.dao;

import model.entities.AppUser;

import java.util.List;

public interface AppUserDao {

        void insert(AppUser obj);

        void update(AppUser obj);

        void deleteById(Integer id);

        AppUser findById(Integer id);

        List<AppUser> findAll();

}
