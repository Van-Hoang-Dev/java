package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Category;
import bean.Product;

public class CategoryDAO {
	public static ArrayList<Category> getAllCategories() {
		ArrayList<Category> categoryList = new ArrayList<Category>();
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "SELECT * FROM `categories`";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Category category = new Category();
				category.setId(rs.getString("id"));
				category.setName(rs.getString("name"));
				category.setNote(rs.getString("note"));
				categoryList.add(category);
			}
		
		} catch (Exception e) {
			System.out.println(e);
		}
		return categoryList;
	}
	
	public static Category getCategorieByID(String id) {
		Category category = new Category();
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "SELECT * FROM `categories` WHERE id=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				category.setId(rs.getString("id"));
				category.setName(rs.getString("name"));
				category.setNote(rs.getString("note"));
			}
		
		} catch (Exception e) {
			System.out.println(e);
		}
		return category;
	}
	
	public static boolean addCategory(Category category) {
		boolean check = false;
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "INSERT INTO `categories` VALUES (?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,category.getId());
			ps.setString(2,category.getName());
			ps.setString(3,category.getNote());
			
			int rs = ps.executeUpdate();
			if(rs > 0) {
				check = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	public static boolean updateCategory(Category category) {
		boolean check = false;
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "UPDATE `categories` SET `name`=?,`note`=? WHERE id= ?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,category.getName());
			ps.setString(2,category.getNote());
			ps.setString(3,category.getId());
			
			int rs = ps.executeUpdate();
			if(rs > 0) {
				check = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	public static boolean deleteCategory(String categoryID) {
		boolean check = false;
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "DELETE FROM `categories` WHERE id=?";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1,categoryID);
			
			int rs = ps.executeUpdate();
			if(rs > 0) {
				check = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return check;
	}
	
	public static void main(String[] args) {
//		System.out.println(CategoryDAO.getAllCategories());
//		System.out.println(CategoryDAO.deleteCategory("1"));
	}
}
