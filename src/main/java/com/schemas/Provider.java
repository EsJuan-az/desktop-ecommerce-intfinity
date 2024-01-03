package com.schemas;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import org.json.JSONObject;

public class Provider {
    public static Provider fromJSON(JSONObject obj){
        return new Provider(
                obj.getInt("id"),
                obj.getString("name"),
                obj.getString("NIT"),
                obj.getString("direction"),
                obj.getString("phone"),
                obj.getString("email"),
                obj.getString("description")
        );
    }
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty NIT;
    private final SimpleStringProperty direction;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty email;
    private final SimpleStringProperty description;
    public Provider(int id, String name, String NIT, String direction, String phone, String email, String description) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.NIT = new SimpleStringProperty(NIT);
        this.direction = new SimpleStringProperty(direction);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.description = new SimpleStringProperty(description);
    }

    // Getters aquí...
    public Integer getid() { return id.get(); }
    public String getName() { return name.get(); }
    public String getNIT() { return NIT.get(); }
    public String getDirection() { return direction.get(); }
    public String getPhone() { return phone.get(); }
    public String getEmail() { return email.get(); }
    public String getDescription() { return description.get(); }
    public int getId(){
        return id.get();
    }



    // Setters aquí si son necesarios...
    // Property getters...
    public SimpleStringProperty nameProperty() { return name; }
    // ... otros property getters
}

