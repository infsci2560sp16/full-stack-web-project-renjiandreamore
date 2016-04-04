import java.util.ArrayList;

public class UserDB {
	ArrayList<User> UsrCollection = new ArrayList<User>();
	int id = 1;
	public void DefaultDB(){
		UsrCollection.add(new User("1", "sdjfiowef", "male"));
		UsrCollection.add(new User("2", "dfwef", "male"));
		UsrCollection.add(new User("3", "erefd", "female"));
		id = 4;
	}
	
	public void addUser(String name, String gender){
		String id = String.valueOf(this.id);
		UsrCollection.add(new User(id, name, gender));
		this.id++;
	}
	
	public String getID(String name){
		for(User usr : UsrCollection){
			if(usr.getName().equals(name))
				return usr.getId();
		}
		return "null";
	}
	
	public User Find(String id){
		for(User usr : UsrCollection){
			if(usr.getId().equals(id))
				return usr;
		}
		return new User("null", "null", "null");
	}
	
	public User FindbyName(String name){
		for(User usr : UsrCollection){
			if(usr.getName().equals(name))
				return usr;
		}
		return new User("null", "null", "null");
	}
}
