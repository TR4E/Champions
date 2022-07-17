package me.trae.champions;

import me.trae.core.energy.EnergyManager;
import me.trae.core.framework.MiniPlugin;

public class Champions extends MiniPlugin {

    @Override
    public void registerManagers() {
        addManager(EnergyManager.class);
    }
}