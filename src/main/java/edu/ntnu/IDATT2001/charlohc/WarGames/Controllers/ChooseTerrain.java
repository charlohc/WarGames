package edu.ntnu.IDATT2001.charlohc.WarGames.Controllers;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.Battle;
import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class ChooseTerrain extends ChildController{
    Army armyOne,armyTwo;
    TerrainTypesENUM terrainType;
    Battle battle;


    @FXML public Pane background, infoPane;
    public ImageView imageView;

    @FXML public Text Vs, stats, infoBattle,terrain;

    @FXML public Button simulate, confirm;

    Image forrest = new Image("file:img/forrest.png");
    Image plains = new Image("file:img/plains.png");
    Image hill = new Image("file:img/hill.png");

    @Override
    public void load() {
        armyOne = parent.currentArmyOne;
        armyTwo = parent.currentArmyTwo;
        battle = parent.battle;
        terrainType = parent.terrainTypes;

        if(terrainType != null){
            simulate.setDisable(false);
            confirm.setDisable(false);

            infoBattle.setText("You have chosen the terrain type: " + terrainType.toString() + " \n you can now start the simulation...");
            infoPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
            getInfoBoxBackgroundColour();
        }

        Vs.setText(armyOne.getName() + " VS. " + armyTwo.getName());
    }

    public void terrainForrest(ActionEvent event) {
        terrain.setText("Forrest");
       imageView.setImage(forrest);
        stats.setText("""
                Infantry Unit: advantage in both attack and defence\s
                \s
                 Ranged Unit: disadvantage in attack \s\s
                 
                 Calvary Unit: disadvantage in defence""");

        terrainType = TerrainTypesENUM.FOREST;
        confirmPossible();
    }

    public void terrainPlains(ActionEvent event) {
        terrain.setText("Plains");
        imageView.setImage(plains);
        stats.setText(" Calvary Unit: advantage in attack");

        terrainType = TerrainTypesENUM.PLAINS;
        confirmPossible();
    }

    public void terrainHill(ActionEvent event) {
        terrain.setText("Hill");
        imageView.setImage(hill);
        stats.setText("Ranged Unit: advantage in attack");

        terrainType = TerrainTypesENUM.HILL;
        confirmPossible();
    }

    private void getInfoBoxBackgroundColour() {
        if(terrainType == TerrainTypesENUM.FOREST){
            infoPane.setBackground(new Background(new BackgroundFill(Color.web("#ACCFAA"),CornerRadii.EMPTY, Insets.EMPTY)));
        }else if(terrainType == TerrainTypesENUM.PLAINS) {
            infoPane.setBackground(new Background(new BackgroundFill(Color.web("#D2A76B"), CornerRadii.EMPTY, Insets.EMPTY)));
        }else{
            infoPane.setBackground(new Background(new BackgroundFill(Color.web("#FDE992"), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    public void confirmPossible(){
        confirm.setDisable(false);
    }

    public void confirmTerrain(ActionEvent event) {
        infoBattle.setText("");

        infoPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        infoBattle.setText("You have chosen the terrain type: " + terrainType.toString() + " \n you can now start the simulation...");
        getInfoBoxBackgroundColour();

        battle = new Battle(armyOne,armyTwo,terrainType);
        parent.battle = battle;
        parent.terrainTypes = terrainType;

        simulate.setDisable(false);
    }

    public void simulateBattle(ActionEvent event) {
        parent.show("SimulateBattle.fxml");
    }

    public void goBack(ActionEvent event) {
        if(parent.customArmy) {
            parent.show("ViewCustomArmyTwo.fxml");
        }else if (parent.importedArmies){
            parent.show("ViewImportedArmyTwo.fxml");
        }else{
            parent.show("ViewDefaultArmyTwo.fxml");
        }
    }
}
