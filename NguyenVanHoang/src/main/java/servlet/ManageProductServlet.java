package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import bean.Product;
import dao.ProductDAO;

/**
 * Servlet implementation class ManageProductServlet
 */
public class ManageProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ManageProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();
		if(session.getAttribute("role") != null){
    		int role = (Integer) session.getAttribute("role");
    		if(role == 1)
    		{
		ArrayList<Product> productList = new ArrayList<Product>();
		String  paginationBar = "";
		String key = "";
		int total = 0;
		if(request.getParameter("search") != null) {
			key = request.getParameter("search");
			productList = ProductDAO.findProductByNameOrDescription(key);
			total = ProductDAO.getTotalBySearch(key);
		}
		else {
		
		
		int perPage = ProductDAO.PERPAGE_ADMIN;
		int currentPage = 1;
		
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		productList = ProductDAO.getProductWithLimit(currentPage,perPage);
		if(request.getParameter("page") != null){
			int number = Integer.parseInt(request.getParameter("page"));
			paginationBar =  ProductDAO.getPaginationBar("ManageProductServlet" , ProductDAO.getTotalProduct(),number,perPage, ProductDAO.OFFSET); 
		}
		else{
			paginationBar = ProductDAO.getPaginationBar("ManageProductServlet" , ProductDAO.getTotalProduct(),1,perPage, ProductDAO.OFFSET);
		}
		
			total = ProductDAO.getTotalProduct();
			request.setAttribute("paginationBar", paginationBar);
		}
		
		request.setAttribute("total", total );
		request.setAttribute("productList", productList);
		request.getRequestDispatcher("manage_product.jsp").forward(request, response);
    		}
		}
		else {
			response.getWriter().print("You are not admin!");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
