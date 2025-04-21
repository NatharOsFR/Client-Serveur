package FrontEnd.src.UI;

import BackEnd.src.Models.DetailCommande;
import BackEnd.src.Models.Produit;
import FrontEnd.src.Services.FrontService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Panier extends JFrame {

    public Panier(FrontService frontService, int idCommande) {
        setTitle("Mon Panier");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 245));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Mon Panier", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 128, 0));
        panel.add(titleLabel, BorderLayout.NORTH);

        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));
        productPanel.setBackground(new Color(245, 245, 245));

        try {
            List<DetailCommande> details = frontService.getDetailsParCommande(idCommande);
            if (details == null || details.isEmpty()) {
                JLabel emptyLabel = new JLabel("Votre panier est vide.");
                emptyLabel.setFont(new Font("Arial", Font.ITALIC, 16));
                emptyLabel.setForeground(Color.GRAY);
                productPanel.add(emptyLabel);
            } else {
                int total = 0;
                for (DetailCommande dc : details) {
                    Produit p = frontService.getProduitParId(dc.getIdProduit());
                    int sousTotal = dc.getQuantiteProduit() * p.getPrixProduit();
                    String productLine = "<html>" + p.getNomProduit() + " × " + dc.getQuantiteProduit() + " = " + sousTotal + " €</html>";
                    JLabel productLabel = new JLabel(productLine);
                    productLabel.setFont(new Font("Arial", Font.PLAIN, 16));
                    productLabel.setForeground(new Color(0, 0, 0));
                    productPanel.add(productLabel);
                    total += sousTotal;
                }
                JLabel totalLabel = new JLabel("<html><strong>Total : " + total + " €</strong></html>");
                totalLabel.setFont(new Font("Arial", Font.BOLD, 18));
                totalLabel.setForeground(new Color(34, 139, 34));
                productPanel.add(Box.createVerticalStrut(10));
                productPanel.add(totalLabel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JLabel errorLabel = new JLabel("Erreur lors du chargement du panier : " + e.getMessage());
            errorLabel.setFont(new Font("Arial", Font.PLAIN, 14));
            errorLabel.setForeground(Color.RED);
            productPanel.add(errorLabel);
        }

        JScrollPane scrollPane = new JScrollPane(productPanel);
        scrollPane.setPreferredSize(new Dimension(550, 400));
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(new Color(245, 245, 245));

        JButton payButton = new JButton("Payer");
        payButton.setFont(new Font("Arial", Font.BOLD, 18));
        payButton.setBackground(new Color(34, 139, 34));
        payButton.setForeground(Color.WHITE);
        payButton.setPreferredSize(new Dimension(150, 45));
        payButton.setFocusPainted(false);
        payButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        payButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                payButton.setBackground(new Color(28, 128, 28));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                payButton.setBackground(new Color(34, 139, 34));
            }
        });

        payButton.addActionListener(e -> {
            String[] modes = {"Carte", "Espece", "Virement"};
            String modePaiement = (String) JOptionPane.showInputDialog(
                    this,
                    "Sélectionnez le mode de paiement :",
                    "Mode de paiement",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    modes,
                    modes[0]
            );

            if (modePaiement != null) {
                boolean paiementOK = frontService.payerCommande(idCommande, modePaiement);

                if (paiementOK) {
                    JOptionPane.showMessageDialog(this, "Commande payée avec succès !");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Échec du paiement. Veuillez réessayer.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton quitButton = new JButton("Quitter");
        quitButton.setFont(new Font("Arial", Font.BOLD, 18));
        quitButton.setBackground(new Color(220, 20, 60));
        quitButton.setForeground(Color.WHITE);
        quitButton.setPreferredSize(new Dimension(150, 45));
        quitButton.setFocusPainted(false);
        quitButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        quitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                quitButton.setBackground(new Color(178, 0, 40));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                quitButton.setBackground(new Color(220, 20, 60));
            }
        });

        quitButton.addActionListener(e -> dispose());

        buttonPanel.add(payButton);
        buttonPanel.add(quitButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }
}
