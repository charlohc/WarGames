package edu.ntnu.IDATT2001.charlohc.WarGames.Controller;

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
import java.util.ResourceBundle;

/**
 * Imports army one and army two from file
 */
public class ImportArmies extends ChildController implements Initializable {
    FileChooser fileChooser = new FileChooser();
    ReadFile readFile;
    Army armyOne, armyTwo;

    @FXML public TextField filePathOne, filePathTwo;
    public Pane infoPane;
    Text infoText;

    /**
     * Gets information from parent about army one and two
     */
    @Override
    public void load() {
        armyOne = parent.currentArmyOne;
        armyTwo = parent.currentArmyTwo;
        readFile = new ReadFile();

    }

    /**
     * Opens the filedirectory to the user
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fileChooser.setInitialDirectory(new File("C:\\Users"));
    }

    /**
     * Imports the file from the user and reads it, prints filepath in textfield
     * and creates army one
     * @param event button import
     */
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
    /**
     * Imports the file from the user and reads it, prints filepath in textfield
     * and creates army two
     * @param event button import
     */
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

    /**
     * tells the user that the import was successful
     */
    public void importedSuccessfully(){
        infoPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
        infoPane.setBackground(new Background(new BackgroundFill(Color.web("#ACCFAA"),CornerRadii.EMPTY, Insets.EMPTY)));
        infoText.setText("File imported successfully!");
    }

    /**
     * If the user do not have imported both armies, it will get information that they cannot continue
     * @param event
     */
    public void viewArmyOne(ActionEvent event) {
        if (parent.currentArmyOne != null && parent.currentArmyTwo!=null) {
            parent.show("ViewImportedArmyOne.fxml");
        }else{
            infoPane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY,BorderWidths.DEFAULT)));
            infoPane.setBackground(new Background(new BackgroundFill(Color.web("#FE7658"),CornerRadii.EMPTY,Insets.EMPTY)));
            infoText.setText("Must import armies before continuing!");
        }
    }

    /**
     * Methode to go back to earlier scene in program flow
     * @param event
     */
    public void goBack(ActionEvent event) {
        parent.show("Home.fxml");
    }
}
