package BackEnd.src.Services;

import BackEnd.src.DAO.ProduitDAO;
import BackEnd.src.Models.Produit;
import java.sql.SQLException;

public class ProduitService {
    private ProduitDAO produitDAO;
    private CommandeService commandeService;

    public ProduitService(ProduitDAO produitDAO, CommandeService commandeService) {
        this.produitDAO = produitDAO;
        this.commandeService = commandeService;
    }

    public int acheterProduit(String nomProduit) throws SQLException {
        Produit produit = produitDAO.getProduit(nomProduit);
        if (produit == null) {
            throw new SQLException("Produit introuvable.");
        }
        if (produit.getQuantiteDisponible() <= 0) {
            throw new SQLException("Stock insuffisant pour le produit " + nomProduit);
        }

        int idCommande = commandeService.getCommandeEnCoursOuCreer();

        commandeService.ajouterProduitACommande(idCommande, produit.getIdProduit(), 1);

        produitDAO.mettreAJourStock(produit.getIdProduit(), -1);

        return idCommande;
    }

    public Produit getProduit(String nomProduit) throws SQLException {
        Produit produit = produitDAO.getProduit(nomProduit);
        if (produit == null) {
            throw new SQLException("Produit introuvable.");
        }
        return produit;
    }
}
