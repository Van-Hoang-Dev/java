<%@page import="dao.CategoryDAO"%>
<%@page import="bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Product</title>
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
   

    <form action="AddProductServlet" method="post" enctype="multipart/form-data">
      <div class="formbold-form-title">
        <h2 class="">Add Product</h2>
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
          class="formbold-form-input"
        />
      </div>
      <div class="formbold-mb-3">
        <label for="price" class="formbold-form-label">
         	Price
        </label>
        <input
          type="number"
          name="price"
          id="price"
          class="formbold-form-input"
        />
      </div>

      <div class="formbold-mb-3">
        <label for="description" class="formbold-form-label">
          Description
        </label>
        <input
          type="text"
          name="description"
          id="description"
          class="formbold-form-input"
        />
      </div>
      <div class="formbold-mb-3">
        <label for="image" class="formbold-form-label">
         	Image
        </label>
      <img id="boxDisplayImage" alt="" src="" style="width: 200px; margin: 10px 0">
        <input
          type="file"
          name="image"
          id="image"
          class="formbold-form-input"
        />
      </div>
      
      <% ArrayList<Category> catagoryList = CategoryDAO.getAllCategories();
       	for(Category category : catagoryList){
       %>
       <div class="form-check">
  		<input class="form-check-input" type="radio" value="<% out.print(category.getId()); %>" name="category_id" id="<% out.print(category.getId()); %>">
  		<label class="form-check-label" for="<% out.print(category.getId()); %>">
    		<% out.print(category.getName()); %>
  		</label>
		</div>
        <% } %>
      <button type="submit" class="formbold-btn">Add</button>
      <a href="ManageProductServlet" class="formbold-btn-outline">Back</a>
    </form>
  </div>
</div>
	<script type="text/javascript">	
	let image = document.getElementById("image");
	let boxDisplayImage = document.getElementById("boxDisplayImage");
	console.log(image);
	
	image.addEventListener("input", (e) => {
        boxDisplayImage.src = URL.createObjectURL(e.target.files[0]);
        console.log(URL.createObjectURL(e.target.files[0]));
    })
	</script>
</body>
</html>