package event;

import java.time.LocalDateTime;

public class ApplicationRejectedEvent {

    private final String applicationFormId;
    private final LocalDateTime updatedAt;

    public ApplicationRejectedEvent(String applicationFormId) {
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
