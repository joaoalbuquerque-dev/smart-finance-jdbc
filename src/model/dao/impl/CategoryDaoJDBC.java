package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.CategoryDao;
import model.entities.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoJDBC implements CategoryDao {

    private Connection conn;
    public CategoryDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Category obj) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                "INSERT INTO category "
                    + "(name) "
                    + "VALUES "
                    + "(?)",
            Statement.RETURN_GENERATED_KEYS);

            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();

           if(rowsAffected > 0) {
               rs = st.getGeneratedKeys();
               if(rs.next()) {
                   int id = rs.getInt(1);
                   obj.setId(id);
               }
           }else {
               throw new DbException("Unexpected error! No rows affected!");
           }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public void update(Category obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE category "
                            + "SET name = ? "
                            + "WHERE id = ? ");

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected == 0) {
                throw new DbException("No category found with this id!");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM category WHERE id = ?");
            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();

            if(rowsAffected == 0) {
                throw new DbException ("No category found with this id!");
            }
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Category findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM category WHERE id = ? ");

            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()) {
                Category cat = new Category();
                cat.setId(rs.getInt("id"));
                cat.setName(rs.getString("name"));

                return cat;
            }
            return null;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Category> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
             st = conn.prepareStatement(
                    "SELECT * FROM category ORDER BY  id");
             rs = st.executeQuery();

             List<Category> list = new ArrayList<>();
             while(rs.next()) {
                 Category cat = new Category();
                 cat.setId(rs.getInt("id"));
                 cat.setName(rs.getString("name"));

                 list.add(cat);
             }
             return list;
        }
        catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
