package edu.ntnu.IDATT2001.charlohc.WarGames.gui;

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


public class CreateArmyTwoController extends ChildController{
    private ObservableList<Unit> units = FXCollections.observableArrayList();
    private Army currentArmyTwo;

    UnitFactory unitFactory;
    UnitTypeENUM unitType;
    Unit newUnit, testUnit;
    List<Unit> newUnits;
    Boolean unitTypeSelected = false;
    int numbersOfUnits = 0;

    @FXML
    ImageView calvaryImgView, commanderImgView, infantryImgView, rangedImgView;

    @FXML
    Pane cavalry, commander, infantry, ranged,infoPane;

    @FXML
    TextField armyName, unitName;

    @FXML
    Button addUnit,addFiveUnits,viewArmy, confirm;

    @FXML
    Text armour,attack,info;

    @FXML public Spinner<Integer> health;

    Image calvaryImg = new Image("file:cavalry.png");
    Image commanderImg = new Image("file:commander.png");
    Image infantryImg = new Image("file:infantry.png");
    Image rangedImg = new Image("file:ranged.png");


    @Override
    public void load() {
        currentArmyTwo = parent.currentArmyTwo;
        System.out.println(currentArmyTwo);

        if(currentArmyTwo != null){
            System.out.println("ikke null");
            armyName.setDisable(true);
            armyName.setText(currentArmyTwo.getName());

            viewArmy.setDisable(false);
            confirm.setDisable(true);
            addUnit.setDisable(false);
            addFiveUnits.setDisable(false);

            numbersOfUnits = currentArmyTwo.getAllUnits().size();

        }
        calvaryImgView.setImage(calvaryImg);
        commanderImgView.setImage(commanderImg);
        infantryImgView.setImage(infantryImg);
        rangedImgView.setImage(rangedImg);

        SpinnerValueFactory<Integer> valueFactory1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        health.setValueFactory(valueFactory1);
        valueFactory1.setValue(1);

        unitFactory = new UnitFactory();
    }

    public void newArmy(ActionEvent event) {
        if(armyName.getText().isBlank()){
            blankName();
        }else {
            currentArmyTwo = new Army(armyName.getText());
            info.setText(armyName.getText() + " is ready for battle!");
            addUnit.setDisable(false);
            addFiveUnits.setDisable(false);
            viewArmy.setDisable(false);

            parent.currentArmyTwo = currentArmyTwo;
        }
    }

    public void addUnit(ActionEvent event) {
        if(unitName.getText().isBlank()){
            blankName();
        }else {
            newUnit = unitFactory.createUnitByType(unitType, unitName.getText(), health.getValue());
            units.add(newUnit);
            currentArmyTwo.addUnit(newUnit);
            unitName.clear();

            numbersOfUnits++;
            info();
        }
    }

    public void addFiveUnits(ActionEvent event){
        if(unitName.getText().isBlank()){
            blankName();
        }else {
            newUnits = unitFactory.createListOfUnits(5, unitType, unitName.getText(), health.getValue());
            units.addAll(newUnit);
            currentArmyTwo.addAllUnits(newUnits);

            unitName.clear();
            numbersOfUnits += 5;
            info();
        }
    }

    public void info(){
        infoPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        if(numbersOfUnits > 0){
            info.setText("Numbers of units registered : " + numbersOfUnits);
        }
    }

    public void selectedUnitType(MouseEvent mouseEvent) {
        Pane pane = (Pane) mouseEvent.getSource();

        if (unitTypeSelected) {
            cavalry.setBackground(new Background(new BackgroundFill(Color.web("#FAFAFA"), new CornerRadii(0), Insets.EMPTY)));
            commander.setBackground(new Background(new BackgroundFill(Color.web("#FAFAFA"), new CornerRadii(0), Insets.EMPTY)));
            infantry.setBackground(new Background(new BackgroundFill(Color.web("#FAFAFA"), new CornerRadii(0), Insets.EMPTY)));
            ranged.setBackground(new Background(new BackgroundFill(Color.web("#FAFAFA"), new CornerRadii(0), Insets.EMPTY)));

        }
        pane.setBackground(new Background(new BackgroundFill(Color.web("#C7DFC5"), new CornerRadii(0), Insets.EMPTY)));
        unitTypeSelected = true;
        unitType = UnitTypeENUM.valueOf(pane.getId().toUpperCase());
        testUnit = unitFactory.createUnitByType(unitType,"test",1);

        armour.setText(String.valueOf(testUnit.getArmor()));
        attack.setText(String.valueOf(testUnit.getAttackValue()));
    }

    public void blankName(){
        infoPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        info.setText("Name can not be blank!");
    }

    public void viewArmy(ActionEvent event){
        parent.armies.add(currentArmyTwo);
        parent.show("ViewArmyTwo.fxml");

    }
    public void goBack(ActionEvent event) {
        parent.show("viewArmy.fxml");
    }
}
