delete from LugarEntity;
delete from CalificacionEntity;
delete from HackatonEntity;

insert into LugarEntity(id,ciudad,direccion,nombre) values (987654321,'Bogotá','Carrera 90a # 8a -10', 'Centro empresarial ANDES');
insert into LugarEntity(id,ciudad,direccion,nombre) values (987654321,'Bogotá','Carrera 90a # 8a -10', 'Centro empresarial ANDES');

insert into HackatonEntity(id,especificacion,fechafin,fechainicio,finalizada,nivel,nombre,tema,lugar_id) values (123,'ninguna','4/7/2019','1/7/2019',1,3,'prueba','ninguno',987654321);
insert into CalificacionEntity(id,calificacion,comentario,hackaton_id) values (0,4.9,'Todo bien',123);

