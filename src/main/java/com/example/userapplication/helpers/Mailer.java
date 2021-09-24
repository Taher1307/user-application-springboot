package com.example.userapplication.helpers;

import com.example.userapplication.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class Mailer {

    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(String username, String otpSaved) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(username);
        message.setSubject(Constants.otp);
        message.setText(Constants.OTP_MESSAGE + otpSaved + Constants.OTP_MESSAGE_TWO);
        mailSender.send(message);
    }

}
