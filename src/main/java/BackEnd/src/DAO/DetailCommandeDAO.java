package BackEnd.src.DAO;
import BackEnd.src.Models.DetailCommande;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DetailCommandeDAO {
    private Connection connection;

    public DetailCommandeDAO(Connection connection) {
        this.connection = connection;
    }

    public List<DetailCommande> getDetailsParCommande(int idCommande) throws SQLException {
        List<DetailCommande> details = new ArrayList<>();
        String query = "SELECT * FROM DetailCommande WHERE id_commande = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCommande);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                details.add(new DetailCommande(
                        rs.getInt("id_detail"),
                        rs.getInt("id_commande"),
                        rs.getInt("id_produit"),
                        rs.getInt("quantite_produit")
                ));
            }
        }
        return details;
    }

    public void ajouterProduitACommande(int idCommande, int idProduit, int quantite) throws SQLException {
        String query = "INSERT INTO DetailCommande (id_commande, id_produit, quantite_produit) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCommande);
            stmt.setInt(2, idProduit);
            stmt.setInt(3, quantite);
            stmt.executeUpdate();
        }
    }

    public void mettreAJourQuantiteProduit(int idCommande, int idProduit, int nouvelleQuantite) throws SQLException {
        String query = "UPDATE DetailCommande SET quantite_produit = ? WHERE id_commande = ? AND id_produit = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, nouvelleQuantite);
            stmt.setInt(2, idCommande);
            stmt.setInt(3, idProduit);
            stmt.executeUpdate();
        }
    }

    public Integer getQuantiteProduitDansCommande(int idCommande, int idProduit) throws SQLException {
        String query = "SELECT quantite_produit FROM DetailCommande WHERE id_commande = ? AND id_produit = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCommande);
            stmt.setInt(2, idProduit);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantite_produit");
            }
        }
        return null;
    }

}
