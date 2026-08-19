package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.AppUserDao;
import model.entities.AppUser;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppUserDaoJDBC implements AppUserDao {

    private Connection conn;
    public AppUserDaoJDBC(Connection conn) { this.conn = conn; }

    @Override
    public void insert(AppUser obj) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO app_user "
                    + "(name, email, password) "
                    + "VALUES "
                    + "(?, ?, ?) ",
                    Statement.RETURN_GENERATED_KEYS );
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setString(3, obj.getPassword());

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
    public void update(AppUser obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE app_user "
                    + "SET name = ?, email = ?, password = ? "
                    + "WHERE id = ? ");
            st.setString(1, obj.getName());
            st.setString(2, obj.getEmail());
            st.setString(3, obj.getPassword());
            st.setInt(4, obj.getId());

            int rowsAffected = st.executeUpdate();

            if(rowsAffected == 0) {
                throw new DbException("No user found with this id!");
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
                    "DELETE FROM app_user WHERE id = ? ");
            st.setInt(1, id);

            int rowsAffected = st.executeUpdate();

            if(rowsAffected == 0) {
                throw new DbException("No user found with this id!");
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
