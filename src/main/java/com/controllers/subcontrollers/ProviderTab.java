package com.controllers.subcontrollers;

import com.controllers.PrincipalController;
import com.schemas.Provider;
import com.services.ProviderService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import static com.helpers.GUIHandler.displayMessage;

public class ProviderTab {

    private PrincipalController principalController;

    private boolean providersLoaded = false;
    ObservableList<Provider> providers;

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void loadProviders() {
        if( providersLoaded ) return;

        try {
            //Hacemos una observable list de provider
            this.providers = FXCollections.observableArrayList();
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
        try {
            JSONObject newProvider = ProviderService.create(nombreValue,nitValue,direccionValue,numeroValue,correoValue,descripcionValue);
            System.out.println(newProvider.toString());
            //Añadimos el proveedor y actualizamos la tabla
            addProvider( Provider.fromJSON( newProvider ) );
            displayMessage("Successful", null, "Usuario guardado exitosamente");


        } catch (Exception e) {
            // Manejar la excepción, posiblemente mostrar un mensaje al usuario
            e.printStackTrace();
        }
    }
}
