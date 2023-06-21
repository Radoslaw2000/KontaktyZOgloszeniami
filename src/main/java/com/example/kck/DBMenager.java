package com.example.kck;

    import java.sql.*;
    import java.util.ArrayList;
    import java.util.List;

public class DBMenager
{
    private Connection conn;

    public DBMenager(){
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

    public void restoreContact(Contact contact) {
    String sql = "INSERT INTO Kontakt (kontaktID, imie, nazwisko, nrtelefonu, email, miejscowosc, ulica, nrdomu, opis) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, contact.getId());
        pstmt.setString(2, contact.getName());
        pstmt.setString(3, contact.getSurname());
        pstmt.setString(4, contact.getPhoneNumber());
        pstmt.setString(5, contact.getEmail());
        pstmt.setString(6, contact.getTown());
        pstmt.setString(7, contact.getStreet());
        pstmt.setString(8, contact.getHouseNumber());
        pstmt.setString(9, contact.getDescription());
        pstmt.executeUpdate(); // wykonaj zapytanie
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }

}
    public void restoreAnnouncment(Announcment announcment) {
        String sql = "INSERT INTO Ogloszenie (ogloszenieID, tytul, cena, opis, miejscowosc, kategoria, nrtelefonu, wojewodztwo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, announcment.getId());
            pstmt.setString(2, announcment.getTitle());
            pstmt.setString(3, announcment.getPrice());
            pstmt.setString(4, announcment.getDescription());
            pstmt.setString(5, announcment.getTown());
            pstmt.setString(6, announcment.getCategory());
            pstmt.setString(7, announcment.getPhoneNumber());
            pstmt.setString(8, announcment.getVoivodeship());
            pstmt.executeUpdate(); // wykonaj zapytanie
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void insertAnnouncment(Announcment announcment) {
        String sql = "INSERT INTO Ogloszenie (autorID, tytul, cena, opis, miejscowosc, kategoria, nrtelefonu, wojewodztwo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, announcment.getAutorId());
            pstmt.setString(2, announcment.getTitle());
            pstmt.setString(3, announcment.getPrice());
            pstmt.setString(4, announcment.getDescription());
            pstmt.setString(5, announcment.getTown());
            pstmt.setString(6, announcment.getCategory());
            pstmt.setString(7, announcment.getPhoneNumber());
            pstmt.setString(8, announcment.getVoivodeship());
            pstmt.executeUpdate(); // wykonaj zapytanie
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertFavouriteContact(User user, Contact contact) {
        String sql = "INSERT INTO UlubionyKontakt (uzytkownikID, kontaktID) VALUES (?, ?)";
        int userID = selectUserIdByLogin(user.getLogin());

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(userID));
            pstmt.setString(2, String.valueOf(contact.getId()));
            pstmt.executeUpdate(); // wykonaj zapytanie
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertFavouriteAnnouncment(User user, Announcment announcment) {
        String sql = "INSERT INTO UlubioneOgloszenie (uzytkownikID, ogloszenieID) VALUES (?, ?)";
        int userID = selectUserIdByLogin(user.getLogin());

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, String.valueOf(userID));
            pstmt.setString(2, String.valueOf(announcment.getId()));
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


    public List<Contact> selectContacts() {
        List<Contact> kontakty = new ArrayList<>();
        String sql = "SELECT * FROM Kontakt";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("kontaktID"));
                contact.setName(rs.getString("imie"));
                contact.setSurname(rs.getString("nazwisko"));
                contact.setPhoneNumber(rs.getString("nrtelefonu"));
                contact.setEmail(rs.getString("email"));
                contact.setTown(rs.getString("miejscowosc"));
                contact.setStreet(rs.getString("ulica"));
                contact.setHouseNumber(rs.getString("nrdomu"));
                contact.setDescription(rs.getString("opis"));
                kontakty.add(contact);
            }
            return kontakty;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Contact> selectContacts(int pageNumber, int contactsNumberOnPage) {
        List<Contact> kontakty = new ArrayList<>();
        int startIndex = (pageNumber - 1) * contactsNumberOnPage;
        String sql = "SELECT * FROM Kontakt ORDER BY nazwisko LIMIT " + startIndex + ", " + contactsNumberOnPage;
        //System.out.println(sql);
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("kontaktID"));
                contact.setName(rs.getString("imie"));
                contact.setSurname(rs.getString("nazwisko"));
                contact.setPhoneNumber(rs.getString("nrtelefonu"));
                contact.setEmail(rs.getString("email"));
                contact.setTown(rs.getString("miejscowosc"));
                contact.setStreet(rs.getString("ulica"));
                contact.setHouseNumber(rs.getString("nrdomu"));
                contact.setDescription(rs.getString("opis"));
                kontakty.add(contact);
            }
            return kontakty;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Contact> selectContactsFiltered(int pageNumber, int contactsNumberOnPage,
                                                String generalText, String nameText, String surnameText, String emailText,
                                                String townText, String phoneNumberText, String streetText, String homeNumberText,
                                                String descriptionText) {

        List<Contact> kontakty = new ArrayList<>();
        int startIndex = (pageNumber - 1) * contactsNumberOnPage;
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * FROM Kontakt WHERE ");
        boolean whereClauseAdded = false;

        if (!generalText.isEmpty()) {
            sqlBuilder.append("(imie LIKE '%").append(generalText).append("%' OR ");
            sqlBuilder.append("nazwisko LIKE '%").append(generalText).append("%' OR ");
            sqlBuilder.append("nrtelefonu LIKE '%").append(generalText).append("%' OR ");
            sqlBuilder.append("email LIKE '%").append(generalText).append("%' OR ");
            sqlBuilder.append("miejscowosc LIKE '%").append(generalText).append("%' OR ");
            sqlBuilder.append("ulica LIKE '%").append(generalText).append("%' OR ");
            sqlBuilder.append("nrdomu LIKE '%").append(generalText).append("%' OR ");
            sqlBuilder.append("opis LIKE '%").append(generalText).append("%')");
            whereClauseAdded = true;
        }
        if (!nameText.isEmpty()) {
            if (whereClauseAdded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append("imie LIKE '%").append(nameText).append("%'");
            whereClauseAdded = true;
        }
        if (!surnameText.isEmpty()) {
            if (whereClauseAdded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append("nazwisko LIKE '%").append(surnameText).append("%'");
            whereClauseAdded = true;
        }
        if (!phoneNumberText.isEmpty()) {
            if (whereClauseAdded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append("nrtelefonu LIKE '%").append(phoneNumberText).append("%'");
            whereClauseAdded = true;
        }
        if (!emailText.isEmpty()) {
            if (whereClauseAdded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append("email LIKE '%").append(emailText).append("%'");
            whereClauseAdded = true;
        }
        if (!townText.isEmpty()) {
            if (whereClauseAdded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append("miejscowosc LIKE '%").append(townText).append("%'");
            whereClauseAdded = true;
        }
        if (!streetText.isEmpty()) {
            if (whereClauseAdded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append("ulica LIKE '%").append(streetText).append("%'");
            whereClauseAdded = true;
        }
        if (!homeNumberText.isEmpty()) {
            if (whereClauseAdded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append("nrdomu LIKE '%").append(homeNumberText).append("%'");
            whereClauseAdded = true;
        }
        if (!descriptionText.isEmpty()) {
            if (whereClauseAdded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append("opis LIKE '%").append(descriptionText).append("%'");
            whereClauseAdded = true;
        }
        if(!whereClauseAdded)
            sqlBuilder.append(" 1 ");

        sqlBuilder.append(" ORDER BY nazwisko LIMIT ").append(startIndex).append(", ").append(contactsNumberOnPage);

        String sql = sqlBuilder.toString();
        //System.out.println(sql);

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("kontaktID"));
                contact.setName(rs.getString("imie"));
                contact.setSurname(rs.getString("nazwisko"));
                contact.setPhoneNumber(rs.getString("nrtelefonu"));
                contact.setEmail(rs.getString("email"));
                contact.setTown(rs.getString("miejscowosc"));
                contact.setStreet(rs.getString("ulica"));
                contact.setHouseNumber(rs.getString("nrdomu"));
                contact.setDescription(rs.getString("opis"));
                kontakty.add(contact);
            }
            return kontakty;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


    public List<Announcment> selectAnnouncmentFiltered(int pageNumber, int contactsNumberOnPage, String generalText, String title,
                                              String description, String priceFrom, String priceTo, String town, String voivodeship) {
        List<Announcment> announcments = new ArrayList<>();
        int indeksStartowy = (pageNumber - 1) * contactsNumberOnPage;

        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("SELECT * FROM Ogloszenie WHERE ");
        boolean whereClauseAdded = false;

        if (!generalText.isEmpty()) {
            sqlBuilder.append("tytul LIKE '%").append(generalText).append("%' OR ");
            sqlBuilder.append("opis LIKE '%").append(generalText).append("%' OR ");
            sqlBuilder.append("miejscowosc LIKE '%").append(generalText).append("%'");
            whereClauseAdded = true;
        }
        if (!title.isEmpty()) {
            if (whereClauseAdded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append("tytul LIKE '%").append(title).append("%'");
            whereClauseAdded = true;
        }
        if (!description.isEmpty()) {
            if (whereClauseAdded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append("opis LIKE '%").append(description).append("%'");
            whereClauseAdded = true;
        }
        if (!priceFrom.isEmpty()) {
            if (whereClauseAdded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append("cena >= ").append(priceFrom);
            whereClauseAdded = true;
        }
        if (!priceTo.isEmpty()) {
            if (whereClauseAdded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append("cena <= ").append(priceTo);
            whereClauseAdded = true;
        }
        if (!town.isEmpty()) {
            if (whereClauseAdded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append("miejscowosc LIKE '%").append(town).append("%'");
            whereClauseAdded = true;
        }
        if (!voivodeship.isEmpty() && !voivodeship.equals("cała polska")) {
            if (whereClauseAdded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append("wojewodztwo LIKE '%").append(voivodeship).append("%'");
            whereClauseAdded = true;
        }
        if (!Settings.getInstance().getCategory().equals("Wszystko")) {
            if (whereClauseAdded) {
                sqlBuilder.append(" AND ");
            }
            sqlBuilder.append("kategoria LIKE '%").append(Settings.getInstance().getCategory()).append("%'");
            whereClauseAdded = true;
        }

        if(!whereClauseAdded)
            sqlBuilder.append(" 1 ");
        sqlBuilder.append(" ORDER BY tytul LIMIT ").append(indeksStartowy).append(", ").append(contactsNumberOnPage);

        String sql = sqlBuilder.toString();
        System.out.println(sql); ///////////////

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Announcment announcment = new Announcment();
                announcment.setId(rs.getInt("ogloszenieID"));
                announcment.setAutorId(rs.getInt("autorID"));
                announcment.setTitle(rs.getString("tytul"));
                announcment.setPrice(rs.getString("cena"));
                announcment.setDescription(rs.getString("opis"));
                announcment.setTown(rs.getString("miejscowosc"));
                announcment.setCategory(rs.getString("kategoria"));
                announcment.setPhoneNumber(rs.getString("nrtelefonu"));
                announcment.setVoivodeship(rs.getString("wojewodztwo"));
                announcments.add(announcment);
            }
            return announcments;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }



    public void updateContact(int contactID, String name, String surname, String phoneNumber, String email, String town, String street, String homeNumber, String description) {
        String sql = "UPDATE Kontakt SET imie = ?, nazwisko = ?, nrtelefonu = ?, email = ?, miejscowosc = ?, ulica = ?, nrdomu = ?, opis = ? WHERE kontaktID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, surname);
            pstmt.setString(3, phoneNumber);
            pstmt.setString(4, email);
            pstmt.setString(5, town);
            pstmt.setString(6, street);
            pstmt.setString(7, homeNumber);
            pstmt.setString(8, description);
            pstmt.setInt(9, contactID);
            pstmt.executeUpdate(); // wykonaj zapytanie
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public void updateAnnouncment(Announcment announcment) {
        String sql = "UPDATE Ogloszenie SET tytul = ?, cena = ?, opis = ?, miejscowosc = ?, kategoria = ?, nrtelefonu = ?, wojewodztwo = ? WHERE ogloszenieID = ?";
        System.out.println(sql);

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, announcment.getTitle());
            pstmt.setString(2, announcment.getPrice());
            pstmt.setString(3, announcment.getDescription());
            pstmt.setString(4, announcment.getTown());
            pstmt.setString(5, announcment.getCategory());
            pstmt.setString(6, announcment.getPhoneNumber());
            pstmt.setString(7, announcment.getVoivodeship());
            pstmt.setInt(8, announcment.getId());
            pstmt.executeUpdate(); // wykonaj zapytanie
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

    public boolean isFavoriteAnnouncment(User user, int announcmentID) {
        String sql = "SELECT * FROM UlubioneOgloszenie WHERE uzytkownikID = ? AND ogloszenieID = ?";
        int userID = selectUserIdByLogin(user.getLogin());
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            pstmt.setInt(2, announcmentID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }


    public List<Contact> selectFavoriteContacts() {
        String login = Settings.getInstance().getUser().getLogin();
        int pageNumber = Settings.getInstance().getPageNumber();
        int contactsNumberOnPage = Settings.getInstance().getContactsNumberOnPage();
        int userID = selectUserIdByLogin(login);

        List<Contact> favoriteContacts = new ArrayList<>();
        int startIndex = (pageNumber - 1) * contactsNumberOnPage;
        String sql = "SELECT k.* FROM Kontakt k JOIN UlubionyKontakt uk ON k.kontaktID = uk.kontaktID WHERE uk.uzytkownikID = ? LIMIT ?,?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            pstmt.setInt(2, startIndex);
            pstmt.setInt(3, contactsNumberOnPage);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("kontaktID"));
                contact.setName(rs.getString("imie"));
                contact.setSurname(rs.getString("nazwisko"));
                contact.setPhoneNumber(rs.getString("nrtelefonu"));
                contact.setEmail(rs.getString("email"));
                contact.setTown(rs.getString("miejscowosc"));
                contact.setStreet(rs.getString("ulica"));
                contact.setHouseNumber(rs.getString("nrdomu"));
                contact.setDescription(rs.getString("opis"));
                favoriteContacts.add(contact);
            }
            return favoriteContacts;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Announcment> selectFavoriteAnnouncments() {
        String login = Settings.getInstance().getUser().getLogin();
        int pageNumber = Settings.getInstance().getPageNumber();
        int announcmentsNumberOnPage = Settings.getInstance().getContactsNumberOnPage();
        int userID = selectUserIdByLogin(login);

        List<Announcment> favoriteAnnouncments = new ArrayList<>();
        int startIndex = (pageNumber - 1) * announcmentsNumberOnPage;
        String sql = "SELECT o.* FROM Ogloszenie o JOIN UlubioneOgloszenie uo ON o.ogloszenieID = uo.ogloszenieID WHERE uo.uzytkownikID = ? LIMIT ?,?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, userID);
            pstmt.setInt(2, startIndex);
            pstmt.setInt(3, announcmentsNumberOnPage);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Announcment announcment = new Announcment();
                announcment.setId(rs.getInt("ogloszenieID"));
                announcment.setTitle(rs.getString("tytul"));
                announcment.setPrice(rs.getString("cena"));
                announcment.setDescription(rs.getString("opis"));
                announcment.setTown(rs.getString("miejscowosc"));
                announcment.setCategory(rs.getString("kategoria"));
                announcment.setPhoneNumber(rs.getString("nrtelefonu"));
                announcment.setVoivodeship(rs.getString("wojewodztwo"));
                favoriteAnnouncments.add(announcment);
            }
            return favoriteAnnouncments;
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

    public void deleteAnnouncment(int announcmentID) {
        String sql = "DELETE FROM Ogloszenie WHERE ogloszenieID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, announcmentID);
            pstmt.executeUpdate();
            deleteAnnouncmentCascadeFavourites(announcmentID);
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

    private void deleteAnnouncmentCascadeFavourites(int announcmentID) {
        String sql = "DELETE FROM UlubioneOgloszenie WHERE ogloszenieID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, announcmentID);
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

    public void deleteFavouriteAnnouncment(int announcmentID) {
        String login = Settings.getInstance().getUser().getLogin();
        int userID = selectUserIdByLogin(login);

        String sql = "DELETE FROM UlubioneOgloszenie WHERE ogloszenieID = ? AND uzytkownikID = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, announcmentID);
            pstmt.setInt(2, userID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }







}
