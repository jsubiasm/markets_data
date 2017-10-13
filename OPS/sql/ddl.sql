--
-- TABLA MERCADOS
--
CREATE TABLE public.mercados (
	mercado varchar NOT NULL,
	bolsa varchar NOT NULL,
	indice varchar NOT NULL,
	ticker varchar NOT NULL,
	fecha date NOT NULL,
	maximo numeric NOT NULL,
	minimo numeric NOT NULL,
	cierre numeric NOT NULL,
	volumen numeric NOT NULL
)
WITH (
	OIDS=FALSE
) ;
create
    index mercados_bolsa_idx on
    mercados
        using btree(bolsa) ;
create
    index mercados_fecha_idx on
    mercados
        using btree(fecha) ;
create
    index mercados_indice_idx on
    mercados
        using btree(indice) ;
create
    index mercados_mercado_idx on
    mercados
        using btree(mercado) ;
create
    index mercados_ticker_idx on
    mercados
        using btree(ticker) ;
create
    unique index mercados_unique_idx on
    mercados
        using btree(
        mercado,
        bolsa,
        indice,
        ticker,
        fecha
    ) ;
--
-- CLONACIÓN DE LA TABLA MERCADOS DESTINADA A REALIZAR CONSULTAS CON SUBCONJUNTOS DE LA TABLA MERCADOS
--
CREATE TABLE public.tmp_data (
	mercado varchar NOT NULL,
	bolsa varchar NOT NULL,
	indice varchar NOT NULL,
	ticker varchar NOT NULL,
	fecha date NOT NULL,
	maximo numeric NOT NULL,
	minimo numeric NOT NULL,
	cierre numeric NOT NULL,
	volumen numeric NOT NULL
)
WITH (
	OIDS=FALSE
) ;
create
    index tmp_data_bolsa_idx on
    tmp_data
        using btree(bolsa) ;
create
    index tmp_data_fecha_idx on
    tmp_data
        using btree(fecha) ;
create
    index tmp_data_indice_idx on
    tmp_data
        using btree(indice) ;
create
    index tmp_data_mercado_idx on
    tmp_data
        using btree(mercado) ;
create
    index tmp_data_ticker_idx on
    tmp_data
        using btree(ticker) ;
create
    unique index tmp_data_unique_idx on
    tmp_data
        using btree(
        mercado,
        bolsa,
        indice,
        ticker,
        fecha
    ) ;
--
-- INSERTA DATOS EN TMP_DATA A PARTIR DE MERCADOS FILTRANDO POR FECHAS
--
delete from public.tmp_data;
insert into public.tmp_data select * from public.mercados where fecha > '2015-01-01' and fecha < '2015-12-31'; 
select count(1) from public.tmp_data;
--
-- 
--