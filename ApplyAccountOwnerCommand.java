package command;

public class ApplyAccountOwnerCommand {

	private String id;

	private String lastName;
	private String firstName;
	private String address;
	private String city;
	private int postalCode;
	private String phone;
	private String email;

	public ApplyAccountOwnerCommand(String id, String lastName, String firstName, String address, String city,
			int postalCode, String phone, String email) {
		this.id = id;

		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.city = city;
		this.postalCode = postalCode;
		this.phone = phone;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
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

}
