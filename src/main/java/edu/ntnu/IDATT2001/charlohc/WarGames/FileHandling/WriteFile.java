package edu.ntnu.IDATT2001.charlohc.WarGames.FileHandling;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;

import java.io.PrintWriter;

public class WriteFile {
      Army armies;
      PrintWriter writer;

public WriteFile(Army army){
    this.armies = army;
}

    public void printTxt(Army army){

        try{
            writer = new PrintWriter("out.csv");
            writer.println(army);
            writer.flush();
            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}


