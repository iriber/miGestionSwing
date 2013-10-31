package com.migestion.swing.navigation;

import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.KeyStroke;

import com.migestion.swing.i18n.exceptions.ExceptionMessagesBundle;
import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkWindowAddRelated;
import com.migestion.swing.navigation.listeners.LinkAddListener;
import com.migestion.swing.view.frames.ListCollectionListener;

/**
 * Link para agregar un elemento relacionado a otro existente. Llama a la ventana
 * para agregar un elemento.
 * 
 * @author Bernardo Iribarne
 *
 */
public class LinkAddRelatedObject extends Link  implements ListCollectionListener{


	//ventana para crear el objeto
	protected ILinkWindowAddRelated dialog;
	
	//objetos listeners que ser�n informados cuando
	//se cree el nuevo objeto
	private Vector listeners;
	
	//objeto relacionado al que se agrega
	private Object relatedObject;
	
	//----------------
	//CONTRUCTORES
	//----------------
	
	
	public LinkAddRelatedObject(ILinkWindowAddRelated dialog, String title, URL pathSmallIcon, URL pathLargeIcon){
		super ( title, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;
		//inicializamos los listeners
		listeners = new Vector();
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Add_KeyStroke));
		//key event por default
		//setKeyEvent(KeyEvent.VK_F6);
		//imagen del link
		setImageURL(pathSmallIcon);
		//descripci�n del link
		setDescription(title);
		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
		setEnabled(false);
	}
	
	public LinkAddRelatedObject(ILinkWindowAddRelated dialog, String title, URL pathSmallIcon){
		super ( title, pathSmallIcon);
		this.dialog = dialog;
		//inicializamos los listeners
		listeners = new Vector();
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Add_KeyStroke));
		//key event por default
		//setKeyEvent(KeyEvent.VK_F6);
		//imagen del link
		setImageURL(pathSmallIcon);
		//descripci�n del link
		setDescription(title);
		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
		setEnabled(false);
	}
	
	public LinkAddRelatedObject(ILinkWindowAddRelated dialog, String title, String pathSmallIcon, String pathLargeIcon){
		super ( title, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;
		//inicializamos los listeners
		listeners = new Vector();
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Add_KeyStroke));
		//key event por default
		//setKeyEvent(KeyEvent.VK_F6);
		//imagen del link
		setImageName(pathSmallIcon);
		//descripci�n del link
		setDescription(title);
		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
		setEnabled(false);
	}
	
	/**
	 * se construye con la ventana que ser� invocada
	 * para cargar la informaci�n del nuevo objeto.
	 * si la ventana es nula, el link se deshabilitar�.
	 */
	public LinkAddRelatedObject(ILinkWindowAddRelated dialog){
		this(dialog, LinkLabelsBundle.link_Add, LinkImagesBundle.link_Add);
		
	}
	
	
	/**
	 * se construye con la ventana que ser� invocada
	 * para cargar la informaci�n del nuevo objeto.
	 * si la ventana es nula, el link se deshabilitar�.
	 * 
	 * @param dialog
	 * @param title
	 * @param pathImage
	 */
	public LinkAddRelatedObject(ILinkWindowAddRelated dialog, String title, String pathImage){
		this(dialog, title, pathImage, pathImage);
		setEnabled(false);
	}

	
	/**
	 * se construye con la ventana que ser� invocada
	 * para cargar la informaci�n del nuevo objeto.
	 * si la ventana es nula, el link se deshabilitar�.
	 * 
	 * @param dialog
	 * @param title
	 */
	public LinkAddRelatedObject(ILinkWindowAddRelated dialog, String title){
		this(dialog, title, LinkImagesBundle.link_Add);
		setEnabled(false);
	}
	
	/**
	 * retorna el objeto creado.
	 * @return
	 */
	public Object getObjectCreated() {
		return getDialog().getObjectCreated();
	}

	/**
	 * retorna si el objeto fue creado o si se cancel�
	 * la acci�n. 
	 * @return
	 */
	public boolean isCreated(){
		return getDialog().isAcepted();
	}
	

	/**
	 * se ejecuta la acci�n.
	 */
	protected void doMyExecute() throws LinkException{
		//verificamos que se haya inicializado el objeto
		//relacionado
		if(this.relatedObject==null)
			throw new LinkException(ExceptionMessagesBundle.link_Update_objectNotDefined);
		
		//se llama al di�logo para modificar el objeto.
		getDialog().open(this.relatedObject);		
		//si fue aceptada la operaci�n, avisamos a los listeners
		//que el objeto fue modificado.
		if(getDialog().isAcepted())
			alertListeners();
	}
	
	/**
	 * se ejecuta la acci�n.
	 */
	public void doExecute(Object relatedObject) throws LinkException{
		//seteamos el objeto relacionado
		setRelatedObject(relatedObject);
		//se llama al di�logo para modificar el objeto.
		doExecute();		
	}
	/**
	 * se agrega un listener para avisarle y darle
	 * el objeto creado.
	 * 
	 * @param listener
	 */
	public void addListener(LinkAddListener listener){
		this.listeners.add(listener);
	}
	
	/*
	 * se avisa a los listener que el objeto
	 * fue creado.  
	 */
	private void alertListeners(){
		Iterator iter = listeners.iterator();
		while (iter.hasNext()) {
			//le avisamos a cada listener
			LinkAddListener element = (LinkAddListener) iter.next();
			element.objectCreated(getObjectCreated());			
		}
	}
	
	protected ILinkWindowAddRelated getDialog(){
		return this.dialog;
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
		setEnabled(relatedObject!=null);
	}

	public void valueSelectedChange(Object selectedObject) {
		setRelatedObject(selectedObject);		
	}
}
