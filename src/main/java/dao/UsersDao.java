// package dao;

// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;
// import entity.User;

// public class UsersDao {

//     // ユーザーの登録メソッド
//     public void create(User user) throws DaoException{
//         String sql = "INSERT INTO users (user_id, user_name, password) VALUES (?, ?, ?)";
//         try (Connection connection = ConnectionManager.getConnection();
//                 PreparedStatement statement = connection.prepareStatement(sql)){
//             statement.setInt(1, user.getUserId);
//             statement.setString(2, user.getUserName);
//             statement.setString(3, user.getPassword);
//             statement.exsuteUpdate();
//         } catch (SQLException e) {
//             throw new DaoException("ユーザ登録時にエラーが発生しました。", e);
//         }
//     }

//     // ユーザIDでユーザ情報を取得するメソッド
//     public User getOne(int userId) throws DaoException {
//         String sql = "SELECT * FROM users WHERE user_id = ?";
//         try (Connection connection = ConnectionManager.getConnection();
//                 PreparedStatement statement = connection.prepareStatement(sql)) {
//             statement.setInt(1, userId);
//             try (ResultSet rs = statement.executeQuery()){
//                 if (rs.next()){
//                     try {
//                         return new User(
//                                 rs.getInt("user_id"),
//                                 rs.getString("user_name"),
//                                 rs.getString("password"));
//                     } catch (modelUtil.Failure f) {
//                         throw new DaoException("ユーザ情報取得時にエラーが発生しました。",f);
//                     }

//                 }
//             }
//         }
//         }
//     }
// }