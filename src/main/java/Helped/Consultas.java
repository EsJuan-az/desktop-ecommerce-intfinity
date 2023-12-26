package Helped;

import javafx.scene.control.TextField;
import okhttp3.OkHttpClient;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.IOException;

public class Consultas {

    public static String consulta(String email, String password) throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/json");
        String json = "{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}";
        RequestBody body = RequestBody.create(mediaType, json);

        Request request = new Request.Builder()
                .url("https://intfinity-enterprise-backend.onrender.com/api/company/1/user/login")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            } else {

                return "Error: " + response.code();
            }
        }

    }

    public static String AgregarP(String name, String Nit, String Direccion, String Phone, String email, String Descripcion) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n" +
                "    \"name\": \"" + name + "\",\r\n" +
                "    \"NIT\": \"" + Nit + "\",\r\n" +
                "    \"direction\": \"" + Direccion + "\",\r\n" +
                "    \"phone\": \"" + Phone + "\",\r\n" +
                "    \"email\": \"" + email + "\",\r\n" +
                "    \"description\": \"" + Descripcion + "\"\r\n" +
                "}");
        Request request = new Request.Builder()
                .url("https://intfinity-enterprise-backend.onrender.com/api/company/4/provider")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            // You should also check the response code to handle different HTTP responses appropriately.
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            // Assuming you want to return the response body as a string.
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception according to your requirements, like returning an error message.
            return "Error: " + e.getMessage();
        }
    }
}



