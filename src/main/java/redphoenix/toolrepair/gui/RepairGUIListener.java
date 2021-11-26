package redphoenix.toolrepair.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
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

            Player player = (Player)event.getWhoClicked();

            if(event.getClick() == ClickType.LEFT || event.getClick() == ClickType.SHIFT_LEFT){
                if(event.getCurrentItem() != null){
                    ItemStack item = event.getCurrentItem();
                    ItemMeta itemMeta = item.getItemMeta();
                    Damageable dmg = (Damageable)itemMeta;
                    ItemMeta infoBookMeta = event.getInventory().getItem(10).getItemMeta();
                    ArrayList<String> lore = (ArrayList<String>) infoBookMeta.getLore();
                    if(dmg.getDamage() != 0){
                        lore.set(1, "§c1 Haltbarkeitspunkt: §e" + (float)Main.calculateRepairCost(item.getEnchantments(), 1));
                        lore.set(2, "§b10 Haltbarkeitspunkte: §e" + (float)Main.calculateRepairCost(item.getEnchantments(), 10));
                        lore.set(3, "§aKomplette Reparatur: §e" + (float)Main.calculateRepairCost(item.getEnchantments(), dmg.getDamage()));
                    }
                    infoBookMeta.setLore(lore);
                    event.getInventory().getItem(10).setItemMeta(infoBookMeta);

                    Bukkit.getScheduler().runTaskLaterAsynchronously(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            player.updateInventory();
                        }
                    }, 2L);
                }
            }

            if(event.getCurrentItem() == null){
                return;
            }
            if(event.getCurrentItem().getType().equals(Material.GRAY_STAINED_GLASS_PANE)){
                event.setCancelled(true);
            }
            else if(event.getCurrentItem().getType().equals(Material.BLACK_STAINED_GLASS_PANE)){
                event.setCancelled(true);
            }
            else if(event.getCurrentItem().getType().equals(Material.BOOK) && event.getCurrentItem().getItemMeta().getDisplayName().equals("§eReparaturpreise")){
                event.setCancelled(true);
            }
            else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§c+1 Haltbarkeit")){
                event.setCancelled(true);
                if(event.getInventory().getItem(13) == null){
                       player.sendMessage("§9§lReparieren §8§l✘ §cDu hast noch kein Item zum reparieren ausgewählt.");
                }
                else{
                    if(event.getInventory().getItem(13).getType().getMaxDurability() == 0 || event.getInventory().getItem(13).getDurability() == 0){
                        player.sendMessage("§9§lReparieren §8§l✘ §cDieses Item kann nicht repariert werden!");
                    }
                    else{
                        Damageable dmg = (Damageable)event.getInventory().getItem(13).getItemMeta();
                        double cost = Main.calculateRepairCost(event.getInventory().getItem(13).getEnchantments(), 1);
                        if(Main.getEconomy().getBalance((OfflinePlayer) player) < cost){
                            player.sendMessage("§9§lReparieren §8§l✘ §cDu hast nicht genügend Geld um das Item zu reparieren!");
                        }
                        else{
                            dmg.setDamage(dmg.getDamage() - 1);
                            event.getInventory().getItem(13).setItemMeta((ItemMeta)dmg);
                            Main.getEconomy().withdrawPlayer(player, cost);
                            player.sendMessage("§9§lReparieren §8§l✘ §aDein Item wurde erfolgreich repariert.");
                        }
                    }
                }
            }
            else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§b+10 Haltbarkeit")){
                event.setCancelled(true);
                if(event.getInventory().getItem(13) == null){
                    player.sendMessage("§9§lReparieren §8§l✘ §cDu hast noch kein Item zum reparieren ausgewählt.");
                }
                else{
                    if(event.getInventory().getItem(13).getType().getMaxDurability() == 0 || event.getInventory().getItem(13).getDurability() == 0){
                        player.sendMessage("§9§lReparieren §8§l✘ §cDieses Item kann nicht repariert werden!");
                    }
                    else{
                        Damageable dmg = (Damageable)event.getInventory().getItem(13).getItemMeta();
                        double cost = Main.calculateRepairCost(event.getInventory().getItem(13).getEnchantments(), 10);
                        if(Main.getEconomy().getBalance((OfflinePlayer) player) < cost){
                            player.sendMessage("§9§lReparieren §8§l✘ §cDu hast nicht genügend Geld um das Item zu reparieren!");
                        }
                        else{
                            dmg.setDamage(dmg.getDamage() - 10);
                            event.getInventory().getItem(13).setItemMeta((ItemMeta)dmg);
                            Main.getEconomy().withdrawPlayer(player, cost);
                            player.sendMessage("§9§lReparieren §8§l✘ §aDein Item wurde erfolgreich repariert.");
                        }
                    }
                }
            }
            else if(event.getCurrentItem().getItemMeta().getDisplayName().equals("§aKomplett reparieren")){
                event.setCancelled(true);
                if(event.getInventory().getItem(13) == null){
                    player.sendMessage("§9§lReparieren §8§l✘ §cDu hast noch kein Item zum reparieren ausgewählt.");
                }
                else{
                    if(event.getInventory().getItem(13).getType().getMaxDurability() == 0 || event.getInventory().getItem(13).getDurability() == 0){
                        player.sendMessage("§9§lReparieren §8§l✘ §cDieses Item kann nicht repariert werden!");
                    }
                    else{
                        if(event.getInventory().getItem(13).getType().getMaxDurability() == 0 || event.getInventory().getItem(13).getDurability() == 0){
                            player.sendMessage("§9§lReparieren §8§l✘ §cDieses Item kann nicht repariert werden!");
                        }
                        else{
                            Damageable dmg = (Damageable)event.getInventory().getItem(13).getItemMeta();
                            double cost = Main.calculateRepairCost(event.getInventory().getItem(13).getEnchantments(), dmg.getDamage());
                            if(Main.getEconomy().getBalance((OfflinePlayer) player) < cost){
                                player.sendMessage("§9§lReparieren §8§l✘ §cDu hast nicht genügend Geld um das Item zu reparieren!");
                            }
                            else{
                                dmg.setDamage(0);
                                event.getInventory().getItem(13).setItemMeta((ItemMeta)dmg);
                                Main.getEconomy().withdrawPlayer(player, cost);
                                player.sendMessage("§9§lReparieren §8§l✘ §aDein Item wurde erfolgreich repariert.");
                            }
                        }
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
