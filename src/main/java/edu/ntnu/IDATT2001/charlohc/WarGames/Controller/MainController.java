package edu.ntnu.IDATT2001.charlohc.WarGames.Controller;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.Battle;
import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/*
Imported from group project IDATT2001
Abstract child controller class.
 */

public class MainController extends Component {

    @FXML public BorderPane scenePane;
    HostServices hostServices;

    protected ObservableList<Army> armies = FXCollections.observableArrayList();
    protected Army currentArmyOne,currentArmyTwo;
    protected Battle battle;
    protected TerrainTypesENUM terrainTypes;
    protected Boolean customArmy, importedArmies;


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

    /**
     * Shows dialog box to user with the message if they want to save army to file or not
     * if they choose yes it returns true, if they choose no it returns false
     * @return boolean true og false
     */
    public Boolean saveArmy() {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Do you want to save your army?", "Save", dialogButton);
            if (dialogResult == 0) {
                return true;
            } else {
                return false;
            }
    }
}
