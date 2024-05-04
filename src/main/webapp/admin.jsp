<%@ page import="com.example.appdest.Models.Candidat" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: moume
  Date: 01/05/2024
  Time: 3:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css"
          integrity="sha512-SnH5WK+bZxgPHs44uWIX+LLJAJ9/2PkPKZ5QiAj6Ta86w+fsb2TkcmfRyVX3pBnMFcV7oQPJkl9QevSCWr3W6A=="
          crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="AppDist.css">
</head>
<body>
<nav>
    <span>Voting</span>
    <div class="right-class">
        <div class="login-class">
            <span>${username}</span>
            <a href="${pageContext.request.contextPath}/logout"><i class="fa-regular fa-user"></i></a>
        </div>
    </div>
</nav>

<% if (request.getAttribute("successMessage") != null) { %>
<div style="color: green;">
    <%= request.getAttribute("successMessage") %>
</div>
<% } %>

<% if (request.getAttribute("errorMessage") != null) { %>
<div style="color: red;">
    <%= request.getAttribute("errorMessage") %>
</div>
<% } %>


<section class="vote-section">
    <div class="table">
        <div class="add-button" id="addButton">
            <i class="fa-solid fa-plus"></i>
            <span>add candidat</span>
        </div>
        <div class="table-container">
            <table>
                <tr>
                    <td class="id">ID</td>
                    <td>Name</td>
                    <td>Email</td>
                    <td>Election</td>
                    <td></td>
                </tr>
                <% List<Candidat> candidats = (List<Candidat>) request.getAttribute("candidats");
                    if (candidats != null && !candidats.isEmpty()) {
                        for (Candidat candidat : candidats) { %>
                <tr>
                    <td class="id"><%= candidat.getId() %></td>
                    <td><%= candidat.getName() %></td>
                    <td><%= candidat.getEmail() %></td>
                    <td><%= candidat.getElection() %></td>
                    <td class="button-column">
                        <div class="add-button">
                            <span>Update</span>
                        </div>
                        <form action="deleteCandidat" method="post">
                            <input type="hidden" name="id" value="<%= candidat.getId() %>">
                            <button type="submit" class="add-button"><span>Delete</span></button>
                        </form>
                    </td>
                </tr>
                <%     }
                } else { %>
                <tr>
                    <td colspan="4">No candidats found</td>
                </tr>
                <% } %>
            </table>

            <% if (request.getAttribute("errorMessage") !=null) { %>
            <p style="color: red;">Error: ${errorMessage}</p>
            <% } %>
        </div>
    </div>

</section>


<div class="add-candidat-class" id="addCandidat">
    <div class="add-candidat-class-container" style="height: 480px">
        <span>Add Candidat</span>
        <form action="addCandidat" method="post">
            <div class="candidat-form text-form">
                <label for="name">Name:</label>
                <input type="text" placeholder="Name.." name="name" id="name" required>
            </div>
            <div class="candidat-form text-form">
                <label for="email">Email:</label>
                <input type="email" placeholder="Email.." name="email" id="email" required>
            </div>
            <div class="candidat-form text-form">
                <label for="election">Election:</label>
                <input type="text" placeholder="Election.." name="election" id="election" required>
            </div>
            <div class="candidat-form form-image">
                <input type="file" name="image" id="image" class="inputfile" />
                <label for="image">Choose a image</label>
            </div>
            <input type="submit" value="Save" class="save-button">
        </form>
    </div>

</div>

<script>
    var addCandidat = document.getElementById('addCandidat');
    var addButton = document.getElementById('addButton');

    addButton.addEventListener('click', function () {
        addCandidat.classList.add('Candidat-toggle');
        console.log('candidate clicked');
    });

</script>

</body>
</html>
