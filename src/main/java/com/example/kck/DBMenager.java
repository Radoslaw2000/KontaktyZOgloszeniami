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

    public void insertUlubione(User user, Kontakt kontakt) {
        String sql = "INSERT INTO UlubionyKontakt (uzytkownikID, kontaktID) VALUES (?, ?)";
        int userID = selectUserIdByLogin(user.getLogin());

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(userID));
            pstmt.setString(2, String.valueOf(kontakt.getId()));
            pstmt.executeUpdate(); // wykonaj zapytanie
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int selectUserIdByLogin(String login) {
        String sql = "SELECT uzytkownikID FROM Uzytkownik WHERE login = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, login);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("uzytkownikID");

            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1; // gdy nie znaleziono użytkownika
    }

    public List<Kontakt> selectContacts() {
        List<Kontakt> kontakty = new ArrayList<>();
        String sql = "SELECT * FROM Kontakt";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Kontakt kontakt = new Kontakt();
                kontakt.setId(rs.getInt("kontaktID"));
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
                kontakt.setId(rs.getInt("kontaktID"));
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

    public boolean isFavoriteContact(User user, int kontaktID) {
        String sql = "SELECT * FROM UlubionyKontakt WHERE uzytkownikID = ? AND kontaktID = ?";
        int userID = selectUserIdByLogin(user.getLogin());
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            pstmt.setInt(2, kontaktID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    public void deleteFavoriteContact(User user, int kontaktID) {
        String sql = "DELETE FROM UlubionyKontakt WHERE uzytkownikID = ? AND kontaktID = ?";
        int userID = selectUserIdByLogin(user.getLogin());
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            pstmt.setInt(2, kontaktID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Kontakt> selectFavoriteContacts() {
        String login = Settings.getInstance().getUser().getLogin();
        int pageNumber = Settings.getInstance().getPageNumber();
        int contactsNumberOnPage = Settings.getInstance().getContactsNumberOnPage();
        int userID = selectUserIdByLogin(login);

        List<Kontakt> favoriteContacts = new ArrayList<>();
        int startIndex = (pageNumber - 1) * contactsNumberOnPage;
        String sql = "SELECT k.* FROM Kontakt k JOIN UlubionyKontakt uk ON k.kontaktID = uk.kontaktID WHERE uk.uzytkownikID = ? LIMIT ?,?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            pstmt.setInt(2, startIndex);
            pstmt.setInt(3, contactsNumberOnPage);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Kontakt kontakt = new Kontakt();
                kontakt.setId(rs.getInt("kontaktID"));
                kontakt.setImie(rs.getString("imie"));
                kontakt.setNazwisko(rs.getString("nazwisko"));
                kontakt.setNrTelefonu(rs.getString("nrtelefonu"));
                kontakt.setEmail(rs.getString("email"));
                kontakt.setMiejscowosc(rs.getString("miejscowosc"));
                kontakt.setUlica(rs.getString("ulica"));
                kontakt.setNrDomu(rs.getString("nrdomu"));
                kontakt.setOpis(rs.getString("opis"));
                favoriteContacts.add(kontakt);
            }
            return favoriteContacts;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public void deleteContact(int contactId) {
        String sql = "DELETE FROM Kontakt WHERE kontaktID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, contactId);
            pstmt.executeUpdate();
            deleteContactCascadeFavourites(contactId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteContactCascadeFavourites(int contactId) {
        String sql = "DELETE FROM UlubionyKontakt WHERE kontaktID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, contactId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void deleteFavouriteContact(int contactId) {
        String login = Settings.getInstance().getUser().getLogin();
        int userID = selectUserIdByLogin(login);

        String sql = "DELETE FROM UlubionyKontakt WHERE kontaktID = ? AND uzytkownikID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, contactId);
            pstmt.setInt(2, userID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }





}
