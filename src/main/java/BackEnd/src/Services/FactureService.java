package BackEnd.src.Services;

/* import BackEnd.src.Models.DetailCommande;

import javax.swing.text.Document;

public class FactureService {
    public static void genererFacturePDF(int idCommande, List<DetailCommande> details, String modePaiement) {
        try {
            String fileName = "factures/facture_commande_" + idCommande + ".pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));
            document.open();

            document.add(new Paragraph("Facture - Commande N° " + idCommande));
            document.add(new Paragraph("Date : " + LocalDate.now()));
            document.add(new Paragraph("Mode de paiement : " + modePaiement));
            document.add(new Paragraph(" ")); // espace

            int total = 0;
            for (DetailCommande dc : details) {
                Produit p = // récupère produit depuis ton service ou le passe en paramètre
                int sousTotal = dc.getQuantiteProduit() * p.getPrixProduit();
                document.add(new Paragraph(p.getNomProduit() + " x" + dc.getQuantiteProduit() + " = " + sousTotal + " €"));
                total += sousTotal;
            }

            document.add(new Paragraph(" "));
            document.add(new Paragraph("TOTAL : " + total + " €"));
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} */
