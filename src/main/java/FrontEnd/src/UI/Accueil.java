package FrontEnd.src.UI;

import FrontEnd.src.Services.ProduitService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Accueil extends JFrame {
    private JTextField searchField;
    private JButton searchButton;
    private JButton buyButton;
    private JButton categoriesButton;
    private JButton panierButton;
    private JButton factureButton;
    private ProduitService produitService;

    public Accueil() {
        produitService = new ProduitService();

        setTitle("Accueil");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6, 1, 10, 10));

        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Rechercher Produit");
        buyButton = new JButton("Acheter Produit");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(buyButton);

        categoriesButton = new JButton("Voir Catégories");
        panierButton = new JButton("Voir Mon Panier");
        factureButton = new JButton("Consulter Ma Facture");

        add(new JLabel("Bienvenue sur l'application de commande", SwingConstants.CENTER));
        add(searchPanel);
        add(categoriesButton);
        add(panierButton);
        add(factureButton);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                if (!searchText.isEmpty()) {
                    String result = produitService.getProduit(searchText);
                    JOptionPane.showMessageDialog(null, result);
                }
            }
        });

        buyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                if (!searchText.isEmpty()) {
                    String result = produitService.acheterProduit(searchText, 1);
                    JOptionPane.showMessageDialog(null, result);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new Accueil();
    }
}
