package com.example.entity;

public class Product {

    private int id;
    private String name;
    private String description;
    private String color;
    private String size;
    private double price;

    // Constructor for creating new products (no ID yet)
    public Product(String name, String description, String color, String size, double price) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    // Constructor for loading existing products from DB (with ID)
    public Product(int id, String name, String description, String color, String size, double price) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    public int getProductId() {
        return id;
    }

    // ✅ REQUIRED by JSP + DAO + Servlets
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Other getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
