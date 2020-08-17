package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.UserAccount;

public interface AccountDAO {
	
	public UserAccount getAccountBalance(int userId);
	
}
