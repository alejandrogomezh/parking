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