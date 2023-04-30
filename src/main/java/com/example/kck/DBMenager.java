package com.example.kck;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

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
        String sql = "INSERT INTO Uzytkownik(login, haslo, isAdmin) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            pstmt.setString(3, "0");
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

    public User logInUser(String login, String password) {
        String sql = "SELECT * FROM Uzytkownik WHERE login = ? AND haslo = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                boolean isAdmin = rs.getInt("isAdmin") == 1;
                if(isAdmin)
                    return new User(login, isAdmin);
                else
                    return new User((login));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void insertContact(String name, String surname, String phoneNumber, String email, String town, String street, String homeNumber, String description) {
        String sql = "INSERT INTO Kontakt (imie, nazwisko, nrtelefonu, email, miejscowosc, ulica, nrdomu, opis) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setString(3, phoneNumber);
            pstmt.setString(4, email);
            pstmt.setString(5, town);
            pstmt.setString(6, street);
            pstmt.setString(7, homeNumber);
            pstmt.setString(8, description);
            pstmt.executeUpdate(); // wykonaj zapytanie
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Kontakt> selectContacts() {
        List<Kontakt> kontakty = new ArrayList<>();
        String sql = "SELECT * FROM Kontakt";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Kontakt kontakt = new Kontakt();
                kontakt.setImie(rs.getString("imie"));
                kontakt.setNazwisko(rs.getString("nazwisko"));
                kontakt.setNrTelefonu(rs.getString("nrtelefonu"));
                kontakt.setEmail(rs.getString("email"));
                kontakt.setMiejscowosc(rs.getString("miejscowosc"));
                kontakt.setUlica(rs.getString("ulica"));
                kontakt.setNrDomu(rs.getString("nrdomu"));
                kontakt.setOpis(rs.getString("opis"));
                kontakty.add(kontakt);
            }
            return kontakty;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Kontakt> selectContacts(int pageNumber, int contactsNumberOnPage) {
        List<Kontakt> kontakty = new ArrayList<>();
        int startIndex = (pageNumber - 1) * contactsNumberOnPage;
        String sql = "SELECT * FROM Kontakt ORDER BY nazwisko LIMIT " + startIndex + ", " + contactsNumberOnPage;
        //System.out.println(sql);
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Kontakt kontakt = new Kontakt();
                kontakt.setImie(rs.getString("imie"));
                kontakt.setNazwisko(rs.getString("nazwisko"));
                kontakt.setNrTelefonu(rs.getString("nrtelefonu"));
                kontakt.setEmail(rs.getString("email"));
                kontakt.setMiejscowosc(rs.getString("miejscowosc"));
                kontakt.setUlica(rs.getString("ulica"));
                kontakt.setNrDomu(rs.getString("nrdomu"));
                kontakt.setOpis(rs.getString("opis"));
                kontakty.add(kontakt);
            }
            return kontakty;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



}
/*
    while (rs.next()) {
        System.out.println("Imię: " + rs.getString("imie"));
        System.out.println("Nazwisko: " + rs.getString("nazwisko"));
        System.out.println("Numer telefonu: " + rs.getString("nrtelefonu"));
        System.out.println("Email: " + rs.getString("email"));
        System.out.println("Miejscowość: " + rs.getString("miejscowosc"));
        System.out.println("Ulica: " + rs.getString("ulica"));
        System.out.println("Numer domu: " + rs.getString("nrdomu"));
        System.out.println("Opis: " + rs.getString("opis"));
        System.out.println("-----------------------------");
    }
     */