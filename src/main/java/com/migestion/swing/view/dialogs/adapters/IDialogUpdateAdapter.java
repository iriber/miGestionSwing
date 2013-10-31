package com.migestion.swing.view.dialogs.adapters;

import java.awt.Container;
import java.util.List;

import com.migestion.swing.view.exceptions.ViewException;
import com.migestion.swing.view.inputs.InputValidator;


/**
 * Inteface para un panel a utilizar en un diálogo para actualizar
 * una entidad.
 * 
 * @author Bernardo Iribarne
 *
 */
public interface IDialogUpdateAdapter {

	/**
	 * crea el panel con los inputs.
	 * @return
	 */
	Container getInputPanel();

	/**
	 * crea el objeto con la informacián cargada desde
	 * la ui.
	 * 
	 * @return
	 */
	Object getObjectFromUI() throws ViewException;
	
	/**
	 * inicializa el validador. 
	 * 
	 * @return
	 */
	List<InputValidator> getValidators();
	
	
	/**
	 * mensaje de error sobre los campos ingresados
	 * @return
	 */
	//String getInputErrorMsg();
	
	/**
	 * limpia los campos de entrada del diálogo.
	 */
	void clearInputs();

	/**
	 * muestra el objeto a actualizar.
	 * @param object
	 */
	void showObject(Object object);
	
}