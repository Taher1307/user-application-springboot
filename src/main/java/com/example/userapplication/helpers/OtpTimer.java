package com.example.userapplication.helpers;

import com.example.userapplication.constants.Constants;
import com.example.userapplication.model.User;
import com.example.userapplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class OtpTimer {

    @Autowired
    UserRepository userRepository;

    public String checkOtpTime(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) return Constants.USER_NOT_FOUND;
        long otpDate = user.getOtpTime();
        long otpTime = otpDate + (60000 * 5);
        long newTime = new Date().getTime();
        Boolean otpValid = (newTime > otpTime);
        return otpValid.toString();
    }

}
