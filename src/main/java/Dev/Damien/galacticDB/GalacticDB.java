package Dev.Damien.galacticDB;

import Dev.Damien.galacticDB.Command.CommandHandler;
import Dev.Damien.galacticDB.DB.DatabaseManager;
import Dev.Damien.galacticDB.Data.PlayerDataHandler;
import org.bukkit.plugin.java.JavaPlugin;

public final class GalacticDB extends JavaPlugin {
    private DatabaseManager databaseManager;

    @Override
    public void onEnable() {
        try {
            // Register MariaDB JDBC driver first
            Class.forName("org.mariadb.jdbc.Driver");

            // Then initialize database
            databaseManager = new DatabaseManager();

            // Register listeners and commands
            getServer().getPluginManager().registerEvents(new PlayerDataHandler(databaseManager), this);
            getCommand("reloadDB").setExecutor(new CommandHandler(databaseManager));

            getLogger().info("DB wilt dood!");
        } catch (ClassNotFoundException e) {
            getLogger().severe("MariaDB JDBC driver not found! Plugin will be disabled.");
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
        }
    }


    @Override
    public void onDisable() {
        getLogger().info("DB is gestorven door eenzaamheid");
    }
}
