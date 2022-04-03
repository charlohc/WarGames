package edu.ntnu.IDATT2001.charlohc.WarGames.FileHandling;

import java.io.*;

public class ReadFile {

    public ReadFile() throws IOException { }

    public void ReadFileGivenFile(File file) throws Exception {
        try {
            BufferedReader br = null;
            FileReader fr = null;

            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String sCurrentLine = "";

            while ((sCurrentLine = br.readLine()) != null) {
                System.out.println(sCurrentLine);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(file.getName().trim().isBlank()){
            try {
                throw new Exception("Can not read from a file without name.");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(file.length() == 0){
            try {
                throw new Exception("The given file is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

