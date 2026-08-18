package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.AppUserDao;
import model.entities.AppUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AppUserDaoJDBC implements AppUserDao {

    private Connection conn;
    public AppUserDaoJDBC(Connection conn) { this.conn = conn; }

    @Override
    public void insert(AppUser obj) {

    }

    @Override
    public void update(AppUser obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public AppUser findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM app_user WHERE id = ? ");

            st.setInt(1, id);
            rs = st.executeQuery();

            if(rs.next()) {
                AppUser user = new AppUser();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                return user;
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
    public List<AppUser> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT * FROM app_user ORDER BY id ");

            rs = st.executeQuery();

            List<AppUser> list = new ArrayList<>();
            while(rs.next()) {
                AppUser user = new AppUser();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                list.add(user);
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
