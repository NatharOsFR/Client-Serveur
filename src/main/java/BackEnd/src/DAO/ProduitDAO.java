package BackEnd.src.DAO;

import BackEnd.src.Models.Produit;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduitDAO {
    private Connection connection;

    public ProduitDAO(Connection connection) {
        this.connection = connection;
    }

    public Produit getProduit(int idProduit) throws SQLException {
        String query = "SELECT * FROM PRODUIT WHERE id_produit = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idProduit);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Produit(
                        rs.getInt("id_produit"),
                        rs.getInt("id_categorie"),
                        rs.getString("nom_produit"),
                        rs.getString("description_produit"),
                        rs.getInt("prix_produit"),
                        rs.getInt("quantité_disponible")
                );
            }
        }
        return null;
    }

    public List<Produit> getProduitsParCategorie(int idCategorie) throws SQLException {
        List<Produit> produits = new ArrayList<>();
        String query = "SELECT * FROM PRODUIT WHERE id_categorie = ? AND quantité_disponible > 0";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCategorie);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                produits.add(new Produit(
                        rs.getInt("id_produit"),
                        rs.getInt("id_categorie"),
                        rs.getString("nom_produit"),
                        rs.getString("description_produit"),
                        rs.getInt("prix_produit"),
                        rs.getInt("quantité_disponible")
                ));
            }
        }
        return produits;
    }

    public boolean mettreAJourStock(int idProduit, int quantite) throws SQLException {
        String query = "UPDATE PRODUIT SET quantité_disponible = quantité_disponible + ? WHERE id_produit = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, quantite);
            stmt.setInt(2, idProduit);
            return stmt.executeUpdate() > 0;
        }
    }

    public int verifierStock(int idProduit) throws SQLException {
        String query = "SELECT quantité_disponible FROM PRODUIT WHERE id_produit = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idProduit);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantité_disponible");
            }
        }
        return 0;
    }
}
