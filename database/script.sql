use db_snake;

DROP TABLE IF EXISTS `Joueur_Partie`;
DROP TABLE IF EXISTS `Partie`;
DROP TABLE IF EXISTS `Joueur`;

CREATE TABLE Joueur(
	id_joueur int NOT NULL AUTO_INCREMENT,
	email VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	pseudo VARCHAR(50) NOT NULL,
	skin VARCHAR(100) NULL,
	PRIMARY  KEY(id_joueur)
);

LOCK TABLES `Joueur` WRITE;
/*!40000 ALTER TABLE `Joueur` DISABLE KEYS */;
INSERT INTO `Joueur` (email, password, pseudo) VALUES ('darkSasukedu49@naroute.fr','12345678','darkSasukéDu49'),('test@test.fr','testtest','test');
/*!40000 ALTER TABLE `Joueur` ENABLE KEYS */;
UNLOCK TABLES;

CREATE TABLE Partie(
	id_partie int NOT NULL AUTO_INCREMENT,
	map_name VARCHAR(50) NOT NULL,
	temps_depart TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
	id_joueur_gagnant int,
	nb_tour int,
	nb_tour_max int,
	speed int,
	status VARCHAR(50),
	PRIMARY  KEY(id_partie),
	FOREIGN KEY(id_joueur_gagnant) REFERENCES Joueur(id_joueur)
);

CREATE TABLE Joueur_Partie(
	id_partie int NOT NULL,
	id_joueur int NOT NULL,
	score int,
	PRIMARY  KEY(id_partie, id_joueur),
	FOREIGN KEY(id_joueur) REFERENCES Joueur(id_joueur),
	FOREIGN KEY(id_partie) REFERENCES Partie(id_partie)
);


/*INSERT INTO `Partie` (map_name, id_joueur_gagnant, nb_tour, nb_tour_max) VALUES ('Maptest', 27, 54, 1000);

INSERT INTO `Joueur_Partie` (id_partie, id_joueur, score) VALUES (1, 27, 25);

INSERT INTO `Partie` (map_name, nb_tour, nb_tour_max) VALUES ('Maptest', 104, 1000);

INSERT INTO `Joueur_Partie` (id_partie, id_joueur, score) VALUES (2, 27, 58);

INSERT INTO `Partie` (map_name, nb_tour, nb_tour_max) VALUES ('Maptest', 65, 1000);

INSERT INTO `Joueur_Partie` (id_partie, id_joueur, score) VALUES (3, 20, 10);

INSERT INTO `Partie` (map_name, nb_tour, nb_tour_max) VALUES ('alone', 65, 1000);

INSERT INTO `Joueur_Partie` (id_partie, id_joueur, score) VALUES (4, 20, 15);*/
