package com.example.dao;

import com.example.entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public void ensureTable() {
        String sql = """
            CREATE TABLE product (
                product_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                product_name VARCHAR(100),
                product_description VARCHAR(255),
                product_color VARCHAR(50),
                product_size VARCHAR(20),
                product_price DOUBLE
            )
        """;

        try (Connection c = DB.getConnection();
             Statement s = c.createStatement()) {
            s.executeUpdate(sql);
        } catch (SQLException ignored) {}
    }

    public void save(Product p) {
        ensureTable();
        String sql = """
            INSERT INTO product
            (product_name, product_description, product_color, product_size, product_price)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection c = DB.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getName());
            ps.setString(2, p.getDescription());
            ps.setString(3, p.getColor());
            ps.setString(4, p.getSize());
            ps.setDouble(5, p.getPrice());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAll() {
        ensureTable();
        List<Product> list = new ArrayList<>();

        try (Connection c = DB.getConnection();
             Statement s = c.createStatement();
             ResultSet rs = s.executeQuery("SELECT * FROM product")) {

            while (rs.next()) {
                list.add(new Product(
                        rs.getInt("product_id"),
                        rs.getString("product_name"),
                        rs.getString("product_description"),
                        rs.getString("product_color"),
                        rs.getString("product_size"),
                        rs.getDouble("product_price")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

    public void delete(int id) {
        try (Connection c = DB.getConnection();
             PreparedStatement ps =
                     c.prepareStatement("DELETE FROM product WHERE product_id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
