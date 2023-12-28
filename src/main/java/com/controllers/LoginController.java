package com.controllers;
import com.helpers.CRUDHandler;
import com.helpers.GUIHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;
import com.services.UserService;

public class LoginController {

    @FXML
    private TextField LEmailField; // Asegúrate de que el FXML tiene un TextField con fx:id="emailField"

    @FXML
    private PasswordField LPasswordField; // Asegúrate de que el FXML tiene un PasswordField con fx:id="passwordField"

    @FXML
    private Button LSubmitButton; // Asegúrate de que el FXML tiene un Button con fx:id="loginButton"

    @FXML
    protected void onLoginButtonClick(ActionEvent event) {
        String email = LEmailField.getText();
        String password = LPasswordField.getText();


        //Validación frontend
        if (email.isEmpty() || password.isEmpty()) {
            //Aquí alerta para errores del servidor o de credenciales.
            GUIHandler.displayWarning("Campos Vacíos", null, "Por favor, completa todos los campos para iniciar sesión.");
            return;
        } else {

            //Validación
            try {
                //Obtenemos el usuario de la respuesta
                JSONObject user = UserService.login(email, password);
                ;

                //En el administrador de peticiones añadimos el id de empresa
                CRUDHandler.getInstance()
                        .setCompanyId(user.getInt("CompanyId"));

                //Cargamos la pantalla principal.
                GUIHandler.loadPrincipal(user);

            } catch (IOException | AssertionError e) {
                //Aquí una alerta de error. Para errores de la app.
                e.printStackTrace();
                GUIHandler.displayError("Error al cargar la vista", null, "No se logró cargar esta vista, intentelo denuevo en unos segundos o comuniquese con un responsable.");
            } catch (JSONException e) {
                e.printStackTrace();
                //Aquí alerta para errores del servidor o de credenciales.
                GUIHandler.displayWarning("Credenciales inválidas", null, "Usuario o contraseña incorrectos");
            }

        }

    }
}

