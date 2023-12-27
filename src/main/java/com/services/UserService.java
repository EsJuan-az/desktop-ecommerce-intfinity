package com.services;


import com.helpers.CRUDHandler;
import org.json.JSONObject;

import java.io.IOException;

public class UserService {

    public static JSONObject login(String email, String password) throws IOException {
        String body = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}";
        return CRUDHandler.getInstance()
                .post("/user/login", body);
    }
}




