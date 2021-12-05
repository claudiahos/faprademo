package query;

import aggregates.CustomerApplicationStatus;

public class ApplicationResponse {

    private String applicationFormId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private int postalCode;
    private String phone;
    private String email;
    private CustomerApplicationStatus status;

    public ApplicationResponse(String applicationFormId, String firstName, String lastName, String address, String city,
                               int postalCode, String phone, String email, CustomerApplicationStatus status) {
        this.applicationFormId = applicationFormId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }

    public String getApplicationFormId() {
        return applicationFormId;
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

}
