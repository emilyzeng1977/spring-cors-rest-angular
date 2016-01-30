package users;

//POJO representing a resource class
public class Contact {
    private final long id;
    private final String name;
    private final String address;
    
    public Contact(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    
    public long getId() {
        return id;
    }
    
    public String getName() {
		return name;
	}
    
	public String getAddress() {
		return address;
	}
}
