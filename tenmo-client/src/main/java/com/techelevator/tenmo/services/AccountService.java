package com.techelevator.tenmo.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.UserAccount;


public class AccountService {
	
	private String jwt;
	private String baseUrl;
	private RestTemplate restTemplate = new RestTemplate();
	
	
	public AccountService(String jwt, String baseUrl) {
		
		this.jwt = jwt;
		this.baseUrl = baseUrl;
		
	}
	
	public UserAccount getAccountUser(int userId) {
		
		UserAccount userAccount = null;
		
		String url = baseUrl + "accounts/" + userId;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(jwt);
		HttpEntity request = new HttpEntity<>(headers);
		
		try {
			 userAccount = restTemplate.exchange(url, HttpMethod.GET, request, UserAccount.class).getBody();
		} catch (RestClientResponseException ex) {
			// TODO 404, something else kinda error
		}
		
		return userAccount;
	}

	
	public Transfer addTransfer(int transferId, String destinationName, double transferBalance, int transferTypeId, int transferStatusId, int accountFrom, int accountTo, double destinationBalance, int userId) {
		
		Transfer transfer = new Transfer();
		transfer.setTransferId(transferId);
		transfer.setDestinationName(destinationName);
		transfer.setTransferBalance(transferBalance);
		transfer.setAccountFrom(accountFrom);
		transfer.setAccountTo(accountTo);
		transfer.setTransferStatusId(transferStatusId);
		transfer.setTransferTypeId(transferTypeId);
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(jwt);
		HttpEntity<Transfer> requestEntity = new HttpEntity<Transfer>(transfer, headers);
		
		String url = baseUrl + "transfers/" + userId;
		
		try {
			
			transfer = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Transfer.class).getBody();
		} catch (RestClientResponseException ex) {
	    	// TODO 
	    }
		
		
		return transfer;
	}
	
	
	
	public Transfer[] showTransferHistory(int userId) {
		
		Transfer[] transfer = null;
		
		String url = baseUrl + "transfers/" + userId;
		
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(jwt);
		HttpEntity request = new HttpEntity<>(headers);
		
		try {
			 transfer = restTemplate.exchange(url, HttpMethod.GET, request, Transfer[].class).getBody();
		} catch (RestClientResponseException ex) {
			// TODO 404, something else kinda error
			System.out.println(ex.getRawStatusCode() + " " + ex.getStatusText());
		}
		
		return transfer;
	}
		
	
}
	

