package event;

import java.time.LocalDateTime;

public class ApplicationApprovedEvent {

    private final String applicationFormId;
    private final LocalDateTime updatedAt;

    public ApplicationApprovedEvent(String applicationFormId) {
        this.applicationFormId = applicationFormId;
        this.updatedAt = LocalDateTime.now();
    }

    public String getApplicationFormId() {
        return applicationFormId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

}
