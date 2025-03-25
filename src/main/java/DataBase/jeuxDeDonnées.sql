INSERT INTO Categorie (nom_categorie, description_categorie) VALUES
('Football', 'Sélection de produits dédiés au football, conçus pour améliorer vos performances sur le terrain et exprimer votre passion pour ce sport captivant.'),
('Tennis', 'Produits dédiés au tennis, conçus pour améliorer vos performances sur le court'),
('Natation','Nos produits vous offre une expérience aquatique inoubliable'),
('Randonnée', 'Conçus pour vous accompagner dans vos escapades en pleine nature'),
('Camping', 'Que vous soyez un campeur occasionnel ou un aventurier aguerri, découvrez tout ce dont vous avez besoin');

INSERT INTO Produit (id_categorie, nom_produit, description_produit, prix_produit, Produit.quantite_disponible) VALUES
(1,'Ballon Adidas Brazuca 2014', 'ballon officiel de la Coupe du Monde de la FIFA 2014', 50, 5),
(1, 'Crampons Nike Total 90', 'Magnifique crampons porté par Ronaldinho', 90, 150),
(2, 'Raquette Artengo Novice', 'Legere, parfaite pour commencer le tennis', 15, 500),
(2, 'Bandeau transpiration', 'Idéal pour les longues sessions sur courts', 5, 300),
(3, 'Maillot de bain Arena', 'Maillot de bain 1re Prix', 3, 420),
(3, 'Lunette natation', 'Pour voir sous leau', 4, 250),
(4, 'Tente 20sec', 'Prete en 20 secondes !', 75, 200),
(4, 'Barre energetique', 'Pour tenir les longues randonnées', 1,650),
(5, 'Table Pliante', 'Parfaite pour les pique-niques',85, 15),
(5, 'Matelas Gonflable', 'Parfait pour dormir', 20, 100);
