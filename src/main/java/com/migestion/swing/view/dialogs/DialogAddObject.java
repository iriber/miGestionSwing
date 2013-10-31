package com.migestion.swing.view.dialogs;

import java.awt.Container;

import com.migestion.swing.controller.IControllerAdd;
import com.migestion.swing.controller.exception.ControllerException;
import com.migestion.swing.navigation.interfaces.ILinkWindowAdd;
import com.migestion.swing.utils.UbicacionVentana;
import com.migestion.swing.view.exceptions.ViewException;

/**
 * Diálogo utilizado como base para agregar un objeto.
 * 
 * Tendrá que ser instanciado con el controlador específico
 * el cuál se encargará de agregar al modelo el objeto creado.
 * 
 * @author Bernardo Iribarne
 *
 */
public abstract class DialogAddObject extends DialogOkCancel implements ILinkWindowAdd {

	/**
	 * controlador que se encargará de agregar al modelo el objeto creado al modelo
	 */
	private IControllerAdd controller; 
	

	/**
	 * objeto que será creado con los valores de la UI
	 */
	private Object objectCreated = null;

	
	//------------------
	// CONSTRUCTORES
	//------------------
	
	/**
	 * lo construimos con el controlador.
	 */
	public DialogAddObject(String title, IControllerAdd controller) {
		super(title);
		this.controller = controller;
	}

	/**
	 * lo construimos con el controlador y una imagen de fondo.
	 * 
	 * @param title
	 * @param controller
	 * @param pathImage
	 */
	public DialogAddObject(String title, IControllerAdd controller, String pathImage) {
		super(title, pathImage);
		this.controller = controller;
	}

	/**
	 * retorna el objeto creado 
	 * 
	 * @return
	 */
	public Object getObjectCreated(){
		return objectCreated;
	}

	/**
	 * redefinimos el open para que limpie los
	 * inputs cada vez que se abre el diálogo.
	 */
	public void open() {
		UbicacionVentana.centrar(this, false);
		clearInputs();
		this.setVisible(true);
	}

	
	//----------------------
	// MÉTODOS PROTECTED
	//----------------------
	
	
	/**
	 * se acepta la operación.
	 * si hay algún error, se informa el mismo.
	 * si todo está ok, la operación se concluye y se
	 * cierra la ventana.
	 */
	protected void doOk(){		
		try {			
			//se valida la informaci�n cargada.
			validateInput();
			//creamos el objeto con la info de la ui.
			objectCreated = getObjectFromUI();
			controller.addObject(objectCreated);
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
	 * retorna el panel donde se mostrará la información.
	 * cada subclase definirá qué información se mostrará.
	 */
	protected Container createInfoPanel(){
		//se retornará un panel para cargar la información 
		//del objeto a crear
		return createInputPanel();
	}
	
	/**
	 * crea el objeto con la información cargada desde
	 * la ui.
	 * 
	 * @return
	 */
	protected abstract Object getObjectFromUI() throws ViewException;
	
	/**
	 * valida la información cargada por el usuario. 
	 * 
	 * @return
	 */
	protected abstract void validateInput() throws ViewException;
	
	/**
	 * retorna el panel con los campos de entrada para generar
	 * el objeto.
	 * cada subclase definirá qué campos deben llenarse.
	 * 
	 * @return
	 */
	protected abstract Container createInputPanel();
	
	/**
	 * limpia los campos de entrada del diálogo.
	 */
	public abstract void clearInputs();

	
}
