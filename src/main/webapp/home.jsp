<%--
  Created by IntelliJ IDEA.
  User: moume
  Date: 01/05/2024
  Time: 5:28 PM
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
    <link rel="stylesheet" href="style.css">
</head>
<body>
<nav>
    <span>Voting</span>
    <div class="right-class">
        <div class="login-class">
            <i class="fa-regular fa-user"></i>
            <a href="login.jsp">Login</a>
        </div>
        <div class="register-class">
            <a href="registration.jsp">register as a voter</a>
        </div>
    </div>
</nav>

<section class="vote-section">
    <h2>Presidential</h2>
    <div class="vote-cards">
    <c:forEach items="${candidats}" var="candidat">
        <div class="vote-card">
            <div class="vote-img">
                <img src="Images/person.jpg" alt="">
            </div>
            <div class="vote-content">
                <h3>${candidat.name}</h3>
                <span>Vote for your favourite candidate</span>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quisquam, quae, quo, quas, quos
                    repudiandae
                    voluptatibus voluptatum voluptates quaerat quibusdam quia quo nihil quod.</p>
            </div>
            <input type="submit" value="Vote">
        </div>
    </c:forEach>
    </div>
</section>

</body>
</html>
