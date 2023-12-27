package com.services;


import com.helpers.CRUDHandler;
import org.json.JSONObject;

import java.io.IOException;

public class UserService {

    public static JSONObject login(String email, String password) throws IOException {
        JSONObject body = new JSONObject();
        body.put("email", email);
        body.put("password", password);
        return CRUDHandler.getInstance()
                .post("/user/login", body.toString());
    }
}




