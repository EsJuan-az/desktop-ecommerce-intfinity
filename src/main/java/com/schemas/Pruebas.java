package com.schemas;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Pruebas extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Carga el archivo FXML
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/controllers/Principal.fxml")));
// Asegúrate de que la ruta al archivo FXML sea correcta.

            // Crea la escena con la raíz cargada desde el FXML
            Scene scene = new Scene(root);

            // Establece la escena en el escenario principal
            primaryStage.setScene(scene);

            // Configura el título de la ventana si lo deseas
            primaryStage.setTitle("Infinity Enterprise");

            // Muestra la ventana
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args); // Lanza la aplicación JavaFX
    }
}
