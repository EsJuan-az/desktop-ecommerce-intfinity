package com.controllers;

import com.helpers.GUIHandler;
import javafx.application.Application;

import javafx.stage.Stage;
import java.io.IOException;

public class MainController extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Seteamos los valores del escenario y la clase.
        GUIHandler.setStage( stage );
        GUIHandler.setClass( getClass() );
        //Carga el login.
        GUIHandler.loadLogin();
    }

    public static void main(String[] args) {
        //We deploy the login view
        launch();
    }
}
