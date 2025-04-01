package FrontEnd.src.Services;

import BackEnd.src.Models.Produit;
import Middleware.src.Interfaces.IProduitService;

import java.rmi.Naming;

public class ProduitService {
    private IProduitService produitServiceRMI;

    public ProduitService() {
        try {
            produitServiceRMI = (IProduitService) Naming.lookup("rmi://localhost/ProduitService");
            System.out.println("Connexion au service RMI réussie !");
        } catch (Exception e) {
            System.out.println("Erreur lors de la connexion RMI : " + e.getMessage());
            e.printStackTrace();
        }
    }

    public String getProduit(String idProduit) {
        try {
            Produit produit = produitServiceRMI.getProduit(Integer.parseInt(idProduit));
            if (produit != null) {
                return produit.toString();
            } else {
                return "Produit introuvable.";
            }
        } catch (Exception e) {
            return "Erreur lors de la récupération du produit: " + e.getMessage();
        }
    }

    public String acheterProduit(String idProduit) {
        try {
            String reponse = String.valueOf(produitServiceRMI.acheterProduit(Integer.parseInt(idProduit)));
            return reponse;
        } catch (Exception e) {
            return "Erreur lors de l'achat du produit: " + e.getMessage();
        }
    }
}
