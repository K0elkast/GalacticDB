package Dev.Damien.galacticDB.Data;

import Dev.Damien.galacticDB.DB.DatabaseManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerDataHandler implements Listener {
    private final DatabaseManager databaseManager;

    public PlayerDataHandler(DatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();
        String name = player.getName();
        String rank = getPlayerRank(player);

        databaseManager.savePlayerData(uuid, name, rank);
    }
    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();

        databaseManager.removePlayerData(uuid);
    }
    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        Player player = event.getPlayer();
        String uuid = player.getUniqueId().toString();

        databaseManager.removePlayerData(uuid);
    }
    private String getPlayerRank(Player player) {
        LuckPerms luckPerms = LuckPermsProvider.get();
        User user = luckPerms.getUserManager().getUser(player.getUniqueId());

        return (user != null) ? user.getPrimaryGroup() : "DefaultRank";
    }
}
