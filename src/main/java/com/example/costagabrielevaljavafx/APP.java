package com.example.costagabrielevaljavafx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;



/**
 * JavaFX App
 */
import javafx.scene.layout.VBox;

/**
 * JavaFX App
 */
public class APP extends Application {
    Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Evaluation JavaFx");


        Button button = new Button("Consulter les données");
        Button button_Telecharger = new Button("Telecharger le fichier");
        Button button_Telecharger_api = new Button("Telecharger le fichier format Json");
        Button button_MAJ = new Button("Mettre la météo a jour");
        Button button_bonus = new Button("Bonus");
        Button button_retour = new Button("Retour");
        Button button_retour_2 = new Button("Retour");
        TextField Nom_Ville = new TextField();
        TextField Nom_Ville_api = new TextField();
        Button button_get_api = new Button("Regardez l'api de la ville");


        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.getChildren().addAll(button, button_Telecharger, button_MAJ, button_bonus);
        Scene scene = new Scene(layout, 200, 200);
        VBox layout2 = new VBox();
        layout2.setAlignment(Pos.CENTER);
        layout2.setPadding(new Insets(20, 20, 20, 20));
        layout2.getChildren().addAll(Nom_Ville, button_Telecharger_api, button_retour);
        Scene scene2 = new Scene(layout2, 300, 300);
        VBox layout3 = new VBox(10);
        layout3.setPadding(new Insets(20, 20, 20, 20));
        layout3.getChildren().addAll(Nom_Ville_api, button_get_api, button_retour_2);
        Scene scene3 = new Scene(layout3, 200, 200);
        button_retour.setOnAction(e -> primaryStage.setScene(scene));
        button_retour_2.setOnAction(e -> primaryStage.setScene(scene));

        button.setOnAction(e -> primaryStage.setScene(scene3));
        button_Telecharger.setOnAction(e -> primaryStage.setScene(scene2));
        Text api_donne = new Text();
        AtomicReference<String> api_string = new AtomicReference<>("");
        button_get_api.setOnAction(e -> {
            try {
                api_string.set(Api(Nom_Ville_api.getText()));
                api_donne.setText(api_string.get());
                layout3.getChildren().setAll(Nom_Ville_api, button_get_api, button_retour_2, api_donne);
                primaryStage.setScene(scene3);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        });
        api_donne.setText(api_string.get());;
//        button_Telecharger_api.setOnAction(e -> WeatherAPI(Nom_Ville.getText()));

        //button_get_api.setOnAction(e ->;
        window.setScene(scene);
        window.show();

        
    }

    public static String Api(String message) throws Exception {
        String message_ville = message;
        String url = "https://api.weatherbit.io/v2.0/current?&city=" + message_ville + "&key=876c70d7741f4bb384a906aa02a19053";
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");

        BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = input.readLine()) != null) {
            response.append(line);
        }
        input.close();
        Text api_donne = new Text();
        String api_string = String.valueOf(response);
        api_donne.setText(api_string);
        System.out.println(response.toString());
        return api_string;
    }
//    //public void WeatherAPI(String ville) throws IOException {
//        String ville_api = ville;
//        final String API_URL = "https://api.weatherbit.io/v2.0/current?&city="+ville_api+"&key=876c70d7741f4bb384a906aa02a19053";
//        final String OUTPUT_FILE = "meteo-"+ville_api+".json";
//
//        public static void downloadData() throws Object IOException;{
//            URL url = new URL(API_URL);
//            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
//
//            FileWriter writer = new FileWriter(OUTPUT_FILE);
//            String line;
//            while ((line = reader.readLine()) != null) {
//                writer.write(line);
//            }
//
//            reader.close();
//            writer.close();
//        }
//    }
}






