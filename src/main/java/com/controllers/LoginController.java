package com.controllers;
import com.helpers.GUIHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;
import org.json.JSONObject;
import com.services.UserService;

public class LoginController {

    @FXML
    private TextField emailField; // Asegúrate de que el FXML tiene un TextField con fx:id="emailField"

    @FXML
    private PasswordField passwordField; // Asegúrate de que el FXML tiene un PasswordField con fx:id="passwordField"

    @FXML
    private Button loginButton; // Asegúrate de que el FXML tiene un Button con fx:id="loginButton"

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            //Aquí alerta para errores del servidor o de credenciales.
            GUIHandler.displayWarning("Campos Vacíos", null, "Por favor, completa todos los campos para iniciar sesión.");
            return;
        }
        try {
            JSONObject response = UserService.login(email, password);
            JSONObject user;

            //Aquí vemos si la validación fue aceptada
            if (response.getInt("statusCode") == 202) {

                //Obtenemos el usuario de la respuesta
                user = response.getJSONObject("result");

                //Cargamos la pantalla principal.
                GUIHandler.loadPrincipal( user );
            } else {
                //Aquí alerta para errores del servidor o de credenciales.
                GUIHandler.displayWarning("Credenciales inválidas", null, "Usuario o contraseña incorrectos" );
            }


        } catch (IOException | AssertionError e) {
            //Aquí una alerta de error. Para errores de la app.
            e.printStackTrace();
            GUIHandler.displayError("Error al cargar la vista", null, "No se logró cargar esta vista, intentelo denuevo en unos segundos o comuniquese con un responsable.");
        }

    }
}

