<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
    <!-- Link boostrap -->
    <link rel="shortcut icon" href="https://cdn-icons-png.flaticon.com/512/628/628324.png" type="image/x-icon">
    <link rel="stylesheet" href="public/css/all.min.css">
    <link rel="stylesheet" href="public/css/bootstrap.min.css">
    <link rel="stylesheet" href="public/css/style.css">
    <link rel="stylesheet" href="public/css/login.css">
</head>
<body>
<%@include file="header.jsp" %>
	<div class="formbold-main-wrapper">
  <!-- Author: FormBold Team -->
  <!-- Learn More: https://formbold.com -->
  <div class="formbold-form-wrapper">
   

    <form action="RegisterServlet" method="POST">
      <div class="formbold-form-title">
        <h2 class="">Register now</h2>
       	<p>Already have an account. <a href="form_login.jsp">Login here!</a></p>
      </div>

      <div class="formbold-input-flex">
        <div>
          <label for="firstname" class="formbold-form-label">
            Fullname
          </label>
          <input
            type="text"
            name="fullname"
            id="fullname"
            class="formbold-form-input"
          />
        </div>
        <div>
          <label for="username" class="formbold-form-label"> Username </label>
          <input
            type="text"
            name="username"
            id="username"
            class="formbold-form-input"
          />
        </div>
      </div>

      <div class="formbold-mb-3">
        <label for="email" class="formbold-form-label">
          Email
        </label>
        <input
          type="text"
          name="email"
          id="email"
          class="formbold-form-input"
        />
      </div>

      <div class="formbold-mb-3">
        <label for="password" class="formbold-form-label">
          Password
        </label>
        <input
          type="password"
          name="password"
          id="password"
          class="formbold-form-input"
        />
      </div>
      <button type="submit" class="formbold-btn">Register Now</button>
      <a href="IndexServlet" class="formbold-btn-outline">Back</a>
    </form>
  </div>
</div>

</body>
</html>