<%@page import="dao.ProductDAO"%>
<%@page import="bean.Product"%>
<%@page import="bean.Category"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Shop</title>
    <!-- Link boostrap -->
    <link rel="shortcut icon" href="https://cdn-icons-png.flaticon.com/512/628/628324.png" type="image/x-icon">
    <link rel="stylesheet" href="public/css/all.min.css">
    <link rel="stylesheet" href="public/css/bootstrap.min.css">
    <link rel="stylesheet" href="public/css/style.css">
    <link rel="stylesheet" href="public/css/login.css">
<style>
.page-item .active{
	background: #39A7FF;
}

.categories .clicked{
	color: #00ab84;
    border-bottom: 2px solid #009170;
}

.categories .count {
	float: right;
}


</style>
</head>
<body>
<%@include file="header.jsp" %>
<div class="container my-3">
     <%
                  	if(session.getAttribute("fullname") != null){
                  %>
                  <h4>Welcome <%out.print(session.getAttribute("fullname")); %>!</h4>
                  <%
                  } 
                  %>
    </div>

    <div class="content mt-5">
      <div class="container">
        <div class="row">
          <div class="col-md-3 border-end">
            <%@include file="searchmodel.jsp" %>
            <div class="categories">
              <h2>Categories</h2>
              <ul>
              
              <% ArrayList<Category> categoryList = (ArrayList<Category>) request.getAttribute("categoryList");
               	for(Category category : categoryList){
               		
               %>
                <li><a href="IndexServlet?category_id=<% out.print(category.getId()); %>" class="<% if(session.getAttribute("category_id") != null){out.print((((String)session.getAttribute("category_id")).equals(category.getId())?"clicked":""));} %>"><% out.print(category.getName() + " "); %> <span class="count">(<% out.print(ProductDAO.getTotalByCategoryID(category.getId())); %>)</span></a></li>
                <%
                }
                %>
              </ul>
            </div>
          </div>
          <div class="col-md-9">
          <h2 style="margin-bottom: 20px; color: #00ab84; font-size: 40px;"><%out.print(request.getAttribute("category_name")); %></h2>
          <%if(session.getAttribute("category_id") != null ){ %>
          <p>Showing all <% out.print(ProductDAO.getTotalByCategoryID((String)session.getAttribute("category_id"))); %> results</p>
          <%} else{ %>
          <p>Showing all <% out.print(ProductDAO.getTotalProduct()); %> results</p>
          <%} %>
            <div class="row ms-3 mt-5">
            
            <% ArrayList<Product> productList = (ArrayList<Product>) request.getAttribute("productList");
               	for(Product product : productList){
               		
               %>
              <div class="col-4">
                <div class="item">
                  <div class="item-image">
                    <a href="DetailServlet?id=<% out.print(product.getId()); %>"><img class="img-fluid" src="public/images/<%out.print(product.getImage()); %>" alt=""></a>
                    <a href="CartControlServlet?func=2&page=<% out.print(request.getAttribute("page")!= null ? request.getAttribute("page") : "" ); %>&id=<% out.print(product.getId()); %>">
                    <button type="button" class="btn-add-to-cart btn btn-success" data-bs-toggle="tooltip" data-bs-placement="left" data-bs-title="Add to cart">
                      <i class="fa-solid fa-bag-shopping"></i>
                    </button>
                    </a>
                  </div>
                  <div class="item-content">
                    <h2><a href="DetailServlet?id=<% out.print(product.getId()); %>"><% out.print(product.getName()); %></a></h2>
                    <div class="rate">
                      <i class="fa-regular fa-star"></i>
                      <i class="fa-regular fa-star"></i>
                      <i class="fa-regular fa-star"></i>
                      <i class="fa-regular fa-star"></i>
                      <i class="fa-regular fa-star"></i>
                    </div>
                    <div class="price">$<% out.print(product.getPrice()); %>.00</div>
                  </div>
                </div>
              </div>
              
              <% } %>
              
              <!-- Pagination -->
    			<div class="number-page">
				  <ul class="pagination">
				<% 
					out.print(request.getAttribute("paginationBar"));
				%>
				</ul>
				</div>
              
              
            </div>
          </div>
        </div>
      </div>
    </div>
    

    <footer>
      
      <div class="footer-content mt-5">
        <div class="container">
          <div class="row">
            <div class="col-md-3">
              <h2>Order & Purchases</h2>
              <ul class="content-list">
                <li><a href="#">Check Order Status</a></li>
                <li><a href="#">Shipping, Delivery</a></li>
                <li><a href="#">Returns</a></li>
                <li><a href="#">Product Recalls</a></li>
                <li><a href="#">Gift Cards</a></li>
              </ul>
            </div>
            <div class="col-md-3">
              <h2>My Account</h2>
              <ul class="content-list">
                <li><a href="#">Login/Register</a></li>
                <li><a href="#">Order History</a></li>
                <li><a href="#">Returns History</a></li>
                <li><a href="#">Address Book</a></li>
                <li><a href="#">Wish Lists</a></li>
              </ul>
            </div>
            <div class="col-md-3">
              <h2>About Us</h2>
              <ul class="content-list">
                <li><a href="#">About Target</a></li>
                <li><a href="#">Careers</a></li>
                <li><a href="#">News & Blog</a></li>
                <li><a href="#">Target Brands</a></li>
                <li><a href="#">Eleeg Shop</a></li>
              </ul>
            </div>
            <div class="col-md-3">
              <h2>Follow Us</h2>
              <ul class="content-list">
                <li><a href="#">Facebook</a></li>
                <li><a href="#">Instagram</a></li>
                <li><a href="#">Pinterest</a></li>
                <li><a href="#">Youtube</a></li>
              </ul>
            </div>
          </div>
          <div class="footer-copy">
             <div class="row">
              <div class="col-md-6">
                <div class="copy-right">Copyright © 2023 Plant Shop</div>
              </div>
              <!-- <div class="col-md-6">
                <div class="payment">
                  <img src="public/images/payment.png" alt="payment">
                </div>
              </div> -->
             </div>
          </div>
        </div>
      </div>
    </footer>
	${notification}
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
  </body>
</html>