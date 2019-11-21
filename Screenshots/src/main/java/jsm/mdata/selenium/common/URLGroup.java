/**
 * 
 */
package jsm.mdata.selenium.common;

import java.util.List;

/**
 * @author Empleado
 *
 */
public class URLGroup
{

	/**
	 * 
	 */
	private String downloadFolder;
	private List<String> listaURL;

	/**
	 * @param downloadFolder
	 * @param listaURL
	 */
	public URLGroup(String downloadFolder, List<String> listaURL)
	{
		this.downloadFolder = downloadFolder;
		this.listaURL = listaURL;
	}

	/**
	 * @return the downloadFolder
	 */
	public String getDownloadFolder()
	{
		return downloadFolder;
	}

	/**
	 * @param downloadFolder
	 *            the downloadFolder to set
	 */
	public void setDownloadFolder(String downloadFolder)
	{
		this.downloadFolder = downloadFolder;
	}

	/**
	 * @return the listaURL
	 */
	public List<String> getListaURL()
	{
		return listaURL;
	}

	/**
	 * @param listaURL
	 *            the listaURL to set
	 */
	public void setListaURL(List<String> listaURL)
	{
		this.listaURL = listaURL;
	}

}
