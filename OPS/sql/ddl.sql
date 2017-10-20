--
-- TABLA MERCADOS INVESTING
--
CREATE TABLE public.mercados_investing (
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
    index mercados_investing_bolsa_idx on
    mercados_investing
        using btree(bolsa) ;
create
    index mercados_investing_fecha_idx on
    mercados_investing
        using btree(fecha) ;
create
    index mercados_investing_indice_idx on
    mercados_investing
        using btree(indice) ;
create
    index mercados_investing_mercado_idx on
    mercados_investing
        using btree(mercado) ;
create
    index mercados_investing_ticker_idx on
    mercados_investing
        using btree(ticker) ;
create
    unique index mercados_investing_unique_idx on
    mercados_investing
        using btree(
        mercado,
        bolsa,
        indice,
        ticker,
        fecha
    ) ;
--
-- TABLA MERCADOS ELECONOMISTA
--
CREATE TABLE public.mercados_eleconomista (
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
    index mercados_eleconomista_bolsa_idx on
    mercados_eleconomista
        using btree(bolsa) ;
create
    index mercados_eleconomista_fecha_idx on
    mercados_eleconomista
        using btree(fecha) ;
create
    index mercados_eleconomista_indice_idx on
    mercados_eleconomista
        using btree(indice) ;
create
    index mercados_eleconomista_mercado_idx on
    mercados_eleconomista
        using btree(mercado) ;
create
    index mercados_eleconomista_ticker_idx on
    mercados_eleconomista
        using btree(ticker) ;
create
    unique index mercados_eleconomista_unique_idx on
    mercados_eleconomista
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
insert into public.tmp_data select * from public.mercados_eleconomista where fecha > '2015-01-01' and fecha < '2015-12-31'; 
select count(1) from public.tmp_data;
--
-- 
--