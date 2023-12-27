package com.controllers;

import com.clases.Provider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import com.services.ProviderService;
import org.json.JSONObject;
import org.json.JSONArray;
import java.io.IOException;



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
    private Button ShowPro;

    @FXML
    private Button ShowOrd;

    @FXML
    private Button ShowCom;

    @FXML
    private Button ShowClient;

    @FXML
    private Button ShowProduc;

    @FXML
    private Button ShowInfor;

    @FXML
    private Button ShowConfig;

    @FXML
    private Tab Tabpro;

    @FXML
    private Tab tabord;

    @FXML
    private Tab tabcomp;

    @FXML
    private Tab tabclient;

    @FXML
    private Tab tabproduc;

    @FXML
    private Tab tabinfor;

    @FXML
    private Tab tanconfig;

    @FXML
    private TableView<Provider> TabPro;
    @FXML
    private TableColumn<Provider, String> nameColumn;
    @FXML
    private TableColumn<Provider, String> nitColumn;
    @FXML
    private TableColumn<Provider, String> directionColumn;
    @FXML
    private TableColumn<Provider, String> phoneColumn;
    @FXML
    private TableColumn<Provider, String> emailColumn;
    @FXML
    private TableColumn<Provider, String> descriptionColumn;

    @FXML
    public void initialize() {
        /*this.user.getString("rol");
        this.user.getString("name");
        this.user.getString("last_name");*/
        ShowPro.setOnAction(e -> onShowPro());
        ShowOrd.setOnAction(e -> onShowOrd());
        ShowCom.setOnAction(e -> onShowCom());
        ShowClient.setOnAction(e -> onShowClient());
        ShowProduc.setOnAction(e -> onShowProduc());
        ShowInfor.setOnAction(e -> onShowInfor());
        ShowConfig.setOnAction(e -> onShowConfig());
        BotonMenu.setOnAction(e -> toggleVBoxVisibility());
        SavePro.setOnAction(e -> handleSaveAction());
    }

    private boolean isDataLoaded = false;
    private void onShowPro() {
        // Cambia siempre a la pestaña correspondiente, independientemente de la carga de datos
        myTabPane.getSelectionModel().select(Tabpro);

        // Si los datos aún no se han cargado, entonces los carga
        if (!isDataLoaded) {
            loadData();
        }
    }

    private void loadData() {
        try {
            JSONArray jsonArray = ProviderService.getAll();
            ObservableList<Provider> providers = FXCollections.observableArrayList();

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                providers.add(new Provider(
                        obj.getString("name"),
                        obj.getString("NIT"),
                        obj.getString("direction"),
                        obj.getString("phone"),
                        obj.getString("email"),
                        obj.getString("description")
                ));
            }

            TabPro.setItems(providers);
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            nitColumn.setCellValueFactory(new PropertyValueFactory<>("nit"));
            directionColumn.setCellValueFactory(new PropertyValueFactory<>("direction"));
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

            // Marcar que los datos se han cargado para evitar recargas en futuras llamadas
            isDataLoaded = true;

        } catch (IOException e) {
            e.printStackTrace(); // Aquí deberías manejar el error más apropiadamente
        }
    }

    private void onShowOrd(){
        myTabPane.getSelectionModel().select(tabord);
    }
    private void onShowClient(){
        myTabPane.getSelectionModel().select(tabclient);
    }
    private void onShowProduc(){
        myTabPane.getSelectionModel().select(tabproduc);
    }
    private void onShowInfor(){
        myTabPane.getSelectionModel().select(tabinfor);
    }
    private void onShowConfig(){
        myTabPane.getSelectionModel().select(tanconfig);
    }
    private void onShowCom(){
        myTabPane.getSelectionModel().select(tabcomp

        );
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

