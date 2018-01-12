--
-- TABLA MERCADOS INVESTING ETFS SOBRE ACCIONES
--
CREATE TABLE public.mercados_investing_equity_etf (
	mercado varchar NOT NULL,
	bolsa varchar NOT NULL,
	indice varchar NOT NULL,
	ticker varchar NOT NULL,
	fecha date NOT NULL,
	apertura numeric NOT NULL,
	maximo numeric NOT NULL,
	minimo numeric NOT NULL,
	cierre numeric NOT NULL,
	volumen numeric NOT NULL
)
WITH (
	OIDS=FALSE
) ;
create
    index mercados_investing_equity_etf_bolsa_idx on
    mercados_investing_equity_etf
        using btree(bolsa) ;
create
    index mercados_investing_equity_etf_fecha_idx on
    mercados_investing_equity_etf
        using btree(fecha) ;
create
    index mercados_investing_equity_etf_indice_idx on
    mercados_investing_equity_etf
        using btree(indice) ;
create
    index mercados_investing_equity_etf_mercado_idx on
    mercados_investing_equity_etf
        using btree(mercado) ;
create
    index mercados_investing_equity_etf_ticker_idx on
    mercados_investing_equity_etf
        using btree(ticker) ;
create
    unique index mercados_investing_equity_etf_unique_idx on
    mercados_investing_equity_etf
        using btree(
        mercado,
        bolsa,
        indice,
        ticker,
        fecha
    ) ;
--
-- TABLA MERCADOS INVESTING
--
CREATE TABLE public.mercados_investing (
	mercado varchar NOT NULL,
	bolsa varchar NOT NULL,
	indice varchar NOT NULL,
	ticker varchar NOT NULL,
	fecha date NOT NULL,
	apertura numeric NOT NULL,
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
	apertura numeric NOT NULL,
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
-- 
--