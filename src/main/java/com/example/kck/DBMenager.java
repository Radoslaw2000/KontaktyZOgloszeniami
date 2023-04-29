package com.example.kck;

    import java.sql.*;

public class DBMenager
{
    private Connection conn;

    DBMenager(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public void insertUser(String login, String password) {
        String sql = "INSERT INTO Uzytkownik(login, haslo) VALUES (?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public boolean checkLogin(String login) {
        String sql = "SELECT * FROM Uzytkownik WHERE login = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean logInUser(String login, String password) {
        String sql = "SELECT * FROM Uzytkownik WHERE login = ? AND haslo = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }



}