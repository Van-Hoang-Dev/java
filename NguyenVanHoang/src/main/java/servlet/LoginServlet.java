package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import bean.Account;
import dao.AccountDAO;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		
		ArrayList<Account> accountList = AccountDAO.getAllAccount();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String mail =  request.getParameter("username");
		boolean check = false;
		String fullname = "";
		int role = 0;
		
		for(Account account : accountList) {
			if((account.getUsername().equals(username) || account.getEmail().equals(mail)) && account.getPassword().equals(password)) {
				check = true;
				session.setAttribute("role", account.getRole());
				fullname = account.getFullname();
				session.setAttribute("account", account);
			}
		}
		
		if(check == true) {
			session.setAttribute("fullname", fullname);
			if((Integer) session.getAttribute("role") == 1 && session.getAttribute("role") != null ) {
				request.getRequestDispatcher("ManageProductServlet").forward(request, response);
			}
			else {
				request.getRequestDispatcher("IndexServlet").forward(request, response);
			}
		}
		else {
			String notification = ("<script>alert('Your email or password was wrong!');</script>");
			request.setAttribute("notification", notification);
			request.getRequestDispatcher("IndexServlet").forward(request, response);
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
