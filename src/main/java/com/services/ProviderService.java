package com.services;

import com.helpers.CRUDHandler;
import okhttp3.*;
import org.json.JSONObject;

import java.io.IOException;

public class ProviderService {
    public static JSONObject create(String name, String nit, String direction, String phone, String email, String description) throws IOException {
        String body = "{\r\n" +
                "    \"name\": \"" + name + "\",\r\n" +
                "    \"NIT\": \"" + nit + "\",\r\n" +
                "    \"direction\": \"" + direction + "\",\r\n" +
                "    \"phone\": \"" + phone + "\",\r\n" +
                "    \"email\": \"" + email + "\",\r\n" +
                "    \"description\": \"" + description + "\"\r\n" +
                "}";
        return CRUDHandler.getInstance()
                .post("/provider", body);
    }
}
