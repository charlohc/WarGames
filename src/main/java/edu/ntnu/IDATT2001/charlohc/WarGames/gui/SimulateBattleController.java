package edu.ntnu.IDATT2001.charlohc.WarGames.gui;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.Battle;
import edu.ntnu.IDATT2001.charlohc.WarGames.Listener.ChangeInHealth;
import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

//TODO: must make own armyCopy constructor
public class SimulateBattleController extends ChildController implements ChangeInHealth {
    ObservableList<Army> armies;
    Army armyOneOriginal, armyTwoOriginal,armyOneCopy, armyTwoCopy;
    Battle copyBattle;
    TerrainTypesENUM terrainType;

    @FXML public Text unitOne,unitTwo, armyOneName, armyTwoName, healthOne, healthTwo,winnerArmyText,unitsArmyOne,unitsArmyTwo;
    public Text oneAttackBonus, twoAttackBonus, oneResistBonus, twoResistBonus;
    public Button startSimulation;

    @Override
    public void load() {
        armyOneOriginal = parent.currentArmyOne;
        armyTwoOriginal = parent.currentArmyTwo;
        terrainType = parent.terrainTypes;

        ArrayList<Unit> armyOneUnits = new ArrayList<>();
        for (Unit unit: armyOneOriginal.getAllUnits()){
            armyOneUnits.add(unit);
        }

        armyOneCopy = new Army(armyOneOriginal.getName(),armyOneUnits);

        ArrayList<Unit> armyTwoUnits = new ArrayList<>();
        for(Unit unit: armyTwoOriginal.getAllUnits()){
            armyTwoUnits.add(unit);
        }
        armyTwoCopy = new Army(armyTwoOriginal.getName(),armyTwoUnits);

        copyBattle = new Battle(armyOneCopy,armyTwoCopy,terrainType);


        for(Unit unitOne : armyOneCopy.getAllUnits()){
            unitOne.setChangeInHealthListener(this);
        }

        for(Unit unitTwo : armyTwoCopy.getAllUnits()){
            unitTwo.setChangeInHealthListener(this);
        }

        armyOneName.setText(armyOneCopy.getName());
        armyTwoName.setText(armyTwoCopy.getName());

        unitsArmyOne.setText(String.valueOf(armyOneCopy.getAllUnits().size()));
        unitsArmyTwo.setText(String.valueOf(armyTwoCopy.getAllUnits().size()));

        System.out.println("original " + armyOneOriginal);
        System.out.println("copy " + armyOneCopy);

        startSimulation.setOnAction(event -> {
            startSimulation();
        });

    }

    @Override
    public void changeInHealth(Unit unit, int startHealth, int currentHealth) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(armyOneCopy.containsUnit(unit)){
                unitOne.setText(unit.getName());
                healthOne.setText(String.valueOf(unit.getHealth()));
                oneAttackBonus.setText(String.valueOf(unit.getAttackBonus()));
                oneResistBonus.setText(String.valueOf(unit.getResistBonus()));

                unitOne.setUnderline(true);
                healthOne.setFill(Color.DARKRED);
                oneAttackBonus.setFill(Color.DARKRED);
                oneResistBonus.setFill(Color.DARKRED);
        }
        if(armyTwoCopy.containsUnit(unit)) {
            unitTwo.setText(unit.getName());
            healthTwo.setText(String.valueOf(unit.getHealth()));
            twoAttackBonus.setText(String.valueOf(unit.getAttackBonus()));
            twoResistBonus.setText(String.valueOf(unit.getResistBonus()));

            unitTwo.setUnderline(true);
            healthTwo.setFill(Color.DARKRED);
            twoAttackBonus.setFill(Color.DARKRED);
            twoAttackBonus.setFill(Color.DARKRED);
        }

    }

    public void startSimulation() {
        Thread battleThread = new Thread(() -> {
            Army winnerArmy = null;
            try {
                winnerArmy = copyBattle.simulate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Army finalWinnerArmy = winnerArmy;
            Platform.runLater(() -> {
                if(finalWinnerArmy != null){
                    winnerArmyText.setText("Winner: " + finalWinnerArmy.getName());
                    startSimulation.setDisable(false);
                    startSimulation.setText("Restart");
                    reStart();
                }

            });
        });


        Thread messageThread = new Thread(() -> {
            while(battleThread.isAlive()){

                startSimulation.setDisable(true);
                Platform.runLater(() ->{
                    if(!armyOneCopy.hasUnit()){
                        unitOne.setText("All dead");
                        unitOne.setFill(Color.RED);
                        healthOne.setText(String.valueOf(0));
                        healthOne.setFill(Color.RED);
                        oneResistBonus.setText("");
                        oneAttackBonus.setText("");
                    }
                    if(!armyTwoCopy.hasUnit()){
                        unitTwo.setText("All dead");
                        unitTwo.setFill(Color.RED);
                        healthTwo.setText(String.valueOf(0));
                        healthTwo.setFill(Color.RED);
                        twoAttackBonus.setText("");
                        twoResistBonus.setText("");
                    }

                    healthOne.setFill(Color.BLACK);
                    unitOne.setUnderline(false);
                    healthOne.setFill(Color.BLACK);
                    oneAttackBonus.setFill(Color.BLACK);
                    oneResistBonus.setFill(Color.BLACK);

                    unitsArmyOne.setText(String.valueOf(armyOneCopy.getAllUnits().size()));
                    unitsArmyTwo.setText(String.valueOf(armyTwoCopy.getAllUnits().size()));

                });

                try {
                    Thread.sleep(copyBattle.getSleepTime());
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        });

        battleThread.start();
        messageThread.start();
    }

    public void reStart(){
        System.out.println("original " + armyOneOriginal);
        System.out.println("copy " + armyOneCopy);
        startSimulation.setOnAction(event -> {
            parent.show("simulateBattle.fxml");
        });
    }

    public void goBack(ActionEvent event) {
        parent.show("battle.fxml");
    }
}
