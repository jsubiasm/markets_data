/**
 * 
 */
package jsm.mdata.seguimiento.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jsm.mdata.seguimiento.dto.GanPerProdPesoDTO;
import jsm.mdata.seguimiento.dto.MovimientoDTO;
import jsm.mdata.seguimiento.dto.ProductoDTO;
import jsm.mdata.seguimiento.dto.ProductoVarDTO;

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
	public static final List<ProductoDTO> select_TB02_PRODUCTOS(Connection connection, String identificador) throws Throwable
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<ProductoDTO> listaProductos = new ArrayList<ProductoDTO>();
		try
		{
			LOGGER.debug("Abriendo Sentencia");
			if (identificador != null)
			{
				statement = connection.prepareStatement("SELECT IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS FROM TB02_PRODUCTOS WHERE IDENTIFICADOR = ?");
				statement.setString(1, identificador);
			}
			else
			{
				statement = connection.prepareStatement("SELECT IDENTIFICADOR, NOMBRE, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS FROM TB02_PRODUCTOS");
			}
			LOGGER.debug("Ejecutando Sentencia");
			resultSet = statement.executeQuery();
			LOGGER.debug("Abriendo Cursor");
			while (resultSet.next())
			{
				ProductoDTO producto = new ProductoDTO();
				producto.setIdentificador(resultSet.getString("IDENTIFICADOR"));
				producto.setInstrumento(resultSet.getString("INSTRUMENTO"));
				producto.setMoneda(resultSet.getString("MONEDA"));
				producto.setNombre(resultSet.getString("NOMBRE"));
				producto.setProveedor(resultSet.getString("PROVEEDOR"));
				producto.setSubtipoActivo(resultSet.getString("SUBTIPO_ACTIVO"));
				producto.setTipoActivo(resultSet.getString("TIPO_ACTIVO"));
				producto.setUsoIngresos(resultSet.getString("USO_INGRESOS"));
				listaProductos.add(producto);
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
		return listaProductos;
	}

	/**
	 * @param connection
	 * @param productoId
	 * @return
	 * @throws Throwable
	 */
	public static final List<ProductoVarDTO> select_TB02_PRODUCTOS_VAR(Connection connection, String productoId) throws Throwable
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<ProductoVarDTO> listaProductoVar = new ArrayList<ProductoVarDTO>();
		try
		{
			LOGGER.debug("Abriendo Sentencia");
			if (productoId != null)
			{
				statement = connection.prepareStatement("SELECT PRODUCTO_ID, VALOR_TITULO, FECHA_VALOR, ULTIMA_ACTUALIZACION, URL_SCRAPING FROM TB02_PRODUCTOS_VAR WHERE PRODUCTO_ID = ?");
				statement.setString(1, productoId);
			}
			else
			{
				statement = connection.prepareStatement("SELECT PRODUCTO_ID, VALOR_TITULO, FECHA_VALOR, ULTIMA_ACTUALIZACION, URL_SCRAPING FROM TB02_PRODUCTOS_VAR");
			}
			LOGGER.debug("Ejecutando Sentencia");
			resultSet = statement.executeQuery();
			LOGGER.debug("Abriendo Cursor");
			while (resultSet.next())
			{
				ProductoVarDTO productoVar = new ProductoVarDTO();
				productoVar = new ProductoVarDTO();
				productoVar.setFechaValor(resultSet.getDate("FECHA_VALOR"));
				productoVar.setProductoId(resultSet.getString("PRODUCTO_ID"));
				productoVar.setUltimaActualizacion(resultSet.getDate("ULTIMA_ACTUALIZACION"));
				productoVar.setValorTitulo(resultSet.getBigDecimal("VALOR_TITULO"));
				productoVar.setUrlScraping(resultSet.getString("URL_SCRAPING"));
				listaProductoVar.add(productoVar);
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
		return listaProductoVar;
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
			statement = connection.prepareStatement("SELECT PRODUCTO_ID, NOMBRE, COMERCIALIZADOR, MERCADO, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, TITULOS_COMPRADOS, PRECIO_TITULOS_COMPRADOS, TITULOS_VENDIDOS, PRECIO_TITULOS_VENDIDOS, TITULOS_ACTUALES, VALOR_TITULO, VALOR_TITULOS_ACTUALES, FLUJO_CAJA, GANANCIA_PERDIDA, GANANCIA_PERDIDA_PRCNT, PESO_EN_CARTERA FROM VW03_GAN_PER_PROD_PESO");
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
				dto.setFlujoCaja(resultSet.getBigDecimal("FLUJO_CAJA"));
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

	/**
	 * @param connection
	 * @param nombreVista
	 * @return
	 * @throws Throwable
	 */
	public static final List<GanPerProdPesoDTO> select_VWF_nombreVista(Connection connection, String nombreVista) throws Throwable
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<GanPerProdPesoDTO> listaGanPerProdPeso = new ArrayList<GanPerProdPesoDTO>();
		try
		{
			LOGGER.debug("Abriendo Sentencia");
			if (nombreVista != null)
			{
				String campoVista = null;
				if ("COMERCIALIZADOR".equalsIgnoreCase(nombreVista))
				{
					campoVista = "\"Comercializador\"";
				}
				else if ("INSTRUMENTO".equalsIgnoreCase(nombreVista))
				{
					campoVista = "\"Instrumento\"";
				}
				else if ("MERCADO".equalsIgnoreCase(nombreVista))
				{
					campoVista = "\"Mercado\"";
				}
				else if ("MONEDA".equalsIgnoreCase(nombreVista))
				{
					campoVista = "\"Moneda\"";
				}
				else if ("PROVEEDOR".equalsIgnoreCase(nombreVista))
				{
					campoVista = "\"Proveedor\"";
				}
				else if ("SUBTIPO_ACTIVO".equalsIgnoreCase(nombreVista))
				{
					campoVista = "\"Subtipo Activo\"";
				}
				else if ("TIPO_ACTIVO".equalsIgnoreCase(nombreVista))
				{
					campoVista = "\"Tipo Activo\"";
				}
				else if ("USO_INGRESOS".equalsIgnoreCase(nombreVista))
				{
					campoVista = "\"Uso Ingresos\"";
				}
				else
				{
					throw new Exception("Nombre de vista inesperado [" + nombreVista + "]");
				}
				statement = connection.prepareStatement("SELECT " + campoVista + ", \"Prec. Tit. C.\", \"Prec. Tit. V.\", \"Val. Tit. Act.\", \"Flujo Caja\", \"Gan./Perd.\", \"Gan./Perd. %\", \"Peso %\" FROM VWF_" + nombreVista);
			}
			else
			{
				statement = connection.prepareStatement("SELECT \"Prec. Tit. C.\", \"Prec. Tit. V.\", \"Val. Tit. Act.\", \"Flujo Caja\", \"Gan./Perd.\", \"Gan./Perd. %\", \"Peso %\" FROM VWF_GAN_PER_PROD_PESO_TOTALES");
			}
			LOGGER.debug("Ejecutando Sentencia");
			resultSet = statement.executeQuery();
			LOGGER.debug("Abriendo Cursor");
			while (resultSet.next())
			{
				GanPerProdPesoDTO dto = new GanPerProdPesoDTO();
				if (nombreVista != null)
				{
					if ("COMERCIALIZADOR".equalsIgnoreCase(nombreVista))
					{
						dto.setComercializador(resultSet.getString("Comercializador"));
					}
					else if ("INSTRUMENTO".equalsIgnoreCase(nombreVista))
					{
						dto.setInstrumento(resultSet.getString("Instrumento"));
					}
					else if ("MERCADO".equalsIgnoreCase(nombreVista))
					{
						dto.setMercado(resultSet.getString("Mercado"));
					}
					else if ("MONEDA".equalsIgnoreCase(nombreVista))
					{
						dto.setMoneda(resultSet.getString("Moneda"));
					}
					else if ("PROVEEDOR".equalsIgnoreCase(nombreVista))
					{
						dto.setProveedor(resultSet.getString("Proveedor"));
					}
					else if ("SUBTIPO_ACTIVO".equalsIgnoreCase(nombreVista))
					{
						dto.setSubtipoActivo(resultSet.getString("Subtipo Activo"));
					}
					else if ("TIPO_ACTIVO".equalsIgnoreCase(nombreVista))
					{
						dto.setTipoActivo(resultSet.getString("Tipo Activo"));
					}
					else if ("USO_INGRESOS".equalsIgnoreCase(nombreVista))
					{
						dto.setUsoIngresos(resultSet.getString("Uso Ingresos"));
					}
					else
					{
						throw new Exception("Nombre de vista inesperado [" + nombreVista + "]");
					}
				}
				dto.setGananciaPerdida(resultSet.getBigDecimal("Gan./Perd."));
				dto.setGananciaPerdidaPrcnt(resultSet.getBigDecimal("Gan./Perd. %"));
				dto.setPesoEnCartera(resultSet.getBigDecimal("Peso %"));
				dto.setPrecioTitulosComprados(resultSet.getBigDecimal("Prec. Tit. C."));
				dto.setPrecioTitulosVendidos(resultSet.getBigDecimal("Prec. Tit. V."));
				dto.setFlujoCaja(resultSet.getBigDecimal("Flujo Caja"));
				dto.setValorTitulosActuales(resultSet.getBigDecimal("Val. Tit. Act."));
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

	/**
	 * @param connection
	 * @param productoVar
	 * @return
	 * @throws Throwable
	 */
	public static final int update_TB02_PRODUCTOS_VAR(Connection connection, ProductoVarDTO productoVar) throws Throwable
	{
		PreparedStatement statement = null;
		int rowsUpdated = 0;
		try
		{
			LOGGER.debug("Abriendo Sentencia");
			if (productoVar.getTer() != null && productoVar.getFechaTer() != null)
			{
				statement = connection.prepareStatement("UPDATE TB02_PRODUCTOS_VAR SET VALOR_TITULO = ?, FECHA_VALOR = ?, TER = ?, FECHA_TER = ?, ULTIMA_ACTUALIZACION = CURRENT_TIMESTAMP WHERE PRODUCTO_ID = ?");
				statement.setBigDecimal(1, productoVar.getValorTitulo());
				statement.setDate(2, new Date(productoVar.getFechaValor().getTime()));
				statement.setBigDecimal(3, productoVar.getTer());
				statement.setDate(4, new Date(productoVar.getFechaTer().getTime()));
				statement.setString(5, productoVar.getProductoId());
			}
			else
			{
				statement = connection.prepareStatement("UPDATE TB02_PRODUCTOS_VAR SET VALOR_TITULO = ?, FECHA_VALOR = ?, ULTIMA_ACTUALIZACION = CURRENT_TIMESTAMP WHERE PRODUCTO_ID = ?");
				statement.setBigDecimal(1, productoVar.getValorTitulo());
				statement.setDate(2, new Date(productoVar.getFechaValor().getTime()));
				statement.setString(3, productoVar.getProductoId());
			}
			LOGGER.debug("Ejecutando Sentencia");
			rowsUpdated = statement.executeUpdate();
		}
		catch (Throwable t)
		{
			LOGGER.error("ERROR", t);
			throw t;
		}
		finally
		{
			LOGGER.debug("Cerrando Sentencia");
			statement.close();
		}
		return rowsUpdated;
	}

}
