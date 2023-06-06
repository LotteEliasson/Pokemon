package com.example.pokemon.Model;

public class Login {

    private int userId;
    private String userName;
    private String userPassw;

    public Login() {
    }

    public Login(int userId, String userName, String userPassw) {
        this.userId = userId;
        this.userName = userName;
        this.userPassw = userPassw;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassw() {
        return userPassw;
    }

    public void setUserPassw(String userPassw) {
        this.userPassw = userPassw;
    }

    @Override
    public String toString() {
        return "Login{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + userPassw + '\'' +
                '}';
    }
}
