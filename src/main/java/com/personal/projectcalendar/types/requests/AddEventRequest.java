package com.personal.projectcalendar.types.requests;

import com.personal.projectcalendar.types.models.EventModel;

public class AddEventRequest {
    private final EventModel eventModel;

    private AddEventRequest(AddEventRequest.Builder builder) {
        this.eventModel = builder.eventModel;
    }

    public EventModel getEventModel() {
        return eventModel;
    }

    public static Builder builder() {
        return new AddEventRequest.Builder();
    }

    public static class Builder {
        private EventModel eventModel;

        private Builder() {}

        public Builder withEventModel(EventModel theEventModel) {
            this.eventModel = theEventModel;
            return this;
        }

        public AddEventRequest build() {
            return new AddEventRequest(this);
        }
    }
}
