package redphoenix.toolrepair.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import redphoenix.toolrepair.Main;
import redphoenix.toolrepair.gui.RepairGUIHolder;

public class RepairGUICommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player)sender;
            player.openInventory(new RepairGUIHolder().getInventory());
        }
        else{
            sender.sendMessage("§cDu darfst diesen Befehl nicht ausführen!");
        }
        return false;
    }
}
