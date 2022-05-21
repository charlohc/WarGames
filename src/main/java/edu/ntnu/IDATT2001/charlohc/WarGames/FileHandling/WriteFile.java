package edu.ntnu.IDATT2001.charlohc.WarGames.FileHandling;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;

import java.io.File;
import java.io.PrintWriter;

public class WriteFile {
      PrintWriter writer;


    public void printTxt(Army army){

        try{
            writer = new PrintWriter(army.getName() + ".csv");
            writer.println(army);
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


