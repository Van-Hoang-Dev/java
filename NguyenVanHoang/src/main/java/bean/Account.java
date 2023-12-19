package bean;

public class Account {
	//Fields
	private String username;
	private String fullname;
	private String password;
	private String email;
	private int role = 2;
	
	//Getter - setter
	
	public String getFullname() {
		return fullname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	//Methods
	public Account() {
		
	}
	public Account(String username, String fullname, String email,String password) {
		this.username = username;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "[username=" + username + ", fullname=" + fullname + ", password=" + password + ", email="
				+ email + ", role=" + role + "\n";
	}
	
	
	//Methods
	
	
	
}
