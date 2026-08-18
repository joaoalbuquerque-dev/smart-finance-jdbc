package model.dao;

import db.DB;
import model.dao.impl.AppUserDaoJDBC;
import model.dao.impl.CategoryDaoJDBC;
import model.dao.impl.TransactionTypeDaoJDBC;

public class DaoFactory {

    public static CategoryDao createCategoryDao() {
        return new CategoryDaoJDBC(DB.getConnection());
    }

    public static TransactionTypeDao createTransactionTypeDao() {
        return new TransactionTypeDaoJDBC(DB.getConnection());
    }

    public static AppUserDao createAppUserDao() {
        return new AppUserDaoJDBC(DB.getConnection());
    }
}
