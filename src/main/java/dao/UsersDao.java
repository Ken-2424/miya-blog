package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import entity.User;

public class UsersDao {

    // ユーザーの登録メソッド
    public void create(User user) throws DaoException{
        String sql = "INSERT INTO users (user_id, user_name, password) VALUES (?, ?, ?)";
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPassword());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("ユーザ登録時にエラーが発生しました。", e);
        }
    }

    // ユーザIDでユーザ情報を取得するメソッド
    public User getOne(int userId) throws DaoException {
        String sql = "SELECT * FROM users WHERE user_id = ?";
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet rs = statement.executeQuery()){
                if (rs.next()){
                    try {
                        return new User(
                                rs.getInt("user_id"),
                                rs.getString("user_name"),
                                rs.getString("password"));
                    } catch (modelUtil.Failure f) {
                        throw new DaoException("ユーザ情報取得時にエンティティ生成エラーが発生しました。",f);
                    }
                }
            }
            return null;
        } catch (SQLException e){
            throw new DaoException("ユーザ情報取得時にエラーが発生しました。", e);
        }
    }

    public User getOneByName(String userName) throws DaoException {
        String sql = "SELECT * FROM users WHERE user_name = ?";
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, userName);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    try {
                        return new User(
                                rs.getInt("user_id"),
                                rs.getString("user_name"),
                                rs.getString("password"));
                    } catch (modelUtil.Failure f) {
                        throw new DaoException("ユーザ情報取得時にエラーが発生しました。", f);
                    }
                }
            }
            return null;
        } catch (SQLException e) {
            throw new DaoException("ユーザ情報取得時にエラーが発生しました。", e);
        }
    }

    public void update(User user) throws DaoException {
        String sql = "UPDATE users SET user_name = ?, password = ? WHERE user_id = ?";
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("ユーザ情報更新時にエラーが発生しました。", e);
        }
    }

    public void delete(int userId) throws DaoException {
        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection connection = ConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("ユーザ削除時にエラーが発生しました。", e);
        }
    }
}
