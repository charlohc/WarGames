package edu.ntnu.IDATT2001.charlohc.WarGames.FileHandling;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.Exceptions.FileEmptyException;
import edu.ntnu.IDATT2001.charlohc.WarGames.Exceptions.UnitException;
import edu.ntnu.IDATT2001.charlohc.WarGames.Exceptions.WrongFormatException;
import edu.ntnu.IDATT2001.charlohc.WarGames.Exceptions.nameContainsCommaException;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;
import edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory.UnitFactory;
import edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory.UnitTypeENUM;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

//TODO: test and clean

public class ReadFile {
    Army army;
    UnitFactory unitFactory = new UnitFactory();
    ExceptionAlert exceptionAlert = new ExceptionAlert();

    public ReadFile() {
    }

    public Army ReadFileGivenFile(File file){
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(file);
        } catch (FileNotFoundException e) {
            exceptionAlert.problemFile();

        }
        exceptionsFile(file);

        ArrayList<Unit> unitsFromFile = new ArrayList<>();
        String armyName = fileReader.nextLine();
        while (fileReader.hasNextLine()) {
            String data[] = fileReader.nextLine().split(",");

            if(data.length != 3){
                exceptionAlert.problemUnits();
            }

            if(Objects.equals(data[2], " ")){
                exceptionAlert.problemUnits();
            }
            incorrectUnitType(data[0]);
            unitsFromFile.add(unitFactory.createUnitByType(UnitTypeENUM.valueOf((data[0].trim())), data[1].trim(), Integer.parseInt(data[2].trim())));
        }
        nullExceptionUnit(unitsFromFile);
        fileReader.close();

        army = new Army(armyName, unitsFromFile);
        return army;
    }

    private void incorrectUnitType(String string) {
        if (!(string.equals(UnitTypeENUM.CAVALRY.toString()) || string.equals(UnitTypeENUM.COMMANDER.toString())
                || string.equals(UnitTypeENUM.RANGED.toString()) || string.equals(UnitTypeENUM.INFANTRY.toString()))) {
            try {
                throw new UnitException("can not read units from file");
            } catch (UnitException e) {
                exceptionAlert.problemUnits();
            }
        }
    }

    public void nullExceptionUnit( ArrayList<Unit> units){
        for(Unit unit: units) {
            if (unit.getUnitType() == null || unit.getName() == null ){
                try {
                    throw new UnitException("can not read units from file");
                } catch (UnitException e) {
                    exceptionAlert.problemUnits();
                }
            }
        }
    }

    public void exceptionsFile(File file){
        if(file.length() == 0){
            try {
                throw new FileEmptyException("File cannot be empty");
            } catch (FileEmptyException e) {
                exceptionAlert.emptyFile();
            }

        }if(!file.getName().endsWith(".csv")){
            try {
                throw new WrongFormatException("Must be a .csv file");
            } catch (WrongFormatException e) {
                exceptionAlert.wrongFormat();
            }

        }if(file.getName().contains("'")){
            try {
                throw new nameContainsCommaException("File name contains comma");
            } catch (nameContainsCommaException e) {
                e.printStackTrace();
            }
        }

    }
}



