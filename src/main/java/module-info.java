module com.intfinty.appintfinity {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires okhttp3;
    requires org.json;

    // Abre los paquetes al módulo javafx.fxml y javafx.base
    opens com.controllers to javafx.fxml;
    opens com.clases to javafx.base, javafx.fxml; // Asegúrate de que este es el paquete correcto de tu clase Provider
    opens com.services to javafx.fxml, javafx.base; // Abre si es necesario para reflexión

    exports com.controllers;
    exports com.services;
    // No es necesario exportar com.clases si solo se utiliza dentro del mismo módulo
}
