package edu.ntnu.IDATT2001.charlohc.WarGames.gui;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class ViewArmyTwo extends ChildController {

    @FXML
    public TableView<Unit> tableView;

    @FXML
    public TableColumn<Unit,String> typeColumn;
    public TableColumn<Unit,String> nameColumn;
    public TableColumn<Unit,Integer> healthColumn;

    @FXML
    public Text armyName;

    private ObservableList<Unit> units = FXCollections.observableArrayList();
    private Army currentArmy;

    @Override
    public void load() {
        currentArmy = parent.currentArmyTwo;
        this.units.addAll(currentArmy.getAllUnits());

        armyName.setText(currentArmy.getName() + " army");

        tableView.setItems(units);
        typeColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("unitType"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("name"));
        healthColumn.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("health"));
    }

    @FXML
    public void removeUnit() {
        Unit toRemove = tableView.getSelectionModel().getSelectedItem();
        units.remove(toRemove);
        currentArmy.getAllUnits().remove(toRemove);
    }

    public void battleScene(ActionEvent event) {
        parent.show("battle.fxml");
    }
    public void goBack(ActionEvent event) {
        parent.show("createArmyTwo.fxml");
    }
}