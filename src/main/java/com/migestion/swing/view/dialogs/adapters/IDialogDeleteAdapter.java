package com.migestion.swing.view.dialogs.adapters;

import java.awt.Container;


/**
 * Interface para un panel a utilizar en un diálogo para eliminar
 * una entidad.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface IDialogDeleteAdapter {

	/**
	 * crea el panel que contiene la info del objeto.
	 * @return
	 */
	Container getDeleteMsgPanel();

	/**
	 * muestra el objeto a actualizar.
	 * @param object
	 */
	void showObject(Object object);
	
}