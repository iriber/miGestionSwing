package com.migestion.swing.view.dialogs;

import javax.swing.JPanel;

import com.migestion.swing.navigation.interfaces.ILinkWindowCreateCriteria;
import com.migestion.swing.search.criteria.UICriteria;
import com.migestion.swing.view.exceptions.ViewException;

/**
 * Di�logo que ser� utilizado como base para la 
 * creaci�n de los criterios de b�squeda.
 * 
 * @author Bernardo Iribarne.
 *
 */
public abstract class DialogCreateCriteria extends DialogOkCancel implements ILinkWindowCreateCriteria{

	private UICriteria criteria;
	
	
	//------------------
	// CONSTRUCTORES
	//------------------
	
	/**
	 * 
	 */
	public DialogCreateCriteria(String title) {
		super(title);
	}
	
	/**
	 * 
	 * @param title
	 * @param pathImage
	 */
	public DialogCreateCriteria(String title, String pathImage) {
		super(title, pathImage);
	}
	
	/**
	 * retorna el criteriaDTO creado
	 * @return
	 */
	public UICriteria getUICriteria(){
		return criteria;
	}

	protected void doOk() {
		try {			
			//se valida la informaci�n cargada.
			validateInput();
			//creamos el criteria con la info de la ui.
			criteria = getCriteriaFromUI();
			this.accepted = true;
			this.dispose();
		}catch(ViewException ex){
			//se informa del error al usuario
			DialogMessage.showErrorMessage(getTitle(), ex.getMessage());			
		}		
	}

	/**
	 * retorna el panel donde se mostrar� la informaci�n deseada.
	 * cada subclase definir� qu� informaci�n se mostrar�.
	 */
	protected JPanel createInfoPanel(){
		//se retornar� un panel para cargar la informaci�n 
		//del objeto a crear
		return createInputPanel();
	}
	
	/**
	 * crea el criteria con la informaci�n cargada desde
	 * la ui.
	 * 
	 * @return
	 */
	protected abstract UICriteria getCriteriaFromUI() throws ViewException;
	
	/**
	 * valida la informaci�n cargada por el usuario. 
	 * 
	 * @return
	 */
	protected abstract void validateInput() throws ViewException;
	
	/**
	 * retorna el panel con los campos de entrada para generar
	 * el criteria.
	 * cada subclase definir� qu� campos deben llenarse.
	 * 
	 * @return
	 */
	protected abstract JPanel createInputPanel();	
}
