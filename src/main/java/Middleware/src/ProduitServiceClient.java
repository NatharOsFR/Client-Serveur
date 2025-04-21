package Middleware.src;

import BackEnd.src.Models.Produit;
import Middleware.src.Interfaces.IProduitService;

import java.rmi.Naming;
import java.util.List;

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
            String produit = String.valueOf(produitServiceRMI.getProduit(String.valueOf(idProduit)));
            System.out.println("Détails du produit : " + produit);
        } catch (Exception e) {
            System.out.println("Erreur lors de la récupération du produit: " + e.getMessage());
        }
    }



    public static void main(String[] args) {
        ProduitServiceClient client = new ProduitServiceClient();

    }

}
