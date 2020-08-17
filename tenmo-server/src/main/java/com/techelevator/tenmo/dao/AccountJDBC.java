package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;


import com.techelevator.tenmo.model.UserAccount;

@Component
public class AccountJDBC implements AccountDAO {

	private JdbcTemplate jdbcTemplate;
	
	
	public AccountJDBC(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}


	@Override
	public UserAccount getAccountBalance(int userId) {
		
		UserAccount userAccount = null;
		
		String selectSql = "SELECT accounts.account_id, users.user_id, accounts.balance, users.username FROM accounts \n" + 
				"JOIN users ON accounts.user_id = users.user_id\n" + 
				"WHERE users.user_id = ?" ;
		
		SqlRowSet rows = jdbcTemplate.queryForRowSet(selectSql, userId);
		
		while (rows.next()) {
			
			userAccount = mapRowToUserAccount(rows);
			
		}
		
		return userAccount;
	}
	
	
    private UserAccount mapRowToUserAccount(SqlRowSet rs) {
        UserAccount userAccount = new UserAccount();
        userAccount.setUserId(rs.getInt("user_id"));
        userAccount.setAccountId(rs.getInt("account_id"));
        userAccount.setAccountBalance(rs.getDouble("balance"));
        userAccount.setUsername(rs.getString("username"));
        return userAccount;
    }

}
