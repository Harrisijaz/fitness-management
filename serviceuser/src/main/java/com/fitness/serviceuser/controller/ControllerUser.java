package com.fitness.serviceuser.controller;

import com.fitness.serviceuser.dto.DtoUser;
import com.fitness.serviceuser.dto.DtoUserRegister;
import com.fitness.serviceuser.services.ServiceUser;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class ControllerUser {


    private ServiceUser serviceUser;


    @PostMapping("/create-user")
    public ResponseEntity<DtoUser> registerUser(@Valid @RequestBody DtoUserRegister dtoUserRegister){
        return serviceUser.registerUser(dtoUserRegister);
    }


    @PostMapping("/find-user-by-Id")
    public ResponseEntity<DtoUser> getUserProfile(@RequestBody DtoUser dtoUser) {
    return serviceUser.getUserProfile(dtoUser);
    }



    @GetMapping("/find-user-by-Id/{userId}/validate")
    public boolean existById(@PathVariable String userId){
        return serviceUser.existById(userId);
    }


}
