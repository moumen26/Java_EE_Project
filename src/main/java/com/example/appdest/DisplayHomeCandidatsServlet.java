package com.example.appdest;

import com.example.appdest.Config.DBConfiguration;
import com.example.appdest.Models.Candidat;
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
import java.util.ArrayList;
import java.util.List;


@WebServlet("/home")
public class DisplayHomeCandidatsServlet  extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sqlPresidential = "SELECT * FROM candidats WHERE election = 'Presidential'";
        String sqlSenatorial = "SELECT * FROM candidats WHERE election = 'Senatorial'";
        Connection con = null;
        PreparedStatement PresidentialStatement = null;
        ResultSet PresidentialResultSet = null;
        PreparedStatement SenatorialStatement = null;
        ResultSet SenatorialResultSet = null;

        try {
            con = DBConfiguration.getConnection();
            PresidentialStatement = con.prepareStatement(sqlPresidential);
            PresidentialResultSet = PresidentialStatement.executeQuery();

            List<Candidat> Presidentialcandidats = new ArrayList<>();
            while (PresidentialResultSet.next()) {
                Candidat candidat = new Candidat(
                        PresidentialResultSet.getInt("id"),
                        PresidentialResultSet.getString("name"),
                        PresidentialResultSet.getString("email"),
                        PresidentialResultSet.getString("election")
                );
                Presidentialcandidats.add(candidat);
            }

            if (Presidentialcandidats.isEmpty()) {
                request.setAttribute("error", "No candidats found");
            } else {
                request.setAttribute("homePresidential", Presidentialcandidats);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
        }

        try {
            con = DBConfiguration.getConnection();
            SenatorialStatement = con.prepareStatement(sqlSenatorial);
            SenatorialResultSet = SenatorialStatement.executeQuery();

            List<Candidat> Senatorialcandidats = new ArrayList<>();
            while (SenatorialResultSet.next()) {
                Candidat candidat = new Candidat(
                        SenatorialResultSet.getInt("id"),
                        SenatorialResultSet.getString("name"),
                        SenatorialResultSet.getString("email"),
                        SenatorialResultSet.getString("election")
                );
                Senatorialcandidats.add(candidat);
            }

            if (Senatorialcandidats.isEmpty()) {
                request.setAttribute("error", "No candidats found");
            } else {
                request.setAttribute("homeSenatorial", Senatorialcandidats);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Implement doPost if needed
    }
}
