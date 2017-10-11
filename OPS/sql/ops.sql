--
-- MOSTRAMOS TODOS LOS VALORES ALMACENADOS, EL NUMERO DE REGISTROS Y LOS INTERVALOS DE ALMACENAMIENTO PARA CADA UNO DE ELLOS
--
select mercado, bolsa, indice, ticker, count(1) as num_dias, min(fecha) as fecha_ini, max(fecha) fecha_fin
from public.mercados
group by mercado, bolsa, indice, ticker order by fecha_ini, mercado, bolsa, indice, ticker;
--
-- MUESTRA VARIACIÓN DE PRECIO DE CIERRE ENTRE DOS FECHAS ESPECIFICADAS
--
select m4.mercado, m4.bolsa, m4.indice, m4.ticker, m4.fecha_inicial, m4.cierre_inicial, m4.fecha_final, m4.cierre_final, 
round((m4.cierre_final*100/m4.cierre_inicial)-100, 2) as var
from
(
	select distinct m1.mercado, m1.bolsa, m1.indice, m1.ticker,
	to_date('2017-09-20', 'yyyy-mm-dd') as fecha_inicial,
	(
		select m2.cierre from public.mercados m2 
		where m2.mercado = m1.mercado and m2.bolsa = m1.bolsa and m2.indice = m1.indice 
		and m2.ticker = m1.ticker and m2.fecha = to_date('2017-09-20', 'yyyy-mm-dd') 
	) 
	as cierre_inicial,
	to_date('2017-10-06', 'yyyy-mm-dd') as fecha_final,
	(
		select m3.cierre from public.mercados m3 
		where m3.mercado = m1.mercado and m3.bolsa = m1.bolsa and m3.indice = m1.indice 
		and m3.ticker = m1.ticker and m3.fecha = to_date('2017-10-06', 'yyyy-mm-dd') 
	) 
	as cierre_final
	from public.mercados m1 order by m1.mercado, m1.bolsa, m1.indice, m1.ticker
)
as m4 order by var asc;
--
-- MUESTRA VARIACIÓN DEL ULTIMO VOLUMEN CON RESPECTO AL VOLUMEN MEDIO
--
select m4.mercado, m4.bolsa, m4.indice, m4.ticker, m4.max_fecha, m4.avg_vol, m4.max_vol,
round((m4.max_vol*100/m4.avg_vol)-100, 2) as var 
from
(
	select m2.mercado, m2.bolsa, m2.indice, m2.ticker, m2.max_fecha, m2.avg_vol,
	(
		select m3.volumen from public.mercados m3 
		where m3.mercado = m2.mercado and m3.bolsa = m2.bolsa and m3.indice = m2.indice 
		and m3.ticker = m2.ticker and m3.fecha = m2.max_fecha
	) as max_vol
	from 
	(
		select m1.mercado, m1.bolsa, m1.indice, m1.ticker, max(fecha) as max_fecha, round(avg(volumen)) as avg_vol
		from public.mercados m1 
		where m1.indice <> '-'
		group by m1.mercado, m1.bolsa, m1.indice, m1.ticker
	)
	as m2
) 
as m4 order by var asc;
--
-- 
--