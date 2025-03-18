package BackEnd.src.Models;

public class DetailCommande {
    private int idDetail;
    private int idCommande;
    private int idProduit;
    private int quantiteProduit;

    public DetailCommande(int idDetail, int idCommande, int idProduit, int quantiteProduit) {
        this.idDetail = idDetail;
        this.idCommande = idCommande;
        this.idProduit = idProduit;
        this.quantiteProduit = quantiteProduit;
    }

    public int getIdDetail() { return idDetail; }
    public void setIdDetail(int idDetail) { this.idDetail = idDetail; }

    public int getIdCommande() { return idCommande; }
    public void setIdCommande(int idCommande) { this.idCommande = idCommande; }

    public int getIdProduit() { return idProduit; }
    public void setIdProduit(int idProduit) { this.idProduit = idProduit; }

    public int getQuantiteProduit() { return quantiteProduit; }
    public void setQuantiteProduit(int quantiteProduit) { this.quantiteProduit = quantiteProduit; }
}
