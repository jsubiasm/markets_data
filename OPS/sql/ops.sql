--
-- MOSTRAMOS TODOS LOS VALORES ALMACENADOS, EL NUMERO DE REGISTROS Y LOS INTERVALOS DE ALMACENAMIENTO PARA CADA UNO DE ELLOS
--
select mercado, bolsa, indice, ticker, count(1) as num_dias, min(fecha) as fecha_ini, max(fecha) fecha_fin
from public.mercados_investing
group by mercado, bolsa, indice, ticker order by fecha_fin, mercado, bolsa, indice, ticker;
--
-- MUESTRA VARIACIÓN DE PRECIO DE CIERRE ENTRE DOS FECHAS CONCRETAS ESPECIFICADAS (AMBAS FECHAS TIENEN QUE CONTENER DATOS)
--
select m4.*, 
round( ((m4.precio_final - m4.precio_inicial) * 100) / m4.precio_inicial , 2) as var_precio 
from
(
	select distinct m5.mercado, m5.bolsa, m5.indice, m5.ticker,
	to_date('2015-09-22', 'yyyy-mm-dd') as fecha_inicial,
	(
		select m2.cierre from public.mercados_investing m2 
		where m2.mercado = m5.mercado and m2.bolsa = m5.bolsa and m2.indice = m5.indice 
		and m2.ticker = m5.ticker and m2.fecha = to_date('2015-09-22', 'yyyy-mm-dd') 
	) 
	as precio_inicial,
	to_date('2015-10-06', 'yyyy-mm-dd') as fecha_final,
	(
		select m3.cierre from public.mercados_investing m3 
		where m3.mercado = m5.mercado and m3.bolsa = m5.bolsa and m3.indice = m5.indice 
		and m3.ticker = m5.ticker and m3.fecha = to_date('2015-10-06', 'yyyy-mm-dd') 
	) 
	as precio_final
	from
	(
		select m1.mercado, m1.bolsa, m1.indice, m1.ticker, min(m1.fecha) as fecha_inicial, max(m1.fecha) as fecha_final
		from public.mercados_investing m1 
		where m1.mercado = 'ETF'
		and m1.ticker not like '%-2x%'
		and m1.ticker not like '%-3x%'
		and m1.ticker not like '%-4x%'
		and m1.ticker not like '%-10x%'
		and m1.ticker not like '%-15x%'
		and m1.ticker not like '%leverage%'
		and m1.ticker not like '%boost%'
		and m1.ticker not like '%inverse%'
		and m1.ticker not like '%short%'
		and m1.ticker not like '%levdax%'
		group by m1.mercado, m1.bolsa, m1.indice, m1.ticker
	) as m5
)
as m4 order by var_precio desc;
--
-- MUESTRA VARIACIÓN DE PRECIO DE CIERRE EN UN INTERVALO DE FECHAS ESPECIFICADO
--
select m7.*, 
round( ((m7.precio_final - m7.precio_inicial) * 100) / m7.precio_inicial , 2) as var_precio  
from
(
	select m4.*,
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
		from public.mercados_investing m1  
		where m1.mercado = 'ETF'
		and m1.ticker not like '%-2x%'
		and m1.ticker not like '%-3x%'
		and m1.ticker not like '%-4x%'
		and m1.ticker not like '%-10x%'
		and m1.ticker not like '%-15x%'
		and m1.ticker not like '%leverage%'
		and m1.ticker not like '%boost%'
		and m1.ticker not like '%inverse%'
		and m1.ticker not like '%short%'
		and m1.ticker not like '%levdax%'
		and m1.fecha between '2016-01-01' and '2016-12-31'
		group by m1.mercado, m1.bolsa, m1.indice, m1.ticker
	)
	as m4
)
as m7 order by var_precio desc;
--
-- MUESTRA VARIACIÓN DEL ULTIMO VOLUMEN CON RESPECTO AL VOLUMEN MEDIO
--
select m4.*,
round( ((m4.ultimo_vol - m4.avg_vol) * 100) / m4.avg_vol , 2) as var_volumen 
from
(
	select m2.*,
	(
		select m3.volumen from public.mercados_investing m3 
		where m3.mercado = m2.mercado and m3.bolsa = m2.bolsa and m3.indice = m2.indice 
		and m3.ticker = m2.ticker and m3.fecha = m2.ultima_fecha
	) as ultimo_vol
	from 
	(
		select m1.mercado, m1.bolsa, m1.indice, m1.ticker, max(fecha) as ultima_fecha, round(avg(volumen)) as avg_vol
		from public.mercados_investing m1 
		where m1.mercado = 'ETF'
		and m1.ticker not like '%-2x%'
		and m1.ticker not like '%-3x%'
		and m1.ticker not like '%-4x%'
		and m1.ticker not like '%-10x%'
		and m1.ticker not like '%-15x%'
		and m1.ticker not like '%leverage%'
		and m1.ticker not like '%boost%'
		and m1.ticker not like '%inverse%'
		and m1.ticker not like '%short%'
		and m1.ticker not like '%levdax%'
		group by m1.mercado, m1.bolsa, m1.indice, m1.ticker
	)
	as m2 where m2.avg_vol > 0
) 
as m4 order by var_volumen desc;
--
-- MUESTRA VARIACIÓN DEL ULTIMO PRECIO CON RESPECTO A LOS PRECIOS MÍNIMOS Y MÁXIMOS FILTRANDO POR FECHA
--
select m4.*,
round( ((m4.ultimo_precio - m4.precio_maximo) * 100) / m4.precio_maximo , 2) as var_maximo,  
round( ((m4.ultimo_precio - m4.precio_minimo) * 100) / m4.precio_minimo , 2) as var_minimo          
from
(
	select m2.*,
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
		and m1.ticker not like '%-2x%'
		and m1.ticker not like '%-3x%'
		and m1.ticker not like '%-4x%'
		and m1.ticker not like '%-10x%'
		and m1.ticker not like '%-15x%'
		and m1.ticker not like '%leverage%'
		and m1.ticker not like '%boost%'
		and m1.ticker not like '%inverse%'
		and m1.ticker not like '%short%'
		and m1.ticker not like '%levdax%'
		group by m1.mercado, m1.bolsa, m1.indice, m1.ticker
	)
	as m2 where m2.primera_fecha < '2012-01-01'
) 
as m4 order by var_maximo desc, var_minimo desc;
--
-- MUESTRA VARIACIÓN ENTRE LOS PRECIOS MÍNIMO Y MÁXIMO (O MÁXIMO Y MÍNIMO SI LA TENDENCIA ES BAJISTA) FILTRANDO POR INTERVALO DE FECHAS
--
select m5.*, 
(
	case when m5.precio_minimo_fecha < m5.precio_maximo_fecha 
		then 
			round( ((m5.precio_maximo - m5.precio_minimo) * 100) / m5.precio_minimo , 2)
		else 
			round( ((m5.precio_minimo - m5.precio_maximo) * 100) / m5.precio_maximo , 2)
		end
) as var_precio
from 
(
	select m2.*,
	(
		select max(m3.fecha) from public.mercados_investing m3 
		where m3.mercado = m2.mercado and m3.bolsa = m2.bolsa and m3.indice = m2.indice 
		and m3.ticker = m2.ticker and m3.cierre = m2.precio_minimo
		and m3.fecha between '2016-01-01' and '2016-12-31'
	) as precio_minimo_fecha,
	(
		select max(m4.fecha) from public.mercados_investing m4 
		where m4.mercado = m2.mercado and m4.bolsa = m2.bolsa and m4.indice = m2.indice 
		and m4.ticker = m2.ticker and m4.cierre = m2.precio_maximo
		and m4.fecha between '2016-01-01' and '2016-12-31'
	) as precio_maximo_fecha
	from 
	(
		select m1.mercado, m1.bolsa, m1.indice, m1.ticker, min(m1.cierre) as precio_minimo, max(m1.cierre) as precio_maximo 
		from public.mercados_investing m1 
		where m1.mercado = 'ETF'
		and m1.ticker not like '%-2x%'
		and m1.ticker not like '%-3x%'
		and m1.ticker not like '%-4x%'
		and m1.ticker not like '%-10x%'
		and m1.ticker not like '%-15x%'
		and m1.ticker not like '%leverage%'
		and m1.ticker not like '%boost%'
		and m1.ticker not like '%inverse%'
		and m1.ticker not like '%short%'
		and m1.ticker not like '%levdax%'
		and m1.fecha between '2016-01-01' and '2016-12-31'
		group by m1.mercado, m1.bolsa, m1.indice, m1.ticker
	) as m2
) as m5 order by var_precio desc;
--
-- BUSQUEDA DE ETFS FILTRANDO POR TICKER Y FECHA
--
select t1.* from 
(
	select mercado, bolsa, indice, ticker, count(1) as num_dias, min(fecha) as fecha_ini, max(fecha) fecha_fin
	from public.mercados_investing
	where mercado = 'ETF' and 
	(
		ticker like '%aerospace%' or 
		ticker like '%auto-parts%' or 
		ticker like '%bank%' or 
		ticker like '%beverage%' or 
		ticker like '%biotech%' or 
		ticker like '%broker%' or 
		ticker like '%capital%' or 
		ticker like '%chemicals%' or 
		ticker like '%cons.---mat.%' or 
		ticker like '%consumer%' or 
		ticker like '%emerging%' or 
		ticker like '%energy%' or 
		ticker like '%financial%' or 
		ticker like '%gold%' or 
		ticker like '%goods%' or 
		ticker like '%health%' or 
		ticker like '%homebuilders%' or 
		ticker like '%ind.-goods%' or 
		ticker like '%industrial%' or 
		ticker like '%insurance%' or 
		ticker like '%materials%' or 
		ticker like '%media%' or 
		ticker like '%medical%' or 
		ticker like '%metals%' or 
		ticker like '%mining%' or 
		ticker like '%oil---gas%' or 
		ticker like '%oil--gas%' or 
		ticker like '%oil-gas%' or 
		ticker like '%pers-housld%' or 
		ticker like '%pharma%' or 
		ticker like '%real-estate%' or 
		ticker like '%resources%' or 
		ticker like '%retail%' or 
		ticker like '%robot%' or 
		ticker like '%semiconductor%' or 
		ticker like '%software%' or 
		ticker like '%solar%' or 
		ticker like '%tech%' or 
		ticker like '%telecom%' or 
		ticker like '%transport%' or 
		ticker like '%trvl---leis.%' or 
		ticker like '%utilities%' or 
		ticker like '%water%'
	)
	and ticker not like '%2x%'
	and ticker not like '%3x%'
	and ticker not like '%4x%'
	and ticker not like '%leverage%'
	and ticker not like '%short%'
	and ticker not like '%long%'
	and ticker not like '%bear%'
	and ticker not like '%bull%'
	and ticker not like '%ultra%'
	and ticker not like '%double%'
	and ticker not like '%boost%'
	and ticker not like '%goldman%'
	and ticker not like '%golden%'
	and ticker not like '%daily%'
	and ticker not like '%eonia%'
	group by mercado, bolsa, indice, ticker
) 
as t1
where t1.fecha_ini < '2009-01-01'
order by t1.fecha_ini, t1.mercado, t1.bolsa, t1.indice, t1.ticker;
--
-- BUSQUEDA DE ETFS FILTRANDO POR TICKER Y FECHA
--
select t1.* from 
(
	select mercado, bolsa, indice, ticker, count(1) as num_dias, min(fecha) as fecha_ini, max(fecha) fecha_fin, round(avg(volumen)) vol_medio
	from public.mercados_investing
	where mercado = 'ETF' 
	and ticker not like '%2x%'
	and ticker not like '%3x%'
	and ticker not like '%4x%'
	and ticker not like '%leverage%'
	and ticker not like '%short%'
	and ticker not like '%long%'
	and ticker not like '%bear%'
	and ticker not like '%bull%'
	and ticker not like '%ultra%'
	and ticker not like '%double%'
	and ticker not like '%boost%'
	and ticker not like '%daily%'
	and ticker not like '%inverse%'
	group by mercado, bolsa, indice, ticker
) 
as t1
where t1.fecha_ini < '2010-01-01'
order by t1.vol_medio, t1.mercado, t1.bolsa, t1.indice, t1.ticker;
--
-- BUSQUEDA DE ETFS FILTRANDO POR TICKER Y FECHA
--
select t1.* from 
(
	select mercado, bolsa, indice, ticker, count(1) as num_dias, min(fecha) as fecha_ini, max(fecha) fecha_fin, round(avg(volumen)) vol_medio
	from public.mercados_investing
	where mercado = 'ETF' 
	and ticker not like '%2x%'
	and ticker not like '%3x%'
	and ticker not like '%4x%'
	and ticker not like '%leverage%'
	and ticker not like '%short%'
	and ticker not like '%long%'
	and ticker not like '%bear%'
	and ticker not like '%bull%'
	and ticker not like '%ultra%'
	and ticker not like '%double%'
	and ticker not like '%boost%'
	and ticker not like '%daily%'
	and ticker not like '%inverse%'
	and
	(
		ticker like '%sov%' or 
		ticker like '%health%' or 
		ticker like '%travel%' or 
		ticker like '%retail%' or 
		ticker like '%telecom%' or 
		ticker like '%media%' or 
		ticker like '%inflation%' or 
		ticker like '%ftse%' or 
		ticker like '%smi%' or 
		ticker like '%infr%' or 
		ticker like '%germany%' or 
		ticker like '%gold%' or 
		ticker like '%precious%' or 
		ticker like '%zinc%' or 
		ticker like '%metal%' or 
		ticker like '%reit%' or 
		ticker like '%wind%' or 
		ticker like '%clean%' or 
		ticker like '%tips%' or 
		ticker like '%treasury%' or 
		ticker like '%bond%' or 
		ticker like '%gov%' or 
		ticker like '%govern%' or 
		ticker like '%pharma%' or 
		ticker like '%energy%' or 
		ticker like '%oil%' or 
		ticker like '%corp%' or 
		ticker like '%estate%' or 
		ticker like '%tr.%' or 
		ticker like '%tre.%' or 
		ticker like '%biotech%' or 
		ticker like '%solar%' or 
		ticker like '%prop%' or 
		ticker like '%mortgage%'	
	)
	group by mercado, bolsa, indice, ticker
) 
as t1
where t1.fecha_ini < '2010-01-01'
order by t1.vol_medio, t1.mercado, t1.bolsa, t1.indice, t1.ticker;
--
-- BUSQUEDA DE ETFS FILTRANDO POR TICKER Y FECHA
--
select t1.* from 
(
	select mercado, bolsa, indice, ticker, count(1) as num_dias, min(fecha) as fecha_ini, max(fecha) fecha_fin, round(avg(volumen)) vol_medio
	from public.mercados_investing
	where mercado = 'ETF' 
	and ticker not like '%2x%'
	and ticker not like '%3x%'
	and ticker not like '%4x%'
	and ticker not like '%leverage%'
	and ticker not like '%short%'
	and ticker not like '%long%'
	and ticker not like '%bear%'
	and ticker not like '%bull%'
	and ticker not like '%ultra%'
	and ticker not like '%double%'
	and ticker not like '%boost%'
	and ticker not like '%daily%'
	and ticker not like '%inverse%'
	and ticker not like '%goldman%'
	and
	(
		ticker like '%sov%' or 
		ticker like '%health%' or 
		ticker like '%retail%' or 
		ticker like '%telecom%' or 
		ticker like '%infr%' or 
		ticker like '%germany%' or 
		ticker like '%gold%' or 
		ticker like '%precious%' or 
		ticker like '%metal%' or 
		ticker like '%reit%' or 
		ticker like '%clean%' or 
		ticker like '%treasury%' or 
		ticker like '%bond%' or 
		ticker like '%gov%' or 
		ticker like '%pharma%' or 
		ticker like '%energy%' or 
		ticker like '%estate%' or 
		ticker like '%biotech%' or 
		ticker like '%solar%' or 
		ticker like '%wind%' or
		ticker like '%water%'
	)
	group by mercado, bolsa, indice, ticker
) 
as t1
where t1.fecha_ini < '2010-01-01'
order by t1.vol_medio, t1.mercado, t1.bolsa, t1.indice, t1.ticker;
--
-- 
--