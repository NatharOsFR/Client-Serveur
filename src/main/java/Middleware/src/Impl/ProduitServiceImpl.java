package Middleware.src.Impl;


import BackEnd.src.Models.Produit;
import BackEnd.src.Services.ProduitService;
import Middleware.src.Interfaces.IProduitService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class ProduitServiceImpl extends UnicastRemoteObject implements IProduitService {
    private ProduitService produitService;

    public ProduitServiceImpl(ProduitService produitService) throws RemoteException {
        this.produitService = produitService;
    }

    @Override
    public Produit getProduit(int idProduit) throws RemoteException, SQLException {
        return produitService.getProduit(idProduit);
    }

    @Override
    public int acheterProduit(int idProduit) throws RemoteException, SQLException {
        return produitService.acheterProduit(idProduit);
    }
}
