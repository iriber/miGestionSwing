package com.migestion.swing.view.dialogs;

import java.awt.Container;

import com.migestion.swing.controller.IControllerDelete;
import com.migestion.swing.controller.exception.ControllerException;
import com.migestion.swing.navigation.interfaces.ILinkWindowDelete;
import com.migestion.swing.navigation.interfaces.ILinkWindowObjectOpen;

/**
 * Di�logo que ser� utilizado como base para eliminar un objeto.
 * 
 * Tendr� que ser instanciado con el controlador espec�fico
 * el cu�l se encargar� de eliminar del modelo el objeto indicado.
 * 
 * @author Bernardo Iribarne
 * 
 */
public abstract class DialogDeleteObject extends DialogOkCancel implements ILinkWindowDelete, ILinkWindowObjectOpen{

	//controlador que se encargar� de eliminar del 
	//modelo el objeto indicado
	private IControllerDelete controller; 
	

	//objeto que ser� eliminado del modelo
	protected Object objectToDelete = null;
	
	//----------------
	// CONSTRUCTORES
	//----------------

	/**
	 * 
	 */
	public DialogDeleteObject(String title, IControllerDelete controller) {
		super(title);
		this.controller = controller;
		
	}

	/**
	 * 
	 * @param title
	 * @param controller
	 */
	public DialogDeleteObject(String title, IControllerDelete controller, String pathImage) {
		super(title, pathImage);
		this.controller = controller;
		
	}

	
	
	/**
	 * se abre el di�logo centrado en la pantalla.
	 */
	public void open(Object objectToDelete) {
		this.objectToDelete = objectToDelete;
		showObjectToDelete();
		open();
	}
	
	/**
	 * se acepta la operaci�n.
	 * si hay alg�n error, se informa el mismo.
	 * si todo est� ok, la operaci�n se concluye y se
	 * cierra la ventana.
	 */
	protected void doOk(){		
		try {			
			controller.deleteObject(objectToDelete);
			this.accepted = true;
			this.dispose();
		} catch (ControllerException e) {
			//se informa del error al usuario
			DialogMessage.showErrorMessage(getTitle(), e.getMessage());
		}
	}
	
	
	/**
	 * retorna el panel donde se mostrar� el objeto a eliminar.
	 * cada subclase definir� qu� campos deben llenarse.
	 */
	protected abstract Container createInfoPanel();

	/**
	 * se muestra el objeto a ser eliminado.
	 * 
	 * @return
	 */
	protected abstract void showObjectToDelete();

}
