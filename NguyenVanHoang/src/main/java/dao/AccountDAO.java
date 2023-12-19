package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.Account;
import bean.Category;

public class AccountDAO {
	public static ArrayList<Account> getAllAccount() {
		ArrayList<Account> accountList = new ArrayList<Account>();
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "SELECT * FROM `accounts`";
			
			PreparedStatement ps = con.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				Account account = new Account();
				account.setUsername(rs.getString("username"));
				account.setFullname(rs.getString("fullname"));
				account.setEmail(rs.getString("email"));
				account.setPassword(rs.getString("password"));
				account.setRole(rs.getInt("role"));
				accountList.add(account);
			}
		
		} catch (Exception e) {
			System.out.println(e);
		}
		return accountList;
	}
	
	public static boolean addAccount(Account account) {
		boolean check = false;
		try {
			Connection con = DatabaseUtil.getConnnection();
			String sql = "INSERT INTO `accounts` (`username`,`fullname`, `email`, `password`) VALUES (?,?,?,?)";
			
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, account.getUsername());
			ps.setString(2, account.getFullname());
			ps.setString(3, account.getEmail());
			ps.setString(4, account.getPassword());
			
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
//		Account account = new Account();
//		account.setFullname("Hoang");
//		account.setPassword("12345");
//		account.setEmail("haong@gmail.com");
//		System.out.println(AccountDAO.addAccount(account));
		System.out.println(AccountDAO.getAllAccount());
	}
}
