package application;

import model.dao.CategoryDao;
import model.dao.DaoFactory;
import model.entities.Category;

import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);

        CategoryDao categoryDao = DaoFactory.createCategoryDao();

        System.out.println("TEST 1: Category findById");
        Category category = categoryDao.findById(3);
        System.out.println(category);

        System.out.println("TEST 2: Category findAll");
        category = new Category();
        List<Category> list = categoryDao.findAll();
        for(Category obj : list) {
            System.out.println(obj);
        }

        /*System.out.println("TEST 3: Category insert");
        Category newCategory = new Category(null, "Viagens");
        categoryDao.insert(newCategory);
        System.out.println("Inserted! New id = " + newCategory.getId());*/

        System.out.println("TEST 4: Category update");
        category = categoryDao.findById(2);
        category.setName("Aluguel");
        categoryDao.update(category);
        System.out.println("Update Completed");

        System.out.println("TEST 5: Category update");
        System.out.println("Enter id for delete test");
        int id = sc.nextInt();
        categoryDao.deleteById(id);
        System.out.println("Delete completed");

        sc.close();
    }
}