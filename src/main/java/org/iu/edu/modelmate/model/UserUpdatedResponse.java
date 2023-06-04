package org.iu.edu.modelmate.model;

import org.springframework.stereotype.Component;

@Component
public class UserUpdatedResponse {
    private String message;

    private String error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
