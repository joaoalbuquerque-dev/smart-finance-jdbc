package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.CategoryDao;
import model.entities.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CategoryDaoJDBC implements CategoryDao {

    private Connection conn;
    public CategoryDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Category obj) {

    }

    @Override
    public void update(Category obj) {

    }

    @Override
    public void deleteById(Integer id) {

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
                cat.setName(rs.getString("Name"));

                return cat;
            }
            return null;
        }catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Category> findAll() {
        return List.of();
    }
}
