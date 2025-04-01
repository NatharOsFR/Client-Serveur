package Middleware.src.Interfaces;

import BackEnd.src.Models.Produit;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface IProduitService extends Remote {
    Produit getProduit(int idProduit) throws RemoteException, SQLException;
    int acheterProduit(int idProduit) throws RemoteException, SQLException;
}
