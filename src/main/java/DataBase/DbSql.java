package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;
import java.io.IOException;

public class DbSql {

  public static Connection getConnection() {

    Properties properties = new Properties();

    try (InputStream input = DbSql.class.getClassLoader().getResourceAsStream("db-config.proprieties")) {
      if (input == null) {

        System.out.println("Je n'ai pas trouvé le fichier de configuration");
        return null;
      }
      properties.load(input);

      String url = properties.getProperty("db.url");
      String username = properties.getProperty("db.username");
      String password = properties.getProperty("db.password");

      return DriverManager.getConnection(url, username, password);

    } catch (IOException | SQLException e) {
      System.err.println("Erreur lors de la connexion à la base de données : " + e.getMessage());
      return null;
    }
  }

  public static void closeConnection(Connection conn) {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        System.err.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
      }

    }
  }
}

