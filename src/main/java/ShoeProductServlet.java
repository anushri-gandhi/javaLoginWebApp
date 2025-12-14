package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@WebServlet("/shoe-product")
public class ShoeProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Show sneaker.jsp (initial page load)
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/sneaker.jsp").forward(req, resp);
    }

    // Handle form submission
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String name = req.getParameter("shoeName");
        String priceParam = req.getParameter("unitPrice");
        String qtyParam = req.getParameter("quantity");

        String error = null;
        BigDecimal unitPrice = BigDecimal.ZERO;
        int quantity = 0;

        if (name == null || name.trim().isEmpty()) {
            name = "Unnamed Shoe";
        }

        // Validate unit price
        try {
            unitPrice = new BigDecimal(priceParam).setScale(2, RoundingMode.HALF_UP);
            if (unitPrice.compareTo(BigDecimal.ZERO) <= 0) {
                error = "Enter a unit price greater than 0.";
            }
        } catch (Exception ex) {
            error = "Invalid unit price.";
        }

        // Validate quantity
        try {
            quantity = Integer.parseInt(qtyParam);
            if (quantity <= 0) {
                error = "Please enter a quantity greater than zero.";
            }
        } catch (Exception ex) {
            error = "Invalid quantity. Enter a whole number.";
        }

        if (error == null) {
            BigDecimal subtotal = unitPrice.multiply(BigDecimal.valueOf(quantity))
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal discountRate = BigDecimal.ZERO;
            if (quantity >= 10) {
                discountRate = new BigDecimal("0.10"); // 10%
            } else if (quantity >= 5) {
                discountRate = new BigDecimal("0.05"); // 5%
            }

            BigDecimal discountAmount = subtotal.multiply(discountRate)
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal total = subtotal.subtract(discountAmount)
                    .setScale(2, RoundingMode.HALF_UP);

            // ✅ MATCH JSP input field names
            req.setAttribute("shoeName", name);
            req.setAttribute("unitPrice", unitPrice.toString());
            req.setAttribute("quantity", quantity);
            req.setAttribute("subtotal", subtotal.toString());
            req.setAttribute("discountPercent", discountRate.multiply(new BigDecimal("100")).intValue());
            req.setAttribute("discountAmount", discountAmount.toString());
            req.setAttribute("total", total.toString());

        } else {
            req.setAttribute("error", error);
            // Still set the values user typed so they aren't lost
            req.setAttribute("shoeName", name);
            req.setAttribute("unitPrice", priceParam);
            req.setAttribute("quantity", qtyParam);
        }

        // Forward back to sneaker.jsp
        req.getRequestDispatcher("/WEB-INF/sneaker.jsp").forward(req, resp);
    }
}
