<%@page import="bean.Category"%>
<%@page import="bean.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category Manage</title>
    <!-- Link boostrap -->
    <link rel="shortcut icon" href="https://cdn-icons-png.flaticon.com/512/628/628324.png" type="image/x-icon">
    <link rel="stylesheet" href="public/css/all.min.css">
    <link rel="stylesheet" href="public/css/bootstrap.min.css">
    <link rel="stylesheet" href="public/css/style.css">
    <link rel="stylesheet" href="public/css/login.css">
    <style type="text/css">
   	
    
    
    </style>
</head>
<body>
	<%@include file="header.jsp" %>
<div class="container py-5">
<h2 style="text-align: center; margin-bottom: 30px;">Manage Category</h2>
	<a class="btn btn-outline-success my-3" href="add_category_form.jsp"><i class="fa-solid fa-plus"></i> Add category</a>
	<table class="table table-hover">
	<thead>
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Note</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
	</thead>
	<tbody>
			<%
			ArrayList<Category> categoryList = (ArrayList<Category>) request.getAttribute("categoryList");
			for(Category category : categoryList){
			%>
				<tr>
				<td><% out.print(category.getId()); %> </td>
				<td><% out.print(category.getName()); %> </td>
				<td><% out.print(category.getNote()); %> </td>
				<td><a class="btn btn-outline-primary" href="update_category_form.jsp?id=<% out.print(category.getId()); %>"><i class="fa-regular fa-pen-to-square"></i></a></td>
				<td><a class="btn btn-outline-danger" onclick="javascript: return confirm('Do you want to delete?')" href="DeleteCategoryServlet?id=<% out.print(category.getId()); %>"><i class="fa-solid fa-trash-can"></i></a></td>
				</tr>
				
			
			<% } %>
			
	</tbody>
		
	</table>
</div>
${notification}
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</body>
</html>