package com.intfinty.appintfinity;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import Helped.Consultas;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HelloController {

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
        String Respuesta = "";

        if (email.isEmpty() || password.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos Vacíos");
            alert.setHeaderText(null);
            alert.setContentText("Por favor, completa todos los campos para iniciar sesión.");
            alert.showAndWait();
        } else {
                try {
                    String respuesta = Consultas.consulta(email, password);
                    Respuesta = respuesta;
                        if (respuesta.contains("202")) {
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("Principal.fxml"));
                            Parent root = loader.load();
                            Stage stage = (Stage) loginButton.getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setScene(scene);
                            stage.show();
                        } else {
                            Alert alert = new Alert(Alert.AlertType.WARNING);
                            alert.setTitle("Credenciales inválidas");
                            alert.setHeaderText(null);
                            alert.setContentText("Usuario o contraseña incorrectos");
                            alert.showAndWait();;
                        }


                } catch (IOException e) {
                    e.printStackTrace();
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("No se pudo cargar la vista principal");
                    alert.setContentText("Hubo un problema al intentar cargar la vista principal.");
                    alert.showAndWait();
                }



        }

        JsonObject jsonResponse = JsonParser.parseString(Respuesta).getAsJsonObject();
        JsonObject result = jsonResponse.getAsJsonObject("result");
        String nombre = result.get("name").getAsString();
        String rol = result.get("rol").getAsString();
        String Id_empresa = result.get("CompanyId").getAsString();
    }
}

