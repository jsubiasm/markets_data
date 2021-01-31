--
--
--
-- connect 'jdbc:derby:C:\\_JSM\\SeguimientoCartera\\03_Fuentes\\markets_data\\SeguimientoCartera\\derby\\seguimiento_cartera;create=true';
-- connect 'jdbc:derby:C:\\_JSM\\SeguimientoCartera\\03_Fuentes\\markets_data\\SeguimientoCartera\\derby\\seguimiento_cartera';
-- run 'C:\_JSM\SeguimientoCartera\03_Fuentes\markets_data\SeguimientoCartera\sql\seguimiento_cartera.sql';
--
--
--
AUTOCOMMIT OFF;
--
--
--
DROP TABLE SCRAP_PRECIOS;
DROP TABLE MASTER_MOVIMIENTOS;
DROP TABLE MASTER_PRODUCTOS;
DROP TABLE MASTER_PROVEEDOR;
DROP TABLE MASTER_INSTRUMENTO;
DROP TABLE MASTER_TIPO_ACTIVO;
DROP TABLE MASTER_SUBTIPO_ACTIVO;
DROP TABLE MASTER_MONEDA;
DROP TABLE MASTER_USO_INGRESOS;
DROP TABLE MASTER_COMPRA_VENTA;
DROP TABLE MASTER_COMERCIALIZADOR;
DROP TABLE MASTER_MERCADO;
--
--
--
CREATE TABLE MASTER_PROVEEDOR
(
	CLAVE INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	VALOR VARCHAR(50) NOT NULL
);
ALTER TABLE MASTER_PROVEEDOR ADD CONSTRAINT MASTER_PROVEEDOR_VALOR_PK Primary Key (VALOR);
ALTER TABLE MASTER_PROVEEDOR ADD CONSTRAINT MASTER_PROVEEDOR_CLAVE_UNQ Unique (CLAVE);
--
--
--
INSERT INTO MASTER_PROVEEDOR (VALOR) VALUES ('Amundi');
INSERT INTO MASTER_PROVEEDOR (VALOR) VALUES ('Deka');
INSERT INTO MASTER_PROVEEDOR (VALOR) VALUES ('Invesco');
INSERT INTO MASTER_PROVEEDOR (VALOR) VALUES ('Lyxor');
INSERT INTO MASTER_PROVEEDOR (VALOR) VALUES ('Vanguard');
INSERT INTO MASTER_PROVEEDOR (VALOR) VALUES ('WisdomTree');
INSERT INTO MASTER_PROVEEDOR (VALOR) VALUES ('DBC');
INSERT INTO MASTER_PROVEEDOR (VALOR) VALUES ('iShares');
--
--
--
CREATE TABLE MASTER_INSTRUMENTO
(
	CLAVE INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	VALOR VARCHAR(50) NOT NULL
);
ALTER TABLE MASTER_INSTRUMENTO ADD CONSTRAINT MASTER_INSTRUMENTO_VALOR_PK Primary Key (VALOR);
ALTER TABLE MASTER_INSTRUMENTO ADD CONSTRAINT MASTER_INSTRUMENTO_CLAVE_UNQ Unique (CLAVE);
--
--
--
INSERT INTO MASTER_INSTRUMENTO (VALOR) VALUES ('Fondo');
INSERT INTO MASTER_INSTRUMENTO (VALOR) VALUES ('ETF');
INSERT INTO MASTER_INSTRUMENTO (VALOR) VALUES ('ETC');
--
--
--
CREATE TABLE MASTER_TIPO_ACTIVO
(
	CLAVE INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	VALOR VARCHAR(50) NOT NULL
);
ALTER TABLE MASTER_TIPO_ACTIVO ADD CONSTRAINT MASTER_TIPO_ACTIVO_VALOR_PK Primary Key (VALOR);
ALTER TABLE MASTER_TIPO_ACTIVO ADD CONSTRAINT MASTER_TIPO_ACTIVO_CLAVE_UNQ Unique (CLAVE);
--
--
--
INSERT INTO MASTER_TIPO_ACTIVO (VALOR) VALUES ('Renta Variable');
INSERT INTO MASTER_TIPO_ACTIVO (VALOR) VALUES ('Renta Fija');
INSERT INTO MASTER_TIPO_ACTIVO (VALOR) VALUES ('Oro');
--
--
--
CREATE TABLE MASTER_SUBTIPO_ACTIVO
(
	CLAVE INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	VALOR VARCHAR(50) NOT NULL
);
ALTER TABLE MASTER_SUBTIPO_ACTIVO ADD CONSTRAINT MASTER_SUBTIPO_ACTIVO_VALOR_PK Primary Key (VALOR);
ALTER TABLE MASTER_SUBTIPO_ACTIVO ADD CONSTRAINT MASTER_SUBTIPO_ACTIVO_CLAVE_UNQ Unique (CLAVE);
--
--
--
INSERT INTO MASTER_SUBTIPO_ACTIVO (VALOR) VALUES ('Oro');
INSERT INTO MASTER_SUBTIPO_ACTIVO (VALOR) VALUES ('RF Alemania LP');
INSERT INTO MASTER_SUBTIPO_ACTIVO (VALOR) VALUES ('RF Europa BLI');
INSERT INTO MASTER_SUBTIPO_ACTIVO (VALOR) VALUES ('RF Europa LP');
INSERT INTO MASTER_SUBTIPO_ACTIVO (VALOR) VALUES ('RF Europa MP');
INSERT INTO MASTER_SUBTIPO_ACTIVO (VALOR) VALUES ('RF Global MP');
INSERT INTO MASTER_SUBTIPO_ACTIVO (VALOR) VALUES ('RF USA MP');
INSERT INTO MASTER_SUBTIPO_ACTIVO (VALOR) VALUES ('RV Emergente');
INSERT INTO MASTER_SUBTIPO_ACTIVO (VALOR) VALUES ('RV Europa');
INSERT INTO MASTER_SUBTIPO_ACTIVO (VALOR) VALUES ('RV Global');
INSERT INTO MASTER_SUBTIPO_ACTIVO (VALOR) VALUES ('RV Japon');
INSERT INTO MASTER_SUBTIPO_ACTIVO (VALOR) VALUES ('RV Norteamerica');
INSERT INTO MASTER_SUBTIPO_ACTIVO (VALOR) VALUES ('RV Pacifico');
INSERT INTO MASTER_SUBTIPO_ACTIVO (VALOR) VALUES ('RV USA');
--
--
--
CREATE TABLE MASTER_MONEDA
(
	CLAVE INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	VALOR VARCHAR(50) NOT NULL
);
ALTER TABLE MASTER_MONEDA ADD CONSTRAINT MASTER_MONEDA_VALOR_PK Primary Key (VALOR);
ALTER TABLE MASTER_MONEDA ADD CONSTRAINT MASTER_MONEDA_CLAVE_UNQ Unique (CLAVE);
--
--
--
INSERT INTO MASTER_MONEDA (VALOR) VALUES ('EUR');
--
--
--
CREATE TABLE MASTER_USO_INGRESOS
(
	CLAVE INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	VALOR VARCHAR(50) NOT NULL
);
ALTER TABLE MASTER_USO_INGRESOS ADD CONSTRAINT MASTER_USO_INGRESOS_VALOR_PK Primary Key (VALOR);
ALTER TABLE MASTER_USO_INGRESOS ADD CONSTRAINT MASTER_USO_INGRESOS_CLAVE_UNQ Unique (CLAVE);
--
--
--
INSERT INTO MASTER_USO_INGRESOS (VALOR) VALUES ('Acumulacion');
INSERT INTO MASTER_USO_INGRESOS (VALOR) VALUES ('Distribucion');
INSERT INTO MASTER_USO_INGRESOS (VALOR) VALUES ('N/A');
--
--
--
CREATE TABLE MASTER_COMPRA_VENTA
(
	CLAVE INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	VALOR VARCHAR(25) NOT NULL
);
ALTER TABLE MASTER_COMPRA_VENTA ADD CONSTRAINT MASTER_COMPRA_VENTA_VALOR_PK Primary Key (VALOR);
ALTER TABLE MASTER_COMPRA_VENTA ADD CONSTRAINT MASTER_COMPRA_VENTA_CLAVE_UNQ Unique (CLAVE);
--
--
--
INSERT INTO MASTER_COMPRA_VENTA (VALOR) VALUES ('Compra');
INSERT INTO MASTER_COMPRA_VENTA (VALOR) VALUES ('Venta');
--
--
--
CREATE TABLE MASTER_COMERCIALIZADOR
(
	CLAVE INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	VALOR VARCHAR(50) NOT NULL
);
ALTER TABLE MASTER_COMERCIALIZADOR ADD CONSTRAINT MASTER_COMERCIALIZADOR_VALOR_PK Primary Key (VALOR);
ALTER TABLE MASTER_COMERCIALIZADOR ADD CONSTRAINT MASTER_COMERCIALIZADOR_CLAVE_UNQ Unique (CLAVE);
--
--
--
INSERT INTO MASTER_COMERCIALIZADOR (VALOR) VALUES ('Openbank');
INSERT INTO MASTER_COMERCIALIZADOR (VALOR) VALUES ('MyInvestor');
INSERT INTO MASTER_COMERCIALIZADOR (VALOR) VALUES ('SelfBank');
INSERT INTO MASTER_COMERCIALIZADOR (VALOR) VALUES ('Degiro');
--
--
--
CREATE TABLE MASTER_MERCADO
(
	CLAVE INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	VALOR VARCHAR(50) NOT NULL
);
ALTER TABLE MASTER_MERCADO ADD CONSTRAINT MASTER_MERCADO_VALOR_PK Primary Key (VALOR);
ALTER TABLE MASTER_MERCADO ADD CONSTRAINT MASTER_MERCADO_CLAVE_UNQ Unique (CLAVE);
--
--
--
INSERT INTO MASTER_MERCADO (VALOR) VALUES ('Xetra');
INSERT INTO MASTER_MERCADO (VALOR) VALUES ('N/A');
--
--
--
CREATE TABLE MASTER_PRODUCTOS
(
	IDENTIFICADOR VARCHAR(50) NOT NULL,
	NOMBRE VARCHAR(100) NOT NULL,
	PROVEEDOR VARCHAR(50) NOT NULL,
	INSTRUMENTO VARCHAR(50) NOT NULL,
	TIPO_ACTIVO VARCHAR(50) NOT NULL,
	SUBTIPO_ACTIVO VARCHAR(50) NOT NULL,
	MONEDA VARCHAR(50) NOT NULL,
	USO_INGRESOS VARCHAR(50) NOT NULL,
	URL_SCRAPING VARCHAR(200) NOT NULL
);
ALTER TABLE MASTER_PRODUCTOS ADD CONSTRAINT MASTER_PRODUCTOS_IDENTIFICADOR_PK Primary Key (IDENTIFICADOR);
ALTER TABLE MASTER_PRODUCTOS ADD CONSTRAINT MASTER_PRODUCTOS_NOMBRE_UNQ Unique (NOMBRE);
ALTER TABLE MASTER_PRODUCTOS ADD CONSTRAINT MASTER_PRODUCTOS_PROVEEDOR_FK Foreign Key (PROVEEDOR) REFERENCES MASTER_PROVEEDOR (VALOR);
ALTER TABLE MASTER_PRODUCTOS ADD CONSTRAINT MASTER_PRODUCTOS_INSTRUMENTO_FK Foreign Key (INSTRUMENTO) REFERENCES MASTER_INSTRUMENTO (VALOR);
ALTER TABLE MASTER_PRODUCTOS ADD CONSTRAINT MASTER_PRODUCTOS_TIPO_ACTIVO_FK Foreign Key (TIPO_ACTIVO) REFERENCES MASTER_TIPO_ACTIVO (VALOR);
ALTER TABLE MASTER_PRODUCTOS ADD CONSTRAINT MASTER_PRODUCTOS_SUBTIPO_ACTIVO_FK Foreign Key (SUBTIPO_ACTIVO) REFERENCES MASTER_SUBTIPO_ACTIVO (VALOR);
ALTER TABLE MASTER_PRODUCTOS ADD CONSTRAINT MASTER_PRODUCTOS_MONEDA_FK Foreign Key (MONEDA) REFERENCES MASTER_MONEDA (VALOR);
ALTER TABLE MASTER_PRODUCTOS ADD CONSTRAINT MASTER_PRODUCTOS_USO_INGRESOS_FK Foreign Key (USO_INGRESOS) REFERENCES MASTER_USO_INGRESOS (VALOR);
--
--
--
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('LU0389812933','Amundi IS JP Morgan GBI Glbl Gvs AHE-C','Amundi','Fondo','Renta Fija','RF Global MP','EUR','Acumulacion','https://www.morningstar.es/es/funds/snapshot/p_snapshot.aspx?id=F000002871');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('DE000ETFL219','Deka Dt. Boerse EUROGOV Ger 10+ ETF','Deka','ETF','Renta Fija','RF Alemania LP','EUR','Distribucion','https://www.morningstar.es/es/etf/snapshot/p_snapshot.aspx?id=0P0000JNSO');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE00B579F325','Invesco Physical Gold ETC EUR','Invesco','ETC','Oro','Oro','EUR','N/A','https://www.morningstar.es/es/etf/snapshot/p_snapshot.aspx?id=0P0000WGWI');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('LU0908500753','Lyxor Core STOXX Europe 600(DR) ETF Acc EUR','Lyxor','ETF','Renta Variable','RV Europa','EUR','Acumulacion','https://www.morningstar.es/es/etf/snapshot/p_snapshot.aspx?id=0P0001BMZU');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE00B246KL88','Vanguard 20+ Yr Eur Trs Idx Eur Acc','Vanguard','Fondo','Renta Fija','RF Europa LP','EUR','Acumulacion','https://www.morningstar.es/es/funds/snapshot/p_snapshot.aspx?id=F000001GFI');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE0031786696','Vanguard Em Mkts Stk Idx Eur Acc','Vanguard','Fondo','Renta Variable','RV Emergente','EUR','Acumulacion','https://www.morningstar.es/es/funds/snapshot/p_snapshot.aspx?id=F00000T1HU');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE0031786142','Vanguard Emerg Mkts Stk Idx Inv EUR Acc','Vanguard','Fondo','Renta Variable','RV Emergente','EUR','Acumulacion','https://www.morningstar.es/es/funds/snapshot/p_snapshot.aspx?id=F0GBR06TSA');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE0007987690','Vanguard European Stock Idx Inv EUR Acc','Vanguard','Fondo','Renta Variable','RV Europa','EUR','Acumulacion','https://www.morningstar.es/es/funds/snapshot/p_snapshot.aspx?id=F0GBR04SKF');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE00BK5BQT80','Vanguard FTSE All-World ETF USD Acc EUR','Vanguard','ETF','Renta Variable','RV Global','EUR','Acumulacion','https://www.morningstar.es/es/etf/snapshot/p_snapshot.aspx?id=0P0001I3S0');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE00BK5BQZ41','Vanguard FTSE Dev AsiaPac exJpn ETF USD Acc EUR','Vanguard','ETF','Renta Variable','RV Pacifico','EUR','Acumulacion','https://www.morningstar.es/es/etf/snapshot/p_snapshot.aspx?id=0P0001IHVY');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE00BK5BR733','Vanguard FTSE Emerg Markets ETF USD Acc EUR','Vanguard','ETF','Renta Variable','RV Emergente','EUR','Acumulacion','https://www.morningstar.es/es/etf/snapshot/p_snapshot.aspx?id=0P0001IHW1');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE0007281425','Vanguard Japan Stock Index Inv EUR Acc','Vanguard','Fondo','Renta Variable','RV Japon','EUR','Acumulacion','https://www.morningstar.es/es/funds/snapshot/p_snapshot.aspx?id=F0GBR061V3');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE0007286036','Vanguard Jpn Stk Idx Eur Acc','Vanguard','Fondo','Renta Variable','RV Japon','EUR','Acumulacion','https://www.morningstar.es/es/funds/snapshot/p_snapshot.aspx?id=F0GBR061XN');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE0007201266','Vanguard Pac ex-Japan Stk Idx EUR Acc','Vanguard','Fondo','Renta Variable','RV Pacifico','EUR','Acumulacion','https://www.morningstar.es/es/funds/snapshot/p_snapshot.aspx?id=F00000T1I9');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE0032126645','Vanguard US 500 Stk Idx Eur Acc','Vanguard','Fondo','Renta Variable','RV USA','EUR','Acumulacion','https://www.morningstar.es/es/funds/snapshot/p_snapshot.aspx?id=F0GBR04UOL');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE0032620787','Vanguard US 500 Stock Index Inv EUR Acc','Vanguard','Fondo','Renta Variable','RV USA','EUR','Acumulacion','https://www.morningstar.es/es/funds/snapshot/p_snapshot.aspx?id=F0GBR04G0F');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE0007987708','Vanguard European Stk Idx Eur Acc','Vanguard','Fondo','Renta Variable','RV Europa','EUR','Acumulacion','https://www.morningstar.es/es/funds/snapshot/p_snapshot.aspx?id=F0GBR04SGM');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE00B04GQR24','Vanguard Eurozone InflLnkd Bd Idx Eur Acc','Vanguard','Fondo','Renta Fija','RF Europa BLI','EUR','Acumulacion','https://www.morningstar.es/es/funds/snapshot/p_snapshot.aspx?id=F0GBR05WKI');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('DE000A1DCTL3','WisdomTree Physical Swiss Gold ETC EUR','WisdomTree','ETC','Oro','Oro','EUR','N/A','https://www.morningstar.es/es/etf/snapshot/p_snapshot.aspx?id=0P0000NA52');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('DE000A0S9GB0','Xetra-Gold EUR','DBC','ETC','Oro','Oro','EUR','N/A','https://www.morningstar.es/es/etf/snapshot/p_snapshot.aspx?id=0P0000M7DL');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE00B4L5YX21','iShares Core MSCI Japan IMI ETF USD Acc EUR','iShares','ETF','Renta Variable','RV Japon','EUR','Acumulacion','https://www.morningstar.es/es/etf/snapshot/p_snapshot.aspx?id=0P0000MEHV');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE00B5BMR087','iShares Core SP 500 ETF USD Acc EUR','iShares','ETF','Renta Variable','RV USA','EUR','Acumulacion','https://www.morningstar.es/es/etf/snapshot/p_snapshot.aspx?id=0P0000OO21');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE00BDRK7R97','iShares Pacific Index (IE) D Acc EUR','iShares','Fondo','Renta Variable','RV Pacifico','EUR','Acumulacion','https://www.morningstar.es/es/funds/snapshot/p_snapshot.aspx?id=F00000Z2S4');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE00B4XCK338','iShares UltraHQ EurGovtBdIdx(IE) InstAcc Eur','iShares','Fondo','Renta Fija','RF Europa MP','EUR','Acumulacion','https://www.morningstar.es/es/funds/snapshot/p_snapshot.aspx?id=F00000OEDJ');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('DE000A0D8Q31','iShares eb.rexx GovtGer 10.5+yr (DE)','iShares','ETF','Renta Fija','RF Alemania LP','EUR','Distribucion','https://www.morningstar.es/es/etf/snapshot/p_snapshot.aspx?id=0P00001NR4');
INSERT INTO MASTER_PRODUCTOS (IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING) VALUES ('IE00BSKRJX20','iShares Euro Govt Bond 20y TgtDur ETF Eur Dist EUR','iShares','ETF','Renta Fija','RF Europa LP','EUR','Distribucion','https://www.morningstar.es/es/etf/snapshot/p_snapshot.aspx?id=0P00015OCO');
--
--
--
CREATE TABLE SCRAP_PRECIOS
(
	IDENTIFICADOR VARCHAR(50) NOT NULL,
	VALOR_TITULO DOUBLE PRECISION,
	FECHA_VALOR DATE,
	ULTIMA_ACTUALIZACION TIMESTAMP
);
ALTER TABLE SCRAP_PRECIOS ADD CONSTRAINT SCRAP_PRECIOS_IDENTIFICADOR_PK Primary Key (IDENTIFICADOR);
ALTER TABLE SCRAP_PRECIOS ADD CONSTRAINT SCRAP_PRECIOS_IDENTIFICADOR_FK Foreign Key (IDENTIFICADOR) REFERENCES MASTER_PRODUCTOS (IDENTIFICADOR);
--
--
--
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('LU0389812933');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('DE000ETFL219');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE00B579F325');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('LU0908500753');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE00B246KL88');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE0031786696');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE0031786142');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE0007987690');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE00BK5BQT80');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE00BK5BQZ41');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE00BK5BR733');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE0007281425');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE0007286036');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE0007201266');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE0032126645');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE0032620787');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE0007987708');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE00B04GQR24');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('DE000A1DCTL3');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('DE000A0S9GB0');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE00B4L5YX21');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE00B5BMR087');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE00BDRK7R97');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE00B4XCK338');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('DE000A0D8Q31');
INSERT INTO SCRAP_PRECIOS (IDENTIFICADOR) VALUES ('IE00BSKRJX20');
--
--
--
CREATE TABLE MASTER_MOVIMIENTOS
(
	MOVIMIENTO_ID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	IDENTIFICADOR VARCHAR(50) NOT NULL,
	NOMBRE VARCHAR(100) NOT NULL,
	COMPRA_VENTA VARCHAR(25) NOT NULL,
	FECHA DATE NOT NULL,
	NUMERO_TITULOS DOUBLE PRECISION NOT NULL,
	PRECIO_TITULO DOUBLE PRECISION NOT NULL,	
	COMISION DOUBLE PRECISION NOT NULL,	
	TOTAL DOUBLE PRECISION NOT NULL,
	COMERCIALIZADOR VARCHAR(50) NOT NULL,
	MERCADO VARCHAR(50) NOT NULL
);
ALTER TABLE MASTER_MOVIMIENTOS ADD CONSTRAINT MASTER_MOVIMIENTOS_MOVIMIENTO_ID_PK Primary Key (MOVIMIENTO_ID);
ALTER TABLE MASTER_MOVIMIENTOS ADD CONSTRAINT MASTER_MOVIMIENTOS_IDENTIFICADOR_FK Foreign Key (IDENTIFICADOR) REFERENCES MASTER_PRODUCTOS (IDENTIFICADOR);
ALTER TABLE MASTER_MOVIMIENTOS ADD CONSTRAINT MASTER_MOVIMIENTOS_COMPRA_VENTA_FK Foreign Key (COMPRA_VENTA) REFERENCES MASTER_COMPRA_VENTA (VALOR);
ALTER TABLE MASTER_MOVIMIENTOS ADD CONSTRAINT MASTER_MOVIMIENTOS_COMERCIALIZADOR_FK Foreign Key (COMERCIALIZADOR) REFERENCES MASTER_COMERCIALIZADOR (VALOR);
ALTER TABLE MASTER_MOVIMIENTOS ADD CONSTRAINT MASTER_MOVIMIENTOS_MERCADO_FK Foreign Key (MERCADO) REFERENCES MASTER_MERCADO (VALOR);
--
--
--
COMMIT;
