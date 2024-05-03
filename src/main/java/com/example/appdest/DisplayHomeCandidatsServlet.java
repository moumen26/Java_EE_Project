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
        String sql = "SELECT * FROM candidats";
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            con = DBConfiguration.getConnection();
            statement = con.prepareStatement(sql);
            resultSet = statement.executeQuery();

            List<Candidat> candidats = new ArrayList<>();
            while (resultSet.next()) {
                Candidat candidat = new Candidat(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("election")
                );
                candidats.add(candidat);
            }

            if (candidats.isEmpty()) {
                request.setAttribute("error", "No candidats found");
            } else {
                request.setAttribute("home", candidats);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Database error: " + e.getMessage());
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
        RequestDispatcher dispatcherUser = request.getRequestDispatcher("user.jsp");
        dispatcher.forward(request, response);
        dispatcherUser.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Implement doPost if needed
    }
}
