DROP SCHEMA IF EXISTS emploi_du_temps;

CREATE SCHEMA emploi_du_temps;



CREATE TABLE IF NOT EXISTS jour
(
    jour_id NUMERIC  NOT NULL,
    nom   VARCHAR(100) NOT NULL,
    PRIMARY KEY (jour_id)
);
   

INSERT INTO jour(jour_id , nom)
VALUES  (1, 'Lundi'),
        (2, 'Mardi'),
        (3, 'Mercredi'),
        (4, 'Jeudi'),
        (5, 'Vendredi');

CREATE TABLE IF NOT EXISTS  horaire
(
    horaire_id  NUMERIC NOT NULL,
    intervalle   VARCHAR(50) NOT NULL,
    PRIMARY KEY ( horaire_id )
);
    
INSERT INTO horaire(horaire_id, intervalle)
VALUES  (1, '8:30'),
        (2, '9:00'),
        (3, '9:30'),
        (4, '10:00'),
        (5, '10:30'),
        (6, '11:00'),
        (7, '11:30'),
        (8, '12:00'),
        (9, '12:30'),
        (10,'13:00'),
        (11,'13:30'),
        (12,'14:00'),
        (13,'14:30'),
        (14,'15:00'),
        (15,'15:30'),
        (16,'16:00'),
        (17,'16:30'),
        (18,'17:00'),
        (19,'17:30'),
        (20,'18:00'),
        (21,'18:30');
        
CREATE TABLE IF NOT EXISTS  jour_horaire
(
    jour_horaire_id SERIAL NOT NULL ,
    matiere VARCHAR(100),
    jour_id  NUMERIC NOT NULL,
    horaire_id  NUMERIC  NOT NULL,
    PRIMARY KEY ( jour_horaire_id  )
);    

INSERT INTO jour_horaire(jour_id, horaire_id)
VALUES  (1, 1),
		(1, 2),
		(1, 3),
		(1, 4),
		(1, 5),
		(1, 6),
		(1, 7),
		(1, 8),
		(1, 9),
		(1, 10),
		(1, 11),
		(1, 12),
		(1, 13),
		(1, 14),
		(1, 15),
		(1, 16),
		(1, 17),
		(1, 18),
		(1, 19),
		(1, 20),
		(1, 21),
		(2, 1),
		(2, 2),
		(2, 3),
		(2, 4),
		(2, 5),
		(2, 6),
		(2, 7),
		(2, 8),
		(2, 9),
		(2, 10),
		(2, 11),
		(2, 12),
		(2, 13),
		(2, 14),
		(2, 15),
		(2, 16),
		(2, 17),
		(2, 18),
		(2, 19),
		(2, 20),
		(2, 21),
		(3, 1),
		(3, 2),
		(3, 3),
		(3, 4),
		(3, 5),
		(3, 6),
		(3, 7),
		(3, 8),
		(3, 9),
		(3, 10),
		(3, 11),
		(3, 12),
		(3, 13),
		(3, 14),
		(3, 15),
		(3, 16),
		(3, 17),
		(3, 18),
		(3, 19),
		(3, 20),
		(3, 21),
		(4, 1),
		(4, 2),
		(4, 3),
		(4, 4),
		(4, 5),
		(4, 6),
		(4, 7),
		(4, 8),
		(4, 9),
		(4, 10),
		(4, 11),
		(4, 12),
		(4, 13),
		(4, 14),
		(4, 15),
		(4, 16),
		(4, 17),
		(4, 18),
		(4, 19),
		(4, 20),
		(4, 21),
		(5, 1),
		(5, 2),
		(5, 3),
		(5, 4),
		(5, 5),
		(5, 6),
		(5, 7),
		(5, 8),
		(5, 9),
		(5, 10),
		(5, 11),
		(5, 12),
		(5, 13),
		(5, 14),
		(5, 15),
		(5, 16),
		(5, 17),
		(5, 18),
		(5, 19),
		(5, 20),
		(5, 21);
		

ALTER TABLE jour_horaire
    ADD CONSTRAINT jour_horaire_jour_fk
        FOREIGN KEY (jour_id)
            REFERENCES jour (jour_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
     
      ADD CONSTRAINT  jour_horaire_horaire_fk
        FOREIGN KEY (horaire_id)
            REFERENCES horaire (horaire_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
commit;


