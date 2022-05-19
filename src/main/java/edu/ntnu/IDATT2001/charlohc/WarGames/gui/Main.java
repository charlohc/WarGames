package edu.ntnu.IDATT2001.charlohc.WarGames.gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

    public class Main extends Application {

        /**
         * Method to start the application.
         * @param stage the window of the application.
         */
        @Override
        public void start(Stage stage) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Main.fxml"));
            Parent root = loader.load();
            MainController controller = loader.getController();
            controller.hostServices = getHostServices();

            root.getStylesheets().add(getClass().getClassLoader().getResource("Style.css").toExternalForm());

            stage.setScene(new Scene(root));
            stage.setMinWidth(900);
            stage.setMinHeight(700);
            stage.show();

            controller.show("Home.fxml");

        }


        public static void main(String[] args){
            launch(args);
        }
    }


