package command;

import java.math.BigDecimal;

public class OpenAccountCommand {

	public final String accountId;
	public final String ownerId;
	private final String firstName;
	private final String lastName;
	private final String address;
	private final String city;
	private final int postalCode;
	private final String phone;
	private final String email;
	public final BigDecimal balance;

	public OpenAccountCommand(String accountId, String ownerId, String firstName, String lastName, String address,
			String city, int postalCode, String phone, String email, BigDecimal balance) {
		this.accountId = accountId;
		this.ownerId = ownerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
		this.phone = phone;
		this.email = email;
		this.balance = balance;
	}

	public String getAccountId() {
		return accountId;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public String getPhone() {
		return phone;
	}

	public String getEmail() {
		return email;
	}

	public BigDecimal getBalance() {
		return balance;
	}

}
