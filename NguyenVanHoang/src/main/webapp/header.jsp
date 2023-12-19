<header>
        <nav class="navbar navbar-expand-lg bg-body py-4">
            <div class="container-fluid">
              <a class="navbar-brand" href="#"><img src="public/images/logo.png" alt=""></a>
              <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
              </button>
              <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                <%
                	if(session.getAttribute("role") != null){
                		int role = (Integer) session.getAttribute("role");
                		if(role == 1)
                		{
                %>
                	<li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="IndexServlet?category_id=0">Home</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="ManageCategoryServlet">Manage Category</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="ManageProductServlet">Manage Product</a>
                  </li>
                  <li class="nav-item">
                    <a  onclick="javascript: return confirm('Do you want to logout?')" class="nav-link" href="LogoutServlet"><i class="fa-solid fa-right-from-bracket"></i></a>
                    </li>                			
                <%			
                		}
                		else if(role == 2){
                %>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="IndexServlet?category_id=0">Home</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="IndexServlet">Shop</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">About Us</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">Testimonials</a>
                  </li>
                  
                  <li class="nav-item">
                    <div class="cart-box">
                      <a class="nav-link icon-header" href="cart.jsp">
                        <i class="fa-solid fa-bag-shopping"></i>
                      </a>
                      <span class="cart-count"><% if(session.getAttribute("quantity") != null){
                    	  out.print(session.getAttribute("quantity"));
                    	  }
                      else{
                    	  out.print("0");
                      }
                    	  
                    	  %></span>
                    </div>
                  </li>
                  <li class="nav-item">
                    <a  onclick="javascript: return confirm('Do you want to logout?')" class="nav-link" href="LogoutServlet"><i class="fa-solid fa-right-from-bracket"></i></a>
                    </li>    
                <%
                		}
                	}
                else{
                %>
                
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="IndexServlet?category_id=0">Home</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="IndexServlet">Shop</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">About Us</a>
                  </li>
                  <li class="nav-item">
                    <a class="nav-link" href="#">Testimonials</a>
                  </li>
                  
                  <li class="nav-item">
                    <div class="cart-box">
                      <a class="nav-link icon-header" href="cart.jsp">
                        <i class="fa-solid fa-bag-shopping"></i>
                      </a>
                      <span class="cart-count"><% if(session.getAttribute("quantity") != null){
                    	  out.print(session.getAttribute("quantity"));
                    	  }
                      else{
                    	  out.print("0");
                      }
                    	  
                    	  %></span>
                    </div>
                  </li>
                  <li class="nav-item">
                     <a class="nav-link icon-header" href="form_login.jsp">
                        <i class="fa-regular fa-user"></i>
                    </a>
                </li>
                
                <%
                }
                %>
                  
                </ul>
              </div>
            </div>
          </nav>
    </header>