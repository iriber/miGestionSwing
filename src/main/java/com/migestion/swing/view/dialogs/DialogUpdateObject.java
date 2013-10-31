package com.migestion.swing.view.dialogs;

import java.awt.Container;

import javax.swing.JPanel;

import com.migestion.swing.controller.IControllerUpdate;
import com.migestion.swing.controller.exception.ControllerException;
import com.migestion.swing.navigation.interfaces.ILinkWindowUpdate;
import com.migestion.swing.view.exceptions.ViewException;

/**
 * Di�logo que ser� utilizado como base para modificar un objeto.
 * 
 * Tendr� que ser instanciado con el controlador espec�fico
 * el cu�l se encargar� de modificar el objeto en el modelo.
 * 
 * @author Bernardo Iribarne
 * 
 */
public abstract class DialogUpdateObject extends DialogOkCancel implements ILinkWindowUpdate{

	//controlador que se encargar� de modificar el objeto
	private IControllerUpdate controller; 
	
	//objeto que ser� modificado con los valores de la UI
	private Object objectToUpdate = null;
	
	//-----------------
	// CONSTRUCTORES
	//-----------------
	
	/**
	 * 
	 */
	public DialogUpdateObject(String title, IControllerUpdate controller) {
		super(title);
		this.controller = controller;		
	}

	/**
	 * 
	 * @param title
	 * @param controller
	 * @param pathImage
	 */
	public DialogUpdateObject(String title, IControllerUpdate controller, String pathImage) {
		super(title, pathImage);
		this.controller = controller;		
	}

	/**
	 * retorna el objeto modificado
	 * 
	 * @return
	 */
	public Object getObjectUpdated(){
		return objectToUpdate;
	}

	/**
	 * se abre el di�logo centrado en la pantalla.
	 */
	public void open(Object objectToUpdate) {
		this.objectToUpdate = objectToUpdate;
		showObjectToUpdate();
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
			//se valida la informaci�n cargada.
			validateInput();
			//creamos el objeto con la info de la ui.
			objectToUpdate = getObjectFromUI();
			controller.updateObject(objectToUpdate);
			this.accepted = true;
			this.dispose();
		}catch(ViewException ex){
			//se informa del error al usuario
			DialogMessage.showErrorMessage(getTitle(), ex.getMessage());
		} catch (ControllerException e) {
			//se informa del error al usuario
			DialogMessage.showErrorMessage(getTitle(), e.getMessage());
		}
	}
	
	/**
	 * retorna el panel donde se mostrar� la informaci�n deseada.
	 * cada subclase definir� qu� informaci�n se mostrar�.
	 */
	protected Container createInfoPanel(){
		//se retornar� un panel para cargar la informaci�n 
		//del objeto a modificar.
		return createInputPanel();
	}
	
	/**
	 * crea el objeto con la informaci�n cargada desde
	 * la ui.
	 * 
	 * @return
	 */
	protected abstract Object getObjectFromUI() throws ViewException;
	
	/**
	 * valida la informaci�n cargada por el usuario. 
	 * 
	 * @return
	 */
	protected abstract void validateInput() throws ViewException;
	
	/**
	 * retorna el panel con los campos de entrada para generar
	 * el objeto.
	 * cada subclase definir� qu� campos deben llenarse.
	 * 
	 * @return
	 */
	protected abstract Container createInputPanel();

	/**
	 * se muestra el objeto a ser modificado.
	 * 
	 * @return
	 */
	protected abstract void showObjectToUpdate();

}
