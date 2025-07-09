package entity;

import modelUtil.Failure;

public class User {
    private int userId;
    private String userName;
    private String password;

    public User(int userId, String userName, String password) throws Failure {
        checkUserId(userId);
        checkUserName(userName);
        checkPassword(password);
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public int getUserId(){
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserId(int userId) throws modelUtil.Failure {
        checkUserId(userId);
        this.userId = userId;
    }

    public void setUserName(String userName) throws modelUtil.Failure {
        checkUserName(userName);
        this.userName = userName;
    }

    public void setPassword(String password) throws modelUtil.Failure {
        checkPassword(password);
        this.password = password;
    }

    private void checkUserId(int userId) throws modelUtil.Failure {
        if (userId < 0) {
            throw new modelUtil.Failure("ユーザーIDは正の正数でなくてはなりません。");
        }
    }

    private void checkUserName(String userName) throws modelUtil.Failure {
        if(userName == null || userName.isEmpty() || userName.length() > 100){
            throw new modelUtil.Failure("ユーザー名は1文字以上100文字以下である必要があります。");
        }
    }

    private void checkPassword(String password) throws modelUtil.Failure {
        if (password == null || password.isEmpty() || password.length() > 64) {
            throw new modelUtil.Failure("パスワードは1文字以上64文字以下である必要があります。");
        }
    }
}