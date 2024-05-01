<%--
  Created by IntelliJ IDEA.
  User: moume
  Date: 01/05/2024
  Time: 3:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Log In</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
</head>
<body>
<div class="wrapper">
    <div class="inner log-class">
        <form action="login" method="post">
            <h3>Log In</h3>
            <div class="form-wrapper">
                <input type="text" placeholder="Email Address" class="form-control" id="email" name="email" required>
                <i class="zmdi zmdi-email"></i>
            </div>
            <div class="form-wrapper">
                <input type="password" placeholder="Password" class="form-control" id="password" name="password" required>
                <i class="zmdi zmdi-lock"></i>
            </div>
            <p class="toast">${errorMessage}</p>
            <input class="button" type="submit" value="Log In">

        </form>
    </div>
</div>

</body>
</html>
