package com.example.entity;

public class Product {
    private int productId;
    private String name;
    private String description;
    private String color;
    private String size;
    private double price;

    public Product() {}

    public Product(int productId, String name, String description,
                   String color, String size, double price) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    public Product(String name, String description,
                   String color, String size, double price) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.size = size;
        this.price = price;
    }

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getColor() { return color; }
    public String getSize() { return size; }
    public double getPrice() { return price; }
}
