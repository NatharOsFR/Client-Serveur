package DataBase;

import java.sql.Connection;

public class ConnexionDB{
  public static void main(String[] args) {
    Connection conn = DbSql.getConnection();
    if (conn != null) {
      System.out.println("Connexion réussie à MySQL !");
    } else {
      System.out.println("Erreur de connexion !");
    }
  }
}
