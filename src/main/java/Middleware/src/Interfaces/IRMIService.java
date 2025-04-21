package Middleware.src.Interfaces;

import BackEnd.src.Models.DetailCommande;
import BackEnd.src.Models.Produit;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.sql.SQLException;
import java.util.List;

public interface IRMIService extends Remote {
    Produit getProduit(String nomProduit) throws RemoteException, SQLException;

    Produit getProduitParId(int idProduit) throws RemoteException, SQLException;

    int acheterProduit(String nomProduit, int quantite) throws RemoteException, SQLException;

    int getCommandeEnCours() throws RemoteException, SQLException;

    List<Produit> getProduitsParNomCategorie(String nomCategorie) throws RemoteException, SQLException;

    List<DetailCommande> getDetailsParCommande(int idCommande) throws RemoteException, SQLException;

    boolean payerCommande(int idCommande, String modePaiement) throws RemoteException;

    String getDerniereFacture() throws RemoteException;

    boolean ajouterExemplairesProduit(int idProduit, int quantite) throws RemoteException;

    double obtenirChiffreAffaire(Date date) throws RemoteException;

}
