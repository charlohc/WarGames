package edu.ntnu.IDATT2001.charlohc.WarGames.Controllers;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javax.swing.*;
import java.io.IOException;

    public class Main extends Application {
        MainController mainController = new MainController();

        /**
         * Method to start the application.
         *
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


        public static void main(String[] args) {
            launch(args);
        }
    }

