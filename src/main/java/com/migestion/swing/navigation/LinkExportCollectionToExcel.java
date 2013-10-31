package com.migestion.swing.navigation;

import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import com.migestion.swing.i18n.exceptions.ExceptionMessagesBundle;
import com.migestion.swing.i18n.links.LinkImagesBundle;
import com.migestion.swing.i18n.links.LinkLabelsBundle;
import com.migestion.swing.model.UICollection;
import com.migestion.swing.navigation.exception.LinkException;
import com.migestion.swing.navigation.interfaces.ILinkPrintCollection;
import com.migestion.swing.view.output.excel.CollectionExcelPrinter;
import com.migestion.swing.view.output.exceptions.HtmlPrinterException;

/**
 * Link para enviar a imprimr una colecci�n en excel.
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public class LinkExportCollectionToExcel extends Link {


	//dialogo para crear el objeto
	protected UICollection uiCollection;
	
	//listener al cual se le pide la colecci�n.
	protected ILinkPrintCollection listener;
	
	//CONTRUCTORES	
	public LinkExportCollectionToExcel(){
		super(LinkLabelsBundle.link_Print_excel, LinkImagesBundle.link_Print_excel);
		//key stroke por default
		setKeyStroke(KeyStroke.getKeyStroke(LinkLabelsBundle.link_Print_excel_KeyStroke));
		//key event por default
		setKeyEvent(KeyEvent.VK_F3);
		//imagen por default
		//setImageName(LinkImagesBundle.link_Print_excel);
		//descripci�n por default
		setDescription(LinkLabelsBundle.link_Print_excel);		
	}
	
	/**
	 * se setea la colecci�n a imprimir.
	 * @param uiCollection
	 */
	public void setUICollectionToPrint(UICollection uiCollection){
		this.uiCollection = uiCollection;
	}

	/**
	 * se setea el listener que nos dar� la colecci�n a imprimir.
	 * @param listener
	 */
	public void setILinkPrintCollection(ILinkPrintCollection listener){
		this.listener = listener;
	}
	
	/**
	 * se ejecuta la acci�n.
	 */
	protected void doMyExecute() throws LinkException{

		//controlamos que se haya seteado el listener al cual se le pide la colecci�n.
		if(getListener()==null)		
			throw new LinkException(ExceptionMessagesBundle.link_Print_listenerNotDefined);
		
		//seteamos la colecci�n.
		setUICollectionToPrint(getListener().getUICollectionToPrint());

		//controlamos que se haya seteado la colecci�n a imprimir.
		if(uiCollection==null)
			throw new LinkException(ExceptionMessagesBundle.link_Print_collectionNotDefined);
	
		CollectionExcelPrinter printer = new CollectionExcelPrinter(uiCollection.getDescription());
		try {
			printer.print(uiCollection);
		} catch (HtmlPrinterException e) {
			throw new LinkException(e);
		}
	}
	
	protected ILinkPrintCollection getListener(){
		return listener;
	}
}
