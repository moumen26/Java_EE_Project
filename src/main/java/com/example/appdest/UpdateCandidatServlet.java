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
import java.sql.SQLException;

@WebServlet("/updateCandidat")
public class UpdateCandidatServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the form
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("UpdateName");
        String email = request.getParameter("UpdateEmail");
        String election = request.getParameter("UpdateElection");

        if (name == null || name.isEmpty() || email == null || email.isEmpty() ||
                election == null || election.isEmpty()) {
            String errorMessage = "Please fill all the required fields";
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("admin.jsp");
            dispatcher.forward(request, response);
            return;
        }

        // Update SQL statement
        String updateSql = "UPDATE candidats SET name=?, email=?, election=? WHERE id=?";

        try (
                Connection con = DBConfiguration.getConnection();
                PreparedStatement statement = con.prepareStatement(updateSql);
        ) {
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, election);
            statement.setInt(4, id);

            // Execute the update query
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                // Successful update
                String successMessage = "Candidat update successfully!";
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

        response.sendRedirect(request.getContextPath() + "/candidats");
    }
}
