package application;

import model.dao.CategoryDao;
import model.dao.DaoFactory;
import model.dao.TransactionTypeDao;
import model.entities.TransactionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main (String[] args) {

        Scanner sc = new Scanner(System.in);

        CategoryDao categoryDao = DaoFactory.createCategoryDao();

       /* System.out.println("TEST 1: Category findById");
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
        System.out.println("Inserted! New id = " + newCategory.getId());

        System.out.println("TEST 4: Category update");
        category = categoryDao.findById(2);
        category.setName("Aluguel");
        categoryDao.update(category);
        System.out.println("Update Completed");

        System.out.println("TEST 5: Category update");
        System.out.println("Enter id for delete test");
        int id = sc.nextInt();
        categoryDao.deleteById(id);
        System.out.println("Delete completed!"); */

        TransactionTypeDao transactionTypeDao = DaoFactory.createTransactionTypeDao();

        /*System.out.println("TEST 1: TransactionType findById");
        TransactionType type = transactionTypeDao.findById(1);
        System.out.println(type);

        System.out.println("TEST 2: TransactionType findAll");
        type = new TransactionType();
        List<TransactionType> list = transactionTypeDao.findAll();
        for(TransactionType obj : list) {
            System.out.println(obj);
        }

        System.out.println("TEST 3: TransactionType insert");
        TransactionType newType = new TransactionType(null, "TRANSFER");
        transactionTypeDao.insert(newType);
        System.out.println("Inserted! New id = " + newType.getId());

        System.out.println("TEST 4: TransactionType update");
        type = transactionTypeDao.findById(3);
        type.setName("Transfer");
        transactionTypeDao.update(type);
        System.out.println("Update completed");

        System.out.println("TEST 5: TransactionType deleteById");
        System.out.println("Enter id for delete test");
        int id = sc.nextInt();
        transactionTypeDao.deleteById(id);
        System.out.println("Delete completed!");*/

        sc.close();
    }
}