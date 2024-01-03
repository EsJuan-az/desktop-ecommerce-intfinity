package com.controllers.subcontrollers;

import com.controllers.PrincipalController;
import com.helpers.GUIHandler;
import com.schemas.Provider;
import com.services.ProviderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import static com.helpers.GUIHandler.displayMessage;

public class ProviderTab {

    public int id;
    private PrincipalController principalController;

    private boolean providersLoaded = false;
    public  ObservableList<Provider> providers = FXCollections.observableArrayList();

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void loadProviders() {
        if( providersLoaded ) return;

        try {
            //Obtenemos todos los proveedores.
            JSONArray jsonArray = ProviderService.getAll();
            //Recorre el array creando y añadiendo providers a la lista.
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);
                addProvider( Provider.fromJSON( obj ) );
            }
            //Envía items a la tabla
            updateProviderTable();


            // Marcar que los datos se han cargado para evitar recargas en futuras llamadas
            this.providersLoaded = true;

        } catch (IOException e) {
            e.printStackTrace(); // Aquí deberías manejar el error más apropiadamente
        }
    }
    public void addProvider( Provider p ){
        this.providers.add( p );
    }
    private void updateProviderTable(){
        principalController.getPProviderTable().setItems(this.providers);
    }
    public void handleSaveProvider() {
        //Obtiene los valores
        String nitValue = principalController.getPProviderNITField().getText();
        String nombreValue = principalController.getPProviderNameField().getText();
        String direccionValue = principalController.getPProviderDirectionField().getText();
        String numeroValue = principalController.getPProviderPhoneField().getText();
        String correoValue = principalController.getPProviderEmailField().getText();
        String descripcionValue = principalController.getPProviderDescriptionField().getText();
        //Tratamos de hacer la petición
        Provider newProvider = new Provider(id, nitValue, nombreValue, direccionValue, numeroValue, correoValue, descripcionValue);


        if (nitValue.isEmpty() || nombreValue.isEmpty() || numeroValue.isEmpty() || correoValue.isEmpty() || direccionValue.isEmpty() ) {
            GUIHandler.displayWarning("Campos Vacíos", null, "Por favor, completa todos los campos");
        }else {
            try {
                JSONObject respuestagregarP = ProviderService.create(nombreValue, nitValue, direccionValue, numeroValue, correoValue, descripcionValue);
                id = respuestagregarP.getInt("id");

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

    public void handleAddProvider(){
        String nitValue = principalController.getPProviderNITField().getText();
        String nombreValue = principalController.getPProviderNameField().getText();
        String direccionValue = principalController.getPProviderDirectionField().getText();
        String numeroValue = principalController.getPProviderPhoneField().getText();
        String correoValue = principalController.getPProviderEmailField().getText();
        String descripcionValue = principalController.getPProviderDescriptionField().getText();
        Provider newProvider = new Provider(id, nitValue, nombreValue, direccionValue, numeroValue, correoValue, descripcionValue);
        this.handleClearProviderFields();

        try {
            String title = "Successful", headerText = null, content = "Proveedor Agregado exitosamente";
            displayMessage(title, headerText, content);
            ObservableList<Provider> providersCharge = principalController.getPProviderTable().getItems(); // Obtener la lista actual de proveedores
            providersCharge.add(newProvider); // Agregar el nuevo proveedor
            principalController.getPProviderTable().refresh(); // Refrescar la tabla
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void handleClearProviderFields() {
        principalController.getPProviderNameField().setText("");
        principalController.getPProviderNITField().setText("");
        principalController.getPProviderDirectionField().setText("");
        principalController.getPProviderPhoneField().setText("");
        principalController.getPProviderEmailField().setText("");
        principalController.getPProviderDescriptionField().setText("");
    }

}
