

select 
clie.clie_id,
clie.clie_nombre,
tick.tick_id, 
tick.tick_fechaprocesado,
CONCAT(paci.paci_nombres,' ',paci.paci_apellidos) nombre,
paci.paci_documento,
TIMESTAMPDIFF(YEAR,paci.paci_fechanacimiento,CURDATE()) edad,
esci.esci_descripcion,
cc.ceco_observacion,
(
select resp.resp_descripcion
from ticket tt
inner join ticket_clienteservicio tcs on tcs.tick_id = tt.tick_id 
inner join javaphc.anotaciones anot on tcs.tics_id = anot.tics_id and anot.form_id =1 and anot.anot_estado ='FINALIZADO'
inner join javaphc.respuestas resp on anot.anot_id = resp.anot_id and resp.camp_id = 1
where  tt.tick_id = tick.tick_id
limit 1
) cargo,
teme.teme_descripcion,
(
select GROUP_CONCAT(cls.serv_id)
from ticket tt
inner join ticket_clienteservicio tcs on tcs.tick_id = tt.tick_id 
inner join clientes_servicio cls on cls.clse_id = tcs.clse_id
where  tt.tick_id = tick.tick_id
group by tt.tick_id
) servicios,
(
select resp.resp_descripcion
from ticket tt
inner join ticket_clienteservicio tcs on tcs.tick_id = tt.tick_id 
inner join javaphc.anotaciones anot on tcs.tics_id = anot.tics_id and anot.form_id =10 and anot.anot_estado ='FINALIZADO'
inner join javaphc.respuestas resp on anot.anot_id = resp.anot_id and resp.camp_id = 149
where  tt.tick_id = tick.tick_id
limit 1
) restricciones,
(
select resp.resp_descripcion
from ticket tt
inner join ticket_clienteservicio tcs on tcs.tick_id = tt.tick_id 
inner join javaphc.anotaciones anot on tcs.tics_id = anot.tics_id and anot.form_id =10 and anot.anot_estado ='FINALIZADO'
inner join javaphc.respuestas resp on anot.anot_id = resp.anot_id and resp.camp_id = 147
where  tt.tick_id = tick.tick_id
limit 1
) recomendaciones,
(
select resp.resp_descripcion
from ticket tt
inner join ticket_clienteservicio tcs on tcs.tick_id = tt.tick_id 
inner join javaphc.anotaciones anot on tcs.tics_id = anot.tics_id and anot.form_id =10 and anot.anot_estado ='FINALIZADO'
inner join javaphc.respuestas resp on anot.anot_id = resp.anot_id and resp.camp_id = 145
where  tt.tick_id = tick.tick_id
limit 1
) diagnostico,
sede.sede_id,
sede.sede_nombre
from 
ticket tick
inner join centrocostos cc on cc.ceco_id = tick.ceco_id 
inner join clientes clie on clie.clie_id = cc.clie_id
inner join javaphc.pacientes paci on paci.paci_id = tick.tick_paciente
inner join estadocivil esci on paci.paci_ecivil = esci.esci_id
inner join tipoexamen_medico teme on teme.teme_id = tick.teme_id
inner join sede sede on sede.sede_id = tick.sede_id
where
tick.tick_estado = 'PROCESADO'
-- and cc.clie_id = 54
and tick.sede_id = 2
and
tick.tick_fechaprocesado BETWEEN '2017-12-01 00:00:00' AND '2018-01-22 23:59:59'

;

select 
distinct(serv.serv_id),
serv.serv_nombre
from 
ticket tick
inner join centrocostos cc on cc.ceco_id = tick.ceco_id 
inner join ticket_clienteservicio tcs on tcs.tick_id = tick.tick_id 
inner join clientes_servicio cls on cls.clse_id = tcs.clse_id
inner join servicios serv on serv.serv_id = cls.serv_id 
where
tick.tick_estado = 'PROCESADO'
-- and cc.clie_id = 54
and tick.sede_id = 2
and
tick.tick_fechaprocesado BETWEEN '2017-12-01 00:00:00' AND '2018-01-22 23:59:59'
order by serv.serv_id;



