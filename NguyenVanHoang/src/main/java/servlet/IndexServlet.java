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
 * Servlet implementation class IndexServlet
 */
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		HttpSession session = request.getSession();
		ArrayList<Category> categoryList = CategoryDAO.getAllCategories();
		ArrayList<Product> productList = ProductDAO.getAllProducts();
		String paginationBar  = "";
		
		int perPage = ProductDAO.PERPAGE;
		int currentPage = 1;
		if(request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}
		
		
		if(request.getParameter("category_id") != null) {
			String categoryId = request.getParameter("category_id");
			session.setAttribute("category_id", categoryId);
		}
		
		if(session.getAttribute("category_id") != null) {
			String categoryId = (String) session.getAttribute("category_id");
			productList = ProductDAO.getAllProductWithLimit(currentPage, perPage, categoryId );
			if(request.getParameter("page") != null){
				int number = Integer.parseInt(request.getParameter("page"));
				paginationBar =  ProductDAO.getPaginationBar("IndexServlet" , ProductDAO.getTotalByCategoryID(categoryId),number,ProductDAO.PERPAGE, ProductDAO.OFFSET); 
		
			}
			else{
				paginationBar = ProductDAO.getPaginationBar("IndexServlet" , ProductDAO.getTotalByCategoryID(categoryId),1,ProductDAO.PERPAGE, ProductDAO.OFFSET);
			}
		}
		
		if(request.getParameter("category_id") != null && request.getParameter("category_id").equals("0")) {
			session.removeAttribute("category_id");
		}
		
		if(session.getAttribute("category_id") == null) {
			productList = ProductDAO.getProductWithLimit(currentPage,perPage);
			if(request.getParameter("page") != null){
				int number = Integer.parseInt(request.getParameter("page"));
				paginationBar =  ProductDAO.getPaginationBar("IndexServlet" , ProductDAO.getTotalProduct(),number,ProductDAO.PERPAGE, ProductDAO.OFFSET); 
		
			}
			else{
				paginationBar = ProductDAO.getPaginationBar("IndexServlet" , ProductDAO.getTotalProduct(),1,ProductDAO.PERPAGE, ProductDAO.OFFSET);
			}
		}
		
		if(session.getAttribute("category_id") != null) {
			String categoryId = (String) session.getAttribute("category_id");
		for(Category category : categoryList) {
			if(category.getId().equals(categoryId)) {
				request.setAttribute("category_name", category.getName());
			}
		}
		}
		else {
			request.setAttribute("category_name", "All");
		}
		
		request.setAttribute("page", currentPage);
		request.setAttribute("categoryList", categoryList);
		request.setAttribute("productList", productList);
		request.setAttribute("paginationBar", paginationBar);
		
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
