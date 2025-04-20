package com.softideas.bursary.notification.microservice.api.Consumer;

import com.softideas.bursary.notification.microservice.application.EmailService;
import com.softideas.bursary.notification.microservice.application.SmsService;
import com.softideas.bursary.notification.microservice.config.RabbitMQConfig;
import com.softideas.bursary.notification.microservice.contracts.UserCreatedEvent;
import com.softideas.bursary.notification.microservice.domain.entities.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserCreatedConsumer {

    @Autowired
    private EmailService emailService;
    @Autowired
    private SmsService smsService;
    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @RabbitListener(queues = RabbitMQConfig.USER_CREATED_QUEUE)
    public void consumeUserCreatedEvent(UserCreatedEvent userCreatedEvent) {

        String phoneMessageBody="Your Verification code for Rattansi Bursary Portal is :\n"+ userCreatedEvent.getOtp();
        smsService.sendSms(userCreatedEvent.getPhoneNumber(), phoneMessageBody).subscribe(System.out::println);

        String verificationCode = userCreatedEvent.getOtp();
        String htmlMessageBody = "<html>" +
                "<head>" +
                "<meta name='viewport' content='width=device-width, initial-scale=1'>" +
                "<style>" +
                "body { font-family: Arial, sans-serif; margin: 0; padding: 0; background: linear-gradient(to right, #f5f5f5 50%, #d1d1d1 50%); display: flex; align-items: center; justify-content: center; height: 100vh; }" +
                ".container { position: relative; width: 100%; max-width: 450px; background-color: #fff; padding: 25px; border-radius: 10px; box-shadow: 0 8px 20px rgba(0, 0, 0, 0.15); text-align: center; overflow: hidden; }" +
                ".watermark { position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); font-size: 85px; color: rgba(0, 0, 0, 0.05); font-weight: bold; z-index: 0; white-space: nowrap; }" +
                ".content { position: relative; z-index: 1; }" +
                ".otp-container { display: flex; justify-content: center; gap: 15px; margin: 25px 0; }" +
                ".otp-box { width: 40px; height: 40px; line-height: 40px; margin-right:2px; margin-left:2px; font-size: 20px; font-weight: bold; color: #007bff; border: 2px solid #007bff; text-align: center; border-radius: 8px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); }" +
                ".info-text { color: #777; font-size: 14px; margin-top: 15px; }" +
                "</style>" +
                "</head>" +
                "<body>" +
                "<div class='container'>" +
                "<div class='watermark'>Rattansi Bursary</div>" +
                "<div class='content'>" +
                "<h2 style='color: #333; margin-bottom: 12px;'>Welcome to Rattansi USSD Bursary Portal</h2>" +
                "<p style='color: #555; font-size: 16px;'>Enter the verification code below to proceed:</p>" +

                "<div class='otp-container'>" +
                "<span class='otp-box'>" + verificationCode.charAt(0) + "</span>" +
                "<span class='otp-box'>" + verificationCode.charAt(1) + "</span>" +
                "<span class='otp-box'>" + verificationCode.charAt(2) + "</span>" +
                "<span class='otp-box'>" + verificationCode.charAt(3) + "</span>" +
                "<span class='otp-box'>" + verificationCode.charAt(4) + "</span>" +
                "<span class='otp-box'>" + verificationCode.charAt(5) + "</span>" +
                "</div>" +

                "<p class='info-text'>If you didn’t request this code, ignore this email.</p>" +
                "</div>" +
                "</div>" +
                "</body></html>";

        Email emailNotification = new Email(

                userCreatedEvent.getEmailAddress(),
                htmlMessageBody,
                "PENDING",
                "Email Verification Via OTP!"

        );

        boolean isSendSuccessful = emailService.sendEmail(emailNotification);

        if(isSendSuccessful){

            logger.info("Email successfully");

        }

    }
}
