package com.company.db;

import com.company.com.company.exeception.DBException;
import com.company.jdbc_util.DBUtil;
import com.company.models.Users;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDB {

    public static List<Users> getAll() throws DBException {
        String sql = "SELECT * FROM Users ORDER BY userId";
        List<Users> users = new ArrayList<>();
        Connection connection = DBUtil.getConnection();

        try(PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            while(rs.next()) {
                int userId = rs.getInt("UserId");
                String username = rs.getString("Username");
                String gender = rs.getString("Gender");
                int age = rs.getInt("Age");
                String userType = rs.getString("UserType");
                int onlineStatus = rs.getInt("OnlineStatus");
                String relationshipStatus = rs.getString("RelationshipStatus");
                String levelOfEducation = rs.getString("LevelOfEduction");

                Users user = new Users(userId, username, null, gender, age, userType, onlineStatus, relationshipStatus, levelOfEducation);
                users.add(user);
            }

            return users;
        } catch( SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static Users getById(int userId) throws DBException {
        String sql = "SELECT * FROM Users WHERE UserId = ?";
        Connection connection = DBUtil.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if( rs.next()) {
                String username = rs.getString("Username");
                String gender = rs.getString("Gender");
                int age = rs.getInt("Age");
                String userType = rs.getString("UserType");
                int onlineStatus = rs.getInt("OnlineStatus");
                String relationshipStatus = rs.getString("RelationshipStatus");
                String levelOfEducation = rs.getString("LevelOfEduction");

                Users user = new Users(userId, username, null, gender, age, userType, onlineStatus, relationshipStatus, levelOfEducation);

                return user;
            } else {
                rs.close();
                return null;
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static Users getByUsername(String username) throws DBException {
        String sql = "SELECT * FROM Users WHERE Username = ?";
        Connection connection = DBUtil.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if( rs.next()) {
                int userId = rs.getInt("UserId");
                String gender = rs.getString("Gender");
                int age = rs.getInt("Age");
                String userType = rs.getString("UserType");
                int onlineStatus = rs.getInt("OnlineStatus");
                String relationshipStatus = rs.getString("RelationshipStatus");
                String levelOfEducation = rs.getString("LevelOfEduction");

                Users user = new Users(userId, username, null, gender, age, userType, onlineStatus, relationshipStatus, levelOfEducation);

                return user;
            } else {
                rs.close();
                return null;
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void add(Users user) throws DBException, NoSuchAlgorithmException {
        String sql =
                "INSERT INTO Users(Username, Password, Gender, Age, UserType, OnlineStatus, RelationshipStatus, LevelOfEduction) " +
                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        // md5 password hash
        final MessageDigest md5 = MessageDigest.getInstance("MD5");
        final byte[] password = user.getPassword().getBytes();

        Connection connection = DBUtil.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, DatatypeConverter.printHexBinary(md5.digest(password)));
            ps.setString(3, user.getGender());
            ps.setInt(4, user.getAge());
            ps.setString(5, user.getUserType());
            ps.setInt(6, user.getOnlineStatus());
            ps.setString(7, user.getRelationshipStatus());
            ps.setString(8, user.getLevelOfEduction());

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void update(Users user) throws DBException {
        String sql = "UPDATE Users SET " +
                "Gender = ?, " +
                "Age = ?, " +
                "UserType = ?, " +
                "OnlineStatus = ?, " +
                "RelationshipStatus = ?, " +
                "LevelOfEducation = ?";

        Connection connection = DBUtil.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, user.getGender());
            ps.setInt(2, user.getAge());
            ps.setString(3, user.getUserType());
            ps.setInt(4, user.getOnlineStatus());
            ps.setString(5, user.getRelationshipStatus());
            ps.setString(6, user.getLevelOfEduction());

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new DBException(e.getMessage());
        }

    }

    public static void delete(Users user) throws DBException {
        String sql = "DELETE FROM Users " +
                "WHERE Username = ? AND UserId = ?";
        Connection connection = DBUtil.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setInt(2, user.getUserId());

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }
}
