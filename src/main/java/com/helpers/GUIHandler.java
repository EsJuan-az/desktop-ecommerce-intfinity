package com.helpers;

import com.controllers.PrincipalController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.json.JSONObject;
import java.lang.Class;
import java.io.IOException;


public class GUIHandler{
    private static Stage stage;
    private static Class<?> sceneClass;
    public static void setStage(Stage stage ){
        GUIHandler.stage = stage;
    }
    public static void setClass( Class<?> sceneClass ){
        GUIHandler.sceneClass = sceneClass;
    }

    public static void displayWarning(String title, String headerText, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public static void displayError(String title, String headerText, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        alert.showAndWait();;
    }

    // Ventana emergente en principal
    public static void displayMessage(String title, String headerText, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(content);
        alert.showAndWait();;
    }
    public static void loadLogin() throws IOException {
        //Obtiene la vista
        FXMLLoader fxmlLoader = new FXMLLoader(sceneClass.getResource("Login.fxml"));
        //Carga la escena principal de login y crea la escena
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root, 265, 400);
        //Añade estilos a la vista
        scene.getStylesheets().add(sceneClass.getResource("assets/css/Login.css").toExternalForm());
        //Título principal de la vista.
        stage.setTitle("Bienvenido");
        stage.setScene(scene);
        stage.show();
    }

    public static void loadPrincipal( JSONObject user ) throws IOException{
        //Obtiene la vista y se carga.

        FXMLLoader fxmlLoader = new FXMLLoader(sceneClass.getResource("Principal.fxml"));
        System.out.println( fxmlLoader );
        //Carga la escena principal y crea la escena
        Parent root = fxmlLoader.load();
        PrincipalController controller = fxmlLoader.getController();
        controller.setUser( user );

        Scene scene = new Scene(root);
        //Añade estilos a la vista.

        //Título principal de la vista.
        stage.setTitle("Intfinity Enterprise");
        stage.setScene(scene);
        stage.show();
    }
}
