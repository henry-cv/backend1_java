DROP TABLE IF EXISTS aviones;
CREATE TABLE aviones(id LONG PRIMARY KEY AUTO_INCREMENT NOT NULL, marca varchar(60) NOT NULL, modelo varchar(60) NOT NULL, matricula varchar(60) NOT NULL, fechaEntrada varchar(30) NOT NULL);
-- PARA CREAR REGISTROS PREVIOS PARA EL TEST
INSERT INTO aviones("marca", "modelo", "matricula","fechaEntrada") values ("acme", "acme-001", "abc-001","2020/8/12");
INSERT INTO aviones("marca", "modelo", "matricula","fechaEntrada") values ("prica", "economico", "lba-001","2022/5/11");
