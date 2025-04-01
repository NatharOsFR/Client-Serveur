package Middleware.src.Interfaces;

import BackEnd.src.Models.Produit;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface IProduitService extends Remote {
    public Produit getProduit(String nomProduit) throws RemoteException, SQLException;

    public int acheterProduit(String nomProduit) throws RemoteException, SQLException;
}
