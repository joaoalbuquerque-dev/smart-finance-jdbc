package model.dao;

import model.dao.impl.CategoryDaoJDBC;

public class DaoFactory {

    public static CategoryDao createCategoryDao() {
        return new CategoryDaoJDBC();
    }
}
