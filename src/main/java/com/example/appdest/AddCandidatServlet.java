package com.example.appdest;

import com.example.appdest.Config.DBConfiguration;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
// Imports and package declaration

@WebServlet("/addCandidat")
public class AddCandidatServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String election = request.getParameter("election");

        if (name == null || name.isEmpty() || email == null || email.isEmpty() ||
                election == null || election.isEmpty()) {
            String errorMessage = "Please fill all the required fields";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String insertSql = "INSERT INTO candidats (name, email, election) VALUES (?, ?, ?)";
        String selectSql = "SELECT * FROM candidats WHERE name = ?";

        try (
                Connection con = DBConfiguration.getConnection();
                PreparedStatement statementInsert = con.prepareStatement(insertSql);
                PreparedStatement statementSelect = con.prepareStatement(selectSql);
        ) {
            statementSelect.setString(1, name);
            ResultSet selectResult = statementSelect.executeQuery();
            if (selectResult.next()) {
                String errorMessage = "Candidat already exists";
                request.setAttribute("errorMessage", errorMessage);
                RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
                dispatcher.forward(request, response);
                return;
            }

            statementInsert.setString(1, name);
            statementInsert.setString(2, email);
            statementInsert.setString(3, election);

            int rowsInserted = statementInsert.executeUpdate();

            if (rowsInserted > 0) {
                String successMessage = "Candidat added successfully!";
                request.setAttribute("successMessage", successMessage);
            } else {
                String errorMessage = "Failed to add candidat.";
                request.setAttribute("errorMessage", errorMessage);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            String errorMessage = "Database error: " + e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
        dispatcher.forward(request, response);
    }
}
