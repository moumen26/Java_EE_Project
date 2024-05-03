package com.example.appdest;

import com.example.appdest.Config.DBConfiguration;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/deleteCandidat")
public class DeleteCandidatServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int candidatId = Integer.parseInt(request.getParameter("id"));

        String sql = "DELETE FROM candidats WHERE id = ?";

        try (Connection con = DBConfiguration.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, candidatId);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                response.sendRedirect(request.getContextPath() + "/admin?successMessage=Candidat deleted successfully");
            } else {
                response.sendRedirect(request.getContextPath() + "/admin?errorMessage=No candidat found with ID " + candidatId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/admin?errorMessage=Database error: " + e.getMessage());
        }
    }
}
