package com.clases;

import javafx.beans.property.SimpleStringProperty;

public class Provider {
    private final SimpleStringProperty name;
    private final SimpleStringProperty nit;
    private final SimpleStringProperty direction;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty email;
    private final SimpleStringProperty description;

    public Provider(String name, String nit, String direction, String phone, String email, String description) {
        this.name = new SimpleStringProperty(name);
        this.nit = new SimpleStringProperty(nit);
        this.direction = new SimpleStringProperty(direction);
        this.phone = new SimpleStringProperty(phone);
        this.email = new SimpleStringProperty(email);
        this.description = new SimpleStringProperty(description);
    }

    // Getters aquí...
    public String getName() { return name.get(); }
    public String getNit() { return nit.get(); }
    public String getDirection() { return direction.get(); }
    public String getPhone() { return phone.get(); }
    public String getEmail() { return email.get(); }
    public String getDescription() { return description.get(); }

    // Setters aquí si son necesarios...
    // Property getters...
    public SimpleStringProperty nameProperty() { return name; }
    // ... otros property getters
}

