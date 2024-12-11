create database colegio_josemaria;
use colegio_josemaria;

-- drop table usuarios;
create table usuarios(
id int primary key auto_increment,
rol varchar(20),
documento varchar(20),
numero varchar(12) unique,
nombre varchar(50),
apellido varchar(50),
direccion varchar(50),
telefono varchar(12),
genero varchar(20),
email varchar(50),
password varchar(255),
nacimiento date,
registro date,
actualizacion date,
estado boolean
);

-- drop table estudiantes;
create table estudiantes(
id int primary key auto_increment,
id_apoderado int,
documento varchar(20),
numero varchar(12) unique,
nombre varchar(50),
apellido varchar(50),
direccion varchar(100),
telefono varchar(12),
genero varchar(20),
email varchar(50),
nacimiento date,
registro date,
actualizacion date,
estado boolean
);

-- drop table apoderados;
create table apoderados(
id int primary key auto_increment,
documento varchar(20),
numero varchar(12) unique,
nombre varchar(50),
apellido varchar(50),
direccion varchar(100),
telefono varchar(12),
relacion varchar(20),
email varchar(50),
password varchar(255),
nacimiento date,
registro date,
actualizacion date,
estado boolean
);

-- drop table conceptos;
create table conceptos(
id int primary key auto_increment,
nombre varchar(20) unique,
registro date,
actualizacion date,
estado boolean
);

-- drop table conceptos_detalle;
create table conceptos_detalle(
id int primary key auto_increment,
id_concepto int,
nombre varchar(20) unique,
registro date,
actualizacion date,
estado boolean
);

-- drop table matriculas;
create table matriculas(
id int primary key auto_increment,
id_estudiante int,
periodo varchar(10),
nivel varchar(10),
grado varchar(10),
seccion varchar(10),
situacion varchar(10),
descripcion varchar(50),
registro date,
actualizacion date,
estado varchar(20)
);

-- drop table matricula_detalle;
create table matricula_detalle(
id int primary key auto_increment,
id_matricula int,
descripcion varchar(50),
monto decimal(6,2),
estado varchar(20)
);

-- drop table pagos;
create table pagos (
id int primary key auto_increment,
id_matricula int,
monto double(6,2),
metodo_pago varchar(20),
numero_op varchar(50),
url_voucher varchar(50),
registro date,
actualizacion date,
estado varchar(20)
);

-- drop table pagos_detalle;
create table pagos_detalle(
id int primary key auto_increment,
id_pago int,
id_matricula_detalle int,
concepto varchar(50),
monto double(6,2),
estado varchar(20)
);

-- drop trigger insert_pagos_detalle;
delimiter $$
create trigger insert_pagos_detalle
after insert on pagos_detalle
for each row
begin
	update matricula_detalle
	set estado = 'PAGADO'
	where id = new.id_matricula_detalle;
end;
$$
delimiter ;

-- drop trigger insert_matriculas;
delimiter $$
create trigger insert_matriculas
after insert on matriculas
for each row
begin
	insert into matricula_detalle(id_matricula,descripcion,monto,estado) values
    (new.id,concat('MATRICULA ',new.periodo),100.00,'PENDIENTE'),
    (new.id,concat('PENSION MARZO ',new.periodo),250.00,'PENDIENTE'),
    (new.id,concat('PENSION ABRIL ',new.periodo),250.00,'PENDIENTE'),
    (new.id,concat('PENSION MAYO ',new.periodo),250.00,'PENDIENTE'),
    (new.id,concat('PENSION JUNIO ',new.periodo),250.00,'PENDIENTE'),
    (new.id,concat('PENSION JULIO ',new.periodo),250.00,'PENDIENTE'),
    (new.id,concat('PENSION AGOSTO ',new.periodo),250.00,'PENDIENTE'),
    (new.id,concat('PENSION SEPTIEMBRE ',new.periodo),250.00,'PENDIENTE'),
    (new.id,concat('PENSION OCTUBRE ',new.periodo),250.00,'PENDIENTE'),
    (new.id,concat('PENSION NOVIEMBRE ',new.periodo),250.00,'PENDIENTE'),
    (new.id,concat('PENSION DICIEMBRE ',new.periodo),250.00,'PENDIENTE');
end $$
delimiter ;

truncate table usuarios;
INSERT INTO usuarios (rol, documento, numero, nombre, apellido, direccion, telefono, email, password, genero, nacimiento, estado) VALUES
('ADMIN', 'DNI', '40123456', 'Juan', 'Perez', 'Av. Siempre Viva 123', '900123456', 'perez@example.com', '$2a$10$bFojXUEilw/VFpWKxxEEmu3URsc6BUA3.2GdG/jC68CwoCxETUQY.', 'M', '1992-05-14', true),
('CONTABILIDAD', 'CEX', '41234567', 'Maria', 'Lopez', 'Calle Falsa 456', '901234567', 'lopez@example.com', '456', 'F', '1993-07-21', true),
('TESORERIA', 'PAS', '42345678', 'Carlos', 'Garcia', 'Calle Real 789', '902345678', 'garcia@example.com', '789', 'M', '1991-09-10', true),
('FACTURACION', 'DNI', '43456789', 'Ana', 'Gomez', 'Av. del Sol 321', '903456789', 'gomez@example.com', '987', 'F', '1994-03-18', true),
('SECRETARIA', 'CEX', '44567890', 'Luis', 'Martinez', 'Calle Luna 654', '904567890', 'martinez@example.com', '654', 'M', '1990-12-25', true),
('ADMIN', 'PAS', '45678901', 'Elena', 'Rodriguez', 'Av. Marte 987', '905678901', 'rodriguez@example.com', '321', 'F', '2000-01-30', true),
('CONTABILIDAD', 'DNI', '46789012', 'Pedro', 'Hernandez', 'Calle Estrella 123', '906789012', 'hernandez@example.com', '432', 'M', '1998-11-11', true),
('TESORERIA', 'CEX', '47890123', 'Lucia', 'Sanchez', 'Av. Cometa 456', '907890123', 'sanchez@example.com', '543', 'F', '2001-02-12', true),
('FACTURACION', 'PAS', '48901234', 'Javier', 'Torres', 'Calle Astro 789', '908901234', 'torres@example.com', '654', 'M', '1996-06-06', true),
('SECRETARIA', 'DNI', '49012345', 'Laura', 'Ramirez', 'Av. Planeta 321', '909012345', 'ramirez@example.com', '765', 'F', '1995-08-24', true),
('ADMIN', 'CEX', '50123456', 'Miguel', 'Flores', 'Calle Universo 654', '900123457', 'flores@example.com', '876', 'M', '1997-04-02', true),
('CONTABILIDAD', 'PAS', '51234567', 'Carmen', 'Diaz', 'Av. Galaxia 987', '901234568', 'diaz@example.com', '987', 'F', '2003-10-19', true),
('TESORERIA', 'DNI', '52345678', 'Raul', 'Morales', 'Calle Via Lactea 123', '902345679', 'morales@example.com', '098', 'M', '2002-03-31', true),
('FACTURACION', 'CEX', '53456789', 'Sara', 'Ortega', 'Av. Nebulosa 456', '903456780', 'ortega@example.com', '210', 'F', '1994-12-12', true),
('SECRETARIA', 'PAS', '54567890', 'Diego', 'Vargas', 'Calle Estrella Fugaz 789', '904567891', 'vargas@example.com', '321', 'M', '2000-07-20', true),
('ADMIN', 'DNI', '55678901', 'Patricia', 'Jimenez', 'Av. Sol Naciente 321', '905678902', 'jimenez@example.com', '432', 'F', '2001-11-08', true),
('CONTABILIDAD', 'CEX', '56789012', 'Felipe', 'Suarez', 'Calle Sol Poniente 654', '906789013', 'suarez@example.com', '543', 'M', '1999-06-15', true),
('TESORERIA', 'PAS', '57890123', 'Marta', 'Ramos', 'Av. Aurora 987', '907890124', 'ramos@example.com', '654', 'F', '1998-09-01', true),
('FACTURACION', 'DNI', '58901234', 'Sergio', 'Ruiz', 'Calle Viento 123', '908901235', 'ruiz@example.com', '765', 'M', '2003-05-11', true),
('SECRETARIA', 'CEX', '59012345', 'Isabel', 'Silva', 'Av. Lluvia 456', '909012346', 'silva@example.com', '876', 'F', '2004-08-29', true);

INSERT INTO apoderados (documento, numero, nombre, apellido, direccion, telefono, relacion, email, password, nacimiento, estado) VALUES 
('DNI', '34567890', 'Juan Carlos', 'Pérez López', 'Av. Las Flores 1234', '912345678', 'PADRE', 'perez@gmail.com', '3456Lopez', '1985-03-15', TRUE),
('PASS', '0023456789', 'Ana María', 'González Ruiz', 'Calle del Sol 4567', '949876543', 'MADRE', 'gonzalez@hotmail.com', '0023Ruiz', '1990-07-22', TRUE),
('CEXT', '000045678910', 'Luis Fernando', 'Torres Álvarez', 'Plaza Mayor 1', '945678912', 'ABUELO', 'torres@gmail.com', '00004566', '1982-11-05', TRUE),
('DNI', '35678901', 'María Elena', 'Ramírez Vega', 'Paseo de la Reforma 234', '917654321', 'ABUELA', 'ramirez@hotmail.com', '3567Vega', '1995-06-30', TRUE),
('PASS', '0028765432', 'Carlos Alberto', 'Martínez Díaz', 'Calle Nueva 8', '936543210', 'TIO', 'martinez@gmail.com', '0028Díaz', '1993-02-19', TRUE),
('CEXT', '000056789012', 'Sofía Isabel', 'Hernández Jiménez', 'Av. Siempre Viva 101', '912345679', 'TIA', 'hernandez@hotmail.com', '00005678', '1998-12-01', TRUE),
('DNI', '36789012', 'Rafael Eduardo', 'Fernández Castillo', 'Calle Real 88', '930987654', 'PADRE', 'fernandez@gmail.com', '3678Castillo', '1988-09-11', TRUE),
('PASS', '0024567890', 'Lucía Andrea', 'López Martínez', 'Av. del Libertador 90', '945678923', 'MADRE', 'lopez@hotmail.com', '0024Martinez', '1992-04-22', TRUE),
('CEXT', '000067890123', 'José Miguel', 'Suárez Pérez', 'Calle del Agua 333', '943456789', 'ABUELO', 'suarez@gmail.com', '00006789', '1980-05-30', TRUE),
('DNI', '37890123', 'Claudia Patricia', 'Castro Ríos', 'Plaza Central 99', '935678912', 'ABUELA', 'castro@hotmail.com', '3789Ríos', '1986-10-10', TRUE),
('PASS', '0023456788', 'Andrés Felipe', 'García Gómez', 'Calle Luna 44', '924567891', 'TIO', 'garcia@gmail.com', '0023Gómez', '1989-08-18', TRUE),
('CEXT', '000078901234', 'Juliana Estefanía', 'Vargas Torres', 'Av. La Libertad 500', '988765432', 'TIA', 'vargas@hotmail.com', '00007890', '1991-01-29', TRUE),
('DNI', '38901234', 'Fernando Javier', 'Mendoza López', 'Calle Jardín 12', '921234567', 'PADRE', 'mendoza@gmail.com', '3890López', '1984-03-13', TRUE),
('PASS', '0024567891', 'Martina Beatriz', 'Salazar Ríos', 'Av. Del Sol 23', '916543210', 'MADRE', 'salazar@hotmail.com', '0024Ríos', '1996-07-04', TRUE),
('CEXT', '000089012345', 'Emilio Andrés', 'Quintero Pérez', 'Calle del Mar 45', '928765431', 'ABUELO', 'quintero@gmail.com', '00008901', '1990-10-05', TRUE),
('DNI', '39012345', 'Isabel Cristina', 'Martinez Cuervo', 'Calle Otoño 114', '943678909', 'ABUELA', 'martinez@gmail.com', '3901Cuervo', '1987-05-25', TRUE),
('PASS', '0021234567', 'Jorge Luis', 'Hernández Ibarra', 'Paseo del Río 20', '952890123', 'TIO', 'hernandez@hotmail.com', '0021Ibarra', '1983-10-29', TRUE),
('CEXT', '000090123456', 'Verónica Carolina', 'Valdez Ríos', 'Av. De La Paz 77', '910123456', 'TIA', 'valdez@gmail.com', '00009012', '1999-04-15', TRUE),
('DNI', '40123456', 'Santiago Alejandro', 'Pérez Pineda', 'Calle Sombra 8', '961234567', 'PADRE', 'perez@gmail.com', '4012Pineda', '1994-02-07', TRUE);

INSERT INTO estudiantes (id_apoderado, documento, numero, nombre, apellido, direccion, telefono, genero, email, nacimiento, estado) VALUES 
(1, 'DNI', '78901234', 'Luciana Valeria', 'Pérez López', 'Calle 1, Urb. Los Ángeles', '912345679', 'FEMENINO', 'perez@gmail.com', '2011-01-15', TRUE),
(2, 'PASS', '0023456789', 'Sebastián Andrés', 'González Ruiz', 'Calle 2, Urb. Santa Rosa', '935678910', 'MASCULINO', 'gonzalez@gmail.com', '2012-02-23', TRUE),
(3, 'CEXT', '000045678910', 'Juliana Sofia', 'Torres Álvarez', 'Calle 3, Urb. Villa Verde', '911234567', 'FEMENINO', 'torres@gmail.com', '2013-03-15', TRUE),
(4, 'DNI', '81234567', 'Diego Fernando', 'Ramírez Vega', 'Calle 4, Urb. Jardines', '952345678', 'MASCULINO', 'ramirez@gmail.com', '2014-04-20', TRUE),
(5, 'PASS', '0028765432', 'Valentina Isabel', 'Martínez Díaz', 'Calle 5, Urb. La Rosa', '917654321', 'FEMENINO', 'martinez@gmail.com', '2015-05-19', TRUE),
(6, 'CEXT', '000056789012', 'Matías Alejandro', 'Hernández Jiménez', 'Calle 6, Urb. El Almendro', '934567891', 'MASCULINO', 'hernandez@gmail.com', '2013-05-25', TRUE),
(7, 'DNI', '23456789', 'Sofía Valentina', 'Fernández Castillo', 'Calle 7, Urb. El Buenavista', '912345679', 'FEMENINO', 'fernandez@gmail.com', '2012-06-13', TRUE),
(8, 'PASS', '0024567890', 'Fernando Mateo', 'López Martínez', 'Calle 8, Urb. Villa Mar', '944567890', 'MASCULINO', 'lopez@gmail.com', '2010-07-25', TRUE),
(9, 'CEXT', '000067890123', 'Nicolás Alejandro', 'Suárez Pérez', 'Calle 9, Urb. Nueva Esperanza', '932345678', 'MASCULINO', 'suarez@gmail.com', '2011-08-17', TRUE),
(10, 'DNI', '34567890', 'Sara Isabela', 'Castro Ríos', 'Calle 10, Urb. Las Colinas', '919876543', 'FEMENINO', 'castro@gmail.com', '2016-09-04', TRUE),
(11, 'PASS', '0023456788', 'Juan David', 'García Gómez', 'Calle 11, Urb. La Paz', '919876543', 'MASCULINO', 'garcia@gmail.com', '2015-08-12', TRUE),
(12, 'CEXT', '000078901234', 'Laura Isabel', 'Vargas Torres', 'Calle 12, Urb. La Libertad', '912345678', 'FEMENINO', 'vargas@gmail.com', '2017-12-01', TRUE),
(13, 'DNI', '67890123', 'Anderson Felipe', 'Mendoza López', 'Calle 13, Urb. Los Jardines', '934567890', 'MASCULINO', 'mendoza@gmail.com', '2018-04-28', TRUE),
(14, 'PASS', '0024567891', 'Ana María', 'Salazar Ríos', 'Calle 14, Urb. El Sol', '938765431', 'FEMENINO', 'salazar@gmail.com', '2020-01-23', TRUE),
(15, 'CEXT', '000089012345', 'Gabriel Santiago', 'Quintero Pérez', 'Calle 15, Urb. La Esperanza', '912345678', 'MASCULINO', 'quintero@gmail.com', '2011-02-17', TRUE),
(16, 'DNI', '89012345', 'María Clara', 'Martinez Cuervo', 'Calle 16, Urb. Valle Verde', '921234567', 'FEMENINO', 'martinez@gmail.com', '2019-02-11', TRUE),
(17, 'PASS', '0021234567', 'Luis Felipe', 'Hernández Ibarra', 'Calle 17, Urb. El Rocío', '912345678', 'MASCULINO', 'hernandez@gmail.com', '2021-06-08', TRUE),
(18, 'CEXT', '000090123456', 'Alejandra Isabel', 'Valdez Ríos', 'Calle 18, Urb. Los Olmos', '919876543', 'FEMENINO', 'valdez@gmail.com', '2015-07-29', TRUE),
(19, 'DNI', '90123456', 'Camilo Andrés', 'Pérez Pineda', 'Calle 19, Urb. La Arboleda', '927654321', 'MASCULINO', 'perez@gmail.com', '2010-11-13', TRUE),
(20, 'PASS', '0023456780', 'Natalia Andrea', 'Carmona Torres', 'Calle 20, Urb. El Encuentro', '914567890', 'FEMENINO', 'carmona@gmail.com', '2022-09-23', TRUE);

select * from usuarios;
select * from estudiantes;
select * from apoderados;
select * from conceptos;
select * from conceptos_detalle;
select * from matriculas;
select * from matricula_detalle;
select * from pagos;
select * from pagos_detalle;