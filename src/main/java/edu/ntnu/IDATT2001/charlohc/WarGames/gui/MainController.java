package edu.ntnu.IDATT2001.charlohc.WarGames.gui;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.io.IOException;


public class MainController {

    @FXML public BorderPane scenePane;
    HostServices hostServices;
     boolean isLightMode = true;

    protected ObservableList<Army> armies = FXCollections.observableArrayList();
    protected Unit unit;
    protected Army currentArmyOne;
    protected Army currentArmyTwo;

    /**
     * Method to show a given .fxml file when executed.
     * @param filename the .fxml file that will be shown.
     */
    public void show(String filename) {
        FXMLLoader loader;

        try {
            loader = new FXMLLoader(getClass().getClassLoader().getResource(filename));
            scenePane.setCenter(loader.load());
        } catch (IOException ioe) {
            System.out.println("Failed to open child document, skipping");
            System.out.println(ioe);
            ioe.printStackTrace();
            return;
        }

        try {
            ChildController childController = loader.getController();
            childController.initialize(this);
        } catch (NullPointerException npe) {
            System.out.println("Failed to load child controller, skipping");
            System.out.println(npe);
            npe.printStackTrace();
        }
    }

    @FXML
    public void changeLight() {
        System.out.println("hei");
        isLightMode = !isLightMode;
        if(isLightMode) {
            System.out.println("hei");
            scenePane.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        }else{
            System.out.println("nei");
            scenePane.setBackground(new Background(new BackgroundFill(Color.WHITE,CornerRadii.EMPTY,Insets.EMPTY)));
        }


    }

    @FXML
    public void change(ActionEvent event) {
        System.out.println("hei");
    }
}
