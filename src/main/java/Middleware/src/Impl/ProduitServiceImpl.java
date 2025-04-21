package Middleware.src.Impl;


import BackEnd.src.Models.DetailCommande;
import BackEnd.src.Models.Produit;
import BackEnd.src.Services.ProduitService;
import BackEnd.src.Services.CommandeService;
import Middleware.src.Interfaces.IProduitService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.List;

public class ProduitServiceImpl extends UnicastRemoteObject implements IProduitService {
    private ProduitService produitService;
    private CommandeService commandeService;

    public ProduitServiceImpl(ProduitService produitService, CommandeService commandeService) throws RemoteException {
        this.produitService = produitService;
        this.commandeService = commandeService;
    }

    @Override
    public Produit getProduit(String nomProduit) throws RemoteException, SQLException {
        return produitService.getProduit(nomProduit);
    }

    @Override
    public Produit getProduitParId(int idProduit) throws RemoteException, SQLException {
        return produitService.getProduitParId(idProduit);
    }

    @Override
    public int acheterProduit(String nomProduit, int quantite) throws RemoteException, SQLException {
        return produitService.acheterProduit(nomProduit, quantite);
    }

    @Override
    public int getCommandeEnCours() throws RemoteException, SQLException {
        return commandeService.getCommandeEnCours();
    }

    @Override
    public List<Produit> getProduitsParNomCategorie(String nomCategorie) throws RemoteException, SQLException {
        return produitService.getProduitsParNomCategorie(nomCategorie);
    }

    @Override
    public List<DetailCommande> getDetailsParCommande(int idCommande)
            throws RemoteException, SQLException {
        return commandeService.getDetailsParCommande(idCommande);
    }

    @Override
    public boolean payerCommande(int idCommande, String modePaiement) throws RemoteException {
        try {
            return commandeService.payerCommande(idCommande, modePaiement);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
