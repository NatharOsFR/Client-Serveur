package BackEnd.src.DAO;
import BackEnd.src.Models.Categorie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieDAO {
    private Connection connection;

    public CategorieDAO(Connection connection) {
        this.connection = connection;
    }

    public Categorie getCategorie(int idCategorie) throws SQLException {
        String query = "SELECT * FROM CATEGORIE WHERE id_categorie = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCategorie);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Categorie(
                        rs.getInt("id_categorie"),
                        rs.getString("nom_categorie"),
                        rs.getString("description_categorie")
                );
            }
        }
        return null;
    }

    public List<Categorie> getAllCategories() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String query = "SELECT * FROM CATEGORIE";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                categories.add(new Categorie(
                        rs.getInt("id_categorie"),
                        rs.getString("nom_categorie"),
                        rs.getString("description_categorie")
                ));
            }
        }
        return categories;
    }
}
