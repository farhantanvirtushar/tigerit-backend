package com.example.tigerit.user;



import com.example.tigerit.authentication.JwtAuth;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final UserRepository userRepository;
    private JwtAuth jwtAuth;

    @Autowired
    public UserService(UserRepository userRepository, JwtAuth jwtAuth) {
        this.userRepository = userRepository;
        this.jwtAuth = jwtAuth;
    }

    List<User> getAllUsers(){

        return userRepository.findAll();
    }

    Map<String, String> createNewUser(User user){

        List<User> users= userRepository.findWithEmail(user.getEmail());

        if(users.size() != 0){
            throw new IllegalStateException("email already exists");
        }

        String passwordHash = new BCryptPasswordEncoder().encode(user.password);
        user.setPassword(passwordHash);

        userRepository.save(user);

        String token = jwtAuth.generateToken(user);
        return jwtAuth.getAuthResponseData(token,user);
    }

    Map<String, String> loginUser(User user){


        List<User> users= userRepository.findWithEmail(user.getEmail());


        if(users.size() == 0){
            throw new IllegalStateException("email does not exist");
        }

        User foundUser = users.get(0);
        boolean passwordMatches = new BCryptPasswordEncoder().matches(user.getPassword(), foundUser.getPassword());

        if(!passwordMatches){
            throw new IllegalStateException("wrong password");
        }


        String token = jwtAuth.generateToken(foundUser);
        System.out.println("token = "+token);
        return jwtAuth.getAuthResponseData(token,foundUser);
    }


}
