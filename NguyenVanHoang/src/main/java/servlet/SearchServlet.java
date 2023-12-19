package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import bean.Category;
import bean.Product;
import dao.CategoryDAO;
import dao.ProductDAO;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
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
		ArrayList<Category> categoryList = CategoryDAO.getAllCategories();
		ArrayList<Product> productList = ProductDAO.getAllProducts();
		
		int perPage = ProductDAO.PERPAGE;
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		if(request.getParameter("search") != null) {
			String search = request.getParameter("search");
			session.setAttribute("search", search);
			
		}
		
		if(session.getAttribute("search") != null) {
			String search = (String) session.getAttribute("search");
			productList = ProductDAO.getAllProductWithLimitBySearch(currentPage, perPage, search);
		}
		
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("productList", productList);
		
		request.getRequestDispatcher("search.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
