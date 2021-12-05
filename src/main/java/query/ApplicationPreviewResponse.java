package query;



import java.time.LocalDateTime;

import aggregates.CustomerApplicationStatus;

public class ApplicationPreviewResponse {

    private String applicationFormId;
    private String firstName;
    private String lastName;
    private CustomerApplicationStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public ApplicationPreviewResponse(String applicationFormId, String firstName, String lastName,
                                      CustomerApplicationStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.applicationFormId = applicationFormId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public CustomerApplicationStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
