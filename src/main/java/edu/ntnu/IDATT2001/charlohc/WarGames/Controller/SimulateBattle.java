package edu.ntnu.IDATT2001.charlohc.WarGames.Controller;

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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import java.util.ArrayList;

/**
 * Class that simulates a battle between to armies
 */
public class SimulateBattle extends ChildController implements ChangeInHealth {
    Army armyOneOriginal, armyTwoOriginal,armyOneCopy, armyTwoCopy;
    Battle copyBattle;
    TerrainTypesENUM terrainType;
    Thread battleThread;
    UnitFactory unitFactory = new UnitFactory();

    @FXML
    public Text unitOne,unitTwo, armyOneName, armyTwoName, healthOne, healthTwo,winnerArmyText,unitsArmyOne,unitsArmyTwo;
    public Text oneAttackBonus, twoAttackBonus, oneResistBonus, twoResistBonus, damageOne, damageTwo, attacks;
    public Button startSimulation;
    public Pane damageOnePane, damageTwoPane, attackInfoPane, mainPane, numberUnitsType;
    public ImageView imageView;
    public TableColumn<Unit,String> typeColumn;
    public TableColumn<Unit,String> nameColumn;
    public TableColumn<Unit,Integer> healthColumn;
    public TableView<Unit> tableView;
    public Rectangle healthBarOne, healthBarTwo;

    Image forrest = new Image("file:img/forrest.png");
    Image plains = new Image("file:img/plains.png");
    Image hill = new Image("file:img/hill.png");

    private ObservableList<Unit> units = FXCollections.observableArrayList();

    /**
     * retains the information about army one, army two and terrain from parent
     * creates a copy of the units
     */
    @Override
    public void load() {
        armyOneOriginal = parent.currentArmyOne;
        armyTwoOriginal = parent.currentArmyTwo;
        terrainType = parent.terrainTypes;

        setBackgroundImg();

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

    /**
     * Sets background image based on terrain type of the armies in the battle
     */
    public void setBackgroundImg(){
        if(terrainType == TerrainTypesENUM.FOREST){
            imageView.setImage(forrest);
        }else if(terrainType == TerrainTypesENUM.PLAINS){
            imageView.setImage(plains);
        }else{
            imageView.setImage(hill);
        }
    }

    /**
     * A methode that shows the damage happened during the attack, to the unit that was attacked
     * The damage is found by calculating the start-health minus the current health
     * Uses thread.sleep() so the user will be able to se the change
     * @param unit the unit that the health changes
     * @param startHealth an int that shows the start health
     * @param currentHealth an int that shows the health to the unit after attack
     */
    @Override
    public void changeInHealth(Unit unit, int startHealth, int currentHealth) {
        try {
            Thread.sleep(1300);
                damageOne.setText("");
                damageTwo.setText("");

                if (armyOneCopy.containsUnit(unit)) {
                    damageOne.setText("Damage: " + (startHealth - currentHealth));
                    float percentage = (currentHealth /(float) startHealth);
                    float width = (percentage * 143);
                    healthBarOne.setWidth(width);
                }

                if (armyTwoCopy.containsUnit(unit)) {
                    damageTwo.setText("Damage : " + (startHealth - currentHealth));
                    float percentage = (currentHealth /(float) startHealth);
                    float width = (percentage * 143);
                    healthBarTwo.setWidth(width);
                }

        } catch (IndexOutOfBoundsException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * Methode that gets the winner of the battle and shows it in text winnerArmyText, implements methodes
     * viewWinnerArmy() and numbersOfUnits to show state of army after battle
     */
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
                    healthOne.setText("");
                    healthTwo.setText("");
                    startSimulation.setDisable(false);
                    startSimulation.setText("Back to start");
                    reStart();
                }

            });
        });

        /**
         * While the battle is occurring there is placed a listener on the health to  all units in each army
         * Implements metode battleInfo() which gives information about the battle
         * the runLater() function happens after the simulation is over and sets the loser army health to null with red fill and
         * sets text "all dead"
         */
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

    /**
     * After the simulation is over, a table view appeares with information about each unit in the winner army
     * @param winnerArmy the army who won the simulation
     */
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

    /**
     * Methode that tells which unit attacks which unit and changes the health, attack and resist text according to the units health
     */
    public void battleInfo(){
        startSimulation.setDisable(true);

        attacks.setText(copyBattle.getAttacker().getName() +  " attacks " + copyBattle.getDefender().getName() + "!");

        if(armyOneCopy.containsUnit(copyBattle.getAttacker())){
            unitOne.setText(copyBattle.getAttacker().getName());
            healthOne.setText(String.valueOf(copyBattle.getAttacker().getHealth()));
            oneAttackBonus.setText(String.valueOf(copyBattle.getAttacker().getAttackBonus() + copyBattle.getAttacker().getAttackValue()));
            oneResistBonus.setText(String.valueOf(copyBattle.getAttacker().getResistBonus() + copyBattle.getAttacker().getArmor()));


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

    /**
     * After the simulation the methode creates a textfield that shows how many units of each type is left in winner army
     * @param winnerArmy the army who win the simulation
     */
    public void numberOfUnits(Army winnerArmy){

        numberUnitsType.setOpacity(1);

        Text numbCavalry = new Text("Cavalry units: " + winnerArmy.getCavalryUnits().size());
        numbCavalry.setY(30);
        numbCavalry.setX(20);

        Text numbCommander = new Text("Commander units: " + winnerArmy.getCommanderUnits().size());
        numbCommander.setY(30);
        numbCommander.setX(130);

        Text numbRanged = new Text("Ranged units: " + winnerArmy.getRangedUnits().size());
        numbRanged.setY(30);
        numbRanged.setX(260);

        Text numbInfantry = new Text("Infantry units: " + winnerArmy.getInfantryUnits().size());
        numbInfantry.setY(30);
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

    public void home(ActionEvent event) {
            parent.show("Home.fxml");
    }
}
