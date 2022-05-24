package edu.ntnu.IDATT2001.charlohc.WarGames.Controller;

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

/**
 * Class that makes user choose what terrain the battle will occure in
 */
public class ChooseTerrain extends ChildController{
    Army armyOne,armyTwo;
    TerrainTypesENUM terrainType;
    Battle battle;


    @FXML public Pane background, infoPane;
    public ImageView imageView;
    public Text Vs, stats, infoBattle,terrain;
    public Button simulate, confirm;

    Image forrest = new Image("file:img/forrest.png");
    Image plains = new Image("file:img/plains.png");
    Image hill = new Image("file:img/hill.png");

    /**
     * Retain the parent objects with information about existing armies and battles
     * if the terrain type is not yet decided, it has to be decided else the user can not go to next scene simulation
     * if the terrain is decided the information will be set with the information about the terrain type
     */
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

    /**
     * When pressing the button forrest, you get to se an image that represents hill and what units benefit from this terrain type
     * @param event button forrest
     */
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

    /**
     * When pressing the button plains, you get to se an image that represents hill and what units benefit from this terrain type
     * @param event plains
     */
    public void terrainPlains(ActionEvent event) {
        terrain.setText("Plains");
        imageView.setImage(plains);
        stats.setText(" Calvary Unit: advantage in attack");

        terrainType = TerrainTypesENUM.PLAINS;
        confirmPossible();
    }

    /**
     * When pressing the button hill, you get to se an image that represents hill and what units benefit from this terrain type
     * @param event hill
     */
    public void terrainHill(ActionEvent event) {
        terrain.setText("Hill");
        imageView.setImage(hill);
        stats.setText("Ranged Unit: advantage in attack");

        terrainType = TerrainTypesENUM.HILL;
        confirmPossible();
    }

    /**
     * Changes infoPane background based on terrain type
     */
    private void getInfoBoxBackgroundColour() {
        if(terrainType == TerrainTypesENUM.FOREST){
            infoPane.setBackground(new Background(new BackgroundFill(Color.web("#ACCFAA"),CornerRadii.EMPTY, Insets.EMPTY)));
        }else if(terrainType == TerrainTypesENUM.PLAINS) {
            infoPane.setBackground(new Background(new BackgroundFill(Color.web("#D2A76B"), CornerRadii.EMPTY, Insets.EMPTY)));
        }else{
            infoPane.setBackground(new Background(new BackgroundFill(Color.web("#FDE992"), CornerRadii.EMPTY, Insets.EMPTY)));
        }
    }

    /**
     * Makes it possible for user to press confirm button
     */
    public void confirmPossible(){
        confirm.setDisable(false);
    }

    /**
     * Shows message to user what kind of terrain they have chosen, and makes it possible to manuver to next page
     * Creates a new battle, sets parent object as new battle object and
     * sets parent terrain object as new terrain object
     * @param event
     */
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

    /**
     * Shows next scene in program flow
     * @param event button simulate
     */
    public void simulateBattle(ActionEvent event) {
        parent.show("SimulateBattle.fxml");
    }

    /**
     * Shows previous scene in program flow
     * @param event button go back
     */
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
