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
<title>Search</title>
<!-- Link boostrap -->
<link rel="shortcut icon"
	href="https://cdn-icons-png.flaticon.com/512/628/628324.png"
	type="image/x-icon">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<!-- Link icon -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">
<link rel="stylesheet" href="public/css/style.css">
</head>
<body>
<%@include file="header.jsp" %>

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
                <li><a href="IndexServlet?category_id=<% out.print(category.getId()); %>"><% out.print(category.getName()); %></a></li>
                <%
                }
                %>
              </ul>
            </div>
          </div>
          <div class="col-md-9">
          <p>Showing all <% out.print(ProductDAO.getTotalBySearch((String)session.getAttribute("search"))); %> results</p>
            <div class="row ms-3 mt-5">
            <% ArrayList<Product> productList = (ArrayList<Product>) request.getAttribute("productList");
               	for(Product product : productList){
               		
               %>
              <div class="col-4">
                <div class="item">
                  <div class="item-image">
                    <a href="DetailServlet?id=<% out.print(product.getId()); %>"><img class="img-fluid" src="public/images/<%out.print(product.getImage()); %>" alt=""></a>
                    <button type="button" class="btn-add-to-cart btn btn-success" data-bs-toggle="tooltip" data-bs-placement="left" data-bs-title="Add to cart">
                      <i class="fa-solid fa-bag-shopping"></i>
                    </button>
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
				if(session.getAttribute("search") != null)
				{
					
					String key = (String) session.getAttribute("search");
					if(request.getParameter("page") != null){
						int number = Integer.parseInt(request.getParameter("page"));
						out.print(ProductDAO.getPaginationBar("SearchServlet" , ProductDAO.getTotalBySearch(key),number,ProductDAO.PERPAGE, ProductDAO.OFFSET)); 
				
					}
					else{
						out.print(ProductDAO.getPaginationBar("SearchServlet" , ProductDAO.getTotalBySearch(key),1,ProductDAO.PERPAGE, ProductDAO.OFFSET));
					}
				}
				%>
				</ul>
				</div>
              
              
            </div>
          </div>
        </div>
      </div>
    </div>


<%@include file="footer.jsp"%>
</body>
</html>