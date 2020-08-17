package com.techelevator.tenmo.model;


public class Transfer {
	
	private int transferId;
	private int transferTypeId = 2;
	private int transferStatusId = 2;
	private int accountFrom;
	private int accountTo;
	private double transferBalance;
	private String destinationName;
	private double destinationBalance;
	
	
	public double getTransferBalance() {
		return transferBalance;
	}
	
	public void setTransferBalance(double transferBalance) {
		this.transferBalance = transferBalance;
	}
	

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public int getTransferTypeId() {
		return transferTypeId;
	}

	public void setTransferTypeId(int transferTypeId) {
		this.transferTypeId = transferTypeId;
	}

	public int getTransferStatusId() {
		return transferStatusId;
	}

	public void setTransferStatusId(int transferStatusId) {
		this.transferStatusId = transferStatusId;
	}

	public int getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(int accountFrom) {
		this.accountFrom = accountFrom;
	}

	public int getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(int accountTo) {
		this.accountTo = accountTo;
	}

	public double getDestinationBalance() {
		return destinationBalance;
	}

	public void setDestinationBalance(double destinationBalance) {
		this.destinationBalance = destinationBalance;
	}
	
	
	

}
