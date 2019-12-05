delete from HackatonEntity;
delete from LugarEntity;
delete from CalificacionEntity;
delete from InteresEntity;
delete from LenguajeEntity;

select * from InteresEntity;
select * from LugarEntity;


insert into InteresEntity(descripcion,nombre, imagen) values ('Desarrollo de video juegos', 'video juegos', 'https://cdn.dribbble.com/users/726818/screenshots/4019303/ohio_gamers_logo.jpg');
insert into InteresEntity(descripcion,nombre, imagen) values ('Desarrollo de aplicaciones mobiles ', 'Aplicaciones mobiles', 'https://icon-library.net/images/mobile-app-development-icon/mobile-app-development-icon-3.jpg');
insert into InteresEntity(descripcion,nombre, imagen) values ('Seguridad en redes sociales', 'Seguridad', 'https://www.reasonwhy.es/sites/default/files/ciberseguridad-estudio-vmware.jpg');
insert into InteresEntity(descripcion,nombre, imagen) values ('Desarrollos para solucionar problemas cotidianos en las grandes ciudades ', 'Programacion orientada a objetos', 'https://dev-res.thumbr.io/libraries/22/16/lib/1466774399567_8.jpg?size=854x493s&ext=jpg');
insert into InteresEntity(descripcion,nombre, imagen) values ('Desarrollos para solucionar problemas ambientales', 'Bioinformatica', 'http://heraldcorrespondent.com/wp-content/uploads/2019/07/Bioinformatics-Market1.png');

insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Bogota','Carrera 90a # 8a -10', 'Centro empresarial ANDES', 'https://panel.baquianos.com/img/blog/2019/28/lugares12-knfx640.jpg');
insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Medellin','Carrera 3 # 18- 45', 'Alianza Colombo Francesa Centro' , 'https://1.bp.blogspot.com/-KciC_jiRE-4/WdTins8xFBI/AAAAAAAAYE8/8xuU5kyR2lcuWwQID4Kyh7QXVpvnbYF_gCLcBGAs/s400/beneficios-de-los-centros-empresariales-1.jpg');
insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Cali','Carrera 7 # 84- 72', 'Biblioteca Luis Ángel Arango', 'https://static.wixstatic.com/media/a307d9_543037bcf8cd498cac0a959e0eaac250~mv2.png/v1/fill/w_626,h_315/a307d9_543037bcf8cd498cac0a959e0eaac250~mv2.png');
insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Bucaramanga','Calle 4 No. 5 – 10', 'CLUB CAMPESTRE CAFAM', 'http://nomadstreetmarket.com/assets/img/Captura%20de%20pantalla%202019-05-11%20a%20las%2010.01.53-1557564613.9393.png');
insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Bogota','Calle 11 No. 4 - 14', 'Camara de comercio de bogota', 'http://static.iris.net.co/semana/upload/images/2015/2/14/417994_142631_1.jpg');
insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Cartagena','Calle 24 N° 5-60', 'Centro Cultural del Libro', 'https://i.pinimg.com/originals/76/83/63/768363de7f8739cfec6e1e8f777f1b04.jpg');
insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Leticia','Calle 48b sur No. 21-13', 'Centro Cultural Magitinto', 'https://www.metacontratas.com/wp-content/uploads/2016/07/coordinacion-de-actividades-empresariales.jpg');
insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Girardot','Avenida Cra. 60 No. 57-60', 'Galería Arte Espacio', 'http://recursos.ccb.org.co/ccb/asorlac/flip_book/PRESENTACION_CONFERENCIA_MUNDIAL_REFORMAS_CREACION_EMPRESA/files/assets/basic-html/page14_images/0002.jpg');
insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Tunja','Calle 11 No. 4-21 / 93', 'Galería Café Libro Norte' , 'https://www.oficinasenmedellin.com/wp-content/uploads/2016/02/CENTRO-DE-NEGOCIOS-DEL-SUR-8-500x350.jpg');
insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Pasto','Carrera 20 No. 37-54', 'Galería de Arte Centro Cultural Santa Cruz', 'https://www.eluniversalqueretaro.mx/sites/default/files/styles/f03-651x400/public/field/image/centrocomercial_dolcevita3-grande.jpg?itok=Sbj6S2RQ');
insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Armenia','Cr.9 # 74-99', 'Galería Uniandinos', 'https://4.bp.blogspot.com/-bnRDEQ6bQjY/T3B5U5d-QgI/AAAAAAAAVhQ/nlinH7NYUDQ/s1600/centros+empresariales.jpg');
insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Villa de leyva','Carrera 7 No. 22 - 79', 'Jardin de la 82', 'https://www.madetecno.co/wp-content/uploads/2016/05/CENTRO-EMPRESARIAL-METROPOLITANO-CONCONCRETO.jpg');
insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Yopal','Calle 45A No. 14-37', 'Centro empresarial FORJANDO', 'https://innave.es/_files/200000309-dd65ade5f7/P1020780.JPG');
insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Cucuta','Cl.119 # 15-41', 'Centro econonmico municipal', 'https://s1.eestatic.com/2017/12/12/economia/empresas/Sector_inmobiliario-Centros_comerciales-Adquisiciones_empresariales-Empresas_268983975_57376607_1024x576.jpg');
insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Santa Marta','Cl.45 # 18 -27', 'Centro de convenciones La costa', 'http://www.cristaleriaalcorisa.com/Panel/server/php/74/IMG_2596.jpg');
insert into LugarEntity(ciudad,direccion,nombre,imagen) values ('Ibague','Cl.70a # 7-41', 'Biblioteca PROGRESAR', 'https://media-cdn.tripadvisor.com/media/photo-s/0e/22/2b/4d/photo1jpg.jpg');

insert into LenguajeEntity(name,imagen) values ('Java','https://upload.wikimedia.org/wikipedia/en/thumb/3/30/Java_programming_language_logo.svg/1200px-Java_programming_language_logo.svg.png');
insert into LenguajeEntity(name,imagen) values ('Python','https://www.python.org/static/opengraph-icon-200x200.png');
insert into LenguajeEntity(name,imagen) values ('C++','https://ih1.redbubble.net/image.416406976.0438/pp,650x642-pad,750x1000,f8f8f8.jpg');
insert into LenguajeEntity(name,imagen) values ('Assembly','https://hackr.io/tutorials/learn-assembly-language/logo/logo-assembly-language?ver=1557983975');


insert into HackatonEntity(especificacion,fechafin,fechainicio,finalizada,nivel,nombre,tema) values 
('ninguna','2019-10-20 00:00:00','2019-10-15 00:00:00',1,3,'prueba','ninguno');
insert into CalificacionEntity(id,calificacion,comentario,hackaton_id) values (0,4.9,'Todo bien',1);

select * from HackatonEntity;