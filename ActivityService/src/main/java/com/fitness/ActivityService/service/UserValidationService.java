package com.fitness.ActivityService.service;

import org.apache.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserValidationService {

	private final WebClient userServiceWebClient;

	public boolean validateUser(String userId) {
		
		log.info("Calling User Validation API for userId: {}"+ userId);
		try {
			return userServiceWebClient.get().uri("/api/users/10859c74-507d-40e6-ae59-af63296f261c/validate", userId)
					.retrieve().bodyToMono(Boolean.class).block();

		} catch (WebClientResponseException e) {
			if (e.getStatusCode()== org.springframework.http.HttpStatus.NOT_FOUND) {
				throw new RuntimeException("User Not Found" + userId);
			}	
			else if (e.getStatusCode()== org.springframework.http.HttpStatus.BAD_REQUEST) {
				throw new RuntimeException("Invalid Request" + userId);
			}	
		}
		return false;
	}
}
