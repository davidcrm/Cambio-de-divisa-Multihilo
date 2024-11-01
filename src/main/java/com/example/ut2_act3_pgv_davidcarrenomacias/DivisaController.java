package com.example.ut2_act3_pgv_davidcarrenomacias;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Arrays;
import java.util.List;

public class DivisaController {
    @FXML
    private TextField cantidadTF;

    @FXML
    private ComboBox<String>  divisa1CB, divisa2CB, divisa3CB;
    @FXML
    private Label divisaLabel, divisa1Label, divisa2Label, divisa3Label;

    @FXML
    private Button convertirButton;

    public void initialize() {
        // Lista de divisas
        List<String> divisas = Arrays.asList(
                "MXN", // Pesos mexicanos
                "USD", // Dólares estadounidenses
                "GBP", // Libra esterlina
                "CHF", // Franco suizo
                "NOK", // Corona noruega
                "SEK", // Corona sueca
                "DKK", // Corona danesa
                "PLN", // Zloty polaco
                "HUF", // Forinto húngaro
                "CZK"  // Corona checa
                );

        // Agregar divisas a cada ComboBox
        divisa1CB.getItems().addAll(divisas);
        divisa2CB.getItems().addAll(divisas);
        divisa3CB.getItems().addAll(divisas);

        // Asignar el método convertir() al botón convertir para que se pueda ejecutar varias veces
        convertirButton.setOnAction(event -> convertir());
    }

    // Metodo para cambiar la divisa numero 1
    public void setDivisa1() {
        if (comprobarDatos(cantidadTF,divisaLabel,divisa1CB)) {
            String a_divisa = divisa1CB.getValue();
            double cantidad = obtenerCantidad();
            Conversor cv = new Conversor(divisaLabel.getText(), a_divisa, cantidad, divisa1Label);
            new Thread(cv).start();
        }
    }
    // Metodo para cambiar la divisa numero 2
    public void setDivisa2() {
        if (comprobarDatos(cantidadTF,divisaLabel,divisa2CB)) {
            String a_divisa = divisa2CB.getValue();
            double cantidad = obtenerCantidad();
            Conversor cv = new Conversor(divisaLabel.getText(), a_divisa, cantidad, divisa2Label);
            new Thread(cv).start();
        }
    }
    // Metodo para cambiar la divisa numero 3
    public void setDivisa3() {
        if (comprobarDatos(cantidadTF,divisaLabel,divisa3CB)) {
            String a_divisa = divisa3CB.getValue();
            double cantidad = obtenerCantidad();
            Conversor cv = new Conversor(divisaLabel.getText(), a_divisa, cantidad, divisa3Label);
            new Thread(cv).start();
        }
    }
    // Metodo para comprobar que los campos no están vacíos
    public boolean comprobarDatos(TextField tf, Label divisa, ComboBox<String> a_divisa) {
        if (tf.getText() != null && divisaLabel.getText() != null && a_divisa.getValue() != null) {
            return true;
        }
        return false;
    }
    // Obtiene la cantidad del textfield y comprueba que es de un formato valido e informa de no ser así
    private double obtenerCantidad() {
        try {
            return Double.parseDouble(cantidadTF.getText());
        } catch (NumberFormatException e) {
            informar("ERROR CON EL DATO CANTIDAD","Debes introducir un numero en el campo de texto.");
            return 0;
        }
    }
    // Crea un diálogo para informar al usuario
    private void informar(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Llama a los métodos para calcular las conversiones
    public void convertir() {
        if (divisaLabel.getText() == null || divisa1CB.getValue() == null || cantidadTF.getText().isEmpty()) {
            informar("ERROR, CAMPOS INCOMPLETOS", "Los campos deben estar rellenos antes de convertir.");
        }
        setDivisa1();
        setDivisa2();
        setDivisa3();
    }
}
