package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.HashMap;

import bean.Product;
import dao.ProductDAO;

/**
 * Servlet implementation class CartControlServlet
 */
public class CartControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CartControlServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		HashMap<String, Product> cart = new HashMap<String, Product>();
		Product product = new Product();
		String id = "";

		// Luu so luong san pham trong do
		int quantity = 0;

		// Gan lai so luong neu co
		if (session.getAttribute("quantity") != null) {
			quantity = (Integer) session.getAttribute("quantity");
		}

		// Them san pham vao do hang
		if (request.getParameter("id") != null) {
			id = request.getParameter("id");
			if (session.getAttribute("cart") == null) {
				session.setAttribute("cart", cart);
				try {
					product = ProductDAO.getProductsByID(id);
					cart.put(id, product);

					// Lay san pham trong do
					if (request.getParameter("quantity") != null) {
						int quantityProduct = Integer.parseInt(request.getParameter("quantity"));
						quantity = quantity + quantityProduct;
						product.setQuantity(product.getQuantity() + quantityProduct);
					} else {
						product.setQuantity(product.getQuantity() + 1);
						quantity++;
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			} else {
				cart = (HashMap<String, Product>) session.getAttribute("cart");
				if (cart.containsKey(id)) {

					product = cart.get(id);
					if (request.getParameter("quantity") != null) {
						int quantityProduct = Integer.parseInt(request.getParameter("quantity"));
						quantity = quantity + quantityProduct;
						product.setQuantity(product.getQuantity() + quantityProduct);
					} else {
						product.setQuantity(product.getQuantity() + 1);
						quantity++;
					}
//					product.setQuantity(product.getQuantity() + 1);
//					quantity++;
				} else {
					try {
						product = ProductDAO.getProductsByID(id);
						cart.put(id, product);
						if (request.getParameter("quantity") != null) {
							int quantityProduct = Integer.parseInt(request.getParameter("quantity"));
							quantity = quantity + quantityProduct;
							product.setQuantity(product.getQuantity() + quantityProduct);
						} else {
							product.setQuantity(product.getQuantity() + 1);
							quantity++;
						}
//						product.setQuantity(product.getQuantity() + 1);
//						quantity++;

					} catch (Exception e) {
						System.out.println(e);
					}
				}
			}
			session.setAttribute("quantity", quantity);
			if (request.getParameter("func").equals("1")) {
				response.sendRedirect("cart.jsp");
			} else if (request.getParameter("func").equals("3")) {
				response.sendRedirect("DetailServlet?id="+ id);
			} else {

				if(request.getParameter("page") != null) {
					String page = request.getParameter("page");
					response.sendRedirect("IndexServlet?page="+ page);
				}
				else {
					response.sendRedirect("IndexServlet");
				}
				
			}

		}

		if (request.getParameter("id_sub") != null) {
			String id_sub = request.getParameter("id_sub");
			cart = (HashMap<String, Product>) session.getAttribute("cart");
			if (cart != null && cart.containsKey(id_sub)) {
				Product productDelete = cart.get(id_sub);
				System.out.println(productDelete);
				if (productDelete.getQuantity() == 1) {
					cart.remove(id_sub);
				}
				productDelete.setQuantity(productDelete.getQuantity() - 1);
				quantity--;
				session.setAttribute("quantity", quantity);
				response.sendRedirect("cart.jsp");
			}
		}

		// Xoa san pham trong do
		if (request.getParameter("id_remove") != null) {
			String idRemove = request.getParameter("id_remove");
			System.out.println(idRemove);
			cart = (HashMap<String, Product>) session.getAttribute("cart");
			if (cart != null && cart.containsKey(idRemove)) {
				Product productRemove = cart.get(idRemove);

				cart.remove(idRemove);
				quantity = quantity - productRemove.getQuantity();
			}
			session.setAttribute("quantity", quantity);
			response.sendRedirect("cart.jsp");
		}

//		session.setAttribute("quantity", quantity);
//		response.sendRedirect("IndexServlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
