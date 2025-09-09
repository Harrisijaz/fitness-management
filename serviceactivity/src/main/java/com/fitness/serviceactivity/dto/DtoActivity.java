package com.fitness.serviceactivity.dto;

import com.fitness.serviceactivity.entity.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoActivity {

    private String id;

    private String userId;

    private ActivityType activityType;

    private Integer duration;

    private Integer caloriesBurned;

    private LocalDateTime startTime;

    private Map<String,Object> additionalMetrics;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;


}
