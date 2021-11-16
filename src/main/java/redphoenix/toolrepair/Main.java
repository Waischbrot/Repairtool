package redphoenix.toolrepair;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import redphoenix.toolrepair.commands.RepairGUICommand;
import redphoenix.toolrepair.gui.RepairGUIListener;
import redphoenix.toolrepair.utils.ConfigManager;

import java.util.*;
import java.util.logging.Logger;

public final class Main extends JavaPlugin {
    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy eco = null;
    public static Plugin plugin;
    public static final ArrayList<Material> tools = new ArrayList<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        registerEvents();
        registerCommands();
        ConfigManager.startupCheck();
        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void fillToolArray(){
        tools.add(Material.DIAMOND_AXE);
        tools.add(Material.DIAMOND_BOOTS);
    }

    public void registerEvents(){
        getServer().getPluginManager().registerEvents(new RepairGUIListener(), this);
    }

    public void registerCommands(){
        getCommand("repair").setExecutor(new RepairGUICommand());
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        eco = rsp.getProvider();
        return eco != null;
    }

    public static Economy getEconomy(){
        return eco;
    }

    public static double calculateRepairCost(Map<Enchantment, Integer> enchantmentMap, int damage){
        int level = Collections.max(enchantmentMap.values());
        double basicCost = damage * ConfigManager.getBasicCostMultiplier();
        double cost = basicCost * ConfigManager.getMultiplier(level);
        return cost;
    }


}
