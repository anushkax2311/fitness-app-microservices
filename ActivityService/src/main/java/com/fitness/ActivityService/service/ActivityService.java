package com.fitness.ActivityService.service;

import java.net.Authenticator.RequestorType;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fitness.ActivityService.ActivityRepository;
import com.fitness.ActivityService.dto.ActivityRequest;
import com.fitness.ActivityService.dto.ActivityResponse;
import com.fitness.ActivityService.model.Activity;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ActivityService {

	private final ActivityRepository activityRepository;
	private final UserValidationService userValidationService;
	private final RabbitTemplate rabbitTemplate;
	
	@Value("${rabbitmq.exchange.name}")
	private String exchange;
	@Value("${rabbitmq.routing.key}")
	private String routingKey;

	public ActivityResponse trackActivity(ActivityRequest request) {
		
		boolean isValidUser = userValidationService.validateUser(request.getUserId());
		if(!isValidUser) {
			throw new RuntimeException("Invalid User: "+ request.getUserId());
		}
		
		
		
		Activity activity = Activity.builder().userId(request.getUserId()).type(request.getType())
				.duration(request.getDuration()).caloriesBurned(request.getCaloriesBurned())
				.startTime(request.getStartTime()).additionalMatrics(request.getAdditionalMatrics()).build();

		Activity savedActivity = activityRepository.save(activity);
		
		try {
			rabbitTemplate.convertAndSend(exchange, routingKey, savedActivity);
		} catch (Exception e) {
			log.error("Failed to publish activity to RabbitMQ",e); 
		}
		
		
		return mapToResponse(savedActivity);

	}

	private ActivityResponse mapToResponse(Activity activity) {
		ActivityResponse response = new ActivityResponse();
		response.setId(activity.getId());
		response.setUserId(activity.getUserId());
		response.setType(activity.getType());
		response.setAdditionalMatrics(activity.getAdditionalMatrics());
		response.setCaloriesBurned(activity.getCaloriesBurned());
		response.setDuration(activity.getDuration());
		response.setCreatedAt(activity.getCreatedAt());
		response.setStartTime(activity.getStartTime());
		response.setUpdatedAt(activity.getUpdatedAt());
		return response;

	}

	public List<ActivityResponse> getUserActivities(String userId) {

		List<Activity> activities = activityRepository.findByUserId(userId);

		return activities.stream().map(this::mapToResponse).collect(Collectors.toList());

	}

	public ActivityResponse getActivityById(String activityId) {
		return activityRepository.findById(activityId).map(this::mapToResponse)
				.orElseThrow(() -> new RuntimeException("Activity not found with id:" + activityId));
	}

}
