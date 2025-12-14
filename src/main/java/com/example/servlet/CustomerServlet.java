package com.example.servlet;

import com.example.entity.Customer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/customers")
public class CustomerServlet extends HttpServlet {

    // Simple in-memory list of customers
    private static List<Customer> customers = new ArrayList<>();

    static {
        // Example customers
        customers.add(new Customer(1, "Jane Doe", "jane@example.com"));
        customers.add(new Customer(2, "John Smith", "john@example.com"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/WEB-INF/customers.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        if (name != null && !name.isEmpty() && email != null && !email.isEmpty()) {
            int newId = customers.size() + 1;
            customers.add(new Customer(newId, name, email));
        }

        response.sendRedirect(request.getContextPath() + "/customers");
    }
}
