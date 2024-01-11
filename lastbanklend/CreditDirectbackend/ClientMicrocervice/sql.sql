

INSERT INTO typefinancement (nom_financement) VALUES
                                                  ('Credit a la consomation'),
                                                  ('Credit immobilier'),
                                                  ('Prêt sur gage'),
                                                  ('Ijara Tamlikia'),
                                                  ('Morabaha véhicule'),
                                                  ('Morabaha moto'),
                                                  ('Morabaha Istihlakiya');

INSERT INTO typecredit (nom_credit, id_financement) VALUES
                                                        ('Produit électronique et électroménager', 1),
                                                        ('Meubles et accessoires de maison', 1),
                                                        ('Véhicule à usage touristique', 1);


INSERT INTO typecredit (nom_credit, id_financement) VALUES
                                                        ('Achat d''un logement promotionnel achevé ou vendu sur plans', 2),
                                                        ('Construction d''un logement rural', 2),
                                                        ('Achat d''un logement LPP (Bonifié)', 2),
                                                        ('Achat d''un logement auprès d''un particulier', 2),
                                                        ('Achat d''un logement auprès d''un promoteur', 2),
                                                        ('Achat d''un logement formule "Vente Sur Plan"', 2),
                                                        ('Acquisition d''un local commercial en "VSP"', 2),
                                                        ('Coopérative immobilière', 2),
                                                        ('Aménagement', 2),
                                                        ('Construction d''une habitation individuelle', 2),
                                                        ('Construction zone rurale', 2),
                                                        ('Extension d''une habitation individuelle', 2);




-- Inserting the first three wilayas into the Wilaya table
INSERT INTO Wilaya (wilaya_name) VALUES ('Tizi Ouzou');
INSERT INTO Wilaya (wilaya_name) VALUES ('Alger');
INSERT INTO Wilaya (wilaya_name) VALUES ('Boumerdes');

-- Inserting communes into commune tizi ouzou
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Tizi Ouzou', '15000', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Draa El Mizan', '15005', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Tizi Gheniff', '15020', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Freha', '15012', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Azazga', '15001', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Aïn El Hammam', '15002', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Larbaâ Nath Irathen', '15006', 1);

INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Azeffoun', '15010', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Draâ Ben Khedda', '15004', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Tadmait', '15018', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Ouaguenoun', '15047', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Tigzirt', '15019', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Mekla', '15014', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('Boghni','15003', 1);
INSERT INTO Commune (nom, code_postal, wilaya_id) VALUES ('AGHRIB','15021', 1);

INSERT INTO direction_regionale (nom, created_at, adresse) VALUES ('Direction Tizi Ouzou', CURRENT_TIMESTAMP, 'Axe du Nouveau Lycée, Boulevard Stitti,Tizi Ouzou');
INSERT INTO direction_regionale (nom, created_at, adresse) VALUES ('Direction Alger', CURRENT_TIMESTAMP, 'Address for Alger direction');

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('138', 'AGENCE BOGHNI', 'Cité 18 Logements Boghni', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('144', 'AGENCE TIGZIRT', 'CITE 192 LOGEMENTS BATIMENT B1 15019 TIGZIRT TIZI-OUZOU', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('145', 'AGENCE FREHA', 'CITE 18 LOGEMENTS 15012 FREHA TIZI-OUZOU', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('147', 'Pôle Commercial DJURDJURA', 'Axe Nouveux Lycée, Tizi Ouzou', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('151', 'Pôle Commercial AZAZGA', 'RUE BELKACEM HANAFI 15001 AZAZGA TIZI-OUZOU', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('160', 'AGENCE NOUVELLE VILLE', 'Cité 145 Logements, EPLF Nouvelle Ville Tizi Ouzou', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('161', 'AGENCE AIN EL HAMMAM', 'CITE 18 LOGEMENTS 15012 FREHA TIZI-OUZOU', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('163', 'AGENCE DRAA BEN-KHEDDA', 'Cité 22 Logements Fathi, Draa Ben khedda, Tizi Ouzou', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('171', 'AGENCE SI ABDELLAH', 'Rue Capitaine Si Abdellah, Tizi Ouzou', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO Agence (num_agence, nom, adresse, direction_regionale_id, created_at, updated_at)
VALUES ('183', 'AGENCE DRAA EL MIZAN', '16 Logemlent LSP, Draa El Mizan, Tizi Ouzou', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);




-- Assuming agency 1 corresponds to communes 1, 2, 3, and agency 2 corresponds to communes 4, 5, 6
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (1, 14);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (2, 12);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (3, 4);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (4, 1);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (5, 5);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (6, 1);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (10, 2);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (10, 3);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (3, 15);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (5, 13);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (8, 9);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (8, 10);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (7, 6);
INSERT INTO Agence_Commune (agence_id, commune_id) VALUES (9, 1);


INSERT INTO `comptes` (`id`, `agence_id`, `created_at`, `nin`, `password`, `role`) VALUES
                                                                                       (1, 1, '2024-01-11 18:58:15.000000', 'c100', '$2a$10$4MTTtd5XkcBh.cEu2flwCeFjKjP2CUwxOgBPa.D0zon.7CrbQT50a', 'courtier'),
                                                                                       (2, 2, '2024-01-11 19:28:46.000000', 'c200', '$2a$10$tUjT1LiP/9i/veBwfuvYcezcDbHAzfbdXiBrphmBKsnSbtPfaPnxy', 'courtier'),
                                                                                       (3, 3, '2024-01-11 19:28:58.000000', 'c300', '$2a$10$nfFiqafUWwRNlgANOb0NGebtuSkOVuyhUYyV9VqhPh78RSEoUcsUG', 'courtier'),
                                                                                       (4, 4, '2024-01-11 19:29:19.000000', 'c400', '$2a$10$vTTfAu8Qu4ggyT3xADp7Duiqq2UPq6YqZuL3HQ96956XPGRwlwd9i', 'courtier'),
                                                                                       (5, 5, '2024-01-11 19:29:33.000000', 'c500', '$2a$10$Se8.3jzAtb2t68kLrjrs2Onm4m8yjcZgiNQxnpernjH3MesSMsz7W', 'courtier'),
                                                                                       (6, 6, '2024-01-11 19:29:47.000000', 'c600', '$2a$10$rRdV3J5bMenTFqtdvdJmNeL.KfQ.kTO7Tt03/56lNEQ1fiuVafmm.', 'courtier'),
                                                                                       (7, 7, '2024-01-11 19:29:58.000000', 'c700', '$2a$10$NMNE/gAFhWDDq1bqmE1xQ.7H1cDIQefF7MGuUpDIPt27/xs1i.zq6', 'courtier'),
                                                                                       (8, 8, '2024-01-11 19:30:21.000000', 'c800', '$2a$10$luz/yRI49QWIkouUV.5Viu5sCN/74iV5wRgLaNVcmP.xGjHs/5bVm', 'courtier'),
                                                                                       (9, 9, '2024-01-11 19:31:05.000000', 'c900', '$2a$10$TzdBxWcE2qmmbaNiPR1n6e5cYwxmNfeMeQh7Yhwt./FMJhiFYQbg6', 'courtier'),
                                                                                       (10, 10, '2024-01-11 19:31:26.000000', 'c1000', '$2a$10$e.a7czp3tNnGNeDy6L09hOG0GwW5dguDOhFuI.ctR3xRucOfJ66OG', 'courtier'),
                                                                                       (11, 1, '2024-01-11 19:35:17.000000', 'd001', '$2a$10$W5aRu3oPXcZJIKCIq.9EFOX0FhjFvgXp50QAkB5H6VGxqkO5zBKJ6', 'directeur'),
                                                                                       (12, 2, '2024-01-11 19:35:29.000000', 'd002', '$2a$10$hLMwhpOAfcvNRflrb/O9yOCdhRjDtwk6MYFMCM9/RCaauIq/Bbu3.', 'directeur'),
                                                                                       (13, 3, '2024-01-11 19:35:42.000000', 'd003', '$2a$10$hJeBaRFlWg.lRg7O7LOZIegisCjoN0J7GKo55vLzqShj9p.2nqSYy', 'directeur'),
                                                                                       (14, 4, '2024-01-11 19:35:53.000000', 'd004', '$2a$10$3uXLSO0oWzx/hlyqjqogo.YF7TJgVg1W5Y/JOzfmF8HxcRXJDc2q.', 'directeur'),
                                                                                       (15, 5, '2024-01-11 19:36:11.000000', 'd005', '$2a$10$zkCTrQ/QqENTRZbFPw5.M.AXIHgbLrnXYUubMVqdQYSmSEQoYG6Bu', 'directeur'),
                                                                                       (16, 6, '2024-01-11 19:36:31.000000', 'd006', '$2a$10$RjwLyuymL2J5kDS0Hc/3d.iDv.pHX4TXOkcQK2Zr.oZIWfSErlgY.', 'directeur'),
                                                                                       (17, 7, '2024-01-11 19:36:43.000000', 'd007', '$2a$10$2/CW80Ti04z21X.EQnIETONm5gDMrVnm8cBgqPjbX2S939DVxx542', 'directeur'),
                                                                                       (18, 8, '2024-01-11 19:36:59.000000', 'd008', '$2a$10$mCVfU0sQZgCIqD2UMQhW7eTqD46BluHFCPl2r77ar1gNs3A8rR.Ly', 'directeur'),
                                                                                       (19, 9, '2024-01-11 19:37:16.000000', 'd009', '$2a$10$FpFFDxWY2XddmQ7UegLpruZ6i3aT.228i9l305/Zd0JfpTWNs2r1e', 'directeur'),
                                                                                       (20, 10, '2024-01-11 19:37:32.000000', 'd1000', '$2a$10$fuXRaz4x.l/5f98XvSY8.ePyVrQzr2Y8gIzthPyIJLUIZBdq08NoC', 'directeur');