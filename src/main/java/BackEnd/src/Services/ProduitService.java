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

    public int acheterProduit(int idProduit, int idClient) throws SQLException {
        Produit produit = produitDAO.getProduit(idProduit);
        if (produit == null) {
            throw new SQLException("Produit introuvable.");
        }
        if (produit.getQuantiteDisponible() <= 0) {
            throw new SQLException("Stock insuffisant pour le produit " + idProduit);
        }

        int idCommande = commandeService.getCommandeEnCoursOuCreer(idClient);

        commandeService.ajouterProduitACommande(idCommande, idProduit, 1);

        produitDAO.mettreAJourStock(idProduit, -1);

        return idCommande;
    }
}
