package com.fitness.serviceactivity.service;

import com.fitness.serviceactivity.dto.DtoActivity;
import com.fitness.serviceactivity.dto.DtoActivityRequest;
import com.fitness.serviceactivity.dto.DtoUserWithId;
import com.fitness.serviceactivity.entity.Activity;
import com.fitness.serviceactivity.repository.RepositoryActivity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.lang.model.util.AbstractElementVisitor6;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceActivity {


    private final RepositoryActivity repositoryActivity;

    private final ServiceUserValidation serviceUserValidation;


    public ResponseEntity<DtoActivity> trackActivity( DtoActivityRequest dtoActivityRequest){


        boolean isValidUser = serviceUserValidation.validateUser(dtoActivityRequest.getUserId());

        if(!isValidUser){
            throw new RuntimeException("Invalid User ID"+ dtoActivityRequest.getUserId());
        }
        Activity activity = Activity.builder()
                .userId(dtoActivityRequest.getUserId())
                .activityType(dtoActivityRequest.getActivityType())
                .duration(dtoActivityRequest.getDuration())
                .caloriesBurned(dtoActivityRequest.getCaloriesBurned())
                .startTime(dtoActivityRequest.getStartTime())
                .additionalMetrics(dtoActivityRequest.getAdditionalMetrics())
                .build();

             Activity savedActivity = repositoryActivity.save(activity);

        DtoActivity response = new DtoActivity();
        response.setId(savedActivity.getId());
        response.setUserId(savedActivity.getUserId());
        response.setActivityType(savedActivity.getActivityType());
        response.setDuration(savedActivity.getDuration());
        response.setCaloriesBurned(savedActivity.getCaloriesBurned());
        response.setStartTime(savedActivity.getStartTime());
        response.setAdditionalMetrics(savedActivity.getAdditionalMetrics());
        response.setCreatedAt(savedActivity.getCreatedAt());
        response.setUpdatedAt(savedActivity.getUpdatedAt());

        return ResponseEntity.ok(response);


    }

    public ResponseEntity<List<DtoActivity>> getUserActivities(){


        List<Activity> activities = repositoryActivity.findAll();
        List<DtoActivity> dtoActivities = activities.stream()
                .map(activity -> new DtoActivity(
                        activity.getId(),
                        activity.getUserId(),
                        activity.getActivityType(),
                        activity.getDuration(),
                        activity.getCaloriesBurned(),
                        activity.getStartTime(),
                        activity.getAdditionalMetrics(),
                        activity.getCreatedAt(),
                        activity.getUpdatedAt()
                ))
                .collect(Collectors.toList());


      return  ResponseEntity.ok(dtoActivities);

    }


    public ResponseEntity<DtoActivity> getActivityById(DtoActivity dtoActivity) {

        String activityId = dtoActivity.getId();

        Optional<Activity> optionalActivity = repositoryActivity.findById(activityId);

        if (optionalActivity.isEmpty()) {
            return ResponseEntity.notFound().build();

        }
        Activity activity = optionalActivity.get();

        DtoActivity response = new DtoActivity();
        response.setId(activity.getId());
        response.setUserId(activity.getUserId());
        response.setActivityType(activity.getActivityType());
        response.setDuration(activity.getDuration());
        response.setCaloriesBurned(activity.getCaloriesBurned());
        response.setStartTime(activity.getStartTime());
        response.setAdditionalMetrics(activity.getAdditionalMetrics());
        response.setCreatedAt(activity.getCreatedAt());
        response.setUpdatedAt(activity.getUpdatedAt());


        return ResponseEntity.ok(response);
    }

//    public Boolean existByUserId(DtoActivity dtoActivity) {
//
//        String userId = dtoActivity.getUserId();
//
//        return repositoryActivity.existsById(userId);
//
//
//
//    }
}
