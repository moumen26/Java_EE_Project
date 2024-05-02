package com.example.appdest;

import com.example.appdest.Config.DBConfiguration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.xml.ws.http.HTTPException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String phone = request.getParameter("phone");


        if (username == null || username.isEmpty() || email == null || email.isEmpty() ||
                password == null || password.isEmpty() || confirmPassword == null || confirmPassword.isEmpty() || phone == null || phone.isEmpty()) {
            String errorMessage = "Please fill all the required fields";
            response.getWriter().println(errorMessage);
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

        if (!password.equals(confirmPassword)) {
            String errorMessage = "Passwords do not match";
            response.getWriter().println(errorMessage);
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

        String InsertSql = "INSERT INTO users (username, email, password, phone) VALUES (?, ?, ?, ?)";
        String SelectSql = "SELECT * FROM users WHERE email = ?";

        try (
                Connection con = DBConfiguration.getConnection();
                PreparedStatement statementInsert = con.prepareStatement(InsertSql);
                PreparedStatement statementSelect = con.prepareStatement(SelectSql);
        ) {
            statementSelect.setString(1, email);
            ResultSet selectResult = statementSelect.executeQuery();
            if (selectResult.next()) {
                String errorMessage = "Email already exists";
                request.setAttribute("errorMessage", errorMessage);
                response.getWriter().println(errorMessage);
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                System.out.println(errorMessage);
                return;
            }

            statementInsert.setString(1, username);
            statementInsert.setString(2, email);
            statementInsert.setString(3, password);
            statementInsert.setString(4, phone);

            int rowsInserted = statementInsert.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Received signup request:");
                System.out.println("Username: " + username);
                System.out.println("Email: " + email);
                System.out.println("Phone: " + phone);
                System.out.println("Role: " + phone);
                request.setAttribute("status", "success");
                request.setAttribute("message", "You have successfully registered!");
                response.sendRedirect(request.getContextPath() + "/login");
            } else {
                System.out.println("Failed to insert user data.");
                response.getWriter().println("Failed to insert user data.");
                request.setAttribute("status", "failed");
                request.setAttribute("message", "Something went wrong!");
                response.sendRedirect(request.getContextPath() + "/signup");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Database error: " + e.getMessage());
            response.getWriter().println("Database error: " + e.getMessage());
        }
    }
}