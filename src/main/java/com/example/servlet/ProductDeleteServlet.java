package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import com.example.dao.ProductDAO;

@WebServlet("/deleteProduct")
public class ProductDeleteServlet extends HttpServlet {
    private ProductDAO dao = new ProductDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Delete product by ID
        int productId = Integer.parseInt(req.getParameter("id"));
        dao.delete(productId);

        // Redirect back to products page
        resp.sendRedirect(req.getContextPath() + "/products");
    }
}
