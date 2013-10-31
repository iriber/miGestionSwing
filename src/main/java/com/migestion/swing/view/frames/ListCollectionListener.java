package com.migestion.swing.view.frames;

/**
 * Interface utilizada para informar cambios en el listado 
 * de los frames.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface ListCollectionListener {

	/**
	 * se informa el nuevo objeto seleccionado en el listado.
	 * 
	 * @param selectedObject
	 */
	public void valueSelectedChange(Object selectedObject);
	
	
}
