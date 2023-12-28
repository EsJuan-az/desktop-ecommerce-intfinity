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
        CRUDHandler instance = CRUDHandler.getInstance();
        return instance.post("/company/" + instance.companyId +  "/provider", body.toString());
    }
    public static JSONArray getAll() throws IOException{
        CRUDHandler instance = CRUDHandler.getInstance();

        return instance.getAll("/company/" + instance.companyId +  "/provider");
    }

        // Método para eliminar un proveedor
        public static JSONObject delete(int providerId) throws IOException {
            CRUDHandler instance = CRUDHandler.getInstance();
            return instance.deleteProviders("/company/" + instance.companyId + "/provider/" + providerId);
        }
        // Metodo para update
    public static JSONObject update(int providerId, String name) throws IOException {
        JSONObject body = new JSONObject();
        body.put("name", name);
        CRUDHandler instance = CRUDHandler.getInstance();
        return instance.update("/company/" + instance.companyId + "/provider/" + providerId, body.toString());
    }

}
