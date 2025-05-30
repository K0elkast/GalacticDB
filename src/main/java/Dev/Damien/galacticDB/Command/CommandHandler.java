package Dev.Damien.galacticDB.Command;

import Dev.Damien.galacticDB.DB.DatabaseManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHandler implements CommandExecutor {
    private final DatabaseManager databaseManager;

    public CommandHandler(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("reloadDB")) {
            if (sender instanceof Player &&  !sender.hasPermission("DB.Reload")) {
                sender.sendMessage("&cJe hebt geen permissie droogkloot");
                return true;
            }
            sender.sendMessage("§a Database word herladen...");
            databaseManager.reloadDatabase();
            sender.sendMessage("§aDatabase is herladen!");
            return true;
        }
        return false;
    }
}
