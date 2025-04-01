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
    public Produit getProduit(String nomProduit) throws RemoteException, SQLException {
        return produitService.getProduit(nomProduit);
    }

    @Override
    public int acheterProduit(String nomProduit) throws RemoteException, SQLException {
        return produitService.acheterProduit(nomProduit);
    }
}
