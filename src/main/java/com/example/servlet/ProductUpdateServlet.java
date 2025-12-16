package com.example.servlet;

import com.example.dao.ProductDAO;
import com.example.entity.Product;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/updateProduct")
public class ProductUpdateServlet extends HttpServlet {

    private final ProductDAO dao = new ProductDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
        Product p = dao.getById(id);

        req.setAttribute("product", p);
        req.getRequestDispatcher("/WEB-INF/product_update.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        int id = Integer.parseInt(req.getParameter("id"));

        Product p = new Product(
                id,
                req.getParameter("name"),
                req.getParameter("description"),
                req.getParameter("color"),
                req.getParameter("size"),
                Double.parseDouble(req.getParameter("price"))
        );

        dao.update(p);
        resp.sendRedirect("products");
    }
}
