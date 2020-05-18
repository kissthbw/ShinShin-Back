ALTER TABLE ticket
ADD COLUMN ticket_code_bar VARCHAR(45) NULL AFTER ticket_hora;

INSERT INTO 
	catalogo_diccionario_tiendas (clave_diccionario,valor_diccionario)
VALUES
('CHEDRAUI','CHEDRAUI'),
('CHEDRAUI','CHEDRAUI'),
('chedraui','CHEDRAUI'),
('CHE DRAUI','CHEDRAUI'),
('che draui','CHEDRAUI'),
('H-E-B','H-E-B'),
('h-e-b','H-E-B');