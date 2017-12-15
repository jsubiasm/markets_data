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
select t1.*,
(
	select t3.cierre from public.mercados_investing t3 
	where t3.mercado = t1.mercado and t3.bolsa = t1.bolsa and t3.indice = t1.indice 
	and t3.ticker = t1.ticker and t3.fecha = t1.fecha_fin
) 
as cierre_fin,
(
	select round(avg(t2.volumen)) from public.mercados_investing t2 
	where t2.mercado = t1.mercado and t2.bolsa = t1.bolsa and t2.indice = t1.indice 
	and t2.ticker = t1.ticker and t2.fecha > (t1.fecha_fin - interval '1 month')
) 
as vol_med_ult_mes
from
(
	select t0.mercado, t0.bolsa, t0.indice, t0.ticker, count(1) as num_dias, min(t0.fecha) as fecha_ini, max(t0.fecha) fecha_fin
	from public.mercados_investing t0
	where t0.mercado = 'ETF'
	and t0.ticker not like '%2x%'
	and t0.ticker not like '%3x%'
	and t0.ticker not like '%4x%'
	and t0.ticker not like '%leverage%'
	and t0.ticker not like '%short%'
	and t0.ticker not like '%long%'
	and t0.ticker not like '%bear%'
	and t0.ticker not like '%bull%'
	and t0.ticker not like '%ultra%'
	and t0.ticker not like '%double%'
	and t0.ticker not like '%boost%'
	and t0.ticker not like '%daily%'
	and t0.ticker not like '%inverse%'
	group by t0.mercado, t0.bolsa, t0.indice, t0.ticker
) 
as t1
where t1.fecha_ini < '2014-01-01'
order by t1.mercado, t1.bolsa, t1.indice, t1.ticker;
--
-- BUSQUEDA DE ETFS SOBRE BONOS
--
select t1.*,
(
	select t3.cierre from public.mercados_investing t3 
	where t3.mercado = t1.mercado and t3.bolsa = t1.bolsa and t3.indice = t1.indice 
	and t3.ticker = t1.ticker and t3.fecha = t1.fecha_fin
) 
as cierre_fin,
(
	select round(avg(t2.volumen)) from public.mercados_investing t2 
	where t2.mercado = t1.mercado and t2.bolsa = t1.bolsa and t2.indice = t1.indice 
	and t2.ticker = t1.ticker and t2.fecha > (t1.fecha_fin - interval '1 month')
) 
as vol_med_ult_mes
from
(
	select t0.mercado, t0.bolsa, t0.indice, t0.ticker, count(1) as num_dias, min(t0.fecha) as fecha_ini, max(t0.fecha) fecha_fin
	from public.mercados_investing t0
	where t0.mercado = 'ETF'
	and t0.ticker not like '%2x%'
	and t0.ticker not like '%3x%'
	and t0.ticker not like '%4x%'
	and t0.ticker not like '%leverage%'
	and t0.ticker not like '%short%'
	and t0.ticker not like '%long%'
	and t0.ticker not like '%bear%'
	and t0.ticker not like '%bull%'
	and t0.ticker not like '%ultra%'
	and t0.ticker not like '%double%'
	and t0.ticker not like '%boost%'
	and t0.ticker not like '%daily%'
	and t0.ticker not like '%inverse%'
	and
	(
		t0.ticker like '%sov%' or
		t0.ticker like '%bond%' or
		t0.ticker like '%gov%' or
		t0.ticker like '%tr.%' or
		t0.ticker like '%tre.%' or 
		t0.ticker like '%treas%'
	)
	group by t0.mercado, t0.bolsa, t0.indice, t0.ticker
) 
as t1
where t1.fecha_ini < '2014-01-01'
order by t1.mercado, t1.bolsa, t1.indice, t1.ticker;
--
-- BUSQUEDA DE ETFS SOBRE ACCIONES (STOXX Y SP)
--
select t1.*,
(
	select t3.cierre from public.mercados_investing t3 
	where t3.mercado = t1.mercado and t3.bolsa = t1.bolsa and t3.indice = t1.indice 
	and t3.ticker = t1.ticker and t3.fecha = t1.fecha_fin
) 
as cierre_fin,
(
	select round(avg(t2.volumen)) from public.mercados_investing t2 
	where t2.mercado = t1.mercado and t2.bolsa = t1.bolsa and t2.indice = t1.indice 
	and t2.ticker = t1.ticker and t2.fecha > (t1.fecha_fin - interval '1 month')
) 
as vol_med_ult_mes
from
(
	select t0.mercado, t0.bolsa, t0.indice, t0.ticker, count(1) as num_dias, min(t0.fecha) as fecha_ini, max(t0.fecha) fecha_fin
	from public.mercados_investing t0
	where t0.mercado = 'ETF'
	and t0.ticker not like '%2x%'
	and t0.ticker not like '%3x%'
	and t0.ticker not like '%4x%'
	and t0.ticker not like '%leverage%'
	and t0.ticker not like '%short%'
	and t0.ticker not like '%long%'
	and t0.ticker not like '%bear%'
	and t0.ticker not like '%bull%'
	and t0.ticker not like '%ultra%'
	and t0.ticker not like '%double%'
	and t0.ticker not like '%boost%'
	and t0.ticker not like '%daily%'
	and t0.ticker not like '%inverse%'
	and
	(
		t0.ticker like '%stoxx%' or 
		t0.ticker like '%djsu%' or 
		t0.ticker like '%-sector-%' or 
		t0.ticker like '%-s-p-%'
	)
	group by t0.mercado, t0.bolsa, t0.indice, t0.ticker
) 
as t1
where t1.fecha_ini < '2010-01-01'
order by t1.mercado, t1.bolsa, t1.indice, t1.ticker;
--
-- BUSQUEDA DE ETFS SOBRE ACCIONES POR ACTIVIDAD (EXCLUYE STOXX Y SP)
--
select t1.*,
(
	select t3.cierre from public.mercados_investing t3 
	where t3.mercado = t1.mercado and t3.bolsa = t1.bolsa and t3.indice = t1.indice 
	and t3.ticker = t1.ticker and t3.fecha = t1.fecha_fin
) 
as cierre_fin,
(
	select round(avg(t2.volumen)) from public.mercados_investing t2 
	where t2.mercado = t1.mercado and t2.bolsa = t1.bolsa and t2.indice = t1.indice 
	and t2.ticker = t1.ticker and t2.fecha > (t1.fecha_fin - interval '1 month')
) 
as vol_med_ult_mes
from
(
	select t0.mercado, t0.bolsa, t0.indice, t0.ticker, count(1) as num_dias, min(t0.fecha) as fecha_ini, max(t0.fecha) fecha_fin
	from public.mercados_investing t0
	where t0.mercado = 'ETF'
	and t0.ticker not like '%2x%'
	and t0.ticker not like '%3x%'
	and t0.ticker not like '%4x%'
	and t0.ticker not like '%leverage%'
	and t0.ticker not like '%short%'
	and t0.ticker not like '%long%'
	and t0.ticker not like '%bear%'
	and t0.ticker not like '%bull%'
	and t0.ticker not like '%ultra%'
	and t0.ticker not like '%double%'
	and t0.ticker not like '%boost%'
	and t0.ticker not like '%daily%'
	and t0.ticker not like '%inverse%'
	and t0.ticker not like '%stoxx%'
	and t0.ticker not like '%djsu%'
	and t0.ticker not like '%-sector-%'
	and t0.ticker not like '%-s-p-%'
	and
	(
		t0.ticker like '%aero%' or 
		t0.ticker like '%auto%' or 
		t0.ticker like '%banking%' or 
		t0.ticker like '%banks%' or 
		t0.ticker like '%basic%' or 
		t0.ticker like '%bever%' or 
		t0.ticker like '%biot%' or 
		t0.ticker like '%broker%' or 
		t0.ticker like '%builder%' or 
		t0.ticker like '%capital%' or 
		t0.ticker like '%chemic%' or 
		t0.ticker like '%clean%' or 
		t0.ticker like '%construc%' or 
		t0.ticker like '%consum%' or 
		t0.ticker like '%energy%' or 
		t0.ticker like '%estate%' or 
		t0.ticker like '%financ%' or 
		t0.ticker like '%food%' or 
		t0.ticker like '%gold%' or 
		t0.ticker like '%goods%' or 
		t0.ticker like '%health%' or 
		t0.ticker like '%home%' or 
		t0.ticker like '%indus%' or 
		t0.ticker like '%infr%' or 
		t0.ticker like '%insuran%' or 
		t0.ticker like '%material%' or 
		t0.ticker like '%media%' or 
		t0.ticker like '%medic%' or 
		t0.ticker like '%metal%' or 
		t0.ticker like '%pharma%' or 
		t0.ticker like '%reit%' or 
		t0.ticker like '%resourc%' or 
		t0.ticker like '%retail%' or 
		t0.ticker like '%robot%' or 
		t0.ticker like '%semicond%' or 
		t0.ticker like '%softw%' or 
		t0.ticker like '%solar%' or 
		t0.ticker like '%tech%' or 
		t0.ticker like '%telecom%' or 
		t0.ticker like '%transport%' or 
		t0.ticker like '%travel%' or 
		t0.ticker like '%utilit%' or 
		t0.ticker like '%water%' or 
		t0.ticker like '%wind%'
	)
	group by t0.mercado, t0.bolsa, t0.indice, t0.ticker
) 
as t1
where t1.fecha_ini < '2010-01-01'
order by t1.mercado, t1.bolsa, t1.indice, t1.ticker;
--
-- BUSQUEDA DE ETFS SOBRE ACCIONES EXCLUYENDO ACTIVIDADES (TAMBIÉN EXCLUYE TODAS LAS ANTERIORES)
--
select t1.*,
(
	select t3.cierre from public.mercados_investing t3 
	where t3.mercado = t1.mercado and t3.bolsa = t1.bolsa and t3.indice = t1.indice 
	and t3.ticker = t1.ticker and t3.fecha = t1.fecha_fin
) 
as cierre_fin,
(
	select round(avg(t2.volumen)) from public.mercados_investing t2 
	where t2.mercado = t1.mercado and t2.bolsa = t1.bolsa and t2.indice = t1.indice 
	and t2.ticker = t1.ticker and t2.fecha > (t1.fecha_fin - interval '1 month')
) 
as vol_med_ult_mes
from
(
	select t0.mercado, t0.bolsa, t0.indice, t0.ticker, count(1) as num_dias, min(t0.fecha) as fecha_ini, max(t0.fecha) fecha_fin
	from public.mercados_investing t0
	where t0.mercado = 'ETF'
	and t0.ticker not like '%2x%'
	and t0.ticker not like '%3x%'
	and t0.ticker not like '%4x%'
	and t0.ticker not like '%leverage%'
	and t0.ticker not like '%short%'
	and t0.ticker not like '%long%'
	and t0.ticker not like '%bear%'
	and t0.ticker not like '%bull%'
	and t0.ticker not like '%ultra%'
	and t0.ticker not like '%double%'
	and t0.ticker not like '%boost%'
	and t0.ticker not like '%daily%'
	and t0.ticker not like '%inverse%'
	and t0.ticker not like '%sov%'
	and t0.ticker not like '%bond%'
	and t0.ticker not like '%bund%'
	and t0.ticker not like '%gov%'
	and t0.ticker not like '%tr.%'
	and t0.ticker not like '%tre.%'
	and t0.ticker not like '%treas%'
	and t0.ticker not like '%german%'
	and t0.ticker not like '%ger.%'
	and t0.ticker not like '%eonia%'
	and t0.ticker not like '%small%'
	and t0.ticker not like '%smlcap%'
	and t0.ticker not like '%large%'
	and t0.ticker not like '%midcap%'
	and t0.ticker not like '%mid-cap%'
	and t0.ticker not like '%dividend%'
	and t0.ticker not like '%month%'
	and t0.ticker not like '%year%'
	and t0.ticker not like '%value%'
	and t0.ticker not like '%growth%'
	and t0.ticker not like '%covered%'
	and t0.ticker not like '%pure-beta%'
	and t0.ticker not like '%coffee%'
	and t0.ticker not like '%corn%'
	and t0.ticker not like '%cotton%'
	and t0.ticker not like '%grains%'
	and t0.ticker not like '%gasoline%'
	and t0.ticker not like '%heating%'
	and t0.ticker not like '%cocoa%'
	and t0.ticker not like '%lean-hogs%'
	and t0.ticker not like '%cattle%'
	and t0.ticker not like '%natural-gas%'
	and t0.ticker not like '%sugar%'
	and t0.ticker not like '%crude%'
	and t0.ticker not like '%soybean%'
	and t0.ticker not like '%wheat%'
	and t0.ticker not like '%wti%'
	and t0.ticker not like '%inflation%'
	and t0.ticker not like '%corp%'
	and t0.ticker not like '%commod%'
	and t0.ticker not like '%strong%'
	and t0.ticker not like '%invest-grade%'
	and t0.ticker not like '%topix%'
	and t0.ticker not like '%agric%'
	and t0.ticker not like '%agri.%'
	and t0.ticker not like '%softs%'
	and t0.ticker not like '%currency%'
	and t0.ticker not like '%-term-%'
	and t0.ticker not like '%-mid-%'
	and t0.ticker not like '%-cap-%'
	and t0.ticker not like '%stoxx%'
	and t0.ticker not like '%djsu%'
	and t0.ticker not like '%-sector-%'
	and t0.ticker not like '%-s-p-%'	
	and t0.ticker not like '%aero%'
	and t0.ticker not like '%auto%'
	and t0.ticker not like '%banking%'
	and t0.ticker not like '%banks%'
	and t0.ticker not like '%basic%'
	and t0.ticker not like '%bever%'
	and t0.ticker not like '%biot%'
	and t0.ticker not like '%broker%'
	and t0.ticker not like '%builder%'
	and t0.ticker not like '%capital%'
	and t0.ticker not like '%chemic%'
	and t0.ticker not like '%clean%'
	and t0.ticker not like '%construc%'
	and t0.ticker not like '%consum%'
	and t0.ticker not like '%energy%'
	and t0.ticker not like '%estate%'
	and t0.ticker not like '%financ%'
	and t0.ticker not like '%food%'
	and t0.ticker not like '%gold%'
	and t0.ticker not like '%goods%'
	and t0.ticker not like '%health%'
	and t0.ticker not like '%home%'
	and t0.ticker not like '%indus%'
	and t0.ticker not like '%infr%'
	and t0.ticker not like '%insuran%'
	and t0.ticker not like '%material%'
	and t0.ticker not like '%media%'
	and t0.ticker not like '%medic%'
	and t0.ticker not like '%metal%'
	and t0.ticker not like '%pharma%'
	and t0.ticker not like '%reit%'
	and t0.ticker not like '%resourc%'
	and t0.ticker not like '%retail%'
	and t0.ticker not like '%robot%'
	and t0.ticker not like '%semicond%'
	and t0.ticker not like '%softw%'
	and t0.ticker not like '%solar%'
	and t0.ticker not like '%tech%'
	and t0.ticker not like '%telecom%'
	and t0.ticker not like '%transport%'
	and t0.ticker not like '%travel%'
	and t0.ticker not like '%utilit%'
	and t0.ticker not like '%water%'
	and t0.ticker not like '%wind%'	
	group by t0.mercado, t0.bolsa, t0.indice, t0.ticker
) 
as t1
where t1.fecha_ini < '2010-01-01'
order by t1.mercado, t1.bolsa, t1.indice, t1.ticker;
--
-- BUSQUEDA DE ETFS SOBRE ACCIONES ORDENADO POR INCREMENTO DE VOLUMEN DE LOS ULTIMOS MESES
--
select t2.*,
(
	case 
		when (t2.vol_med_ult_2_meses > 0) and (t2.vol_med_ult_4_anios > 0) 
		then round( ((t2.vol_med_ult_2_meses - t2.vol_med_ult_4_anios) * 100) / t2.vol_med_ult_4_anios , 2)
		else 0
	end
) 
as var_volumen_medio
from 
(
	select t1.*,
	(
		select t3.cierre from public.mercados_investing t3 
		where t3.mercado = t1.mercado and t3.bolsa = t1.bolsa and t3.indice = t1.indice 
		and t3.ticker = t1.ticker and t3.fecha = t1.fecha_fin
	) 
	as cierre_fin,
		(
		select round(avg(t2.volumen)) from public.mercados_investing t2 
		where t2.mercado = t1.mercado and t2.bolsa = t1.bolsa and t2.indice = t1.indice 
		and t2.ticker = t1.ticker and t2.fecha > (t1.fecha_fin - interval '4 years')
	) 
	as vol_med_ult_4_anios,
	(
		select round(avg(t2.volumen)) from public.mercados_investing t2 
		where t2.mercado = t1.mercado and t2.bolsa = t1.bolsa and t2.indice = t1.indice 
		and t2.ticker = t1.ticker and t2.fecha > (t1.fecha_fin - interval '2 months')
	) 
	as vol_med_ult_2_meses
	from
	(
		select t0.mercado, t0.bolsa, t0.indice, t0.ticker, count(1) as num_dias, min(t0.fecha) as fecha_ini, max(t0.fecha) fecha_fin
		from public.mercados_investing t0
		where t0.mercado = 'ETF' 
		and t0.ticker not like '%2x%'
		and t0.ticker not like '%3x%'
		and t0.ticker not like '%4x%'
		and t0.ticker not like '%leverage%'
		and t0.ticker not like '%short%'
		and t0.ticker not like '%long%'
		and t0.ticker not like '%bear%'
		and t0.ticker not like '%bull%'
		and t0.ticker not like '%ultra%'
		and t0.ticker not like '%double%'
		and t0.ticker not like '%boost%'
		and t0.ticker not like '%inverse%'	
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