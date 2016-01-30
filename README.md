# spring-cors-rest-angular


## What is this repository for? ##

* Demo using Spring MVC to create CORS REST Service
* Demo using AngularJS to create Web UI to access the REST Service


## How do I get set up? ##
### Setup Server ###
* Import the project to STS as a standard Java Project
* Run maven build
* Run Application.java as Spring Boot App
* Access web service with
** http://localhost:8080/contact
** http://localhost:8080/contact?id=2

### Setup Client ###
* 


## How do it work? ##
### Spring REST Service ###
1. Create POJO to represent a resource class: Contact.java
```
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
```
2. Create a resource controller class: ContactController.java
```
@Controller
public class ContactController {
    private static final String template = "Address is %s!";
    @CrossOrigin(origins = "http://localhost:8080")	//enable CORS
    @RequestMapping("/contact")
    public @ResponseBody Contact getContact(@RequestParam(required=false, defaultValue="1") int id) {
        System.out.println("==== request received ====");
        return new Contact(id,
        		Base64.encodeBase64String((id+" name").getBytes()), 
        		String.format(template, Base64.encodeBase64String((id+" addr").getBytes())));
    }
}
```
@CrossOrigin annotation enables cross-origin requests only for this specific method. 
3. Make the application executable
To make it simple, SpringBootApplication is been used
```
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
    