package edu.ntnu.IDATT2001.charlohc.WarGames.FileHandling;
import javafx.scene.control.Alert;

public class ExceptionAlert {

    public void emptyFile() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Importing file error");
        alert.setContentText("Can not load an empty file");
        alert.showAndWait();
    }

    public void wrongFormat() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Importing file error");
        alert.setContentText("File must be a .csv file ");
        alert.showAndWait();
    }


    public void problemFile() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Importing file error");
        alert.setContentText("Something is wrong with the file...Could not import it. ");
        alert.showAndWait();
    }

    public void problemUnits() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Importing file error");
        alert.setContentText("There is a problem with the units in this file");
        alert.showAndWait();
    }
}
