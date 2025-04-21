package BackEnd.src.Services;

import BackEnd.src.DAO.CommandeDAO;
import BackEnd.src.DAO.DetailCommandeDAO;
import BackEnd.src.DAO.ProduitDAO;
import BackEnd.src.Models.Commande;
import BackEnd.src.Models.DetailCommande;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class CommandeService {

    private ProduitDAO produitDAO;
    private final CommandeDAO commandeDAO;
    private final DetailCommandeDAO detailCommandeDAO;

    public CommandeService(CommandeDAO commandeDAO, DetailCommandeDAO detailCommandeDAO, ProduitDAO produitDAO) {
        this.commandeDAO = commandeDAO;
        this.detailCommandeDAO = detailCommandeDAO;
        this.produitDAO = produitDAO;
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
    public List<DetailCommande> getDetailsParCommande(int idCommande) throws SQLException {
        return detailCommandeDAO.getDetailsParCommande(idCommande);
    }
    public int getCommandeEnCours() throws SQLException {
        return commandeDAO.getCommandeEnCours();
    }
    public boolean payerCommande(int idCommande, String modePaiement) throws SQLException {

        Commande commande = commandeDAO.getCommande(idCommande);

        if (commande == null) {
            System.out.println("Erreur : Commande inexistante !");
            return false;
        }

        List<DetailCommande> details = detailCommandeDAO.getDetailsParCommande(idCommande);
        if (details == null || details.isEmpty()) {
            System.out.println("Erreur : La commande est vide, impossible de procéder au paiement.");
            return false;
        }

        boolean update = commandeDAO.mettreAJourPaiementEtStatut(idCommande, modePaiement, "Paye");

        if (update) {
            FactureService factureService = new FactureService(produitDAO);
            factureService.genererFacturePDF(idCommande, details, modePaiement);

            System.out.println("Paiement validé et facture générée.");
            return true;
        } else {
            System.out.println("Erreur lors de la mise à jour de la commande.");
            return false;
        }
    }

    public String getDerniereFacture() throws SQLException {
        Commande derniereCommande = commandeDAO.getDerniereCommande();
        if (derniereCommande != null) {
            return "factures/facture_commande_" + derniereCommande.getIdCommande() + ".pdf";
        }
        return null;
    }

    public double obtenirChiffreAffaire(Date date) {
        if (date == null) return 0.0;
        try {
            return commandeDAO.getChiffreAffaire(date);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
