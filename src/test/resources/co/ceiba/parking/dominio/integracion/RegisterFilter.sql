DROP TABLE IF EXISTS invoice;
DROP TABLE IF EXISTS register;
DROP TABLE IF EXISTS vehicle;

CREATE TABLE vehicle (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  cilindraje int(11) NOT NULL,
  placa varchar(255) DEFAULT NULL,
  tipo varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE register (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  ingreso datetime DEFAULT NULL,
  salida datetime DEFAULT NULL,
  vehicle bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  -- KEY FK7ify17el7v4r8mwava3dutrrn (vehicle),
  -- CONSTRAINT FK7ify17el7v4r8mwava3dutrrn FOREIGN KEY (vehicle) REFERENCES vehicle (id)
  foreign key (vehicle) references vehicle(id)
);

CREATE TABLE invoice (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  dias int(11) NOT NULL,
  horas int(11) NOT NULL,
  valor_dias double NOT NULL,
  valor_horas double NOT NULL,
  valor_recargo double NOT NULL,
  valor_total double NOT NULL,
  register bigint(20) DEFAULT NULL,
  PRIMARY KEY (id),
  -- KEY FK1v7tkmwu2mga24mlmbob02odk (register),
  -- CONSTRAINT FK1v7tkmwu2mga24mlmbob02odk FOREIGN KEY (register) REFERENCES register (id)
  foreign key (register) references register(id)
);

INSERT INTO vehicle VALUES (1,250,'NSK43B','moto'),(2,350,'NSC43B','moto'),(3,350,'NSC43D','moto'),(4,1800,'MCH134','carro'),(5,3234,'GSDFSF','moto'),(6,3234,'DSF','moto'),(7,3234,'DGSDF','moto'),(8,554,'FDGF','moto'),(9,554,'FDGFH','moto'),(10,554,'F45FH','moto');
INSERT INTO register VALUES (1,'2018-02-22 21:33:03',NULL,1),(2,'2018-02-22 21:33:18',NULL,2),(3,'2018-02-22 21:36:39',NULL,3),(4,'2018-02-22 21:41:19',NULL,4),(5,'2018-02-22 23:58:51',NULL,5),(6,'2018-02-22 23:58:57',NULL,6),(7,'2018-02-22 23:59:04',NULL,7),(8,'2018-02-22 23:59:13',NULL,8),(9,'2018-02-22 23:59:20',NULL,9),(10,'2018-02-22 23:59:26','2018-02-23 22:06:26',10);
INSERT INTO invoice VALUES (1,1,0,4000,0,2000,6000,10);