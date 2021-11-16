package redphoenix.toolrepair.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import redphoenix.toolrepair.utils.ItemBuilder;

public class RepairGUIHolder implements InventoryHolder {
    Inventory repairGUI = Bukkit.createInventory(this, 27, "§8§l➤ §9§lReparatur");

    @Override
    public Inventory getInventory() {
        return repairGUI;
    }

    public RepairGUIHolder(){
        ItemStack grayPane = new ItemBuilder(Material.GRAY_STAINED_GLASS_PANE).setDisplayName("§0").build();
        ItemStack blackPane = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("§0").build();

        ItemStack repair1Anvil = new ItemBuilder(Material.DAMAGED_ANVIL).setDisplayName("§c+1 Haltbarkeit").build();
        ItemStack repair10Anvil = new ItemBuilder(Material.CHIPPED_ANVIL).setDisplayName("§b+10 Haltbarkeit").build();
        ItemStack repairFullAnvil = new ItemBuilder(Material.ANVIL).setDisplayName("§aKomplett reparieren").build();

        //top row
        repairGUI.setItem(0, grayPane);
        repairGUI.setItem(1, grayPane);
        repairGUI.setItem(2, grayPane);
        repairGUI.setItem(3, blackPane);
        repairGUI.setItem(4, blackPane);
        repairGUI.setItem(5, blackPane);
        repairGUI.setItem(6, grayPane);
        repairGUI.setItem(7, grayPane);
        repairGUI.setItem(8, grayPane);
        //bottom
        repairGUI.setItem(18, grayPane);
        repairGUI.setItem(19, grayPane);
        repairGUI.setItem(20, grayPane);
        repairGUI.setItem(24, grayPane);
        repairGUI.setItem(25, grayPane);
        repairGUI.setItem(26, grayPane);
        //middle
        repairGUI.setItem(9, grayPane);
        repairGUI.setItem(10, grayPane);
        repairGUI.setItem(11, grayPane);
        repairGUI.setItem(12, blackPane);
        repairGUI.setItem(14, blackPane);
        repairGUI.setItem(15, grayPane);
        repairGUI.setItem(16, grayPane);
        repairGUI.setItem(17, grayPane);
        //repair anvils
        repairGUI.setItem(21, repair1Anvil);
        repairGUI.setItem(22, repair10Anvil);
        repairGUI.setItem(23, repairFullAnvil);

    }
}
