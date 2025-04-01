package BackEnd.src.Services;

import BackEnd.src.DAO.CommandeDAO;
import BackEnd.src.DAO.DetailCommandeDAO;
import BackEnd.src.Models.Commande;

import java.sql.Date;
import java.sql.SQLException;

public class CommandeService {
    private final CommandeDAO commandeDAO;
    private final DetailCommandeDAO detailCommandeDAO;

    public CommandeService(CommandeDAO commandeDAO, DetailCommandeDAO detailCommandeDAO) {
        this.commandeDAO = commandeDAO;
        this.detailCommandeDAO = detailCommandeDAO;
    }


    public int getCommandeEnCoursOuCreer() throws SQLException {
        int idCommande = commandeDAO.getCommandeEnCours();
        if (idCommande == -1) {
            idCommande = commandeDAO.creerCommande();
        }
        return idCommande;
    }

    public void ajouterProduitACommande(int idCommande, int idProduit, int quantite) throws SQLException {
        Integer quantiteExistante = detailCommandeDAO.getQuantiteProduitDansCommande(idCommande, idProduit);

        if (quantiteExistante != null) {
            int nouvelleQuantite = quantiteExistante + quantite;
            detailCommandeDAO.mettreAJourQuantiteProduit(idCommande, idProduit, nouvelleQuantite);
        } else {
            detailCommandeDAO.ajouterProduitACommande(idCommande, idProduit, quantite);
        }

        commandeDAO.mettreAJourTotalCommande(idCommande);
    }

    public boolean payerCommande(int idCommande) throws SQLException {
        Commande commande = commandeDAO.getCommande(idCommande);
        if (commande == null) {
            System.out.println("Erreur : Commande vide ou inexistante !");
            return false;
        }
        return commandeDAO.mettreAJourStatutCommande(idCommande, "payée");
    }

    public double obtenirChiffreAffaire(Date date) {
        try {
            return commandeDAO.getChiffreAffaire(date);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
