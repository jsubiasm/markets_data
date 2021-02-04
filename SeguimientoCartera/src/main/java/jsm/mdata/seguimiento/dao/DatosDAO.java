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
			LOGGER.info("Abriendo Sentencia");
			statement = connection.prepareStatement("SELECT MOVIMIENTO_ID, PRODUCTO_ID, COMPRA_VENTA, FECHA, NUMERO_TITULOS, PRECIO_TITULO, COMISION, TOTAL, COMERCIALIZADOR, MERCADO from TB02_MOVIMIENTOS");
			LOGGER.info("Ejecutando Sentencia");
			resultSet = statement.executeQuery();
			LOGGER.info("Abriendo Cursor");
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
				dto.setProducto(resultSet.getString("PRODUCTO_ID"));
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
			LOGGER.info("Cerrando Cursor");
			resultSet.close();
			LOGGER.info("Cerrando Sentencia");
			statement.close();
		}
		return listaMovimientos;
	}

}
