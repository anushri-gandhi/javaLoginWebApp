package com.example;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

@WebServlet("/sneaker")
public class SneakerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final BigDecimal UNIT_PRICE = new BigDecimal("79.99");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/sneaker.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String qtyParam = req.getParameter("quantity");
        String error = null;
        int quantity = 0;

        try {
            quantity = Integer.parseInt(qtyParam != null ? qtyParam.trim() : "0");
            if (quantity <= 0) {
                error = "Please enter a quantity greater than zero.";
            }
        } catch (NumberFormatException e) {
            error = "Invalid quantity. Enter a whole number.";
        }

        if (error == null) {
            BigDecimal qtyBd = new BigDecimal(quantity);
            BigDecimal totalBefore = UNIT_PRICE.multiply(qtyBd).setScale(2, RoundingMode.HALF_UP);

            BigDecimal discountRate = BigDecimal.ZERO;
            if (quantity >= 10) {
                discountRate = new BigDecimal("0.10"); // 10%
            } else if (quantity >= 5) {
                discountRate = new BigDecimal("0.05"); // 5%
            }

            BigDecimal discountAmount = totalBefore.multiply(discountRate).setScale(2, RoundingMode.HALF_UP);
            BigDecimal totalAfter = totalBefore.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);

            req.setAttribute("unitPrice", UNIT_PRICE.setScale(2, RoundingMode.HALF_UP).toString());
            req.setAttribute("quantity", quantity);
            req.setAttribute("totalBefore", totalBefore.toString());
            req.setAttribute("discountRatePercent", discountRate.multiply(new BigDecimal("100")).intValue());
            req.setAttribute("discountAmount", discountAmount.toString());
            req.setAttribute("totalAfter", totalAfter.toString());
        } else {
            req.setAttribute("error", error);
        }

        req.getRequestDispatcher("/sneaker.jsp").forward(req, resp);
    }
}
