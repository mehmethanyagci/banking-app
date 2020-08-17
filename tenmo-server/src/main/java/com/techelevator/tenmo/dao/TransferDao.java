package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.AvailableUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.UserAccount;

public interface TransferDao {
	
	public boolean sendFunds(UserAccount user, UserAccount destinationUser, double amount);

	List<AvailableUser> getAvailableUser();
	
	public List<Transfer> displayTransfers(int id);
	
	public Transfer addTransferDisplay(Transfer transfer);
	
}
