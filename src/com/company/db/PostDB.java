package com.company.db;

import com.company.com.company.exeception.DBException;
import com.company.jdbc_util.DBUtil;
import com.company.models.Post;
import com.company.models.Users;

import java.io.PipedReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDB {

    public static List<Post> getAll() throws DBException {
        String sql = "SELECT * FROM Post ORDER BY PostID";
        List<Post> posts = new ArrayList<>();
        Connection connection = DBUtil.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while(rs.next()) {
                int postId = rs.getInt("PostID");
                String postTime = rs.getString("PostTime");
                String postUsername = rs.getString("PostUsername");
                String postType = rs.getString("PostType");
                String postCaption = rs.getString("PostCaption");
                String postImage = rs.getString("PostImage");
                String postText = rs.getString("PostText");

                Users user = new Users();
                user.setUsername(postUsername);
                Post p = new Post(postId, postTime, user, postType, postCaption, postImage, postText);

                posts.add(p);
            }

            return posts;
        }catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static Post get(int postId) throws DBException {
        String sql = "SELECT * FROM Post WHERE PostID = ?";
        Connection connection = DBUtil.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, postId);

            ResultSet rs = ps.executeQuery();
            Users user = new Users();
            if( rs.next()) {
                String postTime = rs.getString("PostTime");
                String postUsername = rs.getString("PostUsername");
                String postType = rs.getString("PostType");
                String postCaption = rs.getString("PostCaption");
                String postImage = rs.getString("PostImage");
                String postText = rs.getString("PostText");

                rs.close();
                user.setUsername(postUsername);
                Post p = new Post(postId, postTime, user, postType, postCaption, postImage, postText);
                return p;
            } else {
                rs.close();
                return null;
            }

        }catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void add(Post post) throws DBException {
        String sql = "INSERT INTO Post (PostTime, PostUsername, PostType, PostCaption, PostImage, PostText) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        Connection connection = DBUtil.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, post.getPostTime());
            ps.setString(2, post.getUser().getUsername());
            ps.setString(3, post.getPostType());
            ps.setString(4, post.getPostCaption());
            ps.setString(5, post.getPostImage());
            ps.setString(6, post.getPostText());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void update(Post post) throws DBException {

        String sql = "UPDATE Post SET " +
                "PostType = ?, " +
                "PostCaption = ?, " +
                "PostImage = ?, " +
                "PostText = ? WHERE PostID = ? AND PostUsername = ?";
        Connection connection = DBUtil.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {

            ps.setString(1, post.getPostType());
            ps.setString(2, post.getPostCaption());
            ps.setString(3, post.getPostImage());
            ps.setString(4, post.getPostText());
            ps.setInt(5, post.getPostId());
            ps.setString(6, post.getUser().getUsername());

            ps.executeUpdate();

        }catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }


    public static void delete(Post post) throws DBException {
        String sql = "DELETE FROM Post " +
                "WHERE PostID = ?";
        Connection connection = DBUtil.getConnection();
        try(PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, post.getPostId());
            ps.executeUpdate();
        }catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }
}
