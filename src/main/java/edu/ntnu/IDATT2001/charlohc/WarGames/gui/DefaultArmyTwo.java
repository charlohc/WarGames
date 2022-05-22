package edu.ntnu.IDATT2001.charlohc.WarGames.gui;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;
import edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory.UnitFactory;
import edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory.UnitTypeENUM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class DefaultArmyTwo extends ChildController{

    UnitFactory unitFactory = new UnitFactory();

    @FXML
    public TableView<Unit> tableView;

    @FXML
    public TableColumn<Unit,String> typeColumn;
    public TableColumn<Unit,String> nameColumn;
    public TableColumn<Unit,Integer> healthColumn;

    @FXML
    public Text armyName;

    @FXML
    public Pane scenePane;

    private ObservableList<Unit> units = FXCollections.observableArrayList();
    private Army currentArmy;


    ObservableList<Unit> observableList = FXCollections.observableArrayList(
            unitFactory.createUnitByType(UnitTypeENUM.CAVALRY,"Raider",70),
            unitFactory.createUnitByType(UnitTypeENUM.COMMANDER,"Gul dan",80),
            unitFactory.createUnitByType(UnitTypeENUM.RANGED,"SpearMan",70),
            unitFactory.createUnitByType(UnitTypeENUM.INFANTRY, "Grunt",60)
    );

    @Override
    public void load() {
        currentArmy = new Army("Orcs", observableList);
        parent.currentArmyTwo = currentArmy;

        armyName.setText(currentArmy.getName());

        numbersOfUnitText();

        tableView.setItems(units);
        typeColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("unitType"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("name"));
        healthColumn.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("health"));
        tableView.setItems(observableList);
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

        Text numbInfantry = new Text("Infantry units: " + currentArmy.getCavalryUnits().size());
        numbInfantry.setY(200);
        numbInfantry.setX(630);

        scenePane.getChildren().addAll(numbCavalry,numbCommander,numbRanged,numbInfantry);
    }


    public void goBack(ActionEvent event) {
        parent.show("Home.fxml");
    }

    public void viewArmyTwo(ActionEvent event) {
        parent.show("battle.fxml");
    }
}
