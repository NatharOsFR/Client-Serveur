package Middleware.src.Interfaces;

import BackEnd.src.Models.DetailCommande;
import BackEnd.src.Models.Produit;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.List;

public interface IProduitService extends Remote {
    Produit getProduit(String nomProduit) throws RemoteException, SQLException;

    Produit getProduitParId(int idProduit) throws RemoteException, SQLException;

    int acheterProduit(String nomProduit, int quantite) throws RemoteException, SQLException;

    int getCommandeEnCours() throws RemoteException, SQLException;

    List<Produit> getProduitsParNomCategorie(String nomCategorie) throws RemoteException, SQLException;

    public List<DetailCommande> getDetailsParCommande(int idCommande) throws RemoteException, SQLException;

    boolean payerCommande(int idCommande, String modePaiement) throws RemoteException;

}
