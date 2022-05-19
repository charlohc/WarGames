package edu.ntnu.IDATT2001.charlohc.WarGames.Listener;

import edu.ntnu.IDATT2001.charlohc.WarGames.Unit.Unit;

public interface ChangeInHealth {
    void changeInHealth(Unit unit, int startHealth, int currentHealth);
}
