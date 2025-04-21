package Middleware.src;

import FrontEnd.src.Services.FrontService;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

class Administrateur {
    private FrontService frontService;

    public Administrateur() {
        frontService = new FrontService();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Administrateur administrateur = new Administrateur();

        boolean continuer = true;

        while (continuer) {
            try {
                System.out.println("\nChoisissez une option :");
                System.out.println("1. Ajouter des exemplaires à un produit");
                System.out.println("2. Calculer le chiffre d'affaires d'une date");
                System.out.println("3. Quitter");

                System.out.print("Entrez le numéro de l'option : ");
                int choix = scanner.nextInt();

                if (choix == 1) {
                    System.out.print("Entrez l'ID du produit à mettre à jour : ");
                    int idProduit = scanner.nextInt();

                    System.out.print("Entrez la quantité à ajouter : ");
                    int quantite = scanner.nextInt();

                    boolean success = administrateur.frontService.ajouterExemplairesProduit(idProduit, quantite);
                    System.out.println(success ? "Stock mis à jour avec succès pour le produit " + idProduit
                            : "Échec de l'ajout de stock pour le produit " + idProduit);
                }
                else if (choix == 2) {
                    System.out.print("Entrez une date pour le calcul du chiffre d'affaires (format : yyyy-MM-dd) : ");
                    String dateStr = scanner.next();

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = sdf.parse(dateStr);

                    double chiffreAffaire = administrateur.frontService.obtenirChiffreAffaire(date);
                    System.out.println("Le chiffre d'affaire du " + dateStr + " est : " + chiffreAffaire + " €");
                } else if (choix == 3) {
                    continuer = false;
                } else {
                    System.out.println("Option invalide.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Erreur : " + e.getMessage());
            }
        }

        scanner.close();
    }
}
