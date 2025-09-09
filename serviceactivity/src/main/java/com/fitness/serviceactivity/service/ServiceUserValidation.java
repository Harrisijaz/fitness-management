package com.fitness.serviceactivity.service;

import com.fitness.serviceactivity.dto.DtoActivityRequest;
import com.fitness.serviceactivity.dto.DtoUserWithId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Service
@Slf4j
@RequiredArgsConstructor
public class ServiceUserValidation {


    private final WebClient userServiceWebClient;

    public boolean validateUser(String userId){

        log.info("Calling user validation api for userID {}" , userId);
        try{
            return Boolean.TRUE.equals(userServiceWebClient.get()
                    .uri("/api/user/find-user-by-Id/{userId}/validate",userId)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block());
        } catch (WebClientResponseException e) {
            if(e.getStatusCode() == HttpStatus.NOT_FOUND){
                throw new RuntimeException("User not found");
            }else if (e.getStatusCode() == HttpStatus.BAD_REQUEST){
                throw new RuntimeException(" Invalid User Request");
            }
        }
        return false;
    }
}
