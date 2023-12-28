package com.controllers;

import com.helpers.GUIHandler;
import com.schemas.Provider;
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

import static com.helpers.GUIHandler.displayMessage;


public class PrincipalController {

    private JSONObject user;
    public void setUser( JSONObject user ){
        this.user = user;
    }

    @FXML
    private VBox MenudesP;

    @FXML
    private Button BotonMenu;
    //Provider Form field
    @FXML
    private Button PSaveProviderButton;
    @FXML
    private TextField PProviderNITField;
    @FXML
    private TextField PProviderNameField;
    @FXML
    private TextField PProviderDirectionField;
    @FXML
    private TextField PProviderPhoneField;
    @FXML
    private TextField PProviderEmailField;
    @FXML
    private TextField PProviderDescriptionField;

    //Tab Pane
    @FXML
    private TabPane PMainTabPane;

    //Change tab buttons.
    @FXML
    private Button PShowProvidersButton;
    @FXML
    private Button PShowOrderButton;
    @FXML
    private Button PShowPurchasesButton;
    @FXML
    private Button PShowClientButton;
    @FXML
    private Button PShowProductButton;
    @FXML
    private Button PShowDataButton;
    @FXML
    private Button PShowConfigButton;
    @FXML
    private Button AddProvider;


    //Tabs
    @FXML
    private Tab PTabProviders;
    @FXML
    private Tab PTabOrders;
    @FXML
    private Tab PTabPurchases;
    @FXML
    private Tab PTabCustomers;
    @FXML
    private Tab PTabProducts;
    @FXML
    private Tab PTabData;
    @FXML
    private Tab PTabConfig;


    //Table and columns
    @FXML
    private TableView<Provider> PProviderTable;
    @FXML
    private TableColumn<Provider, String> PProviderNameCol;
    @FXML
    private TableColumn<Provider, String> PProviderNITCol;
    @FXML
    private TableColumn<Provider, String> PProviderDirectionCol;
    @FXML
    private TableColumn<Provider, String> PProviderPhoneCol;
    @FXML
    private TableColumn<Provider, String> PProviderEmailCol;
    @FXML
    private TableColumn<Provider, String> PProviderDescCol;

    @FXML
    public void initialize() {
        AddProvider.setOnAction(e -> addPro());
        PShowProvidersButton.setOnAction(e -> onShowPro());
        PShowOrderButton.setOnAction(e -> onShowOrd());
        PShowPurchasesButton.setOnAction(e -> onShowCom());
        PShowClientButton.setOnAction(e -> onShowClient());
        PShowProductButton.setOnAction(e -> onShowProduc());
        PShowDataButton.setOnAction(e -> onShowInfor());
        PShowConfigButton.setOnAction(e -> onShowConfig());
        BotonMenu.setOnAction(e -> toggleVBoxVisibility());
        PSaveProviderButton.setOnAction(e -> handleSaveProvider());
        PProviderNameCol.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        PProviderNITCol.setCellValueFactory(new PropertyValueFactory<>("nit"));
        PProviderDirectionCol.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        PProviderPhoneCol.setCellValueFactory(new PropertyValueFactory<>("numero"));
        PProviderEmailCol.setCellValueFactory(new PropertyValueFactory<>("correo"));
        PProviderDescCol.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
    }

    private boolean providersLoaded = false;
    private void onShowPro() {
        // Cambia siempre a la pestaña correspondiente, independientemente de la carga de datos
        PMainTabPane.getSelectionModel().select(PTabProviders);

        // Si los datos aún no se han cargado, entonces los carga
        if (!providersLoaded) {
            loadProviders();
        }

    }
    private void loadProviders() {
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

            PProviderTable.setItems(providers);
            PProviderNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            PProviderNITCol.setCellValueFactory(new PropertyValueFactory<>("nit"));
            PProviderDirectionCol.setCellValueFactory(new PropertyValueFactory<>("direction"));
            PProviderPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
            PProviderEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
            PProviderDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));

            // Marcar que los datos se han cargado para evitar recargas en futuras llamadas
            providersLoaded = true;

        } catch (IOException e) {
            e.printStackTrace(); // Aquí deberías manejar el error más apropiadamente
        }
    }

    private void onShowOrd(){
        PMainTabPane.getSelectionModel().select(PTabOrders);
    }
    private void onShowClient(){
        PMainTabPane.getSelectionModel().select(PTabCustomers);
    }
    private void onShowProduc(){
        PMainTabPane.getSelectionModel().select(PTabProducts);
    }
    private void onShowInfor(){
        PMainTabPane.getSelectionModel().select(PTabData);
    }
    private void onShowConfig(){
        PMainTabPane.getSelectionModel().select(PTabConfig);
    }
    private void onShowCom(){
        PMainTabPane.getSelectionModel().select(PTabPurchases);
    }

    private void clearProviderFields() {
        PProviderNITField.setText("");
        PProviderNameField.setText("");
        PProviderDirectionField.setText("");
        PProviderPhoneField.setText("");
        PProviderEmailField.setText("");
        PProviderDescriptionField.setText("");
    }


    private void addPro(){
        String nitValue = PProviderNITField.getText();
        String nombreValue = PProviderNameField.getText();
        String direccionValue = PProviderDirectionField.getText();
        String numeroValue = PProviderPhoneField.getText();
        String correoValue = PProviderEmailField.getText();
        String descripcionValue = PProviderDescriptionField.getText();
        Provider newProvider = new Provider(nitValue, nombreValue, direccionValue, numeroValue, correoValue, descripcionValue);
        clearProviderFields();

        try {
            String title = "Successful", headerText = null, content = "Proveedor Agregado exitosamente";
            displayMessage(title, headerText, content);
            ObservableList<Provider> providersCharge = PProviderTable.getItems(); // Obtener la lista actual de proveedores
            providersCharge.add(newProvider); // Agregar el nuevo proveedor
            PProviderTable.refresh(); // Refrescar la tabla
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void handleSaveProvider() {
        String nitValue = PProviderNITField.getText();
        String nombreValue = PProviderNameField.getText();
        String direccionValue = PProviderDirectionField.getText();
        String numeroValue = PProviderPhoneField.getText();
        String correoValue = PProviderEmailField.getText();
        String descripcionValue = PProviderDescriptionField.getText();

        Provider newProvider = new Provider(nitValue, nombreValue, direccionValue, numeroValue, correoValue, descripcionValue);


        if (nitValue.isEmpty() || nombreValue.isEmpty() || numeroValue.isEmpty() || correoValue.isEmpty() || direccionValue.isEmpty() ) {

            GUIHandler.displayWarning("Campos Vacíos", null, "Por favor, completa todos los campos");
        }else{

            try {
                JSONObject respuestagregarP = ProviderService.create(nombreValue, nitValue, direccionValue, numeroValue, correoValue, descripcionValue);
                System.out.println(respuestagregarP.toString());

                // Mensaje de éxito
                String title = "Successful", headerText = null, content = "Proveedor guardado exitosamente";
                displayMessage(title, headerText, content);

                // Actualizar la tabla

            } catch (Exception e) {
                // Manejar la excepción, posiblemente mostrar un mensaje al usuario
                e.printStackTrace();
            }
        }
    }

    private void toggleVBoxVisibility() {
        MenudesP.setVisible(!MenudesP.isVisible());
    }

}

