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
        searchButton = new JButton("Rechercher Produit");
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
        categoriesButton = new JButton("Voir Catégories");
        panierButton = new JButton("Voir Mon Panier");
        factureButton = new JButton("Consulter Ma Facture");

        categoriesButton.setBackground(new Color(70, 130, 180));
        panierButton.setBackground(new Color(70, 130, 180));
        factureButton.setBackground(new Color(100, 149, 237));
        categoriesButton.setForeground(Color.WHITE);
        panierButton.setForeground(Color.WHITE);
        factureButton.setForeground(Color.WHITE);
        categoriesButton.setFont(new Font("Arial", Font.BOLD, 14));
        panierButton.setFont(new Font("Arial", Font.BOLD, 14));
        factureButton.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(categoriesButton);
        buttonPanel.add(panierButton);
        buttonPanel.add(factureButton);
        add(buttonPanel);

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
                    String result = produitService.acheterProduit(searchText);
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
