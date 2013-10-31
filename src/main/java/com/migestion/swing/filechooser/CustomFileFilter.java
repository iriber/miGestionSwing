package com.migestion.swing.filechooser;

import java.io.File;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.filechooser.FileFilter;

/**
 * 
 * A convenience implementation of FileFilter that filters out
 * all files except for those type extensions that it knows about.
 *
 * FileFilter para filtrar todas las extensiones de archivos
 * excepto las que le indiquemos.
 *
 * Por ejemplo: creamos un filtro para s�lo visualizar archivos
 * .jpg y .gif
 *
 *     JFileChooser chooser = new JFileChooser();
 *     CustomFileFilter filter = new CustomFileFilter(
 *                   new String{"gif", "jpg"}, "JPEG & GIF Images")
 *     chooser.addChoosableFileFilter(filter);
 *     chooser.showOpenDialog(this);
 *     
 * @author Bernardo Iribarne
 *
 */
public class CustomFileFilter extends FileFilter {

	private Hashtable filters = null;

	private String description = null;

	private String fullDescription = null;

	private boolean useExtensionsInDescription = true;

	/**
	 * Se crea un file filter que acepta todos los archivos. 
	 */
	public CustomFileFilter() {
		this.filters = new Hashtable();
	}

	/**
	 * 
	 * Crea un file filter que acepta los archivos con la
	 * extensi�n especificada. 
	 * 
	 * @param extension ("jpg")
	 */
	public CustomFileFilter(String extension) {
		this(extension, null);
	}

	/**
	 * Crea un file filter que acepta los archivos con la
	 * extensi�n especificada, mostrando una descripci�n.
	 * 
	 * @param extension  ("jpg").
	 * @param description  ("Im�genes JPEG").
	 */
	public CustomFileFilter(String extension, String description) {
		this();
		if (extension != null)
			addExtension(extension);
		if (description != null)
			setDescription(description);
	}

	/**
	 * Crea un file filter que acepta los archivos con la
	 * extensi�n especificada en alguno de los elementos del 
	 * array.
	 * 
	 * @param filters  ( {"gif", "jpg"} )
	 */

	public CustomFileFilter(String[] filters) {
		this(filters, null);
	}

	/**
	 * 
	 * Crea un file filter que acepta los archivos con la
	 * extensi�n especificada en alguno de los elementos del 
	 * array, mostrando una descripci�n.
	 * 
	 * Creates a file filter from the given string array and description.
	 * Example: new ExampleFileFilter(String , "Gif and JPG
	 * Images");
	 * 
	 * Note that the "." before the extension is not needed and will be ignored.
	 * 
	 * @param filters    ( {"gif", "jpg"} ).
	 * @param description ("Im�genes GIF y JPEG").
	 */
	public CustomFileFilter(String[] filters, String description) {
		this();
		for (int i = 0; i < filters.length; i++) {
			// add filters one by one
			addExtension(filters[i]);
		}
		if (description != null)
			setDescription(description);
	}

	/**
	 * retorna true si el archivo puede mostrarse en el pane 
	 * del directorio.
	 */
	public boolean accept(File f) {
		if (f != null) {
			if (f.isDirectory()) {
				return true;
			}
			String extension = getExtension(f);
			if (extension != null && filters.get(getExtension(f)) != null) {
				return true;
			}
			;
		}
		return false;
	}

	/**
	 * retorna la extensi�n del archivo.
	 * 
	 * @param f
	 * @return
	 */
	public String getExtension(File f) {
		if (f != null) {
			String filename = f.getName();
			int i = filename.lastIndexOf('.');
			if (i > 0 && i < filename.length() - 1) {
				return filename.substring(i + 1).toLowerCase();
			}
			;
		}
		return null;
	}

	/**
	 * agrega una extensi�n al file filter.
	 * 
	 * @param extension  ("tif")
	 */
	public void addExtension(String extension) {
		if (filters == null) {
			filters = new Hashtable(5);
		}
		filters.put(extension.toLowerCase(), this);
		fullDescription = null;
	}

	/**
	 * retorna la descripci�n del filtro.
	 * 
	 * @return "Im�genes GIF y JPEG (*.gif, *.jpg)"
	 */
	public String getDescription() {
		if (fullDescription == null) {
			if (description == null || isExtensionListInDescription()) {
				fullDescription = description == null ? "(" : description
						+ " (";
				// build the description from the extension list
				Enumeration extensions = filters.keys();
				if (extensions != null) {
					fullDescription += "." + (String) extensions.nextElement();
					while (extensions.hasMoreElements()) {
						fullDescription += ", ."
								+ (String) extensions.nextElement();
					}
				}
				fullDescription += ")";
			} else {
				fullDescription = description;
			}
		}
		return fullDescription;
	}

	/**
	 * 
	 * setea la descripci�n del file filter.
	 * 
	 * @param description  ("Im�genes Gif y JPG").
	 */
	public void setDescription(String description) {
		this.description = description;
		fullDescription = null;
	}

	/**
	 * Determines whether the extension list (.jpg, .gif, etc) should show up in
	 * the human readable description.
	 * 
	 * Only relevent if a description was provided in the constructor or using
	 * setDescription();
	 * 
	 */
	public void setExtensionListInDescription(boolean b) {
		useExtensionsInDescription = b;
		fullDescription = null;
	}

	/**
	 * Returns whether the extension list (.jpg, .gif, etc) should show up in
	 * the human readable description.
	 * 
	 * Only relevent if a description was provided in the constructor or using
	 * setDescription();
	 * 
	 */
	public boolean isExtensionListInDescription() {
		return useExtensionsInDescription;
	}
}
