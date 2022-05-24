package edu.ntnu.IDATT2001.charlohc.WarGames.Controller;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;
import edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory.UnitFactory;
import edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory.UnitTypeENUM;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.List;

/**
 * Creates army one
 */
public class CreateArmyOne extends ChildController{
    private ObservableList<Unit> units = FXCollections.observableArrayList();
    private Army currentArmy;
    UnitFactory unitFactory;
    UnitTypeENUM unitType;
    Unit newUnit, testUnit;
    List<Unit> newUnits;
    Boolean unitTypeSelected = false;
    int numbersOfUnits = 0;

    @FXML
    ImageView calvaryImgView, commanderImgView, infantryImgView, rangedImgView;
    public Pane cavalry, commander, infantry, ranged,infoPane;
    public TextField nameArmy,nameUnit;
    public Button addUnit,addFiveUnits,viewArmy, confirm;
    public Text armour,attack,info;
    public Spinner<Integer> health;

    Image calvaryImg = new Image("file:img/cavalry.png");
    Image commanderImg = new Image("file:img/commander.png");
    Image infantryImg = new Image("file:img/infantry.png");
    Image rangedImg = new Image("file:img/ranged.png");


    /**
     * Retains the information from parent on army one
     * if army one already exist, the name will appear in the name army text-field, and the user will be able to go to next scene
     * Textfield numberOfUnits tells how many units there are currently in the current army
     */
    @Override
    public void load() {
    currentArmy = parent.currentArmyOne;
    if(parent.currentArmyOne != null){
        nameArmy.setText(currentArmy.getName());

        viewArmy.setDisable(false);
        addUnit.setDisable(false);
        addFiveUnits.setDisable(false);

        numbersOfUnits = currentArmy.getAllUnits().size();

    }
    calvaryImgView.setImage(calvaryImg);
    commanderImgView.setImage(commanderImg);
    infantryImgView.setImage(infantryImg);
    rangedImgView.setImage(rangedImg);

        SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(20, 100);
        health.setValueFactory(valueFactory1);
        valueFactory1.setValue(20);

        unitFactory = new UnitFactory();
    }

    /**
     * Creates a new army with input from user, if name contains comma or is blank exeption will be thorwn
     * @param event button confirm
     */
    public void newArmy(ActionEvent event) {
        if(nameArmy.getText().isBlank()){
            invalidArmy();

        }else {
            currentArmy = new Army(nameArmy.getText());

            if(currentArmy.getName().contains(",")){
                invalidArmy();
            }else {
                info.setText(nameArmy.getText() + " is almost ready for battle, only needs units...");
                infoPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
                infoPane.setBackground(new Background(new BackgroundFill(Color.web("#ACCFAA"),CornerRadii.EMPTY,Insets.EMPTY)));
                addUnit.setDisable(false);
                addFiveUnits.setDisable(false);
                viewArmy.setDisable(false);

                parent.currentArmyOne = currentArmy;
                numbersOfUnits = 0;
            }
        }
    }

    /**
     * Creates a new unit based on the user input, if the name is blank or contains comma, or the unit type is not selected the user willl
     * not add unit
     * Adds unit to list of all units
     * increases numbers of units in textfield numbersOfUNits
     * @param event add button
     */
    public void addUnit(MouseEvent event) {
        if(nameUnit.getText().isBlank() || nameUnit.getText().contains(",") || unitType == null){
          invalidUnit();

        }else {
            newUnit = unitFactory.createUnitByType(unitType, nameUnit.getText(), health.getValue());

                units.add(newUnit);

                currentArmy.addUnit(newUnit);

                nameUnit.clear();

                numbersOfUnits++;
                info();
        }
    }

    /**
     * Creates five new unit based on the user input, if the name is blank or contains comma, or the unit type is not selected the user willl
     * not add unit
     * Adds units to unit list
     * increases numbers of units
     * @param event
     */
    public void addFiveUnits(ActionEvent event){
        if(nameUnit.getText().isBlank() || nameUnit.getText().contains(",") || unitType == null ){
           invalidUnit();

        }else {
            newUnits = unitFactory.createListOfUnits(5, unitType, nameUnit.getText(), health.getValue());

                units.addAll(newUnit);
                currentArmy.addAllUnits(newUnits);

                nameUnit.clear();
                numbersOfUnits += 5;
                info();

        }
    }

    /**
     * Shows how many units there are in the army
     */
    public void info(){
        infoPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        infoPane.setBackground(new Background(new BackgroundFill(Color.web("#ACCFAA"),CornerRadii.EMPTY,Insets.EMPTY)));
        if(numbersOfUnits > 0){
            info.setText("Numbers of units registered : " + numbersOfUnits);
        }
    }

    /**
     * Register which pane the mouse clicks on, and gets the id of the pane, sets the id of the pane to the unittype
     * @param mouseEvent cavalryPane, commanderPane,infantryPane,rangedPane
     */
    public void selectedUnitType(MouseEvent mouseEvent) {
        Pane pane = (Pane) mouseEvent.getSource();

        if (unitTypeSelected) {
            cavalry.setBackground(new Background(new BackgroundFill(Color.web("#FAFAFA"), new CornerRadii(0), Insets.EMPTY)));
            commander.setBackground(new Background(new BackgroundFill(Color.web("#FAFAFA"), new CornerRadii(0), Insets.EMPTY)));
            infantry.setBackground(new Background(new BackgroundFill(Color.web("#FAFAFA"), new CornerRadii(0), Insets.EMPTY)));
            ranged.setBackground(new Background(new BackgroundFill(Color.web("#FAFAFA"), new CornerRadii(0), Insets.EMPTY)));

        }
        pane.setBackground(new Background(new BackgroundFill(Color.web("#228811"), new CornerRadii(0), Insets.EMPTY)));
        unitTypeSelected = true;
        unitType = UnitTypeENUM.valueOf(pane.getId().toUpperCase());
        testUnit = unitFactory.createUnitByType(unitType,"test",1);

        armour.setText(String.valueOf(testUnit.getArmor()));
        attack.setText(String.valueOf(testUnit.getAttackValue()));
    }

    /**
     * Shows information that the unit the user tried to declare is invalid
     */
    public void invalidUnit(){
        infoPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        infoPane.setBackground(new Background(new BackgroundFill(Color.web("#FE7658"),CornerRadii.EMPTY,Insets.EMPTY)));
        info.setText("Invalid unit: must contain all info, and name can not contain ','");
    }

    /**
     * Shows information that the army the user tried to declare is invalid
     */
    public void invalidArmy(){
        infoPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        infoPane.setBackground(new Background(new BackgroundFill(Color.web("#FE7658"),CornerRadii.EMPTY,Insets.EMPTY)));
        info.setText("Invalid input: Army name can not be blank nor contain ','");
    }


    /**
     * View next scene in program flow
     * @param event button ViewArmy
     */
    public void viewArmy(ActionEvent event){
        parent.armies.add(currentArmy);
        parent.show("ViewCustomArmyOne.fxml");

    }

    /**
     * View earlier page in program flow
     * @param event button go Bakc
     */
    public void goBack(ActionEvent event) {
        parent.show("Home.fxml");
    }
}
