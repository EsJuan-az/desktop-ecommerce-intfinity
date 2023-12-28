package com.controllers;

import com.controllers.subcontrollers.ProviderTab;
import com.schemas.Provider;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import org.json.JSONObject;



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
        DeleteButon.setOnAction(e -> deleteUserSelected());
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
        //inicializador de tableview

    }

    private void onShowPro() {
        // Cambia siempre a la pestaña correspondiente, independientemente de la carga de datos
        PMainTabPane.getSelectionModel().select(PTabProviders);

        // Si los datos aún no se han cargado, entonces los carga
        providerTabController.loadProviders();
    }

    //Metodo para eliminar proveedores
    public void deleteUserSelected(){
        Provider selectedUser = PProviderTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            // Aquí asumimos que userData es tu lista observable
            providers.remove(selectedUser);
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

