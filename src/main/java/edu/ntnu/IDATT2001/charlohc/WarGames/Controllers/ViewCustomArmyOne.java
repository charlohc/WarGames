package edu.ntnu.IDATT2001.charlohc.WarGames.Controllers;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.FileHandling.WriteFile;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ViewCustomArmyOne extends ChildController {
    WriteFile writeFile = new WriteFile();

    @FXML
    public TableView<Unit> tableView;

    @FXML
    public TableColumn<Unit,String> typeColumn;
    public TableColumn<Unit,String> nameColumn;
    public TableColumn<Unit,Integer> healthColumn;

    @FXML
    public Pane scenePane, infoPane;
    public Button createArmyTwo;
    @FXML
    public Text armyName,info,numbCavalry,numbCommander,numbRanged,numbInfantry;

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
         numbCavalry = new Text("Cavalry units: " + currentArmy.getCavalryUnits().size());
        numbCavalry.setY(200);
        numbCavalry.setX(190);

         numbCommander = new Text("Commander units: " + currentArmy.getCommanderUnits().size());
        numbCommander.setY(200);
        numbCommander.setX(330);

         numbRanged = new Text("Ranged units: " + currentArmy.getRangedUnits().size());
        numbRanged.setY(200);
        numbRanged.setX(480);

         numbInfantry = new Text("Infantry units: " + currentArmy.getInfantryUnits().size());
        numbInfantry.setY(200);
        numbInfantry.setX(630);

        scenePane.getChildren().addAll(numbCavalry,numbCommander,numbRanged,numbInfantry);
    }

    public void numbersOfUnitTextReset(){
        numbCavalry.setText("");
        numbCommander.setText("");
        numbRanged.setText("");
        numbInfantry.setText("");
    }

    @FXML
    public void removeUnit() {
            Unit toRemove = tableView.getSelectionModel().getSelectedItem();
            units.remove(toRemove);
            currentArmy.getAllUnits().remove(toRemove);
            numbersOfUnitTextReset();
            numbersOfUnitText();
    }

    public void saveArmyOne(){
        if(parent.saveArmy()){
            writeFile.printTxt(currentArmy);
        }
    }

    public void goBack(ActionEvent event) {
        parent.show("CreateArmyOne.fxml");
    }

    public void createArmyTwo(ActionEvent event) {
        if(currentArmy.getAllUnits().size() < 1){
            infoPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
            infoPane.setBackground(new Background(new BackgroundFill(Color.web("#FE7658"),CornerRadii.EMPTY, Insets.EMPTY)));
            info.setText("There must be at least one unit in the army");
            createArmyTwo.setDisable(true);

        }else {
            saveArmyOne();
            parent.show("CreateArmyTwo.fxml");
        }
    }

}
