package Dev.Damien.galacticDB.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String  jdbcUrl = "jdbc:mariadb://45.131.60.10:3306/galactic_database";
    private static final String USER = "galactic_database";
    private static final String PASSWORD = "yK^%T4GEy^dBA#KRQpvL";

    public void reloadDatabase() {
        try {
            Connection conn =getConnection();
            conn.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS players (" +
                            "uuid VARCHAR(36) NOT NULL PRIMARY KEY, " +
                            "name VARCHAR(50) NOT NULL UNIQUE, " +
                            "rank VARCHAR(50) NULL)"
            );
            conn.close();
            System.out.println("Database succesvol herladen!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public DatabaseManager() {
        try {
            Connection conn = getConnection();
            conn.createStatement().execute(
                    "CREATE TABLE IF NOT EXISTS players (" +
                            "uuid VARCHAR(36) NOT NULL PRIMARY KEY, " +
                            "name VARCHAR(50) NOT NULL UNIQUE, " +
                            "rank VARCHAR(50) NULL)"
            );
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Connection getConnection() throws  SQLException {
        return DriverManager.getConnection(jdbcUrl, USER, PASSWORD);
    }
    public void savePlayerData(String uuid, String name, String rank) {
        String query = "INSERT INTO players (uuid, name, rank) VALUES (?, ?, ?)";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, uuid);
            statement.setString(2, name);
            statement.setString(3, rank);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void removePlayerData(String uuid) {
        String query = "DELETE FROM players WHERE uuid = ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, uuid);
            statement.executeUpdate();
            System.out.println("Speler verwijderd uit database: " + uuid);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    public void clearAllPlayers() {
        String query = "DELETE FROM players";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.executeUpdate();
            System.out.println("Alle spelers zijn verwijderd uit de database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
