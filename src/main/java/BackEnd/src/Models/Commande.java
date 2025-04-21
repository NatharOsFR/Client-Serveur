package BackEnd.src.Models;
import java.io.Serializable;
import java.util.Date;

public class Commande implements Serializable {
    private static final long serialVersionUID = 1L;
    private int idCommande;
    private Date dateCommande;
    private String statusCommande;
    private int totalCommande;
    private String modeDePaiement;

    public Commande(int idCommande, Date dateCommande, String statusCommande, int totalCommande, String modeDePaiement) {
        this.idCommande = idCommande;
        this.dateCommande = dateCommande;
        this.statusCommande = statusCommande;
        this.totalCommande = totalCommande;
        this.modeDePaiement = modeDePaiement;
    }

    public int getIdCommande() { return idCommande; }
    public void setIdCommande(int idCommande) { this.idCommande = idCommande; }

    public Date getDateCommande() { return dateCommande; }
    public void setDateCommande(Date dateCommande) { this.dateCommande = dateCommande; }

    public String getStatusCommande() { return statusCommande; }
    public void setStatusCommande(String statusCommande) { this.statusCommande = statusCommande; }

    public int getTotalCommande() { return totalCommande; }
    public void setTotalCommande(int totalCommande) { this.totalCommande = totalCommande; }

    public String getModeDePaiement() { return modeDePaiement; }
    public void setModeDePaiement(String modeDePaiement) { this.modeDePaiement = modeDePaiement; }
}
