# Parcial1

como no tengo el repositorio del parcial 1, volvi a hacer el endpoint, lo vas a encontrar en un paquete que se llama parcial dentro de com.utn.TPFinal

ademas hice un test a parte solo para esa clase

si necesitas la vista v_calls_info

create view v_calls_info
as
select concat(uo.name," ",uo.last_name) as full_name_o ,uo.dni as dni_user_origin, concat(co.prefix,po.number_line) as origin_line, pro.name as origin_province, co.name as origin_city, 
concat(ud.name," ",ud.last_name) as full_name_d, ud.dni as dni_user_destination, concat(cd.prefix,pd.number_line) as destination_line, cd.name as destination_city, c.duration, c.total_price, c.total_cost, c.call_date, c.billed
from calls c 
inner join phone_lines po on c.id_line_origin = po.id_line 
inner join users uo on po.id_user = uo.id_user 
inner join cities co on uo.id_city = co.id_city
inner join provinces pro on pro.id_province = co.id_province
inner join phone_lines pd on c.id_line_destination = pd.id_line
inner join users ud on pd.id_user = ud.id_user 
inner join cities cd on ud.id_city = cd.id_city order by call_date;
