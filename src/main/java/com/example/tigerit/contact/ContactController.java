package com.example.tigerit.contact;

import com.example.tigerit.user.User;
import com.example.tigerit.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/contact")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(path = "/all")
    List<Contact> getAllContacts(@RequestParam("search") String search,@RequestParam("limit") int limit,@RequestParam("offset") int offset, @RequestHeader("Authorization") String authHeader){
        search = "%"+search+"%";
        System.out.println(search);
        return contactService.getAllContacts(search,limit,offset,authHeader);
    }

    @PostMapping(path = "/new")
    Contact createNewContact(@RequestHeader("Authorization") String authHeader, @RequestBody Contact contact){

        return contactService.createNewContact(authHeader,contact);
    }

}
