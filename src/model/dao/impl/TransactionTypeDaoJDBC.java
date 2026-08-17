package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.TransactionTypeDao;
import model.entities.TransactionType;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransactionTypeDaoJDBC implements TransactionTypeDao {

    private Connection conn;
    public TransactionTypeDaoJDBC (Connection conn) { this.conn = conn; }

    @Override
    public void insert(TransactionType obj) {

    }

    @Override
    public void update(TransactionType obj) {

    }

    @Override
    public void deleteById(Integer id) {

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
        }catch (SQLException e) {
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
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }
}
