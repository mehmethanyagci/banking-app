package com.techelevator.tenmo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.AvailableUser;


public class AvailableUserService {
	
	private String jwt;
	private String baseUrl;
	private RestTemplate restTemplate = new RestTemplate();
	
	
	public AvailableUserService(String jwt, String baseUrl) {
		
		this.jwt = jwt;
		this.baseUrl = baseUrl;
		
	}
	
	public AvailableUser[] availableUser() {
		
		AvailableUser[] availableUser = null;
		
		String url = baseUrl + "accounts";
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(jwt);
		HttpEntity request = new HttpEntity<>(headers);
		
		try {
			
			 availableUser = restTemplate.exchange(url, HttpMethod.GET, request, AvailableUser[].class).getBody();
			 
		} catch (RestClientResponseException ex) {
			// TODO 404, something else kinda error
		}
		
		return availableUser;
	}
	
	
	
	
	
	

}
