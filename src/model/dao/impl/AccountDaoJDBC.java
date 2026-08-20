package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.AccountDao;
import model.entities.Account;
import model.entities.AppUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AccountDaoJDBC implements AccountDao {

    private Connection conn;
    public AccountDaoJDBC(Connection conn) { this.conn = conn;}

    @Override
    public void insert(Account obj) {

    }

    @Override
    public void update(Account obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Account findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT account.*,"
                    + "app_user.id AS user_id, "
                    + "app_user.name AS user_name "
                    + "FROM account INNER JOIN app_user "
                    + "ON account.user_id = app_user.id "
                    + "WHERE account.id = ? ");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                AppUser user = new AppUser();
                user.setId(rs.getInt("user_id"));
                user.setName(rs.getString("user_name"));
                Account obj = new Account();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setBalance(rs.getDouble("balance"));
                obj.setUser(user);

                return obj;
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
    public List<Account> findAll() {
        return List.of();
    }
}
