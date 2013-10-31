package com.migestion.swing.navigation;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.KeyStroke;

import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkWindowCreateCriteria;
import com.migestion.swing.navigation.listeners.LinkCreateCriteriaListener;
import com.migestion.swing.search.criteria.UICriteria;

/**
 * Link para crear un criteria. Llama a la ventana
 * para crear un criteria.
 * 
 * @author Bernardo Iribarne
 *
 */
public class LinkCreateCriteria extends Link {


	//di�logo para crear el criteria
	protected ILinkWindowCreateCriteria dialog;
	
	//objetos listeners que ser�n informados cuando
	//se cree el nuevo criteria
	private Vector listeners;
	
	//CONTRUCTORES
	/**
	 * se construye con el di�logo que ser� invocado
	 * para cargar la informaci�n del nuevo crieria.
	 * si el di�logo es nulo, el link se deshabilitar�.
	 */
	public LinkCreateCriteria(ILinkWindowCreateCriteria dialog){
		this(dialog, LinkLabelsBundle.link_CreateCriteria, LinkImagesBundle.link_CreateCriteria);		
	}

	public LinkCreateCriteria(ILinkWindowCreateCriteria dialog, String title){
		this(dialog, title, LinkImagesBundle.link_CreateCriteria);		
	}
	
	public LinkCreateCriteria(ILinkWindowCreateCriteria dialog, String description, String imageName){
		this(dialog, description, imageName, imageName);
	}

	public LinkCreateCriteria(ILinkWindowCreateCriteria dialog, String description, URL imageName){
		this(dialog, description, imageName, imageName);
	}

	public LinkCreateCriteria(ILinkWindowCreateCriteria dialog, String description, String pathSmallIcon, String pathLargeIcon){
		super(description, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;
		//inicializamos los listeners
		listeners = new Vector();
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_CreateCriteria_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_F2);
		//imagen
		setImageName(pathSmallIcon);
		//descripci�n
		setDescription(description);
//		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
	}
	
	public LinkCreateCriteria(ILinkWindowCreateCriteria dialog, String description, URL pathSmallIcon, URL pathLargeIcon){
		super(description, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;
		//inicializamos los listeners
		listeners = new Vector();
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_CreateCriteria_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_F2);
		//imagen
		setImageURL(pathSmallIcon);
		//descripci�n
		setDescription(description);
//		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
	}
	/**
	 * retorna el objeto creado.
	 * @return
	 */
	public UICriteria getCriteriaCreated() {
		return getDialog().getUICriteria();
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
		//se llama al di�logo para crear el criteria.
		getDialog().open();		
		//si fue aceptada la operaci�n, avisamos a los listeners
		//que el criteria fue creado.
		if(getDialog().isAcepted())
			alertListeners();

	}

	/**
	 * se agrega un listener para avisarle y darle
	 * el objeto creado.
	 * 
	 * @param listener
	 */
	public void addListener(LinkCreateCriteriaListener listener){
		this.listeners.add(listener);
	}
	
	/*
	 * se avisa a los listener que el criteria
	 * fue creado.  
	 */
	private void alertListeners(){
		Iterator iter = listeners.iterator();
		while (iter.hasNext()) {
			//le avisamos a cada listener
			LinkCreateCriteriaListener element = (LinkCreateCriteriaListener) iter.next();
			element.criteriaCreated(getCriteriaCreated());			
		}
	}
	
	protected ILinkWindowCreateCriteria getDialog(){
		return dialog;
	}
}
