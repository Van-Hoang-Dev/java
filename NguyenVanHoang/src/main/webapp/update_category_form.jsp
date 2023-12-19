<%@page import="dao.ProductDAO"%>
<%@page import="bean.Product"%>
<%@page import="dao.CategoryDAO"%>
<%@page import="bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Category</title>
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
   
<% 	String id = request.getParameter("id");
	Category category = CategoryDAO.getCategorieByID(id); 
%>
    <form action="UpdateCategoryServlet" method="post">
      <div class="formbold-form-title">
        <h2 class="">Update Category</h2>
      </div>

      <div class="formbold-mb-3">
        <label for="id" class="formbold-form-label">
         	ID
        </label>
        <input
          type="text"
          name="id"
          id="id"
          class="formbold-form-input"
          value="<%out.print(category.getId()); %>"
          readonly="readonly"
        />
      </div>

      <div class="formbold-mb-3">
        <label for="name" class="formbold-form-label">
          Name
        </label>
        <input
          type="text"
          name="name"
          id="name"
           value="<%out.print(category.getName()); %>"
          class="formbold-form-input"
        />
      </div>
      <div class="formbold-mb-3">
        <label for="note" class="formbold-form-label">
         	Note
        </label>
        <input
          type="text"
          name="note"
          id="note"
           value="<%out.print(category.getNote()); %>"
          class="formbold-form-input"
        />
      </div>

      <button type="submit" class="formbold-btn">Update</button>
      <a href="ManageCategoryServlet" class="formbold-btn-outline">Back</a>
    </form>
  </div>
</div>

</body>
</html>