package com.migestion.swing.navigation;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.migestion.swing.i18n.exceptions.ExceptionMessagesBundle;
import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.model.UICollection;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkPrintCollection;
import com.migestion.swing.view.output.IReportPrinter;
import com.migestion.swing.view.output.exceptions.ReportPrinterException;

/**
 * Link para enviar a imprimir una colecci�n.
 * 
 * @author Bernardo Iribarne
 *
 */
public class LinkPrintCollection extends Link {


	//colecci�n a imprimir.
	protected UICollection uiCollection;
	//para imprimir.
	protected IReportPrinter printer;
	
	//listener al cual se le pide la colecci�n.
	protected ILinkPrintCollection listener;
	
	//CONTRUCTORES	
	public LinkPrintCollection(){
		super(LinkLabelsBundle.link_Print, LinkImagesBundle.link_Print);
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Print_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_F4);
		//imagen por default
		setImageURL(LinkImagesBundle.link_Print);
		//descripci�n por default
		setDescription(LinkLabelsBundle.link_Print);		
	}

	public LinkPrintCollection(String title){
		super(title, LinkImagesBundle.link_Print);
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Print_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_F4);
		//imagen por default
		setImageURL(LinkImagesBundle.link_Print);
		//descripci�n por default
		setDescription(title);		
	}

	/**
	 * se setea la colecci�n a imprimir.
	 * @param uiCollection
	 */
	public void setUICollectionToPrint(UICollection uiCollection){
		this.uiCollection = uiCollection;
	}

	/**
	 * se setea el manejador de la impresi�n.
	 * @param printer
	 */
	public void setIReportPrinter(IReportPrinter printer){
		this.printer = printer;
	}	
	
	public IReportPrinter getIReportPrinter(){
		return printer;
	}
	
	/**
	 * se setea el listener que nos dar� la colecci�n a imprimir.
	 * @param listener
	 */
	public void setILinkPrintCollection(ILinkPrintCollection listener){
		this.listener = listener;
	}
	
	public ILinkPrintCollection getILinkPrintCollection(){
		return listener;
	}
	
	/**
	 * se ejecuta la acci�n.
	 */
	protected void doMyExecute() throws LinkException{

		//controlamos que se haya seteado el listener al cual se le pide la colecci�n.
		if(getILinkPrintCollection()==null)
			throw new LinkException(ExceptionMessagesBundle.link_Print_listenerNotDefined);

		//seteamos la colecci�n.
		setUICollectionToPrint(getILinkPrintCollection().getUICollectionToPrint());
		
		//controlamos que se haya seteado la colecci�n a imprimir.
		if(uiCollection==null)
			throw new LinkException(ExceptionMessagesBundle.link_Print_collectionNotDefined);
	
		//controlamos que se haya seteado el manejador.
		if(getIReportPrinter()==null)
			throw new LinkException(ExceptionMessagesBundle.link_Print_printerNotDefined);

		try {
			getIReportPrinter().print(uiCollection);
		} catch (ReportPrinterException e) {
			throw new LinkException(e);
		}
		
	}
}
