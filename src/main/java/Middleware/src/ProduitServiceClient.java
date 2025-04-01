package Middleware.src;

import BackEnd.src.Models.Produit;
import Middleware.src.Interfaces.IProduitService;

import java.rmi.Naming;

public class ProduitServiceClient {
    private IProduitService produitServiceRMI;

    public ProduitServiceClient() {
        try {
            produitServiceRMI = (IProduitService) Naming.lookup("rmi://localhost/ProduitService");
            System.out.println("Connexion au service RMI réussie !");
        } catch (Exception e) {
            System.out.println("Erreur client RMI: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void afficherProduit(int idProduit) {
        try {
            String produit = String.valueOf(produitServiceRMI.getProduit(idProduit));
            System.out.println("Détails du produit : " + produit);
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération du produit: " + e.getMessage());
        }
    }

    public String acheterProduit(int idProduit) {
        try {
            String reponse = String.valueOf(produitServiceRMI.acheterProduit(idProduit));
            System.out.println(reponse);
        } catch (Exception e) {
            System.out.println("Erreur lors de l'achat du produit: " + e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        ProduitServiceClient client = new ProduitServiceClient();

        client.afficherProduit(1);
        client.acheterProduit(1);
    }

    public String getProduit(String idProduit) {
        try {
            Produit produit = produitServiceRMI.getProduit(Integer.parseInt(idProduit));
            if (produit != null) {
                return "Produit: ";
            } else {
                return "Produit introuvable.";
            }
        } catch (Exception e) {
            return "Erreur lors de la récupération du produit: " + e.getMessage();
        }
    }
}
