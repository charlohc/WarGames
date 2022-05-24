package edu.ntnu.IDATT2001.charlohc.WarGames.Controller;

import javafx.event.ActionEvent;

/**
 * Home screen, gives the user three options
 */
public class HomeController extends ChildController {

    @Override
    public void load() {
    }

    public void createArmies(ActionEvent event) {
        parent.show("CreateArmyOne.fxml");
    }

    public void importArmies(ActionEvent event) {
        parent.show("ImportArmies.fxml");
    }

    public void defaultArmies(ActionEvent event) {
        parent.show("ViewDefaultArmyOne.fxml");
    }
}
