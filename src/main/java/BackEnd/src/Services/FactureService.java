package BackEnd.src.Services;

import BackEnd.src.DAO.ProduitDAO;
import BackEnd.src.Models.DetailCommande;
import BackEnd.src.Models.Produit;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class FactureService {

    private ProduitDAO produitDAO;

    public FactureService(ProduitDAO produitDAO) {
        this.produitDAO = produitDAO;
    }

    public void genererFacturePDF(int idCommande, List<DetailCommande> details, String modePaiement) {
        try {
            File dossier = new File("factures");
            if (!dossier.exists()) {
                dossier.mkdirs();
            }

            String fileName = "factures/facture_commande_" + idCommande + ".pdf";
            PdfWriter writer = new PdfWriter(new FileOutputStream(fileName));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            document.add(new Paragraph("Facture - Commande N° " + idCommande));
            document.add(new Paragraph("Date : " + LocalDate.now().toString()));
            document.add(new Paragraph("Mode de paiement : " + modePaiement));
            document.add(new Paragraph(" "));

            int total = 0;

            for (DetailCommande dc : details) {
                Produit p = produitDAO.getProduitParId(dc.getIdProduit());

                int sousTotal = dc.getQuantiteProduit() * p.getPrixProduit();
                document.add(new Paragraph(
                        p.getNomProduit() + " x" + dc.getQuantiteProduit() + " = " + sousTotal + " €"
                ));
                total += sousTotal;
            }

            document.add(new Paragraph(" "));
            document.add(new Paragraph("TOTAL : " + total + " €"));

            document.close();
            System.out.println("Facture générée : " + fileName);

        } catch (IOException | SQLException e) {
            System.out.println("Erreur lors de la génération de la facture PDF.");
            e.printStackTrace();
        }
    }
}
