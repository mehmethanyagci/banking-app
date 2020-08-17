package com.techelevator.tenmo.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDAO;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.UserAccount;

@PreAuthorize("isAuthenticated()")
@RestController
public class AccountController {
	

	private AccountDAO accountDAO;
	private TransferDao transferDAO;
	
	public AccountController(AccountDAO accountDao, TransferDao transferDao) {
		this.accountDAO = accountDao;
		this.transferDAO = transferDao;
	}
	
	@RequestMapping(path = "accounts/{id}", method = RequestMethod.GET) 
	public UserAccount getAccountBalance(@PathVariable(name = "id") int id) {
		
		return accountDAO.getAccountBalance(id);
		
	}
	
	@RequestMapping(path = "transfers/{id}", method = RequestMethod.GET)
	public List<Transfer> getTransfers(@PathVariable(name = "id") int id) {
		
		return transferDAO.displayTransfers(id);
		
	}
	
	@RequestMapping(path = "transfers/{id}", method = RequestMethod.POST)
	public Transfer addTransferDisplay(@RequestBody Transfer transfer, @PathVariable(name = "id") int id) {
		
		return transferDAO.addTransferDisplay(transfer);
		
	}
	

}
