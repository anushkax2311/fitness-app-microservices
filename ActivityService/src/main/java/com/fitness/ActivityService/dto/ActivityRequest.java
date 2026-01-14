package com.fitness.ActivityService.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.fitness.ActivityService.model.ActivityType;

import lombok.Data;


@Data
public class ActivityRequest {

	private String userId;
	private ActivityType type;
	private Integer duration;
	private Integer caloriesBurned;
	private LocalDateTime startTime;
	private Map<String , Object> additionalMatrics;
	
}
