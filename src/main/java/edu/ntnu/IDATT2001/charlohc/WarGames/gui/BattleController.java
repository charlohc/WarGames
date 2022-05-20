package edu.ntnu.IDATT2001.charlohc.WarGames.gui;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.Battle;
import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;

public class BattleController extends ChildController{
    Army armyOne,armyTwo;
    TerrainTypesENUM terrainType;
    Battle battle;

    @FXML public Pane background, infoPane;

    @FXML public Text Vs, stats, infoBattle;

    @FXML public Button simulate, confirm;

    @Override
    public void load() {
        armyOne = parent.currentArmyOne;
        armyTwo = parent.currentArmyTwo;
        battle = parent.battle;
        terrainType = parent.terrainTypes;

        if(terrainType != null){
            System.out.println("not null");
            enableButtons();
            infoBattle.setText("You have chosen the terrain: " + terrainType.toString() + "!");
            infoPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        }

        System.out.println("null");


        Vs.setText(armyOne.getName() + " VS. " + armyTwo.getName());
    }

    public void terrainForrest(ActionEvent event) {
        background.setBackground(new Background(new BackgroundFill(Color.web("#155644"), new CornerRadii(0), Insets.EMPTY)));
        stats.setText("""
                Infantry Unit: advantage in both attack and defence\s
                \s
                 Ranged Unit: disadvantage in attack \s\s
                 
                 Calvary Unit: disadvantage in defence""");

        terrainType = TerrainTypesENUM.FORREST;
        enableButtons();
    }

    public void terrainPlains(ActionEvent event) {
        background.setBackground(new Background(new BackgroundFill(Color.LIGHTGOLDENRODYELLOW, new CornerRadii(0), Insets.EMPTY)));
        stats.setText(" Calvary Unit: advantage in attack");

        terrainType = TerrainTypesENUM.PLAINS;
        enableButtons();
    }

    public void terrainHill(ActionEvent event) {
        background.setBackground(new Background(new BackgroundFill(Color.web("#A6C900"), new CornerRadii(0), Insets.EMPTY)));
        stats.setText("Ranged Unit: advantage in attack");

        terrainType = TerrainTypesENUM.HILL;
       enableButtons();
    }

    public void enableButtons(){
        simulate.setDisable(false);
        confirm.setDisable(false);
    }

    public void confirmTerrain(ActionEvent event) {
        infoPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        infoBattle.setText("You have chosen the terrain type: " + terrainType.toString() + "! \n you can now start the simulation");

        battle = new Battle(armyOne,armyTwo,terrainType);
        parent.battle = battle;
        parent.terrainTypes = terrainType;
    }

    public void simulateBattle(ActionEvent event) {
        parent.show("simulateBattle.fxml");
    }

    public void goBack(ActionEvent event) {
        parent.show("ViewArmyTwo.fxml");
    }
}
