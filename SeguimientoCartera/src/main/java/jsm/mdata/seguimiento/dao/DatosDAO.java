/**
 * 
 */
package jsm.mdata.seguimiento.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jsm.mdata.seguimiento.dto.GanPerProdPesoDTO;
import jsm.mdata.seguimiento.dto.MovimientoDTO;
import jsm.mdata.seguimiento.dto.PrecioDTO;
import jsm.mdata.seguimiento.dto.ProductoDTO;

/**
 * @author jsubiasm
 *
 */
public class DatosDAO
{

	/**
	 * Logger
	 */
	private final static Logger LOGGER = LoggerFactory.getLogger(DatosDAO.class);

	/**
	 * @param connection
	 * @return
	 * @throws Throwable
	 */
	public static final List<MovimientoDTO> select_TB02_MOVIMIENTOS(Connection connection) throws Throwable
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<MovimientoDTO> listaMovimientos = new ArrayList<MovimientoDTO>();
		try
		{
			LOGGER.debug("Abriendo Sentencia");
			statement = connection.prepareStatement("SELECT MOVIMIENTO_ID, PRODUCTO_ID, COMPRA_VENTA, FECHA, NUMERO_TITULOS, PRECIO_TITULO, COMISION, TOTAL, COMERCIALIZADOR, MERCADO FROM TB02_MOVIMIENTOS");
			LOGGER.debug("Ejecutando Sentencia");
			resultSet = statement.executeQuery();
			LOGGER.debug("Abriendo Cursor");
			while (resultSet.next())
			{
				MovimientoDTO dto = new MovimientoDTO();
				dto.setComercializador(resultSet.getString("COMERCIALIZADOR"));
				dto.setComision(resultSet.getBigDecimal("COMISION"));
				dto.setCompraVenta(resultSet.getString("COMPRA_VENTA"));
				dto.setFecha(resultSet.getDate("FECHA"));
				dto.setMercado(resultSet.getString("MERCADO"));
				dto.setMovimientoId(resultSet.getInt("MOVIMIENTO_ID"));
				dto.setNumeroTitulos(resultSet.getBigDecimal("NUMERO_TITULOS"));
				dto.setPrecioTitulo(resultSet.getBigDecimal("PRECIO_TITULO"));
				dto.setProductoId(resultSet.getString("PRODUCTO_ID"));
				dto.setTotal(resultSet.getBigDecimal("TOTAL"));
				listaMovimientos.add(dto);
			}
		}
		catch (Throwable t)
		{
			LOGGER.error("ERROR", t);
			throw t;
		}
		finally
		{
			LOGGER.debug("Cerrando Cursor");
			resultSet.close();
			LOGGER.debug("Cerrando Sentencia");
			statement.close();
		}
		return listaMovimientos;
	}

	/**
	 * @param connection
	 * @param identificador
	 * @return
	 * @throws Throwable
	 */
	public static final ProductoDTO select_TB02_PRODUCTOS(Connection connection, String identificador) throws Throwable
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		ProductoDTO producto = null;
		try
		{
			LOGGER.debug("Abriendo Sentencia");
			statement = connection.prepareStatement("SELECT IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, URL_SCRAPING FROM TB02_PRODUCTOS WHERE IDENTIFICADOR = ?");
			statement.setString(1, identificador);
			LOGGER.debug("Ejecutando Sentencia");
			resultSet = statement.executeQuery();
			LOGGER.debug("Abriendo Cursor");
			if (resultSet.next())
			{
				producto = new ProductoDTO();
				producto.setIdentificador(resultSet.getString("IDENTIFICADOR"));
				producto.setInstrumento(resultSet.getString("INSTRUMENTO"));
				producto.setMoneda(resultSet.getString("MONEDA"));
				producto.setNombre(resultSet.getString("NOMBRE"));
				producto.setProveedor(resultSet.getString("PROVEEDOR"));
				producto.setSubtipoActivo(resultSet.getString("SUBTIPO_ACTIVO"));
				producto.setTipoActivo(resultSet.getString("TIPO_ACTIVO"));
				producto.setUrlScraping(resultSet.getString("URL_SCRAPING"));
				producto.setUsoIngresos(resultSet.getString("USO_INGRESOS"));
			}
		}
		catch (Throwable t)
		{
			LOGGER.error("ERROR", t);
			throw t;
		}
		finally
		{
			LOGGER.debug("Cerrando Cursor");
			resultSet.close();
			LOGGER.debug("Cerrando Sentencia");
			statement.close();
		}
		return producto;
	}

	/**
	 * @param connection
	 * @param productoId
	 * @return
	 * @throws Throwable
	 */
	public static final PrecioDTO select_TB02_PRECIOS(Connection connection, String productoId) throws Throwable
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		PrecioDTO precio = null;
		try
		{
			LOGGER.debug("Abriendo Sentencia");
			statement = connection.prepareStatement("SELECT PRODUCTO_ID, VALOR_TITULO, FECHA_VALOR, ULTIMA_ACTUALIZACION FROM TB02_PRECIOS WHERE PRODUCTO_ID = ?");
			statement.setString(1, productoId);
			LOGGER.debug("Ejecutando Sentencia");
			resultSet = statement.executeQuery();
			LOGGER.debug("Abriendo Cursor");
			if (resultSet.next())
			{
				precio = new PrecioDTO();
				precio.setFechaValor(resultSet.getDate("FECHA_VALOR"));
				precio.setProductoId(resultSet.getString("PRODUCTO_ID"));
				precio.setUltimaActualizacion(resultSet.getDate("ULTIMA_ACTUALIZACION"));
				precio.setValorTitulo(resultSet.getBigDecimal("VALOR_TITULO"));
			}
		}
		catch (Throwable t)
		{
			LOGGER.error("ERROR", t);
			throw t;
		}
		finally
		{
			LOGGER.debug("Cerrando Cursor");
			resultSet.close();
			LOGGER.debug("Cerrando Sentencia");
			statement.close();
		}
		return precio;
	}

	/**
	 * @param connection
	 * @return
	 * @throws Throwable
	 */
	public static final List<GanPerProdPesoDTO> select_VW03_GAN_PER_PROD_PESO(Connection connection) throws Throwable
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<GanPerProdPesoDTO> listaGanPerProdPeso = new ArrayList<GanPerProdPesoDTO>();
		try
		{
			LOGGER.debug("Abriendo Sentencia");
			statement = connection.prepareStatement("SELECT PRODUCTO_ID, NOMBRE, COMERCIALIZADOR, MERCADO, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, TITULOS_COMPRADOS, PRECIO_TITULOS_COMPRADOS, TITULOS_VENDIDOS, PRECIO_TITULOS_VENDIDOS, TITULOS_ACTUALES, VALOR_TITULO, VALOR_TITULOS_ACTUALES, GANANCIA_PERDIDA, GANANCIA_PERDIDA_PRCNT, PESO_EN_CARTERA FROM VW03_GAN_PER_PROD_PESO");
			LOGGER.debug("Ejecutando Sentencia");
			resultSet = statement.executeQuery();
			LOGGER.debug("Abriendo Cursor");
			while (resultSet.next())
			{
				GanPerProdPesoDTO dto = new GanPerProdPesoDTO();
				dto.setComercializador(resultSet.getString("COMERCIALIZADOR"));
				dto.setGananciaPerdida(resultSet.getBigDecimal("GANANCIA_PERDIDA"));
				dto.setGananciaPerdidaPrcnt(resultSet.getBigDecimal("GANANCIA_PERDIDA_PRCNT"));
				dto.setInstrumento(resultSet.getString("INSTRUMENTO"));
				dto.setMercado(resultSet.getString("MERCADO"));
				dto.setMoneda(resultSet.getString("MONEDA"));
				dto.setNombre(resultSet.getString("NOMBRE"));
				dto.setPesoEnCartera(resultSet.getBigDecimal("PESO_EN_CARTERA"));
				dto.setPrecioTitulosComprados(resultSet.getBigDecimal("PRECIO_TITULOS_COMPRADOS"));
				dto.setPrecioTitulosVendidos(resultSet.getBigDecimal("PRECIO_TITULOS_VENDIDOS"));
				dto.setProductoId(resultSet.getString("PRODUCTO_ID"));
				dto.setProveedor(resultSet.getString("PROVEEDOR"));
				dto.setSubtipoActivo(resultSet.getString("SUBTIPO_ACTIVO"));
				dto.setTipoActivo(resultSet.getString("TIPO_ACTIVO"));
				dto.setTitulosActuales(resultSet.getBigDecimal("TITULOS_ACTUALES"));
				dto.setTitulosComprados(resultSet.getBigDecimal("TITULOS_COMPRADOS"));
				dto.setTitulosVendidos(resultSet.getBigDecimal("TITULOS_VENDIDOS"));
				dto.setUsoIngresos(resultSet.getString("USO_INGRESOS"));
				dto.setValorTitulo(resultSet.getBigDecimal("VALOR_TITULO"));
				dto.setValorTitulosActuales(resultSet.getBigDecimal("VALOR_TITULOS_ACTUALES"));
				listaGanPerProdPeso.add(dto);
			}
		}
		catch (Throwable t)
		{
			LOGGER.error("ERROR", t);
			throw t;
		}
		finally
		{
			LOGGER.debug("Cerrando Cursor");
			resultSet.close();
			LOGGER.debug("Cerrando Sentencia");
			statement.close();
		}
		return listaGanPerProdPeso;
	}

}
