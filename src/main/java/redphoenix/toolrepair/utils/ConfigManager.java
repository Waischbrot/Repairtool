package redphoenix.toolrepair.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import redphoenix.toolrepair.Main;

import java.io.File;

public class ConfigManager {
    private static Plugin plugin = Main.plugin;

    public static void startupCheck() {
        plugin.getLogger().info("§aChecking config...");
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (configFile.exists()) {
            plugin.getLogger().info("§aConfig has been loaded successfully.");
        } else {
            plugin.getLogger().info("§cConfig doesn´t exist! Creating...");
            plugin.saveDefaultConfig();
            FileConfiguration config = plugin.getConfig();
            plugin.getLogger().info("§aConfig has been created successfully.");
        }
    }

    public static double getBasicCostMultiplier(){
        FileConfiguration config = Main.plugin.getConfig();
        return config.getDouble("basicCostMultiplier");
    }

    public static double getMultiplier(int enchantmentLevel){
        FileConfiguration config = plugin.getConfig();
        double multiplier = 1;
        switch(enchantmentLevel){
            case 1:
                multiplier = config.getDouble("multipliers.1");
                break;
            case 2:
                multiplier = config.getDouble("multipliers.2");
                break;
            case 3:
                multiplier = config.getDouble("multipliers.3");
                break;
            case 4:
                multiplier = config.getDouble("multipliers.4");
                break;
            case 5:
                multiplier = config.getDouble("multipliers.5");
                break;
        }
        return multiplier;
    }

    public static void reloadConfig(){
        plugin.reloadConfig();
    }
}
