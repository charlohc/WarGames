package edu.ntnu.IDATT2001.charlohc.WarGames.Controllers;

import edu.ntnu.IDATT2001.charlohc.WarGames.Army;
import edu.ntnu.IDATT2001.charlohc.WarGames.FileHandling.ReadFile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;

//TODO: make import army two as one
public class ImportArmies extends ChildController implements Initializable {
    FileChooser fileChooser = new FileChooser();
    ReadFile readFile;
    Army armyOne, armyTwo;

    @FXML public TextField filePathOne, filePathTwo;

    @FXML public Pane infoPane;

    @FXML Text infoText;

    @Override
    public void load() {
        armyOne = parent.currentArmyOne;
        armyTwo = parent.currentArmyTwo;
        readFile = new ReadFile();

    }

    //TODO: change initialDirectory
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("C:\\Users"));
    }

    public void importFileOne(ActionEvent event) {
        filePathOne.clear();
        File file = fileChooser.showOpenDialog(new Stage());
            readFile.ReadFileGivenFile(file);
            if(readFile.ReadFileGivenFile(file) != null){
                    filePathOne.setText(file.getAbsolutePath());
                    armyOne = readFile.ReadFileGivenFile(file);
                    importedSuccessfully();

            }
            parent.currentArmyOne = armyOne;
    }

    public void importFileTwo(ActionEvent event) {
        filePathTwo.clear();
        File file = fileChooser.showOpenDialog(new Stage());
        readFile.ReadFileGivenFile(file);
        if(readFile.ReadFileGivenFile(file) != null){
            filePathTwo.setText(file.getAbsolutePath());
            armyTwo = readFile.ReadFileGivenFile(file);
            importedSuccessfully();
        }
        parent.currentArmyTwo = armyTwo;
    }

    public void importedSuccessfully(){
        infoPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        infoPane.setBackground(new Background(new BackgroundFill(Color.web("#ACCFAA"),CornerRadii.EMPTY, Insets.EMPTY)));
        infoText.setText("File imported successfully!");
    }

    public void viewArmyOne(ActionEvent event) {
        if (parent.currentArmyOne != null && parent.currentArmyTwo!=null) {
            parent.show("ViewImportedArmyOne.fxml");
        }else{
            infoPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
            infoPane.setBackground(new Background(new BackgroundFill(Color.web("#FE7658"),CornerRadii.EMPTY,Insets.EMPTY)));
            infoText.setText("Must import armies before continuing!");
        }
    }

    public void goBack(ActionEvent event) {
        parent.show("Home.fxml");
    }
}
