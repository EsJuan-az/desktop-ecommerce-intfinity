package com.services;

import com.helpers.CRUDHandler;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class ProviderService {
    public static JSONObject create(String name, String nit, String direction, String phone, String email, String description) throws IOException {
        JSONObject body = new JSONObject();
        body.put("name",name);
        body.put("NIT", nit);
        body.put("direction", direction);
        body.put("phone", phone);
        body.put("email", email );
        body.put("description", description);
        return CRUDHandler.getInstance()
                .post("/provider", body.toString());
    }
    public static JSONArray getAll() throws IOException{
        return CRUDHandler.getInstance()
                    .getAll("/provider");
    }
}
