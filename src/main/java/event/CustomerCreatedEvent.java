package event;

import java.time.LocalDateTime;

import aggregates.CustomerApplicationStatus;




public class CustomerCreatedEvent { //nicht mehr benötigt ->doch!

    private String customerId;    
    private String lastName;
    private String firstName;
    private String address;
    private String city;
    private int postalCode;
    private String phone;
    private String email;
    private CustomerApplicationStatus status;
    private LocalDateTime createdAt;


    public CustomerCreatedEvent(String customerId, String lastName, String firstName, String address,
                                    String city, int postalCode, String phone, String email) {
        this.customerId = customerId;
        this.firstName = lastName;
        this.lastName = firstName;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.phone = phone;
        this.email = email;
        this.status = CustomerApplicationStatus.PENDING;
        this.createdAt = LocalDateTime.now();

    }

    public String getCustomerId() {
        return customerId;
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
    
    public CustomerApplicationStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }




}
