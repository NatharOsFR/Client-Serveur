package BackEnd.src.DAO;

import BackEnd.src.Models.Commande;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommandeDAO {
    private Connection connection;

    public CommandeDAO(Connection connection) {
        this.connection = connection;
    }

    public Commande getCommande(int idCommande) throws SQLException {
        String query = "SELECT * FROM Commande WHERE id_commande = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCommande);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Commande(
                        rs.getInt("id_commande"),
                        rs.getDate("date_commande"),
                        rs.getString("status_commande"),
                        rs.getInt("total_commande"),
                        rs.getString("mode_de_paiement")
                );
            }
        }
        return null;
    }

    public int getCommandeEnCours() throws SQLException {
        String query = "SELECT id_commande FROM Commande WHERE status_commande = 'en cours'";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_commande");
            }
        }
        return -1;
    }

    public List<Commande> getCommandesParDate(Date date) throws SQLException {
        List<Commande> commandes = new ArrayList<>();
        String query = "SELECT * FROM Commande WHERE date_commande = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                commandes.add(new Commande(
                        rs.getInt("id_commande"),
                        rs.getDate("date_commande"),
                        rs.getString("status_commande"),
                        rs.getInt("total_commande"),
                        rs.getString("mode_de_paiement")
                ));
            }
        }
        return commandes;
    }

    public boolean mettreAJourStatutCommande(int idCommande, String nouveauStatut) throws SQLException {
        String query = "UPDATE Commande SET statut = ? WHERE id_commande = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nouveauStatut);
            stmt.setInt(2, idCommande);
            return stmt.executeUpdate() > 0;
        }
    }

    public int creerCommande() throws SQLException {
        String query = "INSERT INTO Commande (id_client, statut) VALUES (?, 'en cours')";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.executeUpdate();
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                return generatedKeys.getInt(1);
            } else {
                throw new SQLException("Échec de la création de la commande.");
            }
        }
    }

    public void mettreAJourTotalCommande(int idCommande) throws SQLException {
        String query = "UPDATE Commande c SET total_commande = " +
                "(SELECT COALESCE(SUM(dc.quantite * p.prix_produit), 0) " +
                "FROM detail_commande dc " +
                "JOIN Produit p ON dc.id_produit = p.id_produit " +
                "WHERE dc.id_commande = ?) " +
                "WHERE c.id_commande = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCommande);
            stmt.setInt(2, idCommande);
            stmt.executeUpdate();
        }
    }
    public double getChiffreAffaire(Date date) throws SQLException {
        String query = "SELECT SUM(total_commande) AS total_chiffre_affaire FROM Commande WHERE date_commande = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDate(1, new java.sql.Date(date.getTime()));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getDouble("total_chiffre_affaire");
            }
        }
        return 0.0;
    }
}
