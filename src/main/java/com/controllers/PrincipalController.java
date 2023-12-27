package com.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import com.services.ProviderService;
import org.json.JSONObject;


public class PrincipalController {

    private JSONObject user;
    public void setUser( JSONObject user ){
        this.user = user;
    }

    @FXML
    private VBox MenudesP;

    @FXML
    private Button BotonMenu;

    @FXML
    private Button SavePro;

    @FXML
    private TextField Nit;

    @FXML
    private TextField Nombre;

    @FXML
    private TextField DireccionPro;

    @FXML
    private TextField Numero;

    @FXML
    private TextField Correo;
    @FXML
    private TabPane myTabPane;

    @FXML
    private TextField Descripcion;
    @FXML
    private TableView<?> TabPro;
    @FXML
    private Button ShowPro;
    @FXML
    private Tab Tabpro;

    @FXML
    public void initialize() {
        /*this.user.getString("rol");
        this.user.getString("name");
        this.user.getString("last_name");*/
        ShowPro.setOnAction(e -> onShowPro());
        BotonMenu.setOnAction(e -> toggleVBoxVisibility());
        SavePro.setOnAction(e -> handleSaveAction());
    }
    private void onShowPro(){
        myTabPane.getSelectionModel().select(Tabpro);

    }
    private void handleSaveAction() {
        String nitValue = Nit.getText();
        String nombreValue = Nombre.getText();
        String direccionValue = DireccionPro.getText();
        String numeroValue = Numero.getText();
        String correoValue = Correo.getText();
        String descripcionValue = Descripcion.getText();
        try {
            JSONObject respuestagregarP = ProviderService.create(nombreValue,nitValue,direccionValue,numeroValue,correoValue,descripcionValue);
            System.out.println(respuestagregarP.toString());
        } catch (Exception e) {
            // Manejar la excepción, posiblemente mostrar un mensaje al usuario
            e.printStackTrace();
        }
    }

    private void toggleVBoxVisibility() {
        MenudesP.setVisible(!MenudesP.isVisible());
    }

}

