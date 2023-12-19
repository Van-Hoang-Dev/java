package bean;

public class Product {
	//Fields
	private String id;
	private String name;
	private int price;
	private String description;
	private String image;
	private String categoryId;
	
	private int quantity = 0;
	
	//Setter - getter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	//Constructors
	public Product() {
		
	}
	
	public Product(String id, String name, int price, String description, String image, String categoryId) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
		this.categoryId = categoryId;
	}
	
	//Methods
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + ", image="
				+ image + ", categoryId=" + categoryId + "]\n";
	}
	
	
	
}
