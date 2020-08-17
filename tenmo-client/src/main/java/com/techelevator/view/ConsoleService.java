package com.techelevator.view;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

import org.springframework.core.env.SystemEnvironmentPropertySource;

import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.AvailableUser;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.User;
import com.techelevator.tenmo.models.UserAccount;



public class ConsoleService {

	private PrintWriter out;
	private Scanner in;

	public ConsoleService(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output, true);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		out.println();
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println("\n*** " + userInput + " is not a valid option ***\n");
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}

	public String getUserInput(String prompt) {
		out.print(prompt+": ");
		out.flush();
		return in.nextLine();
	}

	public Integer getUserInputInteger(String prompt) {
		Integer result = null;
		do {
			out.print(prompt+": ");
			out.flush();
			String userInput = in.nextLine();
			try {
				result = Integer.parseInt(userInput);
			} catch(NumberFormatException e) {
				out.println("\n*** " + userInput + " is not valid ***\n");
			}
		} while(result == null);
		return result;
	}
	
	
	
	
	public void displayAvailableUsers(AvailableUser[] users) {
		System.out.println("------------------------------");	
		System.out.printf("%-10s", "User ID");
		System.out.printf("%-20s", "Name");
		System.out.println();
		System.out.println("------------------------------");
		
		for (AvailableUser availableUser : users) {

			System.out.printf("%-10d", availableUser.getId());
			System.out.printf("%-20s", availableUser.getUsername());
			System.out.println();
		}
		
		System.out.println("------------------------------");
		
		}

	
	
	public void displayTransferHistory(Transfer[] history) {
		
		System.out.println("-------------------------------------------");
		System.out.printf("%-15s", "Transfers ID");
		System.out.printf("%-20s", "From/To");
		System.out.printf("%-10s", "Amount");
		System.out.println();
		System.out.println("-------------------------------------------");
		
		for (Transfer transfer : history ) {
			
			System.out.printf("%-15d", transfer.getTransferId());
			System.out.printf("%-20s", "To: " + transfer.getDestinationName());
			System.out.printf("%-10s", "$" + transfer.getTransferBalance());
			System.out.println();
			
		}
		
		System.out.println("-------------------------------------------");
		
	}
	
	
	
	public int getDestinationId() {
		Integer destinationId = null;
		do {
			out.print("Enter ID of user you are sending to (0 to cancel): ");
			out.flush();
			String userInput = in.nextLine();
			try {
				destinationId = Integer.parseInt(userInput);
			} catch(NumberFormatException e) {
				out.println("\n*** " + userInput + " is not valid ***\n");
			}
		} while(destinationId == null);
		return destinationId;
	}
		
	
	

	public double getTransferAmount() {
		Double transferAmount = null;
		do {
			out.print("Enter amount: ");
			out.flush();
			String userInput = in.nextLine();
			try {
				transferAmount = Double.parseDouble(userInput);
			} catch(NumberFormatException e) {
				out.println("\n*** " + userInput + " not valid ***\n");
			}
		} while(transferAmount == null || transferAmount < 0);
		return transferAmount;
	}
		
	
	
	public int getTransferIdDetailSelection() {
		
		Integer transferDetailSelection = null;
		do {
			out.print("Please enter transfer ID to view details (0 to cancel): ");
			out.flush();
			String userInput = in.nextLine();
			try {
				transferDetailSelection = Integer.parseInt(userInput);
			} catch(NumberFormatException e) {
				out.println("\n*** " + userInput + " not valid ***\n");
			}
		} while(transferDetailSelection == null);
		return transferDetailSelection;
		
		
	}
	
	
	public void displayTransferDetails(Transfer transfer, UserAccount user) {
		
		System.out.println("----------------------------------");
		System.out.println("Transfer Details");
		System.out.println("----------------------------------");
		
		System.out.println("Id: " + transfer.getTransferId());
		System.out.println("From: " + transfer.getDestinationName());
		System.out.println("To: " + user.getUsername());
		System.out.println("Type: Send");
		System.out.println("Status: Approved");
		System.out.println("Amount: $" + transfer.getTransferBalance());

		
	}
	
	
	
	
	public void displayMessage (String message) {
		out.println(message);
		out.flush();
	}
	
}
