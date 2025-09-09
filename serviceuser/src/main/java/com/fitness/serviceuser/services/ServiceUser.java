package com.fitness.serviceuser.services;


import com.fitness.serviceuser.dto.DtoUser;
import com.fitness.serviceuser.dto.DtoUserRegister;
import com.fitness.serviceuser.entities.User;
import com.fitness.serviceuser.exceptionhandling.UserAlreadyExistException;
import com.fitness.serviceuser.repositries.RepositryUser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@Slf4j
public class ServiceUser {


    private RepositryUser repositryUser;

    public ServiceUser(RepositryUser repositryUser) {
        this.repositryUser = repositryUser;
    }

    public ResponseEntity<DtoUser> registerUser(@RequestBody DtoUserRegister userRegister){

        String checkEmail = userRegister.getEmail();

        if(repositryUser.existsByEmail(checkEmail)){
            throw new UserAlreadyExistException("Email already exists");
        }

        User user = new User();
    user.setEmail(userRegister.getEmail());
    user.setPassword(userRegister.getPassword());
    user.setFirstName(userRegister.getFirstName());
    user.setLastName(userRegister.getLastName());

    User savedUser = repositryUser.save(user);

        DtoUser response = new DtoUser();
        response.setUserId(savedUser.getId());
        response.setEmail(savedUser.getEmail());
        response.setPassword(savedUser.getPassword());
        response.setFirstName(savedUser.getFirstName());
        response.setLastName(savedUser.getLastName());
        response.setCreatedAt(savedUser.getCreatedAt());
        response.setUpdatedAt(savedUser.getUpdatedAt());


        return ResponseEntity.ok(response);
    }



    public ResponseEntity<DtoUser> getUserProfile(@RequestBody DtoUser dtoUser){

        String userId = dtoUser.getUserId();
        Optional<User> optionalUser = repositryUser.findById(userId);
        if(optionalUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        User user = optionalUser.get();

        DtoUser response = new DtoUser();
        response.setUserId(user.getId());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        return  ResponseEntity.ok(response);


    }


    public Boolean existById(String userId) {

        log.info("Calling user validation api for userID {}" , userId);
        return  repositryUser.existsById(userId);
    }
}
