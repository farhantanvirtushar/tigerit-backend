package com.example.tigerit.contact;

import com.example.tigerit.authentication.JwtAuth;
import com.example.tigerit.user.User;
import com.example.tigerit.user.UserController;
import com.example.tigerit.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {
    private final ContactRepository contactRepository;
    private final UserRepository userRepository;
    private JwtAuth jwtAuth;
    @Autowired
    public ContactService(ContactRepository contactRepository,UserRepository userRepository, JwtAuth jwtAuth) {
        this.contactRepository = contactRepository;
        this.userRepository = userRepository;
        this.jwtAuth = jwtAuth;
    }

    private User authenticateUser(String authHeader){


        String[] parts = authHeader.split(" ");


        if( !(parts[0].equals("Bearer")) || parts.length !=2){
            throw new IllegalStateException("authentication failed");
        }

        String token = parts[1];


        String email = jwtAuth.getEmailFromToken(token);

        List<User> users = userRepository.findWithEmail(email);
        if(users.size() == 0){
            throw new IllegalStateException("authentication failed");
        }
        User foundUser = users.get(0);
        return foundUser;
    }

    List<Contact> getAllContacts(String search,int limit, int offset,String authHeader){
        User user = authenticateUser(authHeader);


        return contactRepository.findWithUserId(user.getId(),search,limit,offset);
    }

    Contact createNewContact(String authHeader,Contact contact){

        User user = authenticateUser(authHeader);

        contact.setUserId(user.getId());
        contactRepository.save(contact);
        return contact;
    }

}
