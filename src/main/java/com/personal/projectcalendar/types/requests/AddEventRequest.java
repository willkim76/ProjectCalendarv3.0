package com.personal.projectcalendar.types.requests;

import com.personal.projectcalendar.types.models.EventModel;

public class AddEventRequest {
    private final EventModel eventModel;
    private final String userId;

    private AddEventRequest(AddEventRequest.Builder builder) {
        this.eventModel = builder.eventModel;
        this.userId = builder.userId;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public String getUserId() {
        return userId;
    }

    public static Builder builder() {
        return new AddEventRequest.Builder();
    }

    public static class Builder {
        private EventModel eventModel;
        private String userId;

        private Builder() {}

        public Builder withEventModel(EventModel theEventModel) {
            this.eventModel = theEventModel;
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
