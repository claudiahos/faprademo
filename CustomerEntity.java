package command;

import java.time.LocalDateTime;

import aggregates.CustomerApplicationStatus;

public class CustomerEntity {

	private String customerId;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private int postalCode;
	private String phone;
	private String email;
	private CustomerApplicationStatus status;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public CustomerEntity(String customerId, String firstName, String lastName, String address, String city,
			int postalCode, String phone, String email, CustomerApplicationStatus status, LocalDateTime createdAt) {
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
		this.phone = phone;
		this.email = email;
		this.status = status;
		this.createdAt = createdAt;

	}

	public String getCustomerId() {
		return customerId;
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

	public CustomerApplicationStatus getStatus() {
		return status;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setStatus(CustomerApplicationStatus status) {
		this.status = status;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

}
