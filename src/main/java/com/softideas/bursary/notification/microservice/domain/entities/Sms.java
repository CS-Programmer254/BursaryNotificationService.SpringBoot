package com.softideas.bursary.notification.microservice.domain.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sms {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String phoneNumber;
    private String message;
    private String status;
    private LocalDateTime sendDate;

    public Sms(String phoneNumber, String message, String status) {
        this.id = UUID.randomUUID();
        this.phoneNumber = phoneNumber;
        this.message = message;
        this.status = status;
        this.sendDate = LocalDateTime.now();
    }

    public static Sms addNewSms(String phoneNumber, String message, String status) {
        return new Sms(phoneNumber, message, status);
    }
}