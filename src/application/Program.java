package application;

import model.dao.CategoryDao;
import model.dao.DaoFactory;
import model.entities.Category;

public class Program {
    public static void main (String[] args) {

        CategoryDao categoryDao = DaoFactory.createCategoryDao();

        Category category = categoryDao.findById(3);

        System.out.println(category);
    }
}