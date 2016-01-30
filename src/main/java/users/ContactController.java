package users;

import java.util.concurrent.atomic.AtomicLong;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

//resource controller
@Controller
public class ContactController {
    private static final String template = "Address is %s!";

    @CrossOrigin(origins = "http://localhost:8080")	//enable CORS
    @RequestMapping("/contact")
    public @ResponseBody Contact getContact(@RequestParam(required=false, defaultValue="1") int id) {
        System.out.println("==== request received ====");
        //RESTful web service controller returns an object. 
        //The object data will be written directly to the HTTP response as JSON using Jackson.
        return new Contact(id,
        		Base64.encodeBase64String((id+" name").getBytes()), 
        		String.format(template, Base64.encodeBase64String((id+" addr").getBytes())));
    }
}
