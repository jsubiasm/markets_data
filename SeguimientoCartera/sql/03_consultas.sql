SELECT TOTALES.PRODUCTO_ID, TOTALES.NOMBRE, TOTALES.DESEMBOLSOS, TOTALES.TITULOS_COMPRADOS, TOTALES.REEMBOLSOS, TOTALES.TITULOS_VENDIDOS, (TOTALES.TITULOS_COMPRADOS - TOTALES.TITULOS_VENDIDOS) AS TITULOS_ACTUALES, PRECIOS.VALOR_TITULO FROM (
	SELECT COMPRAS.PRODUCTO_ID, COMPRAS.NOMBRE, COMPRAS.DESEMBOLSOS, COMPRAS.TITULOS_COMPRADOS, VENTAS.REEMBOLSOS, VENTAS.TITULOS_VENDIDOS FROM (
		SELECT PRODUCTO_ID, NOMBRE, SUM((NUMERO_TITULOS * PRECIO_TITULO) + COMISION) AS DESEMBOLSOS, SUM(NUMERO_TITULOS) AS TITULOS_COMPRADOS FROM VIEW_MOVIMIENTO_PRODUCTO WHERE COMPRA_VENTA = 'Compra' GROUP BY PRODUCTO_ID, NOMBRE
	) COMPRAS
	LEFT OUTER JOIN (
		SELECT PRODUCTO_ID, NOMBRE, SUM((NUMERO_TITULOS * PRECIO_TITULO) + COMISION) AS REEMBOLSOS, SUM(NUMERO_TITULOS) AS TITULOS_VENDIDOS FROM VIEW_MOVIMIENTO_PRODUCTO WHERE COMPRA_VENTA = 'Venta' GROUP BY PRODUCTO_ID, NOMBRE
	) VENTAS
	ON COMPRAS.PRODUCTO_ID = VENTAS.PRODUCTO_ID AND COMPRAS.NOMBRE = VENTAS.NOMBRE
) TOTALES
INNER JOIN SCRAP_PRECIOS PRECIOS 
ON TOTALES.PRODUCTO_ID = PRECIOS.PRODUCTO_ID;