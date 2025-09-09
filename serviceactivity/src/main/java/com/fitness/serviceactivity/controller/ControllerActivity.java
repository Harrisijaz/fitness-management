package com.fitness.serviceactivity.controller;

import com.fitness.serviceactivity.dto.DtoActivity;
import com.fitness.serviceactivity.dto.DtoActivityRequest;
import com.fitness.serviceactivity.dto.DtoUserWithId;
import com.fitness.serviceactivity.service.ServiceActivity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/activity")
public class ControllerActivity {



private ServiceActivity serviceActivity;

@PostMapping("/track-activity")
public ResponseEntity<DtoActivity> trackActivity(@RequestBody DtoActivityRequest dtoActivityRequest){
    return  serviceActivity.trackActivity(dtoActivityRequest);
}

    @GetMapping("/get-user-activities")
    public ResponseEntity<List<DtoActivity>> getUserActivities(){

     return  serviceActivity.getUserActivities();

    }


    @PostMapping("/get-activity-by-ID")
    public ResponseEntity<DtoActivity> getActivityById(@RequestBody DtoActivity dtoActivity) {

    return  serviceActivity.getActivityById(dtoActivity);
    }

//
//    @PostMapping("/get-activity-by-ID/validate")
//    public ResponseEntity<Boolean> validation(@RequestBody DtoActivity dtoActivity) {
//
//        return  ResponseEntity.ok(serviceActivity.existByUserId(dtoActivity));
//    }
}
