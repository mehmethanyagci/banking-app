package com.techelevator.tenmo.dao;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.techelevator.tenmo.model.AvailableUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.UserAccount;

@Component
public class TransferJDBC implements TransferDao {

	
	private List<Transfer> transfers = new ArrayList<>();
	private JdbcTemplate jdbcTemplate;

	public TransferJDBC(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	
	@Override
	public boolean sendFunds(UserAccount user, UserAccount destinationUser, double amount) {
		
		if (user.getAccountBalance() >= amount && amount > 0) {
			
			user.setAccountBalance(user.getAccountBalance() - amount);
			destinationUser.setAccountBalance(destinationUser.getAccountBalance() + amount);

			String updateSql = "UPDATE accounts SET balance = ? WHERE user_id = ?";
			jdbcTemplate.update(updateSql, user.getAccountBalance(), user.getAccountId());
		
			String updateSql2 = "UPDATE accounts SET balance = ? WHERE user_id = ?";
			jdbcTemplate.update(updateSql2, destinationUser.getAccountBalance(), destinationUser.getAccountId());

			String insertSql = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount)\n" + 
					"VALUES (DEFAULT, 2, 2, ?, ?, ?)";
			jdbcTemplate.update(insertSql, user.getAccountId(), destinationUser.getAccountId(), amount);
			
			return true;
		
		} else {
			
			return false;
		}

	}
	
	
	@Override
	public List<Transfer> displayTransfers(int id) {
		
		List<Transfer> transfers = new ArrayList<Transfer>();
		
		String selectSql = "SELECT transfers.transfer_id, transfers.transfer_type_id, transfers.transfer_status_id, transfers.account_from, transfers.account_to, transfers.amount, accounts.balance, users.username from transfers\n" + 
				"JOIN accounts ON transfers.account_to = accounts.account_id\n" + 
				"JOIN users ON accounts.user_id = users.user_id\n" + 
				"WHERE transfers.account_from = ?";
		
		SqlRowSet rowSet = jdbcTemplate.queryForRowSet(selectSql, id);
		
		while (rowSet.next()) {
			 Transfer transfer = mapRowToTransfer(rowSet);
			 transfers.add(transfer);
		}
		
		return transfers;
	}
	
	
	
	@Override
	public List<AvailableUser> getAvailableUser() {
		
		List<AvailableUser> availableUsers = new ArrayList<AvailableUser>();
		
		String selectSql = "SELECT users.user_id, users.username, accounts.balance\n" + 
				"FROM users\n" + 
				"JOIN accounts ON users.user_id = accounts.user_id";

		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql);

		while (rows.next()) {
			AvailableUser availableUser = mapRowToAvailableUser(rows);
			availableUsers.add(availableUser);

		}

		return availableUsers;
		
	}
	

	public Transfer addTransferDisplay(Transfer transfer) {
		
		String insertSql = "INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount) VALUES (?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(insertSql, transfer.getTransferId(), transfer.getTransferTypeId(), transfer.getTransferStatusId(), transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getTransferBalance());
		
		return transfer;
		
	}
	
	

	private AvailableUser mapRowToAvailableUser(SqlRowSet rows) {
		AvailableUser availableUser = new AvailableUser();
		availableUser.setId(rows.getInt("user_id"));
		availableUser.setUsername(rows.getString("username"));
		availableUser.setAmount(rows.getDouble("balance"));
        return availableUser;
	}

	
	private Transfer mapRowToTransfer(SqlRowSet rows) {
		Transfer transfer = new Transfer();
		transfer.setDestinationName(rows.getString("username"));
		transfer.setTransferId(rows.getInt("transfer_id"));
		transfer.setTransferBalance(rows.getDouble("amount"));
		transfer.setAccountFrom(rows.getInt("account_from"));
		transfer.setAccountTo(rows.getInt("account_to"));
		transfer.setDestinationBalance(rows.getDouble("balance"));
		transfer.setTransferStatusId(rows.getInt("transfer_status_id"));
		transfer.setTransferTypeId(rows.getInt("transfer_type_id"));
        return transfer;
	}
	
	
	
//	
//	private Transfer mapRowToTransfer(SqlRowSet rows) {
//		Transfer transfer = new Transfer();
//		transfer.setDestinationId(rows.getInt("account_to"));
//		transfer.setTransferId(rows.getInt("transfer_id"));
//		transfer.setUserId(rows.getInt("account_from"));
//		transfer.setTransferBalance(rows.getDouble("amount"));
//        return transfer;
//	}

}
