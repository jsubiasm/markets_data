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

import jsm.mdata.seguimiento.dto.EfectivoDTO;
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
	public static List<MovimientoDTO> select_TB02_MOVIMIENTOS(Connection connection) throws Throwable
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
	public static List<ProductoDTO> select_TB02_PRODUCTOS(Connection connection, String identificador) throws Throwable
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
	public static List<ProductoVarDTO> select_TB02_PRODUCTOS_VAR(Connection connection, String productoId) throws Throwable
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<ProductoVarDTO> listaProductoVar = new ArrayList<ProductoVarDTO>();
		try
		{
			LOGGER.debug("Abriendo Sentencia");
			if (productoId != null)
			{
				statement = connection.prepareStatement("SELECT PRODUCTO_ID, VALOR_TITULO, FECHA_VALOR, TER, FECHA_TER, ULTIMA_ACTUALIZACION, URL_SCRAPING FROM TB02_PRODUCTOS_VAR WHERE PRODUCTO_ID = ?");
				statement.setString(1, productoId);
			}
			else
			{
				statement = connection.prepareStatement("SELECT PRODUCTO_ID, VALOR_TITULO, FECHA_VALOR, TER, FECHA_TER, ULTIMA_ACTUALIZACION, URL_SCRAPING FROM TB02_PRODUCTOS_VAR");
			}
			LOGGER.debug("Ejecutando Sentencia");
			resultSet = statement.executeQuery();
			LOGGER.debug("Abriendo Cursor");
			while (resultSet.next())
			{
				ProductoVarDTO productoVar = new ProductoVarDTO();
				productoVar = new ProductoVarDTO();
				productoVar.setProductoId(resultSet.getString("PRODUCTO_ID"));
				productoVar.setValorTitulo(resultSet.getBigDecimal("VALOR_TITULO"));
				productoVar.setFechaValor(resultSet.getDate("FECHA_VALOR"));
				productoVar.setTer(resultSet.getBigDecimal("TER"));
				productoVar.setFechaTer(resultSet.getDate("FECHA_TER"));
				productoVar.setUltimaActualizacion(resultSet.getDate("ULTIMA_ACTUALIZACION"));
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
	public static List<GanPerProdPesoDTO> select_VW03_GAN_PER_PROD_PESO_sufijo(Connection connection, String sufijo) throws Throwable
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<GanPerProdPesoDTO> listaGanPerProdPeso = new ArrayList<GanPerProdPesoDTO>();
		try
		{
			LOGGER.debug("Abriendo Sentencia");
			String nombreVista = "VW03_GAN_PER_PROD_PESO_" + sufijo;
			statement = connection.prepareStatement("SELECT PRODUCTO_ID, NOMBRE, COMERCIALIZADOR, MERCADO, PROVEEDOR, INSTRUMENTO, TIPO_ACTIVO, SUBTIPO_ACTIVO, MONEDA, USO_INGRESOS, TER, TITULOS_COMPRADOS, PRECIO_TITULOS_COMPRADOS, TITULOS_VENDIDOS, PRECIO_TITULOS_VENDIDOS, TITULOS_ACTUALES, VALOR_TITULO, VALOR_TITULOS_ACTUALES, FLUJO_CAJA, GANANCIA_PERDIDA, GANANCIA_PERDIDA_PRCNT, PESO_EN_CARTERA FROM " + nombreVista);
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
				dto.setTer(resultSet.getBigDecimal("TER"));
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
	public static List<GanPerProdPesoDTO> select_VWF_nombreVista(Connection connection, String nombreVista) throws Throwable
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
				else if ("SUBTIPO_ACTIVO_GLOBAL".equalsIgnoreCase(nombreVista))
				{
					campoVista = "\"Subtipo Activo\"";
				}
				else if ("SUBTIPO_ACTIVO_ORO".equalsIgnoreCase(nombreVista))
				{
					campoVista = "\"Oro\"";
				}
				else if ("SUBTIPO_ACTIVO_RF".equalsIgnoreCase(nombreVista))
				{
					campoVista = "\"Renta Fija\"";
				}
				else if ("SUBTIPO_ACTIVO_RV".equalsIgnoreCase(nombreVista))
				{
					campoVista = "\"Renta Variable\"";
				}
				else if ("SUBTIPO_ACTIVO_CRIPTO".equalsIgnoreCase(nombreVista))
				{
					campoVista = "\"Criptomonedas\"";
				}
				else if ("SUBTIPO_ACTIVO_LIQUIDEZ".equalsIgnoreCase(nombreVista))
				{
					campoVista = "\"Liquidez\"";
				}
				else if ("SUBTIPO_ACTIVO_OTROS".equalsIgnoreCase(nombreVista))
				{
					campoVista = "\"Otros\"";
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
				statement = connection.prepareStatement("SELECT \"TER\", \"Prec. Tit. C.\", \"Prec. Tit. V.\", \"Val. Tit. Act.\", \"Flujo Caja\", \"Gan./Perd.\", \"Gan./Perd. %\", \"Peso %\" FROM VWF_GAN_PER_PROD_PESO_GLOBAL_TOTALES");
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
					else if ("SUBTIPO_ACTIVO_GLOBAL".equalsIgnoreCase(nombreVista))
					{
						dto.setSubtipoActivo(resultSet.getString("Subtipo Activo"));
					}
					else if ("SUBTIPO_ACTIVO_ORO".equalsIgnoreCase(nombreVista))
					{
						dto.setSubtipoActivo(resultSet.getString("Oro"));
					}
					else if ("SUBTIPO_ACTIVO_RF".equalsIgnoreCase(nombreVista))
					{
						dto.setSubtipoActivo(resultSet.getString("Renta Fija"));
					}
					else if ("SUBTIPO_ACTIVO_RV".equalsIgnoreCase(nombreVista))
					{
						dto.setSubtipoActivo(resultSet.getString("Renta Variable"));
					}
					else if ("SUBTIPO_ACTIVO_CRIPTO".equalsIgnoreCase(nombreVista))
					{
						dto.setSubtipoActivo(resultSet.getString("Criptomonedas"));
					}
					else if ("SUBTIPO_ACTIVO_LIQUIDEZ".equalsIgnoreCase(nombreVista))
					{
						dto.setSubtipoActivo(resultSet.getString("Liquidez"));
					}
					else if ("SUBTIPO_ACTIVO_OTROS".equalsIgnoreCase(nombreVista))
					{
						dto.setSubtipoActivo(resultSet.getString("Otros"));
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
				else
				{
					dto.setTer(resultSet.getBigDecimal("TER"));
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
	public static int update_TB02_PRODUCTOS_VAR(Connection connection, ProductoVarDTO productoVar) throws Throwable
	{
		PreparedStatement statement = null;
		int rowsUpdated = 0;
		try
		{
			LOGGER.debug("Abriendo Sentencia");
			statement = connection.prepareStatement("UPDATE TB02_PRODUCTOS_VAR SET VALOR_TITULO = ?, FECHA_VALOR = ?, TER = ?, FECHA_TER = ?, ULTIMA_ACTUALIZACION = CURRENT_TIMESTAMP WHERE PRODUCTO_ID = ?");
			statement.setBigDecimal(1, productoVar.getValorTitulo());
			statement.setDate(2, new Date(productoVar.getFechaValor().getTime()));
			statement.setBigDecimal(3, productoVar.getTer());
			statement.setDate(4, new Date(productoVar.getFechaTer().getTime()));
			statement.setString(5, productoVar.getProductoId());
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

	/**
	 * @param connection
	 * @return
	 * @throws Throwable
	 */
	public static List<GanPerProdPesoDTO> select_VWF_GAN_PER_PROD_PESO_GLOBAL(Connection connection) throws Throwable
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<GanPerProdPesoDTO> listaGanPerProdPeso = new ArrayList<GanPerProdPesoDTO>();
		try
		{
			LOGGER.debug("Abriendo Sentencia");
			statement = connection.prepareStatement("SELECT \"ISIN\", \"Nombre\", \"Comercializador\", \"Mercado\", \"Proveedor\", \"Instrumento\", \"Tipo Activo\", \"Subtipo Activo\", \"Moneda\", \"Uso Ingresos\", \"TER\", \"Tit. Compra\", \"Prec. Tit. C.\", \"Tit. Venta\", \"Prec. Tit. V.\", \"Flujo Caja\", \"Tit. Act.\", \"Val. Tit.\", \"Val. Tit. Act.\", \"Gan./Perd.\", \"Gan./Perd. %\", \"Peso %\" FROM VWF_GAN_PER_PROD_PESO_GLOBAL");
			LOGGER.debug("Ejecutando Sentencia");
			resultSet = statement.executeQuery();
			LOGGER.debug("Abriendo Cursor");
			while (resultSet.next())
			{
				GanPerProdPesoDTO dto = new GanPerProdPesoDTO();
				dto.setProductoId(resultSet.getString("ISIN"));
				dto.setNombre(resultSet.getString("Nombre"));
				dto.setComercializador(resultSet.getString("Comercializador"));
				dto.setMercado(resultSet.getString("Mercado"));
				dto.setProveedor(resultSet.getString("Proveedor"));
				dto.setInstrumento(resultSet.getString("Instrumento"));
				dto.setTipoActivo(resultSet.getString("Tipo Activo"));
				dto.setSubtipoActivo(resultSet.getString("Subtipo Activo"));
				dto.setMoneda(resultSet.getString("Moneda"));
				dto.setUsoIngresos(resultSet.getString("Uso Ingresos"));
				dto.setTer(resultSet.getBigDecimal("TER"));
				dto.setTitulosComprados(resultSet.getBigDecimal("Tit. Compra"));
				dto.setPrecioTitulosComprados(resultSet.getBigDecimal("Prec. Tit. C."));
				dto.setTitulosVendidos(resultSet.getBigDecimal("Tit. Venta"));
				dto.setPrecioTitulosVendidos(resultSet.getBigDecimal("Prec. Tit. V."));
				dto.setFlujoCaja(resultSet.getBigDecimal("Flujo Caja"));
				dto.setTitulosActuales(resultSet.getBigDecimal("Tit. Act."));
				dto.setValorTitulo(resultSet.getBigDecimal("Val. Tit."));
				dto.setValorTitulosActuales(resultSet.getBigDecimal("Val. Tit. Act."));
				dto.setGananciaPerdida(resultSet.getBigDecimal("Gan./Perd."));
				dto.setGananciaPerdidaPrcnt(resultSet.getBigDecimal("Gan./Perd. %"));
				dto.setPesoEnCartera(resultSet.getBigDecimal("Peso %"));
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
	 * @return
	 * @throws Throwable
	 */
	public static List<EfectivoDTO> select_VWF_EFECTIVO(Connection connection) throws Throwable
	{
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		List<EfectivoDTO> listaEfectivo = new ArrayList<EfectivoDTO>();
		try
		{
			LOGGER.debug("Abriendo Sentencia");
			statement = connection.prepareStatement("SELECT \"Nombre Cuenta\", \"Disponible\", \"Inmovilizado\" FROM VWF_EFECTIVO");
			LOGGER.debug("Ejecutando Sentencia");
			resultSet = statement.executeQuery();
			LOGGER.debug("Abriendo Cursor");
			while (resultSet.next())
			{
				EfectivoDTO dto = new EfectivoDTO();
				dto.setNombreCuenta(resultSet.getString("Nombre Cuenta"));
				dto.setDisponible(resultSet.getBigDecimal("Disponible"));
				dto.setInmovilizado(resultSet.getBigDecimal("Inmovilizado"));
				listaEfectivo.add(dto);
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
		return listaEfectivo;
	}

}
