package bean;

public class Category {
	//Fields
	private String id;
	private String name;
	private String note;
	
	//Getter - setter
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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	//Constructors
	public Category() {
		
	}
	public Category(String id, String name, String note) {
		super();
		this.id = id;
		this.name = name;
		this.note = note;
	}
	
	//Methods
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", note=" + note + "]\n";
	}
	
}
