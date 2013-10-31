package com.migestion.swing.navigation.interfaces;

import com.migestion.swing.model.UICollection;


/**
 * Interfaz que deberán implementar los que utilicen un LinkPrintCollection.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface ILinkPrintCollection {
	
	
	/**
	 * @return colección que se debe imprimir.
	 */
	public UICollection getUICollectionToPrint();

}
