package edu.ntnu.IDATT2001.charlohc.WarGames.gui;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.Battle;
import edu.ntnu.IDATT2001.charlohc.WarGames.Listener.ChangeInHealth;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.util.ArrayList;

public class SimulateBattleController extends ChildController implements ChangeInHealth {
    Army armyOne,armyTwo;
    Battle battle;

    @FXML public Text unitOne,unitTwo, armyOneName, armyTwoName, healthOne, healthTwo,winnerArmyText,unitsArmyOne,unitsArmyTwo;
    public Text oneAttackBonus, twoAttackBonus, oneResistBonus, twoResistBonus;

    @Override
    public void load() {
        armyOne = parent.currentArmyOne;
        armyTwo = parent.currentArmyTwo;
        battle = parent.battle;

        for(Unit unitOne : armyOne.getAllUnits()){
            unitOne.setChangeInHealthListener(this);
        }

        for(Unit unitTwo : armyTwo.getAllUnits()){
            unitTwo.setChangeInHealthListener(this);
        }

        armyOneName.setText(armyOne.getName());
        armyTwoName.setText(armyTwo.getName());

        unitsArmyOne.setText(String.valueOf(armyOne.getAllUnits().size()));
        unitsArmyTwo.setText(String.valueOf(armyTwo.getAllUnits().size()));
    }

    public ArrayList<Unit> armyOneCopy(){
        return new ArrayList<>(armyOne.getAllUnits());
    }

    public ArrayList<Unit> armyTwoCopy(){
        return new ArrayList<>(armyTwo.getAllUnits());
    }

    //TODO: lag en rad ved siden av "vinduet" hvor har info om uniten, helse, attack osv.., bruk progressen for å vise hvor få units har igjen
    @Override
    public void changeInHealth(Unit unit, int startHealth, int currentHealth) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(armyOne.containsUnit(unit)){
                unitOne.setText(unit.getName());
                healthOne.setText(String.valueOf(unit.getHealth()));
                oneAttackBonus.setText(String.valueOf(unit.getAttackBonus()));
                oneResistBonus.setText(String.valueOf(unit.getResistBonus()));

                unitOne.setUnderline(true);
                healthOne.setFill(Color.DARKRED);
                oneAttackBonus.setFill(Color.DARKRED);
                oneResistBonus.setFill(Color.DARKRED);
        }
        if(armyTwo.containsUnit(unit)) {
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

    public void startSimulation(ActionEvent event) {
        Thread battleThread = new Thread(() -> {
            Army winnerArmy = null;
            try {
                winnerArmy = battle.simulate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Army finalWinnerArmy = winnerArmy;
            Platform.runLater(() -> {
                if(finalWinnerArmy != null){
                    winnerArmyText.setText("Winner: " + finalWinnerArmy.getName());
                }

            });
        });


        Thread messageThread = new Thread(() -> {
            while(battleThread.isAlive()){

                Platform.runLater(() ->{
                    if(!armyOne.hasUnit()){
                        unitOne.setText("All dead");
                        unitOne.setFill(Color.RED);
                        healthOne.setText(String.valueOf(0));
                        healthOne.setFill(Color.RED);
                        oneResistBonus.setText("");
                        oneAttackBonus.setText("");
                    }
                    if(!armyTwo.hasUnit()){
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

                    unitsArmyOne.setText(String.valueOf(armyOne.getAllUnits().size()));
                    unitsArmyTwo.setText(String.valueOf(armyTwo.getAllUnits().size()));

                });

                try {
                    Thread.sleep(battle.getSleepTime());
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        });

        battleThread.start();
        messageThread.start();

    }

    public void goBack(ActionEvent event) {
        parent.show("battle.fxml");
    }
}
