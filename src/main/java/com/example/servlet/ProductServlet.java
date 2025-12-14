package com.example.servlet;

import com.example.dao.ProductDAO;
import com.example.entity.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private final ProductDAO dao = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setAttribute("products", dao.getAll());
        req.getRequestDispatcher("products.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        Product p = new Product(
                req.getParameter("name"),
                req.getParameter("description"),
                req.getParameter("color"),
                req.getParameter("size"),
                Double.parseDouble(req.getParameter("price"))
        );

        dao.save(p);
        resp.sendRedirect("products");
    }
}
