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

    public boolean mettreAJourPaiementEtStatut(int idCommande, String modePaiement, String nouveauStatut) throws SQLException {
        String query = "UPDATE Commande SET mode_de_paiement = ?, status_commande = ? WHERE id_commande = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            System.out.println("Tentative de mise à jour de la commande :");
            System.out.println("ID : " + idCommande);
            System.out.println("Mode de paiement : " + modePaiement);
            System.out.println("Nouveau statut : " + nouveauStatut);

            stmt.setString(1, modePaiement);
            stmt.setString(2, nouveauStatut);
            stmt.setInt(3, idCommande);
            return stmt.executeUpdate() > 0;
        }
    }

    public int creerCommande() throws SQLException {
        String query = "INSERT INTO Commande (date_commande, status_commande, total_commande, mode_de_paiement) VALUES (?, 'En cours', 0, 'Carte')";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setDate(1, new java.sql.Date(System.currentTimeMillis()));
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
        String query =
                "UPDATE Commande c SET total_commande = (" +
                        "  SELECT COALESCE(SUM(dc.quantite_produit * p.prix_produit), 0) " +
                        "  FROM DetailCommande dc " +
                        "  JOIN Produit p ON dc.id_produit = p.id_produit " +
                        "  WHERE dc.id_commande = ?" +
                        ") WHERE c.id_commande = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idCommande);
            stmt.setInt(2, idCommande);
            stmt.executeUpdate();
        }
    }

    public Commande getDerniereCommande() throws SQLException {
        String query = "SELECT * FROM Commande ORDER BY id_commande DESC LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
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
