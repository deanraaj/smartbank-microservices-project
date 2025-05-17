package com.smartbank.notify_service.dto;

public class NotificationRequestDto {
    private String to;
    private String message;

    public NotificationRequestDto(String to, String message) {
        this.to = to;
        this.message = message;
    }

    public NotificationRequestDto() {
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
