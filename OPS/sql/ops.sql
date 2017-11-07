--
-- MOSTRAMOS TODOS LOS VALORES ALMACENADOS, EL NUMERO DE REGISTROS Y LOS INTERVALOS DE ALMACENAMIENTO PARA CADA UNO DE ELLOS
--
select mercado, bolsa, indice, ticker, count(1) as num_dias, min(fecha) as fecha_ini, max(fecha) fecha_fin
from public.mercados_investing
group by mercado, bolsa, indice, ticker order by fecha_ini, mercado, bolsa, indice, ticker;
--
-- MUESTRA VARIACIÓN DE PRECIO DE CIERRE ENTRE DOS FECHAS ESPECIFICADAS
--
select m4.mercado, m4.bolsa, m4.indice, m4.ticker, m4.fecha_inicial, m4.precio_inicial, m4.fecha_final, m4.precio_final, 
round((m4.precio_final*100/m4.precio_inicial)-100, 2) as var
from
(
	select distinct m1.mercado, m1.bolsa, m1.indice, m1.ticker,
	to_date('2015-09-22', 'yyyy-mm-dd') as fecha_inicial,
	(
		select m2.cierre from public.mercados_investing m2 
		where m2.mercado = m1.mercado and m2.bolsa = m1.bolsa and m2.indice = m1.indice 
		and m2.ticker = m1.ticker and m2.fecha = to_date('2015-09-22', 'yyyy-mm-dd') 
	) 
	as precio_inicial,
	to_date('2015-10-06', 'yyyy-mm-dd') as fecha_final,
	(
		select m3.cierre from public.mercados_investing m3 
		where m3.mercado = m1.mercado and m3.bolsa = m1.bolsa and m3.indice = m1.indice 
		and m3.ticker = m1.ticker and m3.fecha = to_date('2015-10-06', 'yyyy-mm-dd') 
	) 
	as precio_final
	from public.mercados_investing m1 order by m1.mercado, m1.bolsa, m1.indice, m1.ticker
)
as m4 order by var asc;
--
-- MUESTRA VARIACIÓN DE PRECIO DE CIERRE ENTRE LAS FECHAS MÍNIMAS Y MÁXIMAS ENCONTRADAS FILTRANDO POR MERCADO Y TICKER
--
select m7.mercado, m7.bolsa, m7.indice, m7.ticker, m7.fecha_inicial, m7.precio_inicial, m7.fecha_final, m7.precio_final, 
round((m7.precio_final*100/m7.precio_inicial)-100, 2) as var_precio
from
(
	select m4.mercado, m4.bolsa, m4.indice, m4.ticker, m4.fecha_inicial, m4.fecha_final,
	(
		select m5.cierre from public.mercados_investing m5 
		where m5.mercado = m4.mercado and m5.bolsa = m4.bolsa and m5.indice = m4.indice 
		and m5.ticker = m4.ticker and m5.fecha = m4.fecha_inicial 
	) 
	as precio_inicial,
	(
		select m6.cierre from public.mercados_investing m6 
		where m6.mercado = m4.mercado and m6.bolsa = m4.bolsa and m6.indice = m4.indice 
		and m6.ticker = m4.ticker and m6.fecha = m4.fecha_final
	) 
	as precio_final
	from
	(
		select m1.mercado, m1.bolsa, m1.indice, m1.ticker, min(m1.fecha) as fecha_inicial, max(m1.fecha) as fecha_final
		from public.mercados_investing m1 where mercado = 'ETF' 
		and ticker not like '%leveraged%'
		and ticker not like '%-3x-%'
		and ticker not like '%-2x-%'
		and ticker not like '%short%'
		group by m1.mercado, m1.bolsa, m1.indice, m1.ticker
	)
	as m4
)
as m7 order by var_precio asc;
--
-- MUESTRA VARIACIÓN DEL ULTIMO VOLUMEN CON RESPECTO AL VOLUMEN MEDIO
--
select m4.mercado, m4.bolsa, m4.indice, m4.ticker, m4.ultima_fecha, m4.avg_vol, m4.ultimo_vol,
round((m4.ultimo_vol*100/m4.avg_vol)-100, 2) as var_volumen 
from
(
	select m2.mercado, m2.bolsa, m2.indice, m2.ticker, m2.ultima_fecha, m2.avg_vol,
	(
		select m3.volumen from public.mercados_investing m3 
		where m3.mercado = m2.mercado and m3.bolsa = m2.bolsa and m3.indice = m2.indice 
		and m3.ticker = m2.ticker and m3.fecha = m2.ultima_fecha
	) as ultimo_vol
	from 
	(
		select m1.mercado, m1.bolsa, m1.indice, m1.ticker, max(fecha) as ultima_fecha, round(avg(volumen)) as avg_vol
		from public.mercados_investing m1 
		where m1.indice <> '-'
		group by m1.mercado, m1.bolsa, m1.indice, m1.ticker
	)
	as m2
) 
as m4 order by var_volumen asc;
--
-- MUESTRA VARIACIÓN DEL ULTIMO PRECIO CON RESPECTO AL PRECIO MÁXIMO
--
select m4.mercado, m4.bolsa, m4.indice, m4.ticker, m4.primera_fecha, m4.ultima_fecha, m4.precio_minimo, m4.precio_maximo, m4.ultimo_precio,
round( ((m4.ultimo_precio - m4.precio_maximo) * 100) / m4.precio_maximo , 2) as var_maximo,  
round( ((m4.ultimo_precio - m4.precio_minimo) * 100) / m4.precio_minimo , 2) as var_minimo          
from
(
	select m2.mercado, m2.bolsa, m2.indice, m2.ticker, m2.primera_fecha, m2.ultima_fecha, m2.precio_minimo, m2.precio_maximo,
	(
		select m3.cierre from public.mercados_investing m3 
		where m3.mercado = m2.mercado and m3.bolsa = m2.bolsa and m3.indice = m2.indice 
		and m3.ticker = m2.ticker and m3.fecha = m2.ultima_fecha
	) as ultimo_precio
	from 
	(
		select m1.mercado, m1.bolsa, m1.indice, m1.ticker, 
			   min(m1.fecha) as primera_fecha, max(m1.fecha) as ultima_fecha, 
			   min(m1.cierre) as precio_minimo, max(m1.cierre) as precio_maximo 
		from public.mercados_investing m1 
		where m1.mercado = 'ETF'
		and ticker not like '%-2x%'
		and ticker not like '%-3x%'
		and ticker not like '%-4x%'
		and ticker not like '%-10x%'
		and ticker not like '%-15x%'
		and ticker not like '%leveraged%'
		and ticker not like '%inverse%'
		and ticker not like '%short%'
		and ticker not like '%levdax%'
		group by m1.mercado, m1.bolsa, m1.indice, m1.ticker
	)
	as m2 where m2.primera_fecha < '2012-01-01'
) 
as m4 order by var_maximo desc, var_minimo desc;
--
-- 
--