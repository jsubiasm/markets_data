--
-- MOSTRAMOS TODOS LOS VALORES ALMACENADOS, EL NUMERO DE REGISTROS Y LOS INTERVALOS DE ALMACENAMIENTO PARA CADA UNO DE ELLOS
--
select mercado, bolsa, indice, ticker, count(1) as num_dias, min(fecha) as fecha_ini, max(fecha) fecha_fin
from public.mercados
group by mercado, bolsa, indice, ticker order by fecha_ini, mercado, bolsa, indice, ticker;
--
-- MODO RUDIMENTARIO DE SACAR VARIACION ENTRE DIAS (HAY QUE MEJORARLO)
--
select totals.*, 
(
	(totals.hoy*100/totals.enero)-100
)as var_percent
from 
(
select m1.mercado, m1.bolsa, m1.indice, m1.ticker, 
count(1) as num_dias, min(m1.fecha) as fecha_ini, max(m1.fecha) fecha_fin, 
(
	select m3.cierre from public.mercados m3 where
	m3.mercado = m1.mercado and
	m3.bolsa = m1.bolsa and
	m3.indice = m1.indice and
	m3.ticker = m1.ticker and
	m3.fecha = '2017-10-03'
)
as enero,
(
	select m2.cierre from public.mercados m2 where
	m2.mercado = m1.mercado and
	m2.bolsa = m1.bolsa and
	m2.indice = m1.indice and
	m2.ticker = m1.ticker and
	m2.fecha = max(m1.fecha)
) as hoy
from public.mercados m1
group by m1.mercado, m1.bolsa, m1.indice, m1.ticker order by m1.mercado, m1.bolsa, m1.indice, m1.ticker
) as totals where totals.indice = 'IBEX-35' order by var_percent desc;
--
--
--