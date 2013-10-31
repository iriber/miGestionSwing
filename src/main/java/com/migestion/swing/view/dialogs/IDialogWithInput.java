package com.migestion.swing.view.dialogs;

import java.awt.Component;

import com.migestion.swing.view.inputs.PropertyInput;
import com.migestion.swing.view.inputs.PropertyInputCollection;

/**
 * Inteface que deber�n implementar los di�logos que
 * tengan inputs.
 * 
 * Es para tener acceso desde cualquier clase a cada uno
 * de los inputs (pensado para poder utilizar factories
 * para crearlos, independientemente de los di�logos).
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public interface IDialogWithInput {


	/**
	 * retorna el input asociado a la clave <code>key</code>
	 * @param key
	 * @return
	 */
	Component getInput(String key);
	
	/**
	 * se agrega un propertyInput
	 * @param propertyInput
	 */
	void addPropertyInput(PropertyInput propertyInput);

	/**
	 * retorna la colecci�n de property inputs.
	 * @return
	 */
	PropertyInputCollection getPropertyInputs();
}
