package edu.ntnu.IDATT2001.charlohc.WarGames.Controllers;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import javafx.event.ActionEvent;

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
