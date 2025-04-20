package com.softideas.bursary.notification.microservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class NotificationServiceRunner implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Notification Service is running...");

        Thread.sleep(Long.MAX_VALUE);
    }
}