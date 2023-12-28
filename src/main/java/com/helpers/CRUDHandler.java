package com.helpers;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;

public class CRUDHandler {
    private static CRUDHandler instance;
    private final String API = "https://intfinity-enterprise-backend.onrender.com/api";
    public int companyId;

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public static CRUDHandler getInstance() {
        if (CRUDHandler.instance == null) {
            CRUDHandler.instance = new CRUDHandler();
        }
        return CRUDHandler.instance;
    }

    private final OkHttpClient client;
    private final MediaType mediaType;

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
            //Obtiene la respuesta
            assert response.body() != null;
            String responseBody = response.body().string();
            System.out.println( responseBody );
            JSONObject jsonObject = new JSONObject(responseBody);

            //Maneja la respuesta.
            if(jsonObject.has("result") && jsonObject.get("result") instanceof JSONObject) {
                return jsonObject.getJSONObject("result");
            } else {
                // Manejar el caso en que la respuesta no contenga un arreglo JSON bajo la clave "result"
                throw new JSONException("La clave 'result' no está presente o no es un objeto JSON en la respuesta: " + responseBody);
            }
        }
    }



    public JSONArray getAll(String URL) throws IOException {
        Request request = new Request.Builder()
                .url(API + URL)
                .get()
                .build();
        try (Response response = this.client.newCall(request).execute()) {
            //Obtiene la respuesta.
            assert response.body() != null;
            String responseBody = response.body().string(); // obtiene la respuesta como String
            JSONObject jsonObject = new JSONObject(responseBody);

            //Maneja la respuesta
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
            //Obtiene la respuesta.
            assert response.body() != null;
            String responseBody = response.body().string();
            JSONObject jsonObject = new JSONObject(responseBody);

            //Maneja la respuesta
            if(jsonObject.has("result") && jsonObject.get("result") instanceof JSONObject) {
                return jsonObject.getJSONObject("result");
            } else {
                // Manejar el caso en que la respuesta no contenga un arreglo JSON bajo la clave "result"
                throw new JSONException("La clave 'result' no está presente o no es un objeto JSON en la respuesta: " + responseBody);
            }
        }
    }
    public JSONObject deleteProviders(String URL) throws IOException {
        RequestBody body = RequestBody.create("", this.mediaType); // cuerpo vacío para DELETE
        Request request = new Request.Builder()
                .url(API + URL)
                .delete(body) // Usamos el método delete
                .build();

        try (Response response = this.client.newCall(request).execute()) {
            // Obtener la respuesta
            assert response.body() != null;
            String responseBody = response.body().string();
            return new JSONObject(responseBody);
        }
    }
// metodo Update
        public JSONObject update(String URL, String json) throws IOException {
            RequestBody body = RequestBody.create(json, this.mediaType);
            Request request = new Request.Builder()
                    .url(API + URL)
                    .put(body) // Usamos el método put con el cuerpo de la solicitud
                    .build();

            try (Response response = this.client.newCall(request).execute()) {
                // Obtiene la respuesta
                assert response.body() != null;
                String responseBody = response.body().string();
                return new JSONObject(responseBody);
            }
        }
}
