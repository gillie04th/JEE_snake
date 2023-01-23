CREATE TABLE Joueur(
	id_joueur int NOT NULL,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	pseudo VARCHAR(50) NOT NULL,
	PRIMARY  KEY(id_joueur)
);

CREATE TABLE Partie(
	id_partie int NOT NULL,
	map_name VARCHAR(50) NOT NULL,
	temps_depart TIMESTAMP NOT NULL,
	id_joueur_gagnant int,
	nb_tour int,
	PRIMARY  KEY(id_partie),
	FOREIGN KEY(id_joueur_gagnant) REFERENCES Joueur(id_joueur)
);