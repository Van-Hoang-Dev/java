<%@page import="java.util.Map"%>
<%@page import="java.security.KeyStore.Entry"%>
<%@page import="bean.Product"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart</title>
    <link rel="shortcut icon" href="https://cdn-icons-png.flaticon.com/512/628/628324.png" type="image/x-icon">
    <link rel="stylesheet" href="public/css/all.min.css">
    <link rel="stylesheet" href="public/css/bootstrap.min.css">
    <link rel="stylesheet" href="public/css/style.css">
    <link rel="stylesheet" href="public/css/login.css">
    <style type="text/css">
    	.cart {
    background-color: #f7f7f7;
}
	table{
		text-align: center;
	}
    </style>
</head>
<body>
<%@include file="header.jsp" %>
 <div class="container">
        <div class="cart">
            <div class="container py-3">
                <div class="row">
                    <div class="col-md-8">
                        <table class="table">
                            <thead>
                                <tr>
                                    <td>Image</td>
                                    <td>Name</td>
                                    <td>Price</td>
                                    <td>Quantity</td>
                                    <td>Action</td>
                                </tr>
                            </thead>
    
                            <tbody>
		<%
			HashMap<String, Product> cart =  (HashMap<String, Product>) session.getAttribute("cart");
			int total = 0;
			if(cart != null){
			for(Map.Entry<String, Product> entry : cart.entrySet()){
				total = total + entry.getValue().getPrice() * entry.getValue().getQuantity();
		%>
			<tr>
			<td><a href="DetailServlet?id=<% out.print(entry.getValue().getId()); %>"> <img src="public/images/<% out.print(entry.getValue().getImage()); %>" style="width: 150px"></a> </td>
			<td><%out.print(entry.getValue().getName()); %></td>
			<td>$<%out.print(entry.getValue().getPrice());%>.00</td>
			<td><%out.print(entry.getValue().getQuantity()); %></td>
			<td>
				<a class="btn btn-outline-primary" href="CartControlServlet?func=1&id=<% out.print(entry.getValue().getId());%>"><i class="fa-solid fa-plus"></i></a>
				<a class="btn btn-outline-primary" href="CartControlServlet?func=1&id_sub=<% out.print(entry.getValue().getId());%>"><i class="fa-solid fa-minus"></i></a>
				<a class="btn btn-outline-primary" href="CartControlServlet?id_remove=<%out.print(entry.getValue().getId()); %>">Delete</a>
			</td>
			</tr>
		<%
			}
		}
		%>
		</tbody>
                        </table>
                    </div>
                    <div class="col-md-4">
                        <table class="table">
                            <thead>
                                <tr>
                                    <td>Cart Totals</td>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td class="pt-5">Total: $<%out.print(total); %>.00</td>
                                </tr>
                                <tr>
                                    <td class="pt-5"><button type="button" class="btn btn-success">Check out</button></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

            </div>
        </div>
    </div>



<%@include file="footer.jsp" %>
</body>
</html>