package edu.ntnu.IDATT2001.charlohc.WarGames.Controllers;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.Battle;
import edu.ntnu.IDATT2001.charlohc.WarGames.Listener.ChangeInHealth;
import edu.ntnu.IDATT2001.charlohc.WarGames.Terrain.TerrainTypesENUM;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;
import edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory.UnitFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;

//TODO: must make own armyCopy constructor
public class SimulateBattle extends ChildController implements ChangeInHealth {
    Army armyOneOriginal, armyTwoOriginal,armyOneCopy, armyTwoCopy;
    Battle copyBattle;
    TerrainTypesENUM terrainType;
    Thread battleThread;
    UnitFactory unitFactory = new UnitFactory();

    @FXML public Text unitOne,unitTwo, armyOneName, armyTwoName, healthOne, healthTwo,winnerArmyText,unitsArmyOne,unitsArmyTwo;
    public Text oneAttackBonus, twoAttackBonus, oneResistBonus, twoResistBonus, damageOne, damageTwo, attacks;
    public Button startSimulation;
    public Pane damageOnePane, damageTwoPane, attackInfoPane, mainPane;

    @FXML
    public TableView<Unit> tableView;

    @FXML
    public TableColumn<Unit,String> typeColumn;
    public TableColumn<Unit,String> nameColumn;
    public TableColumn<Unit,Integer> healthColumn;

    private ObservableList<Unit> units = FXCollections.observableArrayList();

    @Override
    public void load() {
        armyOneOriginal = parent.currentArmyOne;
        armyTwoOriginal = parent.currentArmyTwo;
        terrainType = parent.terrainTypes;

        ArrayList<Unit> armyOneUnits = new ArrayList<>();
        for (Unit unit: armyOneOriginal.getAllUnits()){
            Unit unitCopy = unitFactory.createUnitByType(unit.getUnitType(),unit.getName(),unit.getHealth());
            armyOneUnits.add(unitCopy);
        }

        armyOneCopy = new Army(armyOneOriginal.getName(),armyOneUnits);


        ArrayList<Unit> armyTwoUnits = new ArrayList<>();
        for (Unit unit: armyTwoOriginal.getAllUnits()){
            Unit unitCopy = unitFactory.createUnitByType(unit.getUnitType(),unit.getName(),unit.getHealth());
            armyTwoUnits.add(unitCopy);
        }
        armyTwoCopy = new Army(armyTwoOriginal.getName(),armyTwoUnits);

        copyBattle = new Battle(armyOneCopy,armyTwoCopy,terrainType);


        armyOneName.setText(armyOneCopy.getName());
        armyTwoName.setText(armyTwoCopy.getName());

        unitsArmyOne.setText(String.valueOf(armyOneCopy.getAllUnits().size()));
        unitsArmyTwo.setText(String.valueOf(armyTwoCopy.getAllUnits().size()));

        startSimulation.setOnAction(event -> {
            startSimulation();
        });

    }
//2000
    @Override
    public void changeInHealth(Unit unit, int startHealth, int currentHealth) {
        try {
            Thread.sleep(100);
                damageOne.setText("");
                damageTwo.setText("");
                if (armyOneCopy.containsUnit(unit)) {
                    damageOne.setText("Damage: " + (startHealth - currentHealth));
                }

                if (armyTwoCopy.containsUnit(unit)) {
                    damageTwo.setText("Damage : " + (startHealth - currentHealth));
                }

        } catch (IndexOutOfBoundsException | InterruptedException e) {
            e.printStackTrace();
            System.out.println("Something happened!");
        }

    }

    public void startSimulation() {
            battleThread = new Thread(() -> {
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
                    viewWinnerArmy(finalWinnerArmy);
                    numberOfUnits(finalWinnerArmy);
                    damageOnePane.setOpacity(0);
                    damageTwoPane.setOpacity(0);
                    attackInfoPane.setOpacity(0);
                    damageOne.setText("");
                    damageTwo.setText("");
                    startSimulation.setDisable(false);
                    startSimulation.setText("Back to start");
                    reStart();
                }

            });
        });


        Thread messageThread = new Thread(() -> {
            while(battleThread.isAlive() && copyBattle.getDefender() != null){
                battleInfo();

                for(Unit unitOne : armyOneCopy.getAllUnits()){
                    unitOne.setChangeInHealthListener(this);
                }

                for(Unit unitTwo : armyTwoCopy.getAllUnits()){
                    unitTwo.setChangeInHealthListener(this);
                }


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

    private void viewWinnerArmy(Army winnerArmy) {
        tableView.setOpacity(1);

        try {
            for (Unit unit : winnerArmy.getAllUnits()) {
                if (!units.contains(unit)) {
                    units.add(unit);
                }
            }
        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println("something happened");
        }

        tableView.setItems(units);
        typeColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("unitType"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Unit, String>("name"));
        healthColumn.setCellValueFactory(new PropertyValueFactory<Unit, Integer>("health"));

    }

    public void battleInfo(){
        startSimulation.setDisable(true);

        attacks.setText(copyBattle.getAttacker().getName() +  " attacks " + copyBattle.getDefender().getName() + "!");

        if(armyOneCopy.containsUnit(copyBattle.getAttacker())){
            unitOne.setText(copyBattle.getAttacker().getName());
            healthOne.setText(String.valueOf(copyBattle.getAttacker().getHealth()));
            oneAttackBonus.setText(String.valueOf(copyBattle.getAttacker().getAttackBonus() + copyBattle.getAttacker().getAttackValue()));
            oneResistBonus.setText(String.valueOf(copyBattle.getAttacker().getResistBonus() + copyBattle.getAttacker().getArmor()));

            healthOne.setFill(Color.BLACK);

        }else{
            unitOne.setText(copyBattle.getDefender().getName());
            healthOne.setText(String.valueOf(copyBattle.getDefender().getHealth()));
            oneAttackBonus.setText(String.valueOf(copyBattle.getDefender().getAttackBonus() + copyBattle.getDefender().getAttackValue()));
            oneResistBonus.setText(String.valueOf(copyBattle.getDefender().getResistBonus() + copyBattle.getDefender().getArmor()));

        }

        if(armyTwoCopy.containsUnit(copyBattle.getAttacker())){
            unitTwo.setText(copyBattle.getAttacker().getName());
            healthTwo.setText(String.valueOf(copyBattle.getAttacker().getHealth()));
            twoAttackBonus.setText(String.valueOf(copyBattle.getAttacker().getAttackBonus() + copyBattle.getAttacker().getAttackValue()));
            twoResistBonus.setText(String.valueOf(copyBattle.getAttacker().getResistBonus() + copyBattle.getAttacker().getArmor()));


        }else{
            unitTwo.setText(copyBattle.getDefender().getName());
            healthTwo.setText(String.valueOf(copyBattle.getDefender().getHealth()));
            twoAttackBonus.setText(String.valueOf(copyBattle.getDefender().getAttackBonus() + copyBattle.getDefender().getAttackValue()));
            twoResistBonus.setText(String.valueOf(copyBattle.getDefender().getResistBonus() + copyBattle.getDefender().getArmor()));

        }


    }

    public void numberOfUnits(Army winnerArmy){
        Text numbCavalry = new Text("Cavalry units: " + winnerArmy.getCavalryUnits().size());
        numbCavalry.setY(40);
        numbCavalry.setX(20);

        Text numbCommander = new Text("Commander units: " + winnerArmy.getCommanderUnits().size());
        numbCommander.setY(40);
        numbCommander.setX(130);

        Text numbRanged = new Text("Ranged units: " + winnerArmy.getRangedUnits().size());
        numbRanged.setY(40);
        numbRanged.setX(260);

        Text numbInfantry = new Text("Infantry units: " + winnerArmy.getInfantryUnits().size());
        numbInfantry.setY(40);
        numbInfantry.setX(370);

        mainPane.getChildren().addAll(numbCavalry,numbCommander,numbRanged,numbInfantry);
    }

    public void reStart(){
        startSimulation.setOnAction(event -> {
            parent.show("SimulateBattle.fxml");
        });
    }

    public void goBack(ActionEvent event) {
        parent.show("ChooseTerrain.fxml");
    }

}
