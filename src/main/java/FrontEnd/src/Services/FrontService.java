package FrontEnd.src.Services;

import BackEnd.src.Models.DetailCommande;
import BackEnd.src.Models.Produit;
import Middleware.src.Interfaces.IRMIService;

import java.rmi.Naming;
import java.util.List;
import java.util.Date;

public class FrontService {
    private IRMIService produitServiceRMI;

    public FrontService() {
        try {
            produitServiceRMI = (IRMIService) Naming.lookup("rmi://localhost/ProduitService");
            System.out.println("Connexion au service RMI réussie !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion RMI : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Produit getProduit(String nomProduit) {
        try {
            return produitServiceRMI.getProduit(nomProduit);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Produit getProduitParId(int idProduit) {
        try {
            return produitServiceRMI.getProduitParId(idProduit);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String acheterProduit(String nomProduit, int quantite) {
        try {
            int idCommande = produitServiceRMI.acheterProduit(nomProduit, quantite);
            return "Produit ajouté au panier ! Commande N° : " + idCommande;
        } catch (Exception e) {
            return "Erreur lors de l'achat du produit: " + e.getMessage();
        }
    }

    public int getCommandeEnCours() {
        try {
            return produitServiceRMI.getCommandeEnCours();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public List<Produit> getProduitsParNomCategorie(String nomCategorie) {
        try {
            return produitServiceRMI.getProduitsParNomCategorie(nomCategorie);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<DetailCommande> getDetailsParCommande(int idCommande) {
        try {
            return produitServiceRMI.getDetailsParCommande(idCommande);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean payerCommande(int idCommande, String modePaiement) {
        try {
            return produitServiceRMI.payerCommande(idCommande, modePaiement);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getDerniereFacture() {
        try {
            return produitServiceRMI.getDerniereFacture();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean ajouterExemplairesProduit(int idProduit, int quantite) {
        try {
            return produitServiceRMI.ajouterExemplairesProduit(idProduit, quantite);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public double obtenirChiffreAffaire(Date date) {
        try {
            return produitServiceRMI.obtenirChiffreAffaire(date);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
