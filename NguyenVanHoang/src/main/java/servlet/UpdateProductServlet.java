package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import bean.Product;
import dao.ProductDAO;

@MultipartConfig
/**
 * Servlet implementation class UpdateProductServlet
 */
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateProductServlet() {
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
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String description = request.getParameter("description");
		String categoryID = request.getParameter("category_id");
		String imageFileName = request.getParameter("exitingImage");
		System.out.println("path: " + request.getPart("image"));
		Part file = request.getPart("image");
		
		if(file != null && file.getSize() > 0) {
		imageFileName = file.getSubmittedFileName();
		
		
		System.out.println("Selected Image File name: " + imageFileName);
		String uploadPath = request.getServletContext().getRealPath("/") + "public/images\\" + imageFileName;
		System.out.println("Upload path: " + uploadPath);
		
		//Uploading our selected image into the images folder
				try {
					FileOutputStream fos = new FileOutputStream(uploadPath);
					InputStream is = file.getInputStream();
					
					byte[] data = new byte[is.available()];
					is.read(data);
					fos.write(data);
					fos.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		System.out.println("Selected Image File name: " + imageFileName);
		
		Product product = new Product(id, name, Integer.parseInt(price), description, imageFileName, categoryID);
		if(ProductDAO.updateProduct(product)) {
			request.setAttribute("notification", "<script>alert('Update product was succesfull!');</script>");
			request.getRequestDispatcher("ManageProductServlet").forward(request, response);
			
		}
		else {
			request.setAttribute("notification", "<script>alert('Update product was failed!');</script>");
			request.getRequestDispatcher("ManageProductServlet").forward(request, response);
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
