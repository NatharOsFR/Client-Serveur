package BackEnd.src.Models;

public class Categorie {
    private int idCategorie;
    private String nomCategorie;
    private String descriptionCategorie;

    public Categorie(int idCategorie, String nomCategorie, String descriptionCategorie) {
        this.idCategorie = idCategorie;
        this.nomCategorie = nomCategorie;
        this.descriptionCategorie = descriptionCategorie;
    }

    public int getIdCategorie() { return idCategorie; }
    public void setIdCategorie(int idCategorie) { this.idCategorie = idCategorie; }

    public String getNomCategorie() { return nomCategorie; }
    public void setNomCategorie(String nomCategorie) { this.nomCategorie = nomCategorie; }

    public String getDescriptionCategorie() { return descriptionCategorie; }
    public void setDescriptionCategorie(String descriptionCategorie) { this.descriptionCategorie = descriptionCategorie; }
}
