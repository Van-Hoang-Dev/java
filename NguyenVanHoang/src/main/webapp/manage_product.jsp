<%@page import="dao.ProductDAO"%>
<%@page import="bean.Product"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Prodyct Manage</title>
    <!-- Link boostrap -->
    <link rel="shortcut icon" href="https://cdn-icons-png.flaticon.com/512/628/628324.png" type="image/x-icon">
    <link rel="stylesheet" href="public/css/all.min.css">
    <link rel="stylesheet" href="public/css/bootstrap.min.css">
    <link rel="stylesheet" href="public/css/style.css">
    <link rel="stylesheet" href="public/css/login.css">
    <style type="text/css">
    	table td, table th{
	 		vertical-align: middle;
	 		text-align: center;
		}
    
    </style>
</head>
<body>
	<%@include file="header.jsp" %>
<div class="container py-3">

	<form class="d-flex my-5" action="ManageProductServlet">
        <input class="form-control me-2" type="search" placeholder="Search" name="search" aria-label="Search" style="width: 300px">
        <button class="btn btn-outline-success" type="submit">Search</button>
      </form>

	<h2 style="text-align: center; margin-bottom: 30px;">Manage Product</h2>
	<div class="row">
		<div class="col-6">
	 		<p>Showing all <% out.print(request.getAttribute("total")); %> results</p>
		
		</div>
		<div class="col-6">
			 <a class="btn btn-outline-success mb-3 me-auto" href="add_product_form.jsp" style="margin-left: 300px"><i class="fa-solid fa-plus"></i> Add Product</a>
		</div>
	</div>
	<table class="table table-hover">
	<thead>
		<tr>
			<th>Image</th>
			<th>Id</th>
			<th>Name</th>
			<th>Price</th>
			<th>Description</th>
			<th>Catagory id</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
	</thead>
	<tbody>
			<%
			ArrayList<Product> productList = (ArrayList<Product>) request.getAttribute("productList");
			for(Product product : productList){
			%>
				<tr>
				<td><img src="public/images/<% out.print(product.getImage()); %>" style="width: 100px; margin: 10px;"> </td>
				<td><% out.print(product.getId()); %> </td>
				<td><p><% out.print(product.getName()); %></p></td>
				<td><% out.print(product.getPrice()); %> </td>
				<td><textarea style="border: 1px solid #ccc; line-height: 25px" rows="2" cols="30"><% out.print(product.getDescription()); %></textarea></td>
				<td><% out.print(product.getCategoryId()); %> </td>
				<td><a class="btn btn-outline-primary" href="update_product_form.jsp?id=<% out.print(product.getId()); %>"><i class="fa-regular fa-pen-to-square"></i></a></td>
				<td><a class="btn btn-outline-danger" onclick="javascript: return confirm('Do you want to delete?')" href="DeleteProductServlet?id=<% out.print(product.getId()); %>"><i class="fa-solid fa-trash-can"></i></a></td>
				</tr>
				
			
			<% } %>
			
	</tbody>
		
	</table>
	
	 <!-- Pagination -->
	 <% if(request.getAttribute("paginationBar") != null){ %>
    			<div class="number-page">
				  <ul class="pagination">
				<% 
					out.print(request.getAttribute("paginationBar"));
				%>
				</ul>
				</div>
	<%} else{ %>
			<a href="ManageProductServlet" class="btn btn-outline-success"><i class="fa-solid fa-border-all"></i> All Product</a>
	<%} %>
</div>


${notification}
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>

</body>
</html>