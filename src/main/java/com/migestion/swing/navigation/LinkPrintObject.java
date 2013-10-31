package com.migestion.swing.navigation;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.migestion.swing.i18n.exceptions.ExceptionMessagesBundle;
import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkPrintObject;
import com.migestion.swing.view.output.IObjectPrinter;
import com.migestion.swing.view.output.exceptions.ReportPrinterException;

/**
 * Link para enviar a imprimir un objeto.
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public class LinkPrintObject extends Link {


	//para imprimir.
	protected IObjectPrinter printer;	
	//listener al cual se le pide el objeto a imprimir.
	protected ILinkPrintObject listener;
	
	//CONTRUCTORES	
	public LinkPrintObject(IObjectPrinter printer){
		super(LinkLabelsBundle.link_PrintObject, LinkImagesBundle.link_Print);
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_PrintObject_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_PRINTSCREEN);
		//imagen por default
		setImageURL(LinkImagesBundle.link_Print);
		//descripci�n por default
		setDescription(LinkLabelsBundle.link_Print);
		//seteamos el printer, si es nula se deshabilita el link.
		this.printer = printer;
//		if(printer==null)
//			setEnabled(false);
	}
	
	public LinkPrintObject(IObjectPrinter printer, String title){
		super(title, LinkImagesBundle.link_Print);
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_PrintObject_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_PRINTSCREEN);
		//imagen por default
		setImageURL(LinkImagesBundle.link_Print);
		//descripci�n por default
		setDescription(title);
		//seteamos el printer, si es nula se deshabilita el link.
		this.printer = printer;
//		if(printer==null)
//			setEnabled(false);
	}

	/**
	 * se setea el manejador de la impresi�n.
	 * @param printer
	 */
	public void setPrinter(IObjectPrinter printer){
		this.printer = printer;
		setEnabled(true);
	}	
	
	/**
	 * se setea el listener que nos dar� el objeto a imprimir.
	 * @param listener
	 */
	public void setILinkPrintObject(ILinkPrintObject listener){
		this.listener = listener;
	}
	
	
	/**
	 * se ejecuta la acci�n.
	 */
	protected void doMyExecute() throws LinkException{

		//controlamos que se haya seteado el listener al cual se le pide el object.
		if(getILinkPrintObject()==null)
			throw new LinkException(ExceptionMessagesBundle.link_Print_listenerNotDefined);

		//controlamos que se haya seteado el objeto a imprimir.
		if(getILinkPrintObject().getObjectToPrint()==null)
			throw new LinkException(ExceptionMessagesBundle.link_Print_objectNotDefined);
	
		//controlamos que se haya seteado el manejador.
		if(getPrinter()==null)
			throw new LinkException(ExceptionMessagesBundle.link_Print_printerNotDefined);

		try {
			getPrinter().print(getILinkPrintObject().getObjectToPrint());
		} catch (ReportPrinterException e) {
			throw new LinkException(e);
		}
		
	}
	
	protected IObjectPrinter getPrinter(){
		return printer;
	}
	
	protected ILinkPrintObject getILinkPrintObject(){
		return listener;
	}
}
