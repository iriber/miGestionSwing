package com.migestion.swing.view.dialogs.adapters;


/**
 * Inteface para un panel a utilizar en un diálogo para agregar una entidad.
 * 
 * @author Bernardo Iribarne
 * 
 */
public interface IDialogAddRelatedAdapter extends IDialogAddAdapter {

	/**
	 * setea el objeto relacionado.
	 * 
	 * @param object
	 */
	void setRelatedObject(Object object);

}