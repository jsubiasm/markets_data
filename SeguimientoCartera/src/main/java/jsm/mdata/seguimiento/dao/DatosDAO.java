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

import jsm.mdata.seguimiento.dto.MovimientoDTO;
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
	public static final List<MovimientoDTO> selectMovimientos(Connection connection) throws Throwable
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
	public static final ProductoDTO selectProducto(Connection connection, String identificador) throws Throwable
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

}
