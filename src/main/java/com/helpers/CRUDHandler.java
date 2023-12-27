package com.helpers;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class CRUDHandler {
    private static CRUDHandler instance;
    private static String API = "https://intfinity-enterprise-backend.onrender.com/api/company/1";

    public static void setCompanyId(int CompanyId) {
        // CRUDHandler.API = "https://intfinity-enterprise-backend.onrender.com/api/company/" + CompanyId;
    }

    public static CRUDHandler getInstance() {
        if (CRUDHandler.instance == null) {
            CRUDHandler.instance = new CRUDHandler();
        }
        return CRUDHandler.instance;
    }

    private OkHttpClient client;
    private MediaType mediaType;

    public CRUDHandler() {
        this.client = new OkHttpClient();
        this.mediaType = MediaType.parse("application/json");
    }

    public JSONObject post(String URL, String json) throws IOException {
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

    public JSONArray getAll(String URL) throws IOException {
        Request request = new Request.Builder()
                .url(API + URL)
                .get()
                .build();
        try (Response response = this.client.newCall(request).execute()) {
            String responseBody = response.body().string(); // obtiene la respuesta como String

            // Imprimir la respuesta para depuración
            System.out.println("Response: " + responseBody);

            JSONObject jsonObject = new JSONObject(responseBody);
            if(jsonObject.has("result") && jsonObject.get("result") instanceof JSONArray) {
                return jsonObject.getJSONArray("result");
            } else {
                // Manejar el caso en que la respuesta no contenga un arreglo JSON bajo la clave "result"
                throw new JSONException("La clave 'result' no está presente o no es un arreglo JSON en la respuesta: " + responseBody);
            }
        }
    }


    public JSONObject getOne(String URL) throws IOException {
        Request request = new Request.Builder()
                .url(API + URL)
                .get() // Corregido aquí también: no se debe usar un cuerpo de solicitud con GET
                .build();
        try (Response response = this.client.newCall(request).execute()) {
            assert response.body() != null;
            return new JSONObject(response.body().string());
        }
    }
}
