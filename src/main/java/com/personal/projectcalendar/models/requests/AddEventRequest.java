package com.personal.projectcalendar.models.requests;

import com.personal.projectcalendar.models.dtos.EventDto;

public class AddEventRequest {
    private final EventDto eventDto;
    private final String userId;

    private AddEventRequest(AddEventRequest.Builder builder) {
        this.eventDto = builder.eventDTO;
        this.userId = builder.userId;
    }

    public EventDto getEventModel() {
        return eventDto;
    }

    public String getUserId() {
        return userId;
    }

    public static Builder builder() {
        return new AddEventRequest.Builder();
    }

    public static class Builder {
        private EventDto eventDTO;
        private String userId;

        private Builder() {}

        public Builder withEventModel(EventDto theEventDto) {
            this.eventDTO = theEventDto;
            return this;
        }

        public Builder withUserId(String theUserId) {
            this.userId = theUserId;
            return this;
        }

        public AddEventRequest build() {
            return new AddEventRequest(this);
        }
    }
}
