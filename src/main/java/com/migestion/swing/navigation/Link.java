package com.migestion.swing.navigation;

import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import com.migestion.swing.i18n.exceptions.ExceptionMessagesBundle;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.view.dialogs.DialogMessage;


/**
 * Utilizaremos los Link para definir la navegación de la ui. 
 * 
 * Los links serán utilizadas para llamar a los distintos
 * diálogos que manipulan los objetos.
 * 
 * Los links tendrán propiedades sobre la navegación, podrían 
 * tener objetos para la comunicación entre 2 ventanas, etc.
 * 
 * Por ejemplo podemos tener el link linkAdd que llama a un
 * diálogo DialogAdd y al retornar podamos pedirle al link que 
 * nos de el objeto que se agregó.
 * 
 * Esta clase extendiende de AbstractAction para que pueda ser
 * utilizada como acción en los botones, menúes, etc.
 *
 *
 * @author Bernardo Iribarne
 *
 */
public abstract class Link extends AbstractAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6902349579723388952L;

	//tecla asociada al link.
	//(tecla de acceso r�pido).
	private int keyEvent = 0;
	
	//key stroke asociado al link.
	//(utilizado para accesos r�pidos en los men�es).
	private KeyStroke keyStroke = null; 
	
	//descripci�n del link
	private String description="";
	
	//imagen asociada al link
	private String imageName="";
	private URL imageURL;
	
	//CONSTRUCTORES
	public Link(String title, String pathImage){
		this(title, pathImage, pathImage);
	}
	
	public Link(String title, URL pathImage){
		this(title, pathImage, pathImage);
	}
	public Link(String title, String pathSmallIcon, String pathLargeIcon){
		super(title);
		putValue(LARGE_ICON_KEY, new ImageIcon(pathLargeIcon)) ;
		putValue(SMALL_ICON, new ImageIcon(pathSmallIcon)) ;
		setImageName(pathSmallIcon);
		setDescription(title);
	}
	
	public Link(String title, URL pathSmallIcon, URL pathLargeIcon){
		super(title);
		putValue(LARGE_ICON_KEY, new ImageIcon(pathLargeIcon)) ;
		putValue(SMALL_ICON, new ImageIcon(pathSmallIcon)) ;
		setImageURL(pathSmallIcon);
		setDescription(title);
	}
	
	public Link(String title, Icon icon){
		super(title, icon);
	}

	public Link(){
		
	}

	/**
	 * se ejecuta el link.
	 * si llegara a estar deshabilitado, se lanzar� una excepci�n.
	 * 
	 * @throws LinkException
	 */
	public void doExecute()throws LinkException{
		//verificamos que est� habilitado.
		if(!isEnabled())
			throw new LinkException(ExceptionMessagesBundle.link_disable);
		
		doMyExecute();		
	}
	
	/**
	 * retorna el �cono asociado al link
	 * @return
	 */
	public ImageIcon getIcon(){
		if( getImageURL() != null )
			return new ImageIcon(getImageURL());
		else
			return new ImageIcon(getImageName());
	}

	/**
	 * retorna la descripci�n del link.
	 * @return
	 */
	public String getDescription(){
		return this.description;
	}

	/**
	 * setea la descripci�n del link.
	 * @param description
	 */
	public void setDescription(String description){
		this.description = description;
	}
	
	/**
	 * retorna el nombre de la imagen asociada al link.
	 * @return
	 */
	public String getImageName(){
		return this.imageName;
	}
	
	/**
	 * setea el nombre de la imagen asociada al link.
	 * @param imageName
	 */
	public void setImageName(String imageName){
		this.imageName = imageName;
	}
	
	/**
	 * retorna la tecla asociada al link. 
	 * @return
	 */
	public int getKeyEvent(){
		return keyEvent;
	}
	
	/**
	 * setea la tecla asociada al link.
	 * @param keyEvent
	 */	
	public void setKeyEvent(int keyEvent) {
		this.keyEvent = keyEvent;
	}


	/**
	 * retorna el key stroke asociado al link. 
	 * @return
	 */
	public KeyStroke getKeyStroke(){
		return keyStroke;
	}

	/**
	 * setea el key stroke asociado al link.
	 * @param keyStroke
	 */
	public void setKeyStroke(KeyStroke keyStroke) {
		this.keyStroke = keyStroke;
	}	

	/**
	 * se ejecuta el link espec�fico.
	 * @throws LinkException
	 */
	protected abstract void doMyExecute() throws LinkException;

	/**
	 * se implementa el m�todo de AbstractAction.
	 * s�lo se llama al m�todo doExecute.
	 */
	public void actionPerformed(ActionEvent arg0) {
		try {
			doExecute();
		} catch (LinkException e) {
			//se informa del error al usuario
			DialogMessage.showErrorMessage(getDescription(), e.getMessage());
		}		
	}

	/**
	 * @return the imageURL
	 */
	public URL getImageURL() {
		return imageURL;
	}

	/**
	 * @param imageURL the imageURL to set
	 */
	public void setImageURL(URL imageURL) {
		this.imageURL = imageURL;
	}

	
	
}
