package edu.ntnu.IDATT2001.charlohc.WarGames.gui;

import javafx.event.ActionEvent;

public class ImportArmiesController extends ChildController{
    @Override
    public void load() {

    }

    public void goBack(ActionEvent event) {
        parent.show("Home.fxml");
    }
}
