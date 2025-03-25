package FrontEnd.src.Services;

public class ProduitService {
    public String getProduit(String idProduit) {
        return appelerBackend("getProduit", idProduit);
    }

    public String acheterProduit(String idProduit, int idClient) {
        return appelerBackend("acheterProduit", idProduit + "," + idClient);
    }

    private String appelerBackend(String action, String param) {
        if ("getProduit".equals(action)) {
            return "Produit trouvé: ID " + param + ", Quantité: 10, Prix: 100€";
        } else if ("acheterProduit".equals(action)) {
            return "Achat réussi pour le produit ID " + param.split(",")[0];
        }
        return "Erreur: Action inconnue";
    }
}
