package com.example.ut2_act3_pgv_davidcarrenomacias;

import javafx.application.Platform;
import javafx.scene.control.Label;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// PÁGINA API: https://fxratesapi.com/

public class Conversor implements Runnable{

    private String divisa;
    private String a_divisa;
    private double cantidad;

    private Label etiqueta;

    private static String API_URL = "https://api.fxratesapi.com/latest?api_key=";
    private static final String API_KEY = "fxr_live_6e05110eba66af3b84e69eb13a0924bdaa5a";

    public Conversor(String divisa, String a_divisa, double cantidad, Label etiqueta){
        this.divisa = divisa;
        this.a_divisa = a_divisa;
        this.cantidad = cantidad;
        this.etiqueta = etiqueta;
    }
    public double convertir() throws IOException {
        try {
            // Crea el link y establece la conexión con la API
            String urlString = API_URL + API_KEY;
            URL url = new URL(urlString);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            //Lee la salida de la API
            BufferedReader bf = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            StringBuilder respuesta = new StringBuilder();
            String linea;
            while ((linea = bf.readLine()) != null) {
                respuesta.append(linea);
            }
            bf.close();

            JSONObject respuestaEnJson = new JSONObject(respuesta.toString());
            // Contiene las clave-valor de las divisas
            JSONObject rates = respuestaEnJson.getJSONObject("rates");
            // guarda la clave-valor de la divisa elegida
            double rate = rates.getDouble(a_divisa);
            System.out.println(divisa);
            System.out.println(a_divisa);
            double resultado;
            if (divisa.equals(a_divisa)) {
                resultado = cantidad; // No hay conversión necesaria
            } else {
                resultado = cantidad * rate; // Conversión directa de divisa a divisa
            }
            /* PLATFORM.RUNLATER():
            Metodo de JavaFX para poder ejecutar hilos. Los añade a una cola especial de JavaFX para que se ejecute en
            su turno y evita concurrencia. Garantiza que la aplicación s ejecute de forma segura combinando los hilos con la UI*/
            Platform.runLater(() -> etiqueta.setText(String.format("%.2f %s", resultado, a_divisa)));
            return resultado;

        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void run() {
        try {
            convertir();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
