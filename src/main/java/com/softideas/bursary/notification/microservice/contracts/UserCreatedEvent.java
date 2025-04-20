package com.softideas.bursary.notification.microservice.contracts;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class UserCreatedEvent {

    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("emailAddress")
    private String emailAddress;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("otp")
    private String otp;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
