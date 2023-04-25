package com.example.kck;

class Kontakt {
    private String name;
    private String phone;
    private String email;

    private String opis;

    public Kontakt(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.opis = "Brak opisu";
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }
}
