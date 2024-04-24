package com.example.springsecurity.Service;

import com.example.springsecurity.Api.ApiException;
import com.example.springsecurity.Model.User;
import com.example.springsecurity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public void register(User user) {

        user.setRole("CUSTOMER");
        String hashPassword=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(hashPassword);

        authRepository.save(user);
    }

    public void delete(String userNameAdmin, String userNameCustomer) {
        User admin=authRepository.findUserByUsername(userNameAdmin);
        User customer=authRepository.findUserByUsername(userNameCustomer);

        if(!admin.getRole().equals("ADMIN")){

            throw new ApiException("you are not authorized to delete this user") ;
        }
        authRepository.delete(customer);
    }

    public void update(String username,User user){
        User customer=authRepository.findUserByUsername(username);

        if(customer == null){
            throw new ApiException("you are not authorized to update this user");
        }
        customer.setUsername(user.getUsername());
        customer.setPassword(user.getPassword());

        authRepository.save(customer);


    }

    public List<User> getAllUser(String username){
        User user=authRepository.findUserByUsername(username);
        if(user.getRole().equals("ADMIN")){
            return authRepository.findAll();
        }
        return null;
    }

}
