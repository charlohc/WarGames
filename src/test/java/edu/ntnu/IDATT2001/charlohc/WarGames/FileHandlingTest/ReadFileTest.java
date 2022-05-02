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

//TODO: make more test
class ReadFileTest {
    ReadFile readFile;
    WriteFile writeFile;
    Army army;

    @BeforeEach
    public void reset() throws IOException {
        readFile = new ReadFile();
        army = new Army("Humans",new ArrayList<>());
        writeFile = new WriteFile(army);

    }

    //TODO: How do i test that is returns correctly
    //TODO: find another way to read from file, se teacher git lab
    @Test
    public void TestOutFileCorrectUsage() throws Exception {
        readFile.ReadFileGivenFile(new File("out.csv"));
    }


    //Wants to make sure that the system keeps running even though it happens something that shouldn't happen, the user will get a message
    //TODO: corrected tested?
    @Test
    public void TestEmptyNameFile() throws Exception {
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
    public void TestReadEmptyFile() throws Exception{
        readFile.ReadFileGivenFile(new File("emptyFile.csv"));
    }

}
