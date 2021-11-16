package redphoenix.toolrepair.gui;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import redphoenix.toolrepair.Main;
import sun.tools.jconsole.Tab;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class RepairGUIListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event){
        if(event.getInventory().getHolder() instanceof RepairGUIHolder){
            if(event.getCurrentItem() == null){
                return;
            }
            if(event.getCurrentItem().getType().equals(Material.GRAY_STAINED_GLASS_PANE)){
                event.setCancelled(true);
            }
            else if(event.getCurrentItem().getType().equals(Material.BLACK_STAINED_GLASS_PANE)){
                event.setCancelled(true);
            }
            else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§c+1 Haltbarkeit")){
                event.setCancelled(true);
                if(event.getInventory().getItem(13) == null){
                       event.getWhoClicked().sendMessage("§9§lReparieren §8§l✘ §cDu hast noch kein Item zum reparieren ausgewählt.");
                }
                else{
                    if(event.getInventory().getItem(13).getType().getMaxDurability() == 0 || event.getInventory().getItem(13).getDurability() == 0){
                        event.getWhoClicked().sendMessage("§9§lReparieren §8§l✘ §cDieses Item kann nicht repariert werden!");
                    }
                    else{
                        //
                    }
                }
            }
            else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§b+10 Haltbarkeit")){
                event.setCancelled(true);
                if(event.getInventory().getItem(13) == null){
                    event.getWhoClicked().sendMessage("§9§lReparieren §8§l✘ §cDu hast noch kein Item zum reparieren ausgewählt.");
                }
                else{
                    if(event.getInventory().getItem(13).getType().getMaxDurability() == 0 || event.getInventory().getItem(13).getDurability() == 0){
                        event.getWhoClicked().sendMessage("§9§lReparieren §8§l✘ §cDieses Item kann nicht repariert werden!");
                    }
                    else{

                    }
                }
            }
            else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§aKomplett reparieren")){
                event.setCancelled(true);
                if(event.getInventory().getItem(13) == null){
                    event.getWhoClicked().sendMessage("§9§lReparieren §8§l✘ §cDu hast noch kein Item zum reparieren ausgewählt.");
                }
                else{
                    if(event.getInventory().getItem(13).getType().getMaxDurability() == 0 || event.getInventory().getItem(13).getDurability() == 0){
                        event.getWhoClicked().sendMessage("§9§lReparieren §8§l✘ §cDieses Item kann nicht repariert werden!");
                    }
                    else{
                        Damageable item= (Damageable) event.getInventory().getItem(13).getItemMeta();
                        double cost = Main.calculateRepairCost(event.getInventory().getItem(13).getEnchantments(), item.getDamage());
                        event.getWhoClicked().sendMessage("Kosten: " + cost);
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event){
        if(event.getInventory().getHolder() instanceof RepairGUIHolder){
            if(event.getInventory().getItem(13) != null){
                if(event.getPlayer().getInventory().firstEmpty() != -1) {
                    event.getPlayer().getInventory().addItem(event.getInventory().getItem(13));
                }
                else{
                    event.getPlayer().getWorld().dropItem(event.getPlayer().getLocation(), event.getInventory().getItem(13));
                }
            }
        }
    }
}
