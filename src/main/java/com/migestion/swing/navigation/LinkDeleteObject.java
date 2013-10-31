package com.migestion.swing.navigation;

import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.KeyStroke;

import com.migestion.swing.i18n.exceptions.ExceptionMessagesBundle;
import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkWindowDelete;
import com.migestion.swing.navigation.listeners.LinkDeleteListener;
import com.migestion.swing.view.frames.ListCollectionListener;

/**
 * Link para eliminar un elemento. Llama a la ventana
 * para eliminar un elemento.
 * 
 * @author Bernardo Iribarne
 *
 */
public class LinkDeleteObject extends Link implements ListCollectionListener{


	//dialogo para eliminar el objeto
	protected ILinkWindowDelete dialog;
	
	//objeto a ser eliminar
	private Object objectToDelete;

	//objetos listeners que ser�n informados cuando
	//se elimine el objeto
	private Vector listeners;
	
	//---------------
	//CONTRUCTORES
	//---------------
	
	/**
	 * se construye con el di�logo que ser� invocado
	 * para eliminar el objeto.
	 */
	public LinkDeleteObject(ILinkWindowDelete dialog){
		this(dialog, LinkLabelsBundle.link_Delete, LinkImagesBundle.link_Delete);
		this.setEnabled(false);
	}
	
	/**
	 * se construye con el di�logo que ser� invocado
	 * para eliminar el objeto.
	 */
	public LinkDeleteObject(ILinkWindowDelete dialog, String title){
		this(dialog, title, LinkImagesBundle.link_Delete);
		this.setEnabled(false);
	}	
	
	public LinkDeleteObject(ILinkWindowDelete dialog, String title, URL pathImage){
		this( dialog, title, pathImage, pathImage);
		this.setEnabled(false);
	}
	
	public LinkDeleteObject(ILinkWindowDelete dialog, String title, String pathImage){
		this( dialog, title, pathImage, pathImage);
		this.setEnabled(false);
	}
	/**
	 * se construye con el di�logo que ser� invocado
	 * para eliminar el objeto.
	 */
	public LinkDeleteObject(ILinkWindowDelete dialog, String title, String pathSmallIcon, String pathLargeIcon){
		super(title, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;		
		//inicializamos los listeners
		listeners = new Vector();
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Delete_KeyStroke));
		//key event por default
//		setKeyEvent(KeyEvent.VK_F8);
		//imagen 
		setImageName(pathSmallIcon);
		//descripci�n 
		setDescription(title);		
//		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
		
		this.setEnabled(false);
	}
	
	public LinkDeleteObject(ILinkWindowDelete dialog, String title, URL pathSmallIcon, URL pathLargeIcon){
		super(title, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;		
		//inicializamos los listeners
		listeners = new Vector();
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Delete_KeyStroke));
		//key event por default
//		setKeyEvent(KeyEvent.VK_F8);
		//imagen 
		setImageURL(pathSmallIcon);
		//descripci�n 
		setDescription(title);		
//		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
		this.setEnabled(false);
	}
	
	/**
	 * se setea el objeto a eliminar.
	 * 
	 * @param objectToDelete
	 */
	public void setObjectToDelete(Object objectToDelete){
		this.objectToDelete = objectToDelete;
		
		this.setEnabled(objectToDelete!=null);
	}
	
	/**
	 * se ejecuta la acci�n.
	 */
	protected void doMyExecute() throws LinkException{
		//verificamos que se haya inicializado el objeto
		//a modificar
		if(this.objectToDelete==null)
			throw new LinkException(ExceptionMessagesBundle.link_Delete_objectNotDefined);

		//se llama al di�logo para eliminar el objeto.
		getDialog().open(this.objectToDelete);		
		//si fue aceptada la operaci�n, avisamos a los listeners
		//que el objeto fue eliminado.
		if(getDialog().isAcepted())
			alertListeners();		
	}

	/**
	 * se ejecuta la acci�n.
	 */
	public void doExecute(Object objectToDelete) throws LinkException{
		//seteamos el objeto a modificar
		setObjectToDelete(objectToDelete);
		//se llama al di�logo para modificar el objeto.
		doExecute();		
	}

	/**
	 * se agrega un listener para avisarle cuando
	 * el objeto es eliminado.
	 * 
	 * @param listener
	 */
	public void addListener(LinkDeleteListener listener){
		this.listeners.add(listener);
	}
	
	/*
	 * se avisa a los listener que el objeto
	 * fue eliminado.  
	 */
	private void alertListeners(){
		Iterator iter = listeners.iterator();
		while (iter.hasNext()) {
			//le avisamos a cada listener
			LinkDeleteListener element = (LinkDeleteListener) iter.next();
			element.objectDeleted(this.objectToDelete);			
		}
	}	

	/**
	 * el link es listener de un listado, cuando cambia el objeto
	 * seleccionado de la lista, se setea como el objeto a eliminar. 
	 * @param selectedObject
	 */
	public void valueSelectedChange(Object selectedObject) {
		setObjectToDelete(selectedObject);
	}

	protected ILinkWindowDelete getDialog(){
		return dialog;
	}
}
