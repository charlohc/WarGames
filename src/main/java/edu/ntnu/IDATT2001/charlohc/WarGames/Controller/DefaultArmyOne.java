package edu.ntnu.IDATT2001.charlohc.WarGames.Controller;

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

/**
 * Shows default army one in table view
 */
public class DefaultArmyOne extends ChildController{
    UnitFactory unitFactory = new UnitFactory();

    @FXML
    public TableView<Unit> tableView;
    public TableColumn<Unit,String> typeColumn;
    public TableColumn<Unit,String> nameColumn;
    public TableColumn<Unit,Integer> healthColumn;
    public Text armyName;
    public Pane scenePane;
    private ObservableList<Unit> units = FXCollections.observableArrayList();
    private Army currentArmy;

    ObservableList<Unit> observableList = FXCollections.observableArrayList(
            unitFactory.createUnitByType(UnitTypeENUM.CAVALRY,"Knight",70),
            unitFactory.createUnitByType(UnitTypeENUM.COMMANDER,"Mountain King",80),
            unitFactory.createUnitByType(UnitTypeENUM.RANGED,"Archer",70),
            unitFactory.createUnitByType(UnitTypeENUM.INFANTRY, "FootMan",60)
    );

    /**
     * Sets de tableView with information about object parameters of the army
     */
    @Override
    public void load() {
        currentArmy = new Army("Humans", observableList);
        parent.currentArmyOne = currentArmy;

        armyName.setText(currentArmy.getName());

        numbersOfUnitText();

        tableView.setItems(units);
        typeColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("unitType"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("name"));
        healthColumn.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("health"));
        tableView.setItems(observableList);
    }

    /**
     * Text that shows how many of each unit type there is in the army
     */
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

    /**
     * methode to go back to earlier scene in program flow
     * @param event button goBack
     */
    public void goBack(ActionEvent event) {
        parent.show("Home.fxml");
    }

    /**
     * Methode to to to next scene in program flow
     * @param event button viweArmyTwo
     */
    public void viewArmyTwo(ActionEvent event) {
        parent.show("ViewDefaultArmyTwo.fxml");
    }
}
