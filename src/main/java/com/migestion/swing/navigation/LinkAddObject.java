package com.migestion.swing.navigation;

import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.KeyStroke;

import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkWindowAdd;
import com.migestion.swing.navigation.listeners.LinkAddListener;

/**
 * Link para agregar un elemento. Llama a la ventana
 * para agregar un elemento.
 * 
 * @author Bernardo Iribarne
 *
 */
public class LinkAddObject extends Link {


	//ventana para crear el objeto
	protected ILinkWindowAdd dialog;
	
	//objetos listeners que ser�n informados cuando
	//se cree el nuevo objeto
	private Vector listeners;
	
	//----------------
	//CONTRUCTORES
	//----------------
	
	
	public LinkAddObject(ILinkWindowAdd dialog, String title, URL pathSmallIcon, URL pathLargeIcon){
		super ( title, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;
		//inicializamos los listeners
		listeners = new Vector();
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Add_KeyStroke));
//		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(LinkLabelsBundle.link_Add_KeyStroke));
		//key event por default
		//setKeyEvent(KeyEvent.VK_F6);
		//imagen del link
		setImageURL(pathSmallIcon);
		//descripci�n del link
		setDescription(title);
		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
	}
	
	public LinkAddObject(ILinkWindowAdd dialog, String title, URL pathSmallIcon){
		super ( title, pathSmallIcon);
		this.dialog = dialog;
		//inicializamos los listeners
		listeners = new Vector();
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Add_KeyStroke));
//		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(LinkLabelsBundle.link_Add_KeyStroke));
		//key event por default
		//setKeyEvent(KeyEvent.VK_F6);
		//imagen del link
		setImageURL(pathSmallIcon);
		//descripci�n del link
		setDescription(title);
		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
	}
	
	public LinkAddObject(ILinkWindowAdd dialog, String title, String pathSmallIcon, String pathLargeIcon){
		super ( title, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;
		//inicializamos los listeners
		listeners = new Vector();
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Add_KeyStroke));
//		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(LinkLabelsBundle.link_Add_KeyStroke));
		//key event por default
		//setKeyEvent(KeyEvent.VK_F6);
		//imagen del link
		setImageName(pathSmallIcon);
		//descripci�n del link
		setDescription(title);
		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
	}
	
	/**
	 * se construye con la ventana que ser� invocada
	 * para cargar la informaci�n del nuevo objeto.
	 * si la ventana es nula, el link se deshabilitar�.
	 */
	public LinkAddObject(ILinkWindowAdd dialog){
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
	public LinkAddObject(ILinkWindowAdd dialog, String title, String pathImage){
		this(dialog, title, pathImage, pathImage);
		
	}

	
	/**
	 * se construye con la ventana que ser� invocada
	 * para cargar la informaci�n del nuevo objeto.
	 * si la ventana es nula, el link se deshabilitar�.
	 * 
	 * @param dialog
	 * @param title
	 */
	public LinkAddObject(ILinkWindowAdd dialog, String title){
		this(dialog, title, LinkImagesBundle.link_Add);
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
		//se llama al di�logo para crear el objeto.
		getDialog().open();		
		//si fue aceptada la operaci�n, avisamos a los listeners
		//que el objeto fue creado.
		if(getDialog().isAcepted())
			alertListeners();

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
	
	protected ILinkWindowAdd getDialog(){
		return this.dialog;
	}
}
