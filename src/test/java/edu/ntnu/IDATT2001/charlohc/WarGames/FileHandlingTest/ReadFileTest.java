package edu.ntnu.IDATT2001.charlohc.WarGames.FileHandlingTest;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.FileHandling.ReadFile;
import edu.ntnu.IDATT2001.charlohc.WarGames.FileHandling.WriteFile;
import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ReadFileTest {
    ReadFile readFile;
    WriteFile writeFile;
    Army army;

    @BeforeEach
         public void reset() throws IOException {
        readFile = new ReadFile();
        army = new Army("Humans",new ArrayList<>());
        writeFile = new WriteFile();

    }


    @Test
     void TestOutFileCorrectUsage() throws Exception {
        readFile.ReadFileGivenFile(new File(army.getName()));
    }

    @Test
     void TestEmptyNameFile() throws Exception {
        readFile.ReadFileGivenFile(new File(""));
        boolean thrown = false;

        try {
            readFile.ReadFileGivenFile(new File(""));
        } catch (Exception e) {
             thrown = true;
        }

        Assertions.assertFalse(thrown);
    }

    @Test
     void TestReadEmptyFile() throws Exception{
        assertThrows(Exception.class,() -> readFile.ReadFileGivenFile(new File("emptyFile.csv")));

    }

}
