package dao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Product;

public class ProductDAO {
	public static ArrayList<Product> getAllProducts(){
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "SELECT * FROM `products`";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDescription(rs.getString("description"));
				product.setImage(rs.getString("image"));
				product.setCategryId(rs.getString("category_id"));
				productList.add(product);
				
			}
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return productList;	
	}
	
	public static ArrayList<Product> getAllProductsByCategoryID(String categoryID){
		ArrayList<Product> productList = new ArrayList<Product>();
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "SELECT * FROM `products` WHERE category_id =?;";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, categoryID);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDescription(rs.getString("description"));
				product.setImage(rs.getString("image"));
				product.setCategryId(rs.getString("category_id"));
				productList.add(product);
				
			}
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return productList;	
	}
	
	public static Product getProductsByID(String id){
		Product product = new Product();
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "SELECT * FROM `products` WHERE id =?;";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDescription(rs.getString("description"));
				product.setImage(rs.getString("image"));
				product.setCategryId(rs.getString("category_id"));
				
			}
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return product;	
	}
	
	public static boolean addProduct(Product product) {
		boolean check = false;
		try {
			
			Connection con = DatabaseUtil.getConnnection();
			String sql = "INSERT INTO `products` VALUES (?,?,?,?,?,?);";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, product.getId());
			ps.setString(2, product.getName());
			ps.setInt(3, product.getPrice());
			ps.setString(4, product.getDescription());
			ps.setString(5, product.getImage());
			ps.setString(6,  product.getCategoryId());
			
			int rs = ps.executeUpdate();
			if(rs > 0) {
				check = true;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return check;
	}
	
	public static boolean updateProduct(Product product) {
		boolean check = false;
		try {
			
			Connection con = DatabaseUtil.getConnnection();
			String sql = "UPDATE `products` SET `name`=?,`price`=?,`description`=?,`image`=?,`category_id`=? WHERE `id`=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, product.getName());
			ps.setInt(2, product.getPrice());
			ps.setString(3, product.getDescription());
			ps.setString(4, product.getImage());
			ps.setString(5,  product.getCategoryId());
			ps.setString(6, product.getId());
			
			int rs = ps.executeUpdate();
			if(rs > 0) {
				check = true;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return check;
	}
	
	public static boolean deleteProduct(String id) {
		boolean check = false;
		try {
			
			Connection con = DatabaseUtil.getConnnection();
			String sql = "DELETE FROM `products` WHERE id=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			int rs = ps.executeUpdate();
			if(rs > 0) {
				check = true;
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return check;
	}
	
	public static ArrayList<Product> findProductByNameOrDescription(String key) {
		ArrayList<Product> productList = new ArrayList<Product>();
		String keyString = "%" + key + "%";
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "SELECT * FROM `products` WHERE name LIKE ? or description LIKE ?;";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, keyString);
			ps.setString(2, keyString);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDescription(rs.getString("description"));
				product.setImage(rs.getString("image"));
				product.setCategryId(rs.getString("category_id"));
				productList.add(product);
				
			}
		
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		return productList;	
	}
	
	//Pagination for Search 
	public static int getTotalBySearch(String key) {
		int total = 0;
		String keyString = "%" + key + "%";
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "SELECT COUNT(*) FROM `products` WHERE name LIKE ? or description LIKE ?;";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, keyString);
			ps.setString(2, keyString);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return total;
	}
	
	public static int OFFSET = 2;
	public  static int PERPAGE = 3;
	public static int PERPAGE_ADMIN = 5;
	
	public static ArrayList<Product> getAllProductWithLimitBySearch(int curentPage, int perPage, String key){
		//Tinh so thu tu record bat dau
		int startRecord = (curentPage -1) * perPage;
		
		//Dung LIMIT de gioi han so luong tren 1 trang
		ArrayList<Product> productList = new ArrayList<Product>();
		String keyString = "%" + key + "%";
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "SELECT * FROM `products` WHERE name LIKE ? or description LIKE ? LIMIT ?,?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, keyString);
			ps.setString(2, keyString);
			ps.setInt(3, startRecord);
			ps.setInt(4, perPage);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDescription(rs.getString("description"));
				product.setImage(rs.getString("image"));
				product.setCategryId(rs.getString("category_id"));
				productList.add(product);
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return productList;
		
	}
	
	
	public static int getTotalProduct() {
		int total = 0;
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "SELECT COUNT(*) FROM `products`";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return total;
	}
	
	public static ArrayList<Product> getProductWithLimit(int curentPage, int perPage){
		//Tinh so thu tu record bat dau
		int startRecord = (curentPage -1) * perPage;
		
		//Dung LIMIT de gioi han so luong tren 1 trang
		ArrayList<Product> productList = new ArrayList<Product>();
		
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "SELECT * FROM `products` LIMIT ?,?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, startRecord);
			ps.setInt(2, perPage);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDescription(rs.getString("description"));
				product.setImage(rs.getString("image"));
				product.setCategryId(rs.getString("category_id"));
				productList.add(product);
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return productList;
		
	}
	
	
	//pagination for Product By CatageryID
	public static int getTotalByCategoryID(String categoryID) {
		int total = 0;
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "SELECT COUNT(*) FROM `products` WHERE category_id =? ;";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, categoryID);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				total = rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return total;
	}
	
	public static ArrayList<Product> getAllProductWithLimit(int curentPage, int perPage, String categotyID){
		//Tinh so thu tu record bat dau
		int startRecord = (curentPage -1) * perPage;
		
		//Dung LIMIT de gioi han so luong tren 1 trang
		ArrayList<Product> productList = new ArrayList<Product>();
		
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "SELECT * FROM `products` WHERE category_id = ? LIMIT ?,?;";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, categotyID);
			ps.setInt(2, startRecord);
			ps.setInt(3, perPage);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Product product = new Product();
				product.setId(rs.getString("id"));
				product.setName(rs.getString("name"));
				product.setPrice(rs.getInt("price"));
				product.setDescription(rs.getString("description"));
				product.setImage(rs.getString("image"));
				product.setCategryId(rs.getString("category_id"));
				productList.add(product);
			}
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return productList;
		
	}
	
	public static String getPaginationBar(String url,int total, int page, int perPage, int offset) {
		
		if(total <= 0) {
			return "";
		}
		
		int totalLinks = (int)Math.ceil(total * 1.0/perPage);
		if(total <= 1) {
			return "";
		}
		
		String first = "";
		String last = "";
		String previous = "";
		String next = "";
		
		if(page > 1) {
			first = "<li class='page-item'><a class='page-link' href='"+url+"?page="+1+"'><<</a></li>&nbsp;&nbsp;";
			previous = "<li class='page-item'><a class='page-link' href='"+url+"?page="+(page - 1)+"'>Previous</a></li>";
		}
		
		if(page < totalLinks) {
			last = "<li class='page-item'><a class='page-link' href='"+url+"?page="+totalLinks+"'>>></a></li>&nbsp;&nbsp;";
			next = "<li class='page-item'><a class='page-link' href='"+url+"?page="+(page + 1)+"'>Next</a></li>&nbsp;&nbsp;";
		}
		
		int from = page - offset;
		int to = page + offset;
		
		if(from <= 0) {
			from = 1;
		}
		
		if(to > totalLinks) {
			to = totalLinks;
		}
		
		String link = "";
		for(int i =from; i<= to; i++) {
//			link = link + "<a href='"+url+"?page="+i+"'>"+ i +"</a>";
			link = link + "<li class='page-item'><a class='page-link "+ (i == page? "active" : "")+"' href='"+url+"?page="+i+"'>"+i+"</a></li>";
		}
		
		return first + previous +  link  + next+ last;
	}
	
	public static void main(String[] args) {
//		System.out.println(ProductDAO.getAllProducts());
//		Product p = new Product("1","Hoa hoang",3,"4","5","c001");
//		System.out.println(ProductDAO.updateProduct(p));
//		System.out.println(ProductDAO.getProductsByID("p001"));
//		System.out.println(ProductDAO.getTotal());
//		System.out.println(ProductDAO.getProductWithLimit(1, 2));
	}
}
