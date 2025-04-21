package BackEnd.src.DAO;

import BackEnd.src.Models.Categorie;
import java.sql.*;

public class CategorieDAO {
    private Connection connection;

    public CategorieDAO(Connection connection) {
        this.connection = connection;
    }

    public Categorie getCategorieParNom(String nomCategorie) throws SQLException {

        if (connection == null || connection.isClosed()) {
            throw new SQLException("La connexion à la base de données est invalide.");
        }

        String query = "SELECT * FROM Categorie WHERE nom_categorie = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nomCategorie);
            ResultSet rs = stmt.executeQuery();

            if (rs != null && rs.next()) {
                return new Categorie(
                        rs.getInt("id_categorie"),
                        rs.getString("nom_categorie"),
                        rs.getString("description_categorie")
                );
            }
        } catch (SQLException e) {
            throw e;
        }
        return null;
    }
}
