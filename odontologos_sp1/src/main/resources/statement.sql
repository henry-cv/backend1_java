DROP TABLE IF EXISTS ODONTOLOGOS; CREATE TABLE ODONTOLOGOS(ID LONG AUTO_INCREMENT PRIMARY KEY, MATRICULA VARCHAR(60) NOT NULL, NOMBRE VARCHAR(60) NOT NULL, APELLIDO VARCHAR(100) NOT NULL);
--Insertando Odontologos para Tests --
INSERT INTO ODONTOLOGOS(MATRICULA, NOMBRE, APELLIDO) VALUES ('odont-001', 'Nick', 'Riviera'),('odont-002','Gregory',
'House'),('odont-003','Julius','Hibbert'),('odont-004','John','Zoidberg'),('odont-005','Fulano','Mengueche'),
('odont-006','Aquiles','Mata');
