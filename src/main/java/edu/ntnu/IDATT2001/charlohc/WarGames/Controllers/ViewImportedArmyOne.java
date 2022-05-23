package edu.ntnu.IDATT2001.charlohc.WarGames.Controllers;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class ViewImportedArmyOne extends ChildController {

        @FXML
        public TableView<Unit> tableView;

        @FXML
        public TableColumn<Unit,String> typeColumn;
        public TableColumn<Unit,String> nameColumn;
        public TableColumn<Unit,Integer> healthColumn;
        @FXML
        Pane scenePane;
        @FXML
        public Text armyName;

        private ObservableList<Unit> units = FXCollections.observableArrayList();
        private Army currentArmy;

        @Override
        public void load() {
            currentArmy = parent.currentArmyOne;
            this.units.addAll(currentArmy.getAllUnits());

            armyName.setText(currentArmy.getName() + " army");

            tableView.setItems(units);
            typeColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("unitType"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("name"));
            healthColumn.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("health"));

            numbersOfUnitText();
        }


    public void numbersOfUnitText(){
        Text numbCavalry = new Text("Cavalry units: " + currentArmy.getCavalryUnits().size());
        numbCavalry.setY(200);
        numbCavalry.setX(190);

        Text numbCommander = new Text("Commander units: " + currentArmy.getCommanderUnits().size());
        numbCommander.setY(200);
        numbCommander.setX(330);

        Text numbRanged = new Text("Ranged units: " + currentArmy.getRangedUnits().size());
        numbRanged.setY(200);
        numbRanged.setX(480);

        Text numbInfantry = new Text("Infantry units: " + currentArmy.getInfantryUnits().size());
        numbInfantry.setY(200);
        numbInfantry.setX(630);

        scenePane.getChildren().addAll(numbCavalry,numbCommander,numbRanged,numbInfantry);
    }

        public void goBack(ActionEvent event) {
            if(parent.importedArmies){
                parent.show("ImportArmies.fxml");
            }else {
                parent.show("Home.fxml");
            }
        }

    public void viewImportedArmyTwo(ActionEvent event) {
            parent.show("ViewImportedArmyTwo.fxml");
    }
}
