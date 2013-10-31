package com.migestion.swing.navigation;

import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.KeyStroke;

import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkWindowFindObject;
import com.migestion.swing.navigation.listeners.LinkFindObjectListener;

/**
 * Link para buscar un objeto. Llama a la ventana
 * para buscar un objeto.
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public class LinkFindObject extends Link {


	//di�logo para buscar un objeto.
	protected ILinkWindowFindObject dialog;
	
	//objetos listeners que ser�n informados cuando
	//se encuentre el objeto.
	private Vector listeners;
	
	//CONTRUCTORES
	/**
	 * se construye con el di�logo que ser� invocado
	 * buscar un objeto.
	 * si el di�logo es nulo, el link se deshabilitar�.
	 */
	public LinkFindObject(ILinkWindowFindObject dialog){
		this(dialog, LinkLabelsBundle.link_FindObject, LinkImagesBundle.link_FindObject);		
	}

	public LinkFindObject(ILinkWindowFindObject dialog, String title){
		this(dialog, title, LinkImagesBundle.link_FindObject);		
	}
	
	public LinkFindObject(ILinkWindowFindObject dialog, String description, String imageName){
		this(dialog, description, imageName, imageName);
	}
	
	public LinkFindObject(ILinkWindowFindObject dialog, String description, URL imageName){
		this(dialog, description, imageName, imageName);
	}
	
	
	public LinkFindObject(ILinkWindowFindObject dialog, String description, String pathSmallIcon, String pathLargeIcon){
		super(description, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;
		//inicializamos los listeners
		listeners = new Vector();
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_FindObject_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_F2);
		//imagen
		setImageName(pathSmallIcon);
		//descripci�n
		setDescription(description);
		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
	}
	
	public LinkFindObject(ILinkWindowFindObject dialog, String description, URL pathSmallIcon, URL pathLargeIcon){
		super(description, pathSmallIcon, pathLargeIcon);
		this.dialog = dialog;
		//inicializamos los listeners
		listeners = new Vector();
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_FindObject_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_F2);
		//imagen
		setImageURL(pathSmallIcon);
		//descripci�n
		setDescription(description);
		//lo deshabilitamos si el di�logo es nulo
//		if(dialog==null)
//			setEnabled(false);
	}
	
	/**
	 * retorna el objeto encontrado.
	 * @return
	 */
	public Object getObjectFinded() {
		return getDialog().getObjectFinded();
	}

	/**
	 * retorna si el objeto fue encontrado o si se cancel�
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
	public void addListener(LinkFindObjectListener listener){
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
			LinkFindObjectListener element = (LinkFindObjectListener) iter.next();
			if(element!=null)
                            element.objectFound(getObjectFinded());
		}
	}
	
	protected ILinkWindowFindObject getDialog(){
		return dialog;
	}
	
	public void setDialog(ILinkWindowFindObject dialog){
		this.dialog = dialog;
	}
	
	
}
