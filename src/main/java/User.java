
public class User {
	String id;
	String name;
	String gender;
	
	public User(String id, String name, String gender){
		this.id = id;
		this.name = name;
		this.gender = gender;
	}
	
	
	public String getId(){
		return id;
	}
	
	public String getName(){
		return name;
	}
	
	public String getGender(){
		return gender;
	}
	
	public String JSON(){
		return "{\"id\":\"" + this.id + "\",\"name\":\"" + this.name + "\",\"gender\":\"" + this.gender + "\"}";
	}
	
	public String XML(){
		return "<user><id>" + this.id + "</id><name>" + this.name + "</name><gender>" + this.gender + "</gender></user>";
	}
	
	public static void main(String[] args){
		User usr = new User("1", "renjian", "transgender");
		System.out.println(usr.JSON());
		System.out.println(usr.XML());
	}
}
