ALTER TABLE `ShinShin`.`ticket` 
ADD COLUMN `ticket_cp_tienda` VARCHAR(5) NULL AFTER `ticket_code_bar`,
ADD COLUMN `ticket_cp_fiscal` VARCHAR(5) NULL AFTER `ticket_cp_tienda`;

INSERT INTO 
	catalogo_diccionario_tiendas (clave_diccionario,valor_diccionario)
VALUES
('lnCoNNer','LaComer'),
('nConer','LaComer'),
('Hn Comer','LaComer'),
('laComer','LaComer'),
('lacomer','LaComer');
