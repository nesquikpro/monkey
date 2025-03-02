package com.monkey.monkey.config;

import com.monkey.monkey.ModManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import java.io.File;

public class ConfigSetup {
    private ModManager mod;
    private File saveFile;

    public ConfigSetup(ModManager modManager, File saveFile) {
        this.mod = modManager;
        this.saveFile = saveFile;
    }

    public void loadConfig() {
        Configuration config = new Configuration(this.saveFile);
        config.load();
        updateConfig(config, true);
    }

    public void updateConfig(Configuration config, boolean load) {
        Property prop = config.get("general", "offsetX", 0);
        if (load) {
            this.mod.guiSidebar.offsetX = prop.getInt();
        } else {
            prop.setValue(this.mod.guiSidebar.offsetX);
        }
        prop = config.get("general", "offsetY", 0);
        if (load) {
            this.mod.guiSidebar.offsetY = prop.getInt();
        } else {
            prop.setValue(this.mod.guiSidebar.offsetY);
        }
        prop = config.get("general", "scale", 1.1D);
        if (load) {
            this.mod.guiSidebar.scale = (float)prop.getDouble();
        } else {
            prop.setValue(this.mod.guiSidebar.scale);
        }
        prop = config.get("color", "rgb", 0);
        if (load) {
            this.mod.guiSidebar.color = prop.getInt();
        } else {
            prop.setValue(this.mod.guiSidebar.color);
        }
        prop = config.get("color", "alpha", 100);
        if (load) {
            this.mod.guiSidebar.alpha = prop.getInt();
        } else {
            prop.setValue(this.mod.guiSidebar.alpha);
        }
    }
}
