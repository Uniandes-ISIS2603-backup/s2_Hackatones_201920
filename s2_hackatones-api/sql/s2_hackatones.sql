delete from LugarEntity;
<<<<<<< HEAD
delete from HackatonEntity;

insert into LugarEntity(id,ciudad,direccion,nombre) values (100,'Bogota','Carrera 90a # 8a -10', 'Centro empresarial ANDES');
insert into LugarEntity(id,ciudad,direccion,nombre) values (200,'Medellin','Carrera 3 # 18- 45', 'Alianza Colombo Francesa Centro');
insert into LugarEntity(id,ciudad,direccion,nombre) values (300,'Cali','Carrera 7 # 84- 72', 'Biblioteca Luis Ángel Arango');
insert into LugarEntity(id,ciudad,direccion,nombre) values (400,'Bucaramanga','Calle 4 No. 5 – 10', 'CLUB CAMPESTRE CAFAM');
insert into LugarEntity(id,ciudad,direccion,nombre) values (500,'Bogota','Calle 11 No. 4 - 14', 'Camara de comercio de bogota');
insert into LugarEntity(id,ciudad,direccion,nombre) values (600,'Cartagena','Calle 24 N° 5-60', 'Centro Cultural del Libro');
insert into LugarEntity(id,ciudad,direccion,nombre) values (700,'Leticia','Calle 48b sur No. 21-13', 'Centro Cultural Magitinto');
insert into LugarEntity(id,ciudad,direccion,nombre) values (800,'Girardot','Avenida Cra. 60 No. 57-60', 'Galería Arte Espacio');
insert into LugarEntity(id,ciudad,direccion,nombre) values (900,'Tunja','Calle 11 No. 4-21 / 93', 'Galería Café Libro Norte');
insert into LugarEntity(id,ciudad,direccion,nombre) values (10000,'Pasto','Carrera 20 No. 37-54', 'Galería de Arte Centro Cultural Santa Cruz');
insert into LugarEntity(id,ciudad,direccion,nombre) values (11000,'Armenia','Cr.9 # 74-99', 'Galería Uniandinos');
insert into LugarEntity(id,ciudad,direccion,nombre) values (12000,'Villa de leyva','Carrera 7 No. 22 - 79', 'Jardin de la 82');
insert into LugarEntity(id,ciudad,direccion,nombre) values (13000,'Yopal','Calle 45A No. 14-37', 'Centro empresarial FORJANDO');
insert into LugarEntity(id,ciudad,direccion,nombre) values (14000,'Cucuta','Cl.119 # 15-41', 'Centro econonmico municipal');
insert into LugarEntity(id,ciudad,direccion,nombre) values (15000,'Santa Marta','Cl.45 # 18 -27', 'Centro de convenciones La costa');
insert into LugarEntity(id,ciudad,direccion,nombre) values (16000,'Ibague','Cl.70a # 7-41', 'Biblioteca PROGRESAR');

insert into HackatonEntity(id,especificacion,fechafin,fechainicio,finalizada,imagen,limite_participantes,nivel,nombre,premio,tema,tipo,equipo_ganador_id,lugar_id,reglas,restricciones) values 
                          (1,'especificacion', '2019-08-12','2019-08-10',0, 'imagen.png', 50,2, 'HACKATONESANDES',1,'programacion en java',1, null, null, 'mis reglas', 'mis restricciones');

=======
delete from CalificacionEntity;
delete from HackatonEntity;

insert into LugarEntity(id,ciudad,direccion,nombre) values (987654321,'Bogotá','Carrera 90a # 8a -10', 'Centro empresarial ANDES');
insert into HackatonEntity(id,especificacion,fechafin,fechainicio,finalizada,nivel,nombre,tema,lugar_id) values (123,'ninguna','4/7/2019','1/7/2019',1,3,'prueba','ninguno',987654321);
insert into CalificacionEntity(id,calificacion,comentario,hackaton_id) values (0,4.9,'Todo bien',1);
>>>>>>> df4b6cee2beb53230b8abd7650e72042b442ea0d

