package FrontEnd.src.UI;

import FrontEnd.src.Services.FrontService;
import BackEnd.src.Models.Produit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class Accueil extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JButton buyButton;
    private JButton panierButton;
    private JButton factureButton;
    private FrontService frontService;

    public Accueil() {
        frontService = new FrontService();

        setTitle("Accueil");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getContentPane().setBackground(new Color(255, 255, 255));

        JLabel titleLabel = new JLabel("Bienvenue sur l'application de commande", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        add(titleLabel);

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchField = new JTextField(20);
        searchButton = new JButton("Rechercher");
        buyButton = new JButton("Ajouter Produit");

        searchField.setPreferredSize(new Dimension(200, 30));
        searchButton.setPreferredSize(new Dimension(180, 40));
        buyButton.setPreferredSize(new Dimension(180, 40));

        searchButton.setBackground(new Color(70, 130, 180));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        buyButton.setBackground(new Color(60, 179, 113));
        buyButton.setForeground(Color.WHITE);
        buyButton.setFont(new Font("Arial", Font.BOLD, 14));

        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(buyButton);
        add(searchPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        panierButton = new JButton("Voir Mon Panier");
        factureButton = new JButton("Consulter Ma Facture");

        panierButton.setBackground(new Color(70, 130, 180));
        factureButton.setBackground(new Color(100, 149, 237));
        panierButton.setForeground(Color.WHITE);
        factureButton.setForeground(Color.WHITE);
        panierButton.setFont(new Font("Arial", Font.BOLD, 14));
        factureButton.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(panierButton);
        buttonPanel.add(factureButton);
        add(buttonPanel);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                if (!searchText.isEmpty()) {
                    try {
                        List<Produit> produits = frontService.getProduitsParNomCategorie(searchText);

                        if (produits != null && !produits.isEmpty()) {
                            StringBuilder productsInfo = new StringBuilder(" 🛒 Produits disponibles dans la catégorie " + searchText + ":\n\n");
                            for (Produit produit : produits) {
                                productsInfo.append("Nom : ").append(produit.getNomProduit())
                                        .append("\nDescription : ").append(produit.getDescriptionProduit())
                                        .append("\nPrix : ").append(produit.getPrixProduit()).append(" €")
                                        .append("\nQuantité disponible : ").append(produit.getQuantiteDisponible()).append("\n\n");
                            }
                            JOptionPane.showMessageDialog(null, productsInfo.toString(), "Produits de la catégorie", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            Produit produit = frontService.getProduit(searchText);
                            if (produit != null) {
                                String formatted = "🛒 Produit :\n\n" +
                                        "Nom : " + produit.getNomProduit() + "\n" +
                                        "Description : " + produit.getDescriptionProduit() + "\n" +
                                        "Prix : " + produit.getPrixProduit() + " €\n" +
                                        "Quantité disponible : " + produit.getQuantiteDisponible();
                                JOptionPane.showMessageDialog(null, formatted, "Détail Produit", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Produit introuvable.", "Erreur", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Erreur lors de la recherche", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez entrer un nom de produit ou un nom de catégorie.", "Champ vide", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().trim();
                if (searchText.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Veuillez entrer un nom de produit.",
                            "Champ vide",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                try {
                    Produit produit = frontService.getProduit(searchText);
                    if (produit == null) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Produit introuvable. Veuillez d'abord rechercher un produit valide.",
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                    String quantiteStr = JOptionPane.showInputDialog(
                            null,
                            "Quantité à ajouter au panier (1–" + produit.getQuantiteDisponible() + ") :",
                            "Quantité",
                            JOptionPane.PLAIN_MESSAGE
                    );
                    if (quantiteStr == null) {
                        return;
                    }
                    int quantite;
                    try {
                        quantite = Integer.parseInt(quantiteStr);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Quantité invalide.",
                                "Erreur",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                    if (quantite < 1 || quantite > produit.getQuantiteDisponible()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "La quantité doit être comprise entre 1 et " + produit.getQuantiteDisponible() + ".",
                                "Quantité hors stock",
                                JOptionPane.ERROR_MESSAGE
                        );
                        return;
                    }
                    String result = frontService.acheterProduit(searchText, quantite);
                    JOptionPane.showMessageDialog(
                            null,
                            result,
                            "Ajout au panier",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(
                            null,
                            "Erreur lors de l'ajout au panier : " + ex.getMessage(),
                            "Erreur",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        panierButton.addActionListener(e -> {
            try {
                int idCommande = frontService.getCommandeEnCours();
                if (idCommande < 0) {
                    JOptionPane.showMessageDialog(null, "Aucune commande en cours.", "Panier vide", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    new Panier(frontService, idCommande);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors de la récupération du panier : " + ex.getMessage(),
                        "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        factureButton.addActionListener(e -> {
            try {
                String factureFilePath = frontService.getDerniereFacture();

                if (factureFilePath != null) {
                    File factureFile = new File(factureFilePath);

                    if (factureFile.exists()) {
                        Desktop.getDesktop().open(factureFile);
                    } else {
                        JOptionPane.showMessageDialog(null, "La facture pour cette commande est introuvable.", "Erreur", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Aucune facture disponible pour cette commande.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Erreur lors de l'affichage de la facture : " + ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Accueil();
    }
}
