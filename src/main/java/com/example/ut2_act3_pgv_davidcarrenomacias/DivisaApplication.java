package com.example.ut2_act3_pgv_davidcarrenomacias;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class DivisaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(DivisaApplication.class.getResource("CambioDivisas.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Cambio De Divisa");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}