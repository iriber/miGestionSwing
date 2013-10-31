package com.migestion.swing.navigation;

import java.awt.event.KeyEvent;
import java.net.URL;

import javax.swing.KeyStroke;

import com.migestion.swing.i18n.exceptions.ExceptionMessagesBundle;
import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkWindowView;
import com.migestion.swing.view.frames.ListCollectionListener;

/**
 * Link para visualizar un elemento. Llama a la ventana
 * para visualizar un elemento.
 * 
 * @author Bernardo Iribarne
 *
 */
public class LinkViewObject extends Link implements ListCollectionListener {


	//dialogo para visualizar el objeto
	protected ILinkWindowView dialog;
	
	//objeto a ser visualizado
	private Object objectToView;
	
	//----------------
	//CONTRUCTORES
	//----------------
	
	/**
	 * se construye con el di�logo que ser� invocado
	 * para visualizar el objeto.
	 */
	public LinkViewObject(ILinkWindowView dialog){
		this(dialog, LinkLabelsBundle.link_View, LinkImagesBundle.link_View);
		this.setEnabled(false);
	}
	
	/**
	 * se construye con el di�logo que ser� invocado
	 * para visualizar el objeto.
	 * 
	 * @param dialog
	 * @param title
	 */
	public LinkViewObject(ILinkWindowView dialog, String title){
		this(dialog, title, LinkImagesBundle.link_View);
		this.setEnabled(false);
	}


	/**
	 * se construye con el di�logo que ser� invocado
	 * para visualizar el objeto.
	 * 
	 * @param dialog
	 * @param title
	 * @param pathImage
	 */
	public LinkViewObject(ILinkWindowView dialog, String title, String pathImage){
		this( dialog, title, pathImage, pathImage);
		this.setEnabled(false);
	}
	
	public LinkViewObject(ILinkWindowView dialog, String title, URL pathImage){
		this( dialog, title, pathImage, pathImage);
		this.setEnabled(false);
	}
	
	
	public LinkViewObject(ILinkWindowView dialog, String title, String pathSmallIcon, String pathLargeIcon){
		super(title, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;		
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_View_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_F9);
		//imagen 
		setImageName(pathSmallIcon);
		//descripci�n
		setDescription(title);		
		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
//		
		this.setEnabled(false);
	}

	public LinkViewObject(ILinkWindowView dialog, String title, URL pathSmallIcon, URL pathLargeIcon){
		super(title, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;		
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_View_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_F9);
		//imagen 
		setImageURL(pathSmallIcon);
		//descripci�n
		setDescription(title);		
		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
//		
		this.setEnabled(false);
	}
	/**
	 * se setea el objeto a visualizar,.
	 * 
	 * @param objectToView
	 */
	public void setObjectToView(Object objectToView){
		this.objectToView = objectToView;
		this.setEnabled(objectToView!=null);
		
	}
	
	/**
	 * se ejecuta la acci�n.
	 */
	protected void doMyExecute() throws LinkException{
		//verificamos que se haya inicializado el objeto
		//a visualizar
		if(this.objectToView==null)
			throw new LinkException(ExceptionMessagesBundle.link_View_objectNotDefined);

		//se llama al di�logo para visualizar el objeto.
		getDialog().open(this.objectToView);		
	}

	/**
	 * se ejecuta la acci�n.
	 */
	public void doExecute(Object objectToView) throws LinkException{
		//seteamos el objeto a visualizar
		setObjectToView(objectToView);
		//se llama al di�logo para visualizar el objeto.
		doExecute();		
	}

	/**
	 * el link es listener de un listado, cuando cambia el objeto
	 * seleccionado de la lista, se setea como el objeto a visualizar 
	 * @param selectedObject
	 */
	public void valueSelectedChange(Object selectedObject) {
		setObjectToView(selectedObject);
	}
	
	protected ILinkWindowView getDialog(){
		return dialog;
	}
}
