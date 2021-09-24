package com.example.userapplication.helpers;

import com.example.userapplication.constants.Constants;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseConstructor {

    public Map<String, String> response(String responseMessage) {
        Map<String, String> response = new HashMap<>();
        response.put(Constants.MESSAGE, responseMessage);
        return response;
    }

}
