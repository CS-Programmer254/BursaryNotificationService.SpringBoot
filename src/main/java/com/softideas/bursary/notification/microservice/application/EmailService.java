package com.softideas.bursary.notification.microservice.application;

import com.softideas.bursary.notification.microservice.domain.entities.Email;
import com.softideas.bursary.notification.microservice.infrastructure.persistence.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailRepository emailRepository;

    private static final Logger logger = LoggerFactory.getLogger(EmailService.class);

    @Transactional
    public boolean sendEmail(Email email) {

        try {

            emailRepository.save(email);
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true,"utf-8");
            helper.setTo(email.getEmailTo());
            helper.setSubject(email.getSubject());
            helper.setText(email.getMessage(), true);

            javaMailSender.send(mimeMessage);

            email.setStatus("SENT");

            logger.info("Email successfully sent to {} : {} ", email.getEmailTo(),email.getMessage());
            return true;


        } catch (Exception e) {

            email.setStatus("FAILED");
            logger.error("Failed to send email to {}: {}", email.getEmailTo(), e.getMessage());
            return false;
        }
    }
    public String sendMailWithAttachment(Email email) {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setTo(email.getEmailTo());
            mimeMessageHelper.setText(email.getMessage());
            mimeMessageHelper.setSubject(email.getSubject());
            javaMailSender.send(mimeMessage);
            return "Mail sent Successfully";
        }

        catch (MessagingException e) {

            return "Error while sending mail!!!";
        }
    }

}
