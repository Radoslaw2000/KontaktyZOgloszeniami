package com.example.kck;


public class Announcment {
    private int id;
    private String price;
    private String title;
    private String description;
    private String town;
    private String category;
    private String phonenumber;

    private  String voivodeship;

    public Announcment(){}

    public Announcment(int id, String price, String title, String description, String town, String category, String phonenumber, String voivodeship) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.description = description;
        this.town = town;
        this.category = category;
        this.phonenumber = phonenumber;
        this.voivodeship = voivodeship;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }
}