package com.controllers;

import com.controllers.subcontrollers.ProviderTab;
import com.helpers.GUIHandler;
import com.schemas.Provider;

import com.services.ProviderService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import org.json.JSONObject;

import java.io.IOException;


public class PrincipalController extends ProviderTab {

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
    private TableColumn<Provider, Integer> PProviderIdCol;


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
    @FXML
    private Button DeleteButon;
    @FXML
    private Button UpdateButton;


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



    private final ProviderTab providerTabController = new ProviderTab();

    @FXML
    public void initialize() {

        //Initialize subcontrollers
        providerTabController.setPrincipalController(this);
        //Show Buttons
        PShowProvidersButton.setOnAction(e -> onShowPro());
        PShowOrderButton.setOnAction(e -> onShowOrd());
        PShowPurchasesButton.setOnAction(e -> onShowCom());
        PShowClientButton.setOnAction(e -> onShowClient());
        PShowProductButton.setOnAction(e -> onShowProduc());
        PShowDataButton.setOnAction(e -> onShowInfor());
        PShowConfigButton.setOnAction(e -> onShowConfig());
        //Provider column fields
        PProviderNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        PProviderNITCol.setCellValueFactory(new PropertyValueFactory<>("NIT"));
        PProviderDirectionCol.setCellValueFactory(new PropertyValueFactory<>("direction"));
        PProviderPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));
        PProviderEmailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        PProviderDescCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        PProviderIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        //Event buttons
        BotonMenu.setOnAction(e -> toggleVBoxVisibility());
        AddProvider.setOnAction(e -> providerTabController.handleAddProvider());
        PSaveProviderButton.setOnAction(e -> providerTabController.handleSaveProvider());
        DeleteButon.setOnAction(e -> deleteUserSelected());
        UpdateButton.setOnAction(e -> upDate());
        //inicializador de tableview
        PProviderTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                // Aquí es donde manejas el evento de selección. Por ejemplo:
                onProviderSelected(newSelection);
            }
        });

    }

    int idG = 0;
    String NitG  = "";
    String nombreG = "";
    String CorreoG = "";
    String DireccionG = "";
    String DescripcionG = "";
    String NumeroG = "";
    private void onProviderSelected(Provider selectedProvider) {
        Provider selectedUser = PProviderTable.getSelectionModel().getSelectedItem();
        if(selectedUser != null){

            int id = selectedUser.getId();
            idG = id;
            String Nit = selectedUser.getNIT();
            NitG = Nit;
            String nombre = selectedUser.getName();
            nombreG = nombre;
            String Correo = selectedUser.getEmail();
            CorreoG = Correo;
            String Direccion = selectedUser.getDirection();
            CorreoG = Correo;
            String Descripcion = selectedUser.getDescription();
            DescripcionG = Descripcion;
            String Numero = selectedUser.getPhone();
            NumeroG =  Numero;

            PProviderNITField.setText(Nit);
            PProviderNameField.setText(nombre);
            PProviderEmailField.setText(Correo);
            PProviderDescriptionField.setText(Descripcion);
            PProviderPhoneField.setText(Numero);
            PProviderDirectionField.setText(Direccion);


        }else {
            System.out.printf("gay");
        }
    }

    private void onShowPro() {
        // Cambia siempre a la pestaña correspondiente, independientemente de la carga de datos
        PMainTabPane.getSelectionModel().select(PTabProviders);

        // Si los datos aún no se han cargado, entonces los carga
        providerTabController.loadProviders();
    }
    public void upDate(){
        Provider selectedUser = PProviderTable.getSelectionModel().getSelectedItem();
        String nitValue = getPProviderNITField().getText();
        String nombreValue = getPProviderNameField().getText();
        String direccionValue = getPProviderDirectionField().getText();
        String numeroValue = getPProviderPhoneField().getText();
        String correoValue = getPProviderEmailField().getText();
        String descripcionValue = getPProviderDescriptionField().getText();

        if (nitValue.isEmpty() || nombreValue.isEmpty() || numeroValue.isEmpty() || correoValue.isEmpty() || direccionValue.isEmpty() ) {
            GUIHandler.displayWarning("Campos Vacíos", null, "Por favor, selecciona una columna con datos");

        }else{
            try {
                // Llama al método delete de la clase ProviderService y pasa el ID del proveedor seleccionado
                JSONObject response = ProviderService.delete(idG);
                System.out.println("Proveedor editado exitosamente: " + response);

                // Si la eliminación en la base de datos fue exitosa, elimina el proveedor de la lista observable
                providers.remove(selectedUser);
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar el caso de error aquí - podría ser una notificación al usuario, por ejemplo
                System.out.println("Error al eliminar el proveedor: " + e.getMessage());
            }
        }

    }

    //Metodo para eliminar proveedores
    public void deleteUserSelected() {
        Provider selectedUser = PProviderTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            // Intenta eliminar el proveedor de la base de datos
            try {
                // Llama al método delete de la clase ProviderService y pasa el ID del proveedor seleccionado
                JSONObject response = ProviderService.delete(selectedUser.getId());
                System.out.println("Proveedor eliminado exitosamente: " + response);

                // Si la eliminación en la base de datos fue exitosa, elimina el proveedor de la lista observable
                providers.remove(selectedUser);
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar el caso de error aquí - podría ser una notificación al usuario, por ejemplo
                System.out.println("Error al eliminar el proveedor: " + e.getMessage());
            }
        } else {
            // Mostrar algún mensaje de error o notificación si no se seleccionó ningún usuario
            System.out.println("No se seleccionó ningún usuario para eliminar.");
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


    private void toggleVBoxVisibility() {
        MenudesP.setVisible(!MenudesP.isVisible());
    }




    //Getters
    public JSONObject getUser() {
        return user;
    }
    public VBox getMenudesP() {
        return MenudesP;
    }

    public Button getBotonMenu() {
        return BotonMenu;
    }

    public Button getPSaveProviderButton() {
        return PSaveProviderButton;
    }

    public TextField getPProviderNITField() {
        return PProviderNITField;
    }

    public TextField getPProviderNameField() {
        return PProviderNameField;
    }

    public TextField getPProviderDirectionField() {
        return PProviderDirectionField;
    }

    public TextField getPProviderPhoneField() {
        return PProviderPhoneField;
    }

    public TextField getPProviderEmailField() {
        return PProviderEmailField;
    }

    public TextField getPProviderDescriptionField() {
        return PProviderDescriptionField;
    }

    public TableView<Provider> getPProviderTable() {
        return PProviderTable;
    }

    public TableColumn<Provider, String> getPProviderNameCol() {
        return PProviderNameCol;
    }

    public TableColumn<Provider, String> getPProviderNITCol() {
        return PProviderNITCol;
    }

    public TableColumn<Provider, String> getPProviderDirectionCol() {
        return PProviderDirectionCol;
    }

    public TableColumn<Provider, String> getPProviderPhoneCol() {
        return PProviderPhoneCol;
    }

    public TableColumn<Provider, String> getPProviderEmailCol() {
        return PProviderEmailCol;
    }

    public TableColumn<Provider, String> getPProviderDescCol() {
        return PProviderDescCol;
    }
    public TableColumn<Provider, Integer> getPProviderIdCol() { return PProviderIdCol; }

    public TabPane getPMainTabPane() {
        return PMainTabPane;
    }

    public Button getPShowProvidersButton() {
        return PShowProvidersButton;
    }

    public Button getPShowOrderButton() {
        return PShowOrderButton;
    }

    public Button getPShowPurchasesButton() {
        return PShowPurchasesButton;
    }

    public Button getPShowClientButton() {
        return PShowClientButton;
    }

    public Button getPShowProductButton() {
        return PShowProductButton;
    }

    public Button getPShowDataButton() {
        return PShowDataButton;
    }

    public Button getPShowConfigButton() {
        return PShowConfigButton;
    }

    public Tab getPTabProviders() {
        return PTabProviders;
    }

    public Tab getPTabOrders() {
        return PTabOrders;
    }

    public Tab getPTabPurchases() {
        return PTabPurchases;
    }

    public Tab getPTabCustomers() {
        return PTabCustomers;
    }

    public Tab getPTabProducts() {
        return PTabProducts;
    }

    public Tab getPTabData() {
        return PTabData;
    }

    public Tab getPTabConfig() {
        return PTabConfig;
    }


}

