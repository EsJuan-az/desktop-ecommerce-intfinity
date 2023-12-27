package com.helpers;


import okhttp3.*;
import org.json.JSONObject;
import java.io.IOException;
public class CRUDHandler {
    private static CRUDHandler instance;
    private static final String API = "https://intfinity-enterprise-backend.onrender.com/api/company/1";
    public static CRUDHandler getInstance(){
        if( CRUDHandler.instance == null ) {
            CRUDHandler.instance = new CRUDHandler();
        }
        return CRUDHandler.instance;
    }

    private OkHttpClient client;
    private MediaType mediaType;
    public CRUDHandler(){
        this.client = new OkHttpClient();
        this.mediaType = MediaType.parse("application/json");
    }

    public JSONObject post(String URL, String json ) throws IOException {
        RequestBody body = RequestBody.create(json, this.mediaType);
        Request request = new Request.Builder()
                .url(API + URL)
                .post(body)
                .build();
        try (Response response = this.client.newCall(request).execute()) {
            assert response.body() != null;
            return new JSONObject(response.body().string());
        }
    }
}
