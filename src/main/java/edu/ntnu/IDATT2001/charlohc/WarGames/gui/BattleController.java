package edu.ntnu.IDATT2001.charlohc.WarGames.gui;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.Battle;
import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.awt.*;

public class BattleController extends ChildController{
    Army armyOne,armyTwo;
    TerrainTypesENUM terrainType;
    Battle battle;

    @FXML public Pane background;

    @FXML public Text Vs, stats, infoBattle;

    @FXML public Button simulate, confirm;

    @Override
    public void load() {
        armyOne = parent.currentArmyOne;
        armyTwo = parent.currentArmyTwo;
        battle = parent.battle;

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
        battle = new Battle(armyOne,armyTwo,terrainType);
        System.out.println(battle);
    }

    public void simulateBattle(ActionEvent event) {
        parent.show("simulateBattle.fxml");
    }

    public void goBack(ActionEvent event) {
        parent.show("ViewArmyTwo.fxml");
    }
}
