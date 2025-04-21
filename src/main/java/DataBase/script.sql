
CREATE DATABASE IF NOT EXISTS DB_Heptathlon;

USE DB_Heptathlon;

--  Table Catégorie
CREATE TABLE Categorie (
    id_categorie INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nom_categorie NVARCHAR(250) NOT NULL,
    description_categorie NVARCHAR(250)
);

-- Table Produit
CREATE TABLE Produit (
    id_produit INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_categorie INT NOT NULL,
    nom_produit NVARCHAR(250) NOT NULL,
    description_produit NVARCHAR(250),
    prix_produit INT NOT NULL,
    quantite_disponible INT NOT NULL,
    FOREIGN KEY (id_categorie) REFERENCES Categorie(id_categorie)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- Table Commande
CREATE TABLE Commande (
    id_commande INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    date_commande DATE NOT NULL,
    status_commande ENUM('En cours', 'Paye', 'Annule') NOT NULL,
    total_commande INT NOT NULL,
    mode_de_paiement ENUM('Carte', 'Espece', 'Virement') NOT NULL
);

-- Table DétailCommande
CREATE TABLE DetailCommande (
    id_detail INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    id_commande INT NOT NULL,
    id_produit INT NOT NULL,
    quantite_produit INT NOT NULL CHECK (quantite_produit > 0),
    FOREIGN KEY (id_commande) REFERENCES Commande(id_commande)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    FOREIGN KEY (id_produit) REFERENCES Produit(id_produit)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
