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
* Go to the project folder
* Because the Web Service is already running on port 8080, and in the ContactController.java, CORS defines only the localhost:9000 can access the service. So, the client should be deployed at port 9000. We're using Spring Boot buildin tomcat to start the client
```
$ mvn spring-boot:run -Dserver.port=9000
```
* Access client with
** http://localhost:9000

## How do it work? ##
### Spring REST Service ###
####1. Create POJO to represent a resource class: Contact.java####
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
####2. Create a resource controller class: ContactController.java####
```
@Controller
public class ContactController {
    private static final String template = "Address is %s!";
    @CrossOrigin(origins = "http://localhost:9000")	//enable CORS
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
####3. Make the application executable####
To make it simple, SpringBootApplication is been used
```
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```
### Angular Client ###
####1. Create Angular Controller####
```
angular.module('contactApp', [])
.controller('ContactController', ['$scope', '$http', function($scope, $http) {
  $scope.update = function() {
		$http.get('http://localhost:8080/contact?id='+$scope.contact.id).
		    success(function(data) {
		        $scope.contact = data;
		    });
  };
}]);
```
####2. Create view ####
```
<!doctype html>
<html ng-app="contactApp">
	<head>
		<title>AngularJS REST Client</title>
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.0-rc.2/angular.min.js"></script>
    	<script src="contacts.client.controller.js"></script>
	</head>

	<body>
		<div ng-controller="ContactController">
		  <form novalidate class="simple-form">
		  	Id: <input type="text" ng-model="contact.id" /><br /><br />
			Contact ID is {{contact.id}}<br />
			Contact Name is {{contact.name}}<br />
			Contact Address is {{contact.address}}<br /><br />

		    <input type="submit" ng-click="update()" value="Update" />
		  </form>
		  <pre>user = {{contact | json}}</pre>
		</div>
	</body>
</html>
```