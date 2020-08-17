package com.techelevator.tenmo.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.model.AvailableUser;

@PreAuthorize("isAuthenticated()")
@RestController
public class AvailableUserController {
	
	private TransferDao transferDao;

	public AvailableUserController(TransferDao transferDao) {
		this.transferDao = transferDao;
	}
	
	@RequestMapping(path = "accounts", method = RequestMethod.GET)
	public List<AvailableUser> getAvailableUser() {
		return transferDao.getAvailableUser();
	}
	
}
