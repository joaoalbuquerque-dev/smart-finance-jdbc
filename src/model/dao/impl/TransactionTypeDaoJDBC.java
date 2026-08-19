package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.TransactionTypeDao;
import model.entities.TransactionType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionTypeDaoJDBC implements TransactionTypeDao {

    private Connection conn;
    public TransactionTypeDaoJDBC (Connection conn) { this.conn = conn; }

    @Override
    public void insert(TransactionType obj) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
              "INSERT INTO transaction_type "
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
            }
            else {
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
    public void update(TransactionType obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE transaction_type "
                    + "SET name = ? "
                    + "WHERE id = ? ");

            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected == 0) {
                System.out.println("No transaction type found with this id!");
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
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "DELETE FROM transaction_type WHERE id = ? ");

            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();

            if(rowsAffected == 0) {
                System.out.println("No transaction type found with this id!");
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
    public TransactionType findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM transaction_type WHERE id = ? ");
            st.setInt(1, id);
            rs = st.executeQuery();
            if(rs.next()) {
                TransactionType type = new TransactionType();
                type.setId(rs.getInt("id"));
                type.setName(rs.getString("name"));

                return type;
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
    public List<TransactionType> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
              "SELECT * FROM transaction_type ORDER BY id ");

            rs = st.executeQuery();

            List<TransactionType> list = new ArrayList<>();
            while(rs.next()) {
                TransactionType type = new TransactionType();
                type.setId(rs.getInt("id"));
                type.setName(rs.getString("name"));

                list.add(type);
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
