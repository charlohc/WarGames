package edu.ntnu.IDATT2001.charlohc.WarGames.FileHandlingTest;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.FileHandling.WriteFile;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

class WriteFileTest {
    Army humans;
    WriteFile writeFile;

    @BeforeEach
    public void reset() {
        try {
            humans = new Army("Human army", new ArrayList<>());
            writeFile = new WriteFile(humans);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
            humans.addUnit(new InfantryUnit("Footman", 100));

            humans.addUnit(new RangedUnit("Archer", 100));

            humans.addUnit(new CavalryUnit("Knight", 100));

            humans.addUnit(new CommanderUnit("Mountain King", 180));

            writeFile.printTxt(humans);
    }


    @Test
    public void TestWriteFileExist() {
        File f = new File("out.csv");
        Assertions.assertTrue(f.isFile() && !f.isDirectory());
    }
}


