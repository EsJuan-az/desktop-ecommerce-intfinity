package com.schemas;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.json.JSONObject;
public class Cliente {
    public static  Cliente fromJSON(JSONObject obj) {
        return new Cliente(
                obj.getString("email"),
                obj.getString("name"),
                obj.getString("last_name"),
                obj.getString("phone"),
                obj.getString("password")
        );
    }

    private final SimpleIntegerProperty email;
    private final SimpleIntegerProperty name;
    private final SimpleIntegerProperty last_Name;
    private final SimpleIntegerProperty phone;
    private final SimpleIntegerProperty password;

    public Cliente(String email, String name, String last_Name, String phone, String password) {
        this.email = new SimpleStringProperty(email);
        this.name = new SimpleStringProperty(name);
        this.last_Name = new SimpleStringProperty(last_Name);
        this.phone = new SimpleStringProperty(phone);
        this.password = new SimpleStringProperty(password);
    }

    // Getters aquí...
    public String getEmail() { return email.get(); }
    public String getName() { return name.get(); }
    public String getLastName() { return last_Name.get(); }
    public String getPhone() { return phone.get(); }
    public String getPassword() { return password.get(); }

    // Property getters...
    public SimpleStringProperty emailProperty() { return email; }
    public SimpleStringProperty nameProperty() { return name; }
    public SimpleStringProperty last_NameProperty() { return last_Name; }
    public SimpleStringProperty phoneProperty() { return phone; }
    public SimpleStringProperty passwordProperty() { return password; }
}
