package BackEnd.src.Models;

public class Produit {
    private int idProduit;
    private int idCategorie;
    private String nomProduit;
    private String descriptionProduit;
    private int prixProduit;
    private int quantiteDisponible;

    public Produit(int idProduit, int idCategorie, String nomProduit, String descriptionProduit, int prixProduit, int quantiteDisponible) {
        this.idProduit = idProduit;
        this.idCategorie = idCategorie;
        this.nomProduit = nomProduit;
        this.descriptionProduit = descriptionProduit;
        this.prixProduit = prixProduit;
        this.quantiteDisponible = quantiteDisponible;
    }

    public int getIdProduit() { return idProduit; }
    public void setIdProduit(int idProduit) { this.idProduit = idProduit; }

    public int getIdCategorie() { return idCategorie; }
    public void setIdCategorie(int idCategorie) { this.idCategorie = idCategorie; }

    public String getNomProduit() { return nomProduit; }
    public void setNomProduit(String nomProduit) { this.nomProduit = nomProduit; }

    public String getDescriptionProduit() { return descriptionProduit; }
    public void setDescriptionProduit(String descriptionProduit) { this.descriptionProduit = descriptionProduit; }

    public int getPrixProduit() { return prixProduit; }
    public void setPrixProduit(int prixProduit) { this.prixProduit = prixProduit; }

    public int getQuantiteDisponible() { return quantiteDisponible; }
    public void setQuantiteDisponible(int quantiteDisponible) { this.quantiteDisponible = quantiteDisponible; }
}
