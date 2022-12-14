package edu.ntnu.IDATT2001.charlohc.WarGames.Controller;

/*
Imported from group project IDATT2001
Abstract child controller class.
 */
public abstract class ChildController {

    protected MainController parent;

    abstract public void load();

    /**
     * Method to load parent class.
     * @param parent the parent from the MainController class.
     */
    public void initialize(MainController parent) {
        this.parent = parent;
        this.load();
    }
}
