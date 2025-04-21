package BackEnd.src.Services;

import BackEnd.src.DAO.ProduitDAO;
import BackEnd.src.DAO.CategorieDAO;
import BackEnd.src.Models.Categorie;
import BackEnd.src.Models.Produit;


import java.sql.Connection;
import java.util.List;
import java.sql.SQLException;

public class ProduitService {
    private ProduitDAO produitDAO;
    private Connection connection;
    private CommandeService commandeService;

    public ProduitService(Connection connection, ProduitDAO produitDAO, CommandeService commandeService) {
        this.produitDAO = produitDAO;
        this.commandeService = commandeService;
        this.connection = connection;
    }

    public int acheterProduit(String nomProduit, int quantite) throws SQLException {
        Produit produit = produitDAO.getProduit(nomProduit);
        if (produit == null) {
            throw new SQLException("Produit introuvable.");
        }
        if (produit.getQuantiteDisponible() < quantite) {
            throw new SQLException("Stock insuffisant pour le produit " + nomProduit);
        }

        int idCommande = commandeService.getCommandeEnCoursOuCreer();

        commandeService.ajouterProduitACommande(idCommande, produit.getIdProduit(), quantite);

        produitDAO.mettreAJourStock(produit.getIdProduit(), -quantite);

        return idCommande;
    }

    public Produit getProduit(String nomProduit) throws SQLException {
        Produit produit = produitDAO.getProduit(nomProduit);
        return produit;
    }

    public Produit getProduitParId(int idProduit) throws SQLException {
        return produitDAO.getProduitParId(idProduit);
    }


    public List<Produit> getProduitsParNomCategorie(String nomCategorie) throws SQLException {
        CategorieDAO categorieDAO = new CategorieDAO(connection);

        Categorie categorie = categorieDAO.getCategorieParNom(nomCategorie);

        if (categorie != null) {
            return produitDAO.getProduitsParCategorie(categorie.getIdCategorie());
        } else {
            return null;
        }
    }

    public boolean ajouterExemplairesProduit(int idProduit, int quantiteAAjouter) {
        try {
            return produitDAO.mettreAJourStock(idProduit, quantiteAAjouter);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
