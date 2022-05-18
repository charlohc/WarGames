package edu.ntnu.IDATT2001.charlohc.WarGames.gui;

import javafx.event.ActionEvent;

public class HomeController extends ChildController {

    @Override
    public void load() {

    }

    public void createArmies(ActionEvent event) {
        parent.show("createArmyOne.fxml");
    }

    public void importArmies(ActionEvent event) {
        parent.show("importArmies.fxml");
    }

    public void defaultArmies(ActionEvent event) {
        parent.show("defaultArmies.fxml");
    }
}
