package Middleware.src;

import BackEnd.src.DAO.CommandeDAO;
import BackEnd.src.DAO.DetailCommandeDAO;
import BackEnd.src.Services.ProduitService;
import Middleware.src.Impl.RMIServiceImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import BackEnd.src.DAO.ProduitDAO;
import BackEnd.src.Services.CommandeService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Server {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/DB_Heptathlon", "root", "root"
            );

            ProduitDAO produitDAO = new ProduitDAO(connection);
            CommandeDAO commandeDAO = new CommandeDAO(connection);
            DetailCommandeDAO detailCommandeDAO = new DetailCommandeDAO(connection);

            CommandeService commandeService = new CommandeService(commandeDAO, detailCommandeDAO, produitDAO);
            ProduitService produitService = new ProduitService(connection, produitDAO, commandeService);

            RMIServiceImpl RMIServiceImpl = new RMIServiceImpl(produitService, commandeService);

            LocateRegistry.createRegistry(1099);
            Naming.rebind("rmi://localhost/ProduitService", RMIServiceImpl);

            System.out.println("Serveur RMI prêt à accepter les connexions...");

        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Erreur serveur RMI : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
