package edu.ntnu.IDATT2001.charlohc.WarGames.FileHandlingTest;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.FileHandling.WriteFile;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.*;
import edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory.UnitFactory;
import edu.ntnu.IDATT2001.charlohc.WarGames.UnitFactory.UnitTypeENUM;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

class WriteFileTest {
    Army humans;
    WriteFile writeFile;
    UnitFactory unitFactory;

    @BeforeEach
    public void reset() {
        unitFactory = new UnitFactory();
        try {
            humans = new Army("Orcs army", new ArrayList<>());
            writeFile = new WriteFile();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
            Unit infantry = unitFactory.createUnitByType(UnitTypeENUM.INFANTRY,"Footman", 100);
            Unit ranged = unitFactory.createUnitByType(UnitTypeENUM.RANGED,"Archer", 70);
            Unit cavalry = unitFactory.createUnitByType(UnitTypeENUM.CAVALRY,"Knight", 80);
            Unit commander = unitFactory.createUnitByType(UnitTypeENUM.COMMANDER,"Mountain King", 100);

            humans.addUnit(infantry);
            humans.addUnit(ranged);
            humans.addUnit(cavalry);
            humans.addUnit(commander);

            writeFile.printTxt(humans);
    }


    @Test
    public void TestWriteFileExist() {
        File f = new File(humans.getName() + ".csv");
        Assertions.assertTrue(f.isFile() && !f.isDirectory());
    }
}


