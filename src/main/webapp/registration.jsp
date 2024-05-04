<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Sign Up</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="AppDist.css">
</head>
<body>
<div class="wrapper">
	<div class="inner">
		<div class="image-holder">
			<img src="Images/vote.jpg" alt="">
		</div>
		<form class="sign-form" action="signup" method="post">
			<h3>Sign up</h3>
			<div class="form-wrapper">
				<input type="text" placeholder="Username" class="form-control" id="username" name="username" required>
				<i class="zmdi zmdi-account"></i>
			</div>
			<div class="form-wrapper">
				<input type="text" placeholder="Email Address" class="form-control" id="email" name="email" required>
				<i class="zmdi zmdi-email"></i>
			</div>
			<div class="form-wrapper">
				<input type="password" placeholder="Password" class="form-control" id="password" name="password" required>
				<i class="zmdi zmdi-lock"></i>
			</div>
			<div class="form-wrapper">
				<input type="password" placeholder="Confirm Password" class="form-control" id="confirmPassword" name="confirmPassword" required>
				<i class="zmdi zmdi-lock"></i>
			</div>
			<div class="form-wrapper">
				<input type="text" placeholder="Phone" class="form-control" id="phone" name="phone" required>
				<i class="zmdi zmdi-lock"></i>
			</div>
			<p class="toast">${errorMessage}</p>
			<input class="button" type="submit" value="Sign Up">
		</form>
	</div>
</div>

</body>
</html>
