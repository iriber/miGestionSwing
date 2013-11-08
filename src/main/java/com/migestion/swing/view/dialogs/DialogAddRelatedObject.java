package com.migestion.swing.view.dialogs;

import com.migestion.swing.controller.IControllerAdd;
import com.migestion.swing.navigation.interfaces.ILinkWindowAddRelated;
import com.migestion.swing.navigation.interfaces.ILinkWindowObjectOpen;
import com.migestion.swing.navigation.interfaces.ILinkWindowOpen;
import com.migestion.swing.utils.UbicacionVentana;

/**
 * Diálogo utilizado como base para agregar un objeto.
 * 
 * Tendrá que ser instanciado con el controlador específico
 * el cuál se encargará de agregar al modelo el objeto creado.
 * 
 * @author Bernardo Iribarne
 *
 */
public abstract class DialogAddRelatedObject extends DialogAddObject implements ILinkWindowAddRelated, ILinkWindowObjectOpen{


	//objeto relacionado al objecto a crear
	private Object relatedObject = null;

	public DialogAddRelatedObject(String title, IControllerAdd controller) {
		super(title, controller);
	}
	
	public DialogAddRelatedObject(String title, IControllerAdd controller, String pathImage) {
		super(title, controller, pathImage);		
	}
		
	//------------------
	// CONSTRUCTORES
	//------------------
	

	/**
	 * redefinimos el open para que limpie los
	 * inputs cada vez que se abre el diálogo.
	 */
	public void open(Object relatedObject) {
		UbicacionVentana.centrar(this, false);
		clearInputs();
		this.setVisible(true);
	}

	
	
	/**
	 * @return the relatedObject
	 */
	public Object getRelatedObject() {
		return relatedObject;
	}

	/**
	 * @param relatedObject the relatedObject to set
	 */
	public void setRelatedObject(Object relatedObject) {
		this.relatedObject = relatedObject;
	}

	
}
