--
-- TICKER LIKE
--
or ticker like '%aero%'
or ticker like '%auto%'
or ticker like '%banking%'
or ticker like '%banks%'
or ticker like '%basic%'
or ticker like '%bever%'
or ticker like '%biot%'
or ticker like '%broker%'
or ticker like '%builder%'
or ticker like '%canada%'
or ticker like '%capital%'
or ticker like '%chemic%'
or ticker like '%china%'
or ticker like '%clean%'
or ticker like '%construc%'
or ticker like '%consum%'
or ticker like '%defen%'
or ticker like '%denmark%'
or ticker like '%energy%'
or ticker like '%estate%'
or ticker like '%financ%'
or ticker like '%finland%'
or ticker like '%food%'
or ticker like '%gold%'
or ticker like '%goods%'
or ticker like '%health%'
or ticker like '%home%'
or ticker like '%india%'
or ticker like '%indus%'
or ticker like '%infr%'
or ticker like '%insuran%'
or ticker like '%japan%'
or ticker like '%leis%'
or ticker like '%material%'
or ticker like '%media%'
or ticker like '%medic%'
or ticker like '%metal%'
or ticker like '%miner%'
or ticker like '%minin%'
or ticker like '%norway%'
or ticker like '%pharma%'
or ticker like '%reit%'
or ticker like '%resourc%'
or ticker like '%retail%'
or ticker like '%robot%'
or ticker like '%russia%'
or ticker like '%semicond%'
or ticker like '%softw%'
or ticker like '%solar%'
or ticker like '%sweden%'
or ticker like '%tech%'
or ticker like '%telecom%'
or ticker like '%transport%'
or ticker like '%travel%'
or ticker like '%trvl%'
or ticker like '%utilit%'
or ticker like '%water%'
or ticker like '%wind%'
--
-- TICKER NOT LIKE
--
and ticker not like '%2x%'
and ticker not like '%3x%'
and ticker not like '%4x%'
and ticker not like '%5x%'
and ticker not like '%agri%'
and ticker not like '%bear%'
and ticker not like '%boost%'
and ticker not like '%brent%'
and ticker not like '%bull%'
and ticker not like '%cattle%'
and ticker not like '%cocoa%'
and ticker not like '%coffee%'
and ticker not like '%commod%'
and ticker not like '%corn%'
and ticker not like '%cotton%'
and ticker not like '%crude%'
and ticker not like '%double%'
and ticker not like '%gasoline%'
and ticker not like '%grains%'
and ticker not like '%heating%'
and ticker not like '%inverse%'
and ticker not like '%lean-hogs%'
and ticker not like '%lever%'
and ticker not like '%long%'
and ticker not like '%natural-gas%'
and ticker not like '%short%'
and ticker not like '%softs%'
and ticker not like '%soybean%'
and ticker not like '%sugar%'
and ticker not like '%ultra%'
and ticker not like '%wheat%'
and ticker not like '%wti%'
--
-- MOSTRAMOS TODOS LOS VALORES ALMACENADOS, EL NUMERO DE REGISTROS Y LOS INTERVALOS DE ALMACENAMIENTO PARA CADA UNO DE ELLOS
--
select mercado, bolsa, indice, ticker, count(1) as num_dias, min(fecha) as fecha_ini, max(fecha) fecha_fin
from public.mercados_investing_equity_etf
group by mercado, bolsa, indice, ticker order by fecha_fin, mercado, bolsa, indice, ticker;
--
-- MUESTRA VARIACIÓN DE PRECIO DE CIERRE ENTRE DOS FECHAS CONCRETAS ESPECIFICADAS (AMBAS FECHAS TIENEN QUE CONTENER DATOS)
--
select m4.*, 
round( ((m4.precio_final - m4.precio_inicial) * 100) / m4.precio_inicial , 2) as var_precio 
from
(
	select distinct m5.mercado, m5.bolsa, m5.indice, m5.ticker,
	to_date('2014-09-22', 'yyyy-mm-dd') as fecha_inicial,
	(
		select m2.cierre from public.mercados_investing_equity_etf m2 
		where m2.mercado = m5.mercado and m2.bolsa = m5.bolsa and m2.indice = m5.indice 
		and m2.ticker = m5.ticker and m2.fecha = to_date('2014-09-22', 'yyyy-mm-dd') 
	) 
	as precio_inicial,
	to_date('2014-10-06', 'yyyy-mm-dd') as fecha_final,
	(
		select m3.cierre from public.mercados_investing_equity_etf m3 
		where m3.mercado = m5.mercado and m3.bolsa = m5.bolsa and m3.indice = m5.indice 
		and m3.ticker = m5.ticker and m3.fecha = to_date('2014-10-06', 'yyyy-mm-dd') 
	) 
	as precio_final
	from
	(
		select m1.mercado, m1.bolsa, m1.indice, m1.ticker, min(m1.fecha) as fecha_inicial, max(m1.fecha) as fecha_final
		from public.mercados_investing_equity_etf m1 
		where m1.mercado = 'ETF'
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
		select m5.cierre from public.mercados_investing_equity_etf m5 
		where m5.mercado = m4.mercado and m5.bolsa = m4.bolsa and m5.indice = m4.indice 
		and m5.ticker = m4.ticker and m5.fecha = m4.fecha_inicial 
	) 
	as precio_inicial,
	(
		select m6.cierre from public.mercados_investing_equity_etf m6 
		where m6.mercado = m4.mercado and m6.bolsa = m4.bolsa and m6.indice = m4.indice 
		and m6.ticker = m4.ticker and m6.fecha = m4.fecha_final
	) 
	as precio_final
	from
	(
		select m1.mercado, m1.bolsa, m1.indice, m1.ticker, min(m1.fecha) as fecha_inicial, max(m1.fecha) as fecha_final
		from public.mercados_investing_equity_etf m1  
		where m1.mercado = 'ETF'
		and m1.fecha between '2014-01-01' and '2014-12-31'
		group by m1.mercado, m1.bolsa, m1.indice, m1.ticker
	)
	as m4
)
as m7 order by var_precio desc;
--
-- MUESTRA VARIACIÓN DEL ULTIMO VOLUMEN DIARIO CON RESPECTO AL VOLUMEN MEDIO
--
select m4.*,
round( ((m4.ultimo_vol - m4.avg_vol) * 100) / m4.avg_vol , 2) as var_volumen 
from
(
	select m2.*,
	(
		select m3.volumen from public.mercados_investing_equity_etf m3 
		where m3.mercado = m2.mercado and m3.bolsa = m2.bolsa and m3.indice = m2.indice 
		and m3.ticker = m2.ticker and m3.fecha = m2.ultima_fecha
	) as ultimo_vol
	from 
	(
		select m1.mercado, m1.bolsa, m1.indice, m1.ticker, max(fecha) as ultima_fecha, round(avg(volumen)) as avg_vol
		from public.mercados_investing_equity_etf m1 
		where m1.mercado = 'ETF'
		group by m1.mercado, m1.bolsa, m1.indice, m1.ticker
	)
	as m2 where m2.avg_vol > 0
) 
as m4 order by var_volumen desc;
--
-- MUESTRA VARIACIÓN DEL ULTIMO PRECIO CON RESPECTO A LOS PRECIOS MÍNIMOS Y MÁXIMOS
--
select m4.*,
round( ((m4.ultimo_precio - m4.precio_maximo) * 100) / m4.precio_maximo , 2) as var_maximo,  
round( ((m4.ultimo_precio - m4.precio_minimo) * 100) / m4.precio_minimo , 2) as var_minimo          
from
(
	select m2.*,
	(
		select m3.cierre from public.mercados_investing_equity_etf m3 
		where m3.mercado = m2.mercado and m3.bolsa = m2.bolsa and m3.indice = m2.indice 
		and m3.ticker = m2.ticker and m3.fecha = m2.ultima_fecha
	) as ultimo_precio
	from 
	(
		select m1.mercado, m1.bolsa, m1.indice, m1.ticker, 
			   min(m1.fecha) as primera_fecha, max(m1.fecha) as ultima_fecha, 
			   min(m1.cierre) as precio_minimo, max(m1.cierre) as precio_maximo 
		from public.mercados_investing_equity_etf m1 
		where m1.mercado = 'ETF' 
		group by m1.mercado, m1.bolsa, m1.indice, m1.ticker
	)
	as m2 where m2.primera_fecha < '2014-01-01'
) 
as m4 order by var_maximo desc, var_minimo desc;
--
-- MUESTRA VARIACIÓN ENTRE LOS PRECIOS MÍNIMO Y MÁXIMO (O MÁXIMO Y MÍNIMO SI LA TENDENCIA ES BAJISTA) EN UN INTERVALO DE FECHAS
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
		select max(m3.fecha) from public.mercados_investing_equity_etf m3 
		where m3.mercado = m2.mercado and m3.bolsa = m2.bolsa and m3.indice = m2.indice 
		and m3.ticker = m2.ticker and m3.cierre = m2.precio_minimo
		and m3.fecha between '2014-01-01' and '2014-12-31'
	) as precio_minimo_fecha,
	(
		select max(m4.fecha) from public.mercados_investing_equity_etf m4 
		where m4.mercado = m2.mercado and m4.bolsa = m2.bolsa and m4.indice = m2.indice 
		and m4.ticker = m2.ticker and m4.cierre = m2.precio_maximo
		and m4.fecha between '2014-01-01' and '2014-12-31'
	) as precio_maximo_fecha
	from 
	(
		select m1.mercado, m1.bolsa, m1.indice, m1.ticker, min(m1.cierre) as precio_minimo, max(m1.cierre) as precio_maximo 
		from public.mercados_investing_equity_etf m1 
		where m1.mercado = 'ETF'
		and m1.fecha between '2014-01-01' and '2014-12-31'
		group by m1.mercado, m1.bolsa, m1.indice, m1.ticker
	) as m2
) as m5 order by var_precio desc;
--
-- MUESTRA EL VOLUMEN MEDIO DEL ULTIMO MES
--
select t1.*,
(
	select t3.cierre from public.mercados_investing_equity_etf t3 
	where t3.mercado = t1.mercado and t3.bolsa = t1.bolsa and t3.indice = t1.indice 
	and t3.ticker = t1.ticker and t3.fecha = t1.fecha_fin
) 
as cierre_fin,
(
	select round(avg(t2.volumen)) from public.mercados_investing_equity_etf t2 
	where t2.mercado = t1.mercado and t2.bolsa = t1.bolsa and t2.indice = t1.indice 
	and t2.ticker = t1.ticker and t2.fecha > (t1.fecha_fin - interval '1 month')
) 
as vol_med_ult_mes
from
(
	select t0.mercado, t0.bolsa, t0.indice, t0.ticker, count(1) as num_dias, min(t0.fecha) as fecha_ini, max(t0.fecha) fecha_fin
	from public.mercados_investing_equity_etf t0
	where t0.mercado = 'ETF'
	group by t0.mercado, t0.bolsa, t0.indice, t0.ticker
) 
as t1
where t1.fecha_ini < '2014-01-01'
order by t1.mercado, t1.bolsa, t1.indice, t1.ticker;
--
-- MUESTRA EL INCREMENTO DE VOLUMEN DE LOS ULTIMOS MESES CON RESPECTO AL DE LOS ULTIMOS AÑOS
--
select t2.*,
(
	case 
		when (t2.vol_med_ult_4_meses > 0) and (t2.vol_med_ult_4_anios > 0) 
		then round( ((t2.vol_med_ult_4_meses - t2.vol_med_ult_4_anios) * 100) / t2.vol_med_ult_4_anios , 2)
		else 0
	end
) 
as var_volumen_medio
from 
(
	select t1.*,
	(
		select t3.cierre from public.mercados_investing_equity_etf t3 
		where t3.mercado = t1.mercado and t3.bolsa = t1.bolsa and t3.indice = t1.indice 
		and t3.ticker = t1.ticker and t3.fecha = t1.fecha_fin
	) 
	as cierre_fin,
		(
		select round(avg(t2.volumen)) from public.mercados_investing_equity_etf t2 
		where t2.mercado = t1.mercado and t2.bolsa = t1.bolsa and t2.indice = t1.indice 
		and t2.ticker = t1.ticker and t2.fecha > (t1.fecha_fin - interval '4 years')
	) 
	as vol_med_ult_4_anios,
	(
		select round(avg(t2.volumen)) from public.mercados_investing_equity_etf t2 
		where t2.mercado = t1.mercado and t2.bolsa = t1.bolsa and t2.indice = t1.indice 
		and t2.ticker = t1.ticker and t2.fecha > (t1.fecha_fin - interval '4 months')
	) 
	as vol_med_ult_4_meses
	from
	(
		select t0.mercado, t0.bolsa, t0.indice, t0.ticker, count(1) as num_dias, min(t0.fecha) as fecha_ini, max(t0.fecha) fecha_fin
		from public.mercados_investing_equity_etf t0
		where t0.mercado = 'ETF' 
		group by t0.mercado, t0.bolsa, t0.indice, t0.ticker
	) 
	as t1
	where t1.fecha_ini < (current_date - interval '4 years')
)
as t2
order by var_volumen_medio desc, t2.mercado, t2.bolsa, t2.indice, t2.ticker;
--
-- 
--