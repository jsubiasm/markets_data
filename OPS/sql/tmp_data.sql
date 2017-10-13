--
-- CLONACIÓN DE LA TABLA MERCADOS DESTINADA A REALIZAR CONSULTAS CON SUBCONJUNTOS DE DATOS DE LA TABLA MERCADOS
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
