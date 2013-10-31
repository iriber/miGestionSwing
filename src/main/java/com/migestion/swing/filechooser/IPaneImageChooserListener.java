package com.migestion.swing.filechooser;

import java.io.File;

/**
 * Interface que deber�n implementar los contenedores
 * donde se desee agregar un pane image chooser.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface IPaneImageChooserListener {

	/**
	 * se setea la imagen seleccionada.
	 * 
	 * @param imageFile
	 */
	public void setImageFile(File imageFile);
}
