package com.smartbank.notify_service.service;

import com.smartbank.notify_service.dto.NotificationRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String senderEmail;

    public String sendNotification(NotificationRequestDto notificationRequestDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(senderEmail);
        mailMessage.setTo(notificationRequestDto.getTo());
        mailMessage.setText(notificationRequestDto.getMessage());
        mailMessage.setSubject("Account Created Successfully....!");

        javaMailSender.send(mailMessage);
        return "Mail Send Successfully";
    }
}
