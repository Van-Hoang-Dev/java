<%@page import="bean.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail</title>
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
	<% Product product = (Product) request.getAttribute("product"); %>
    <div class="container mt-5">
        <div class="detail">
            <div class="row">
                <div class="col-md-4 border-end">
                    <img class="img-fluid" src="public/images/<% out.print(product.getImage()); %>" alt="">
                </div>
                <div class="col-md-8">
                    <div class="detail-content ms-3">
                        <h2>
                            <% out.print(product.getName()); %>
                        </h2>
                        <div class="price">
                           $<% out.print(product.getPrice()); %>.00
                        </div>
                        <div class="description">
                            <% out.print(product.getDescription()); %>
                        </div>
                        
                        
                         <form action="CartControlServlet" method="get">
                         	<input type="hidden" name="func" value="3">
            				<input type="hidden" name="id" value="<%= product.getId() %>">
        					<div class="quantity-button mt-5 d-flex">
            					<div class="quantity">
                					<button class="btn-quantity minus" type="button" onclick="decrementValue()">-</button>
                					<input class="quantity-input" type="text" name="quantity" id="quantity" value="1">
                					<button class="btn-quantity plus" type="button" onclick="incrementValue()">+</button>
            					</div>
            				<input type="submit" value="Add to cart" class="btn btn-add-to-cart">
        					</div>
    					</form>
                        
                        <hr>
                        <div class="ship">
                            <p>Free shipping on orders over $50!</p>
                            <ul>
                                <li>No-Risk Money Back Guarantee!</li>
                                <li>No Hassle Refunds</li>
                                <li>Secure Payments</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <hr>
    <%@include file="footer.jsp" %>
    
     <script>
        function incrementValue() {
            var inputElement = document.getElementById('quantity');
            var currentValue = parseInt(inputElement.value, 10);
            inputElement.value = currentValue + 1;
        }

        function decrementValue() {
            var inputElement = document.getElementById('quantity');
            var currentValue = parseInt(inputElement.value, 10);
            
            // Đảm bảo giá trị không bao giờ nhỏ hơn 1
            inputElement.value = currentValue > 1 ? currentValue - 1 : 1;
        }
    </script>
</body>
</html>