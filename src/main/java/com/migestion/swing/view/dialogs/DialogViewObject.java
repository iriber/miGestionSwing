package com.migestion.swing.view.dialogs;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.migestion.swing.controller.IControllerView;
import com.migestion.swing.controller.exception.ControllerException;
import com.migestion.swing.i18n.buttons.ButtonImagesBundle;
import com.migestion.swing.i18n.buttons.ButtonLabelsBundle;
import com.migestion.swing.navigation.interfaces.ILinkWindowObjectOpen;
import com.migestion.swing.navigation.interfaces.ILinkWindowView;
import com.migestion.swing.utils.UbicacionVentana;

/**
 * Di�logo que ser� utilizado como base para visualizar un objeto.
 * 
 * Tendr� que ser instanciado con el controlador espec�fico
 * el cu�l se encargar� de leer del modelo el objeto a visualizar.
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public abstract class DialogViewObject extends DialogOkCancel implements ILinkWindowView, ILinkWindowObjectOpen{

	//controlador que se encargar� de agregar al 
	//modelo el objeto creado al modelo
	private IControllerView controller; 
	
	//bot�n para cerrar la ventana
	private JButton btnClose;

	//objeto a visualizar
	protected Object objectToView = null;
	
	
	//------------------
	// CONSTRUCTORES
	//------------------

	/**
	 * 
	 */
	public DialogViewObject(String title, IControllerView controller) {
		super(title);
		this.controller = controller;
	}

	/**
	 * 
	 * @param title
	 * @param pathImage
	 */
	public DialogViewObject(String title, IControllerView controller, String pathImage) {
		super(title, pathImage);
		this.controller = controller;
	}
	
	
	/**
	 * se abre el di�logo centrado en la pantalla.
	 */
	public void open(Object objectToView) {
		
		try {
			//se obtiene el objeto del modelo.
			this.objectToView = controller.getObject(objectToView);
			//seteamos el objeto en pantalla.
			showObject();
			//abrimos la pantalla.
			UbicacionVentana.centrar(this, false);
			this.setVisible(true);		

		} catch (ControllerException e) {
			//informamos al usuario del error.
			DialogMessage.showErrorMessage(this.getTitle(), e.getMessage());	
		}
	}
	
	/**
	 * se cierra el di�logo.
	 */
	protected void doClose(){
		this.dispose();
	}
	
	/**
	 * se cierra el di�logo.
	 */
	protected void doOk(){
		this.doClose();
	}

	/**
	 *  se construye el panel para el bot�n close,
	 *  utilizando un layout.
	 */
	protected JPanel getButtonPanel(){

		//inicializamos los botones
		initializeButtons();
		
		//creamos el panel para los botones
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());		
		btnPanel.add(btnClose);
		
		JPanel btnPanelSuperior = new JPanel();
		btnPanelSuperior.setLayout(new BorderLayout());
		btnPanelSuperior.add(btnPanel, BorderLayout.CENTER);
		btnPanelSuperior.setMinimumSize(new Dimension(220,50));
		btnPanelSuperior.setPreferredSize(new Dimension(220,50));
		return btnPanelSuperior;
	}
	
	/**
	 *  se construye el panel para el bot�n close,
	 *  sin la utilizaci�n de un layout, tomando como referencia
	 *  las medidas del inputPanel.
	 */
	protected JPanel getButtonPanel(int x, int y, int width, int height){
		//creamos el bot�n
		initializeButtons();
		//determinamos la ubicaci�n de los botones
		int centroPanel = width / 2;
		int x_Close = centroPanel - 46;
		btnClose.setBounds(new Rectangle(x_Close, 0, 98, 23));

		//creamos el panel para los botones
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(null);
		btnPanel.setBorder(null);
		btnPanel.setOpaque(false);
		btnPanel.add(btnClose);
		btnPanel.setBounds(new Rectangle(x, y, width, height));
		
		return btnPanel;
	}

	/**
	 * se inicializa el bot�n Close.
	 * los par�metros determinan el tama�o del rect�ngulo
	 * donde se dibujar�n los botones.
	 * 
	 * @param width
	 * @param height
	 */
	private void initializeButtons() {
		
		//bot�n close
		btnClose = new JButton(ButtonLabelsBundle.btn_Close);
		
		btnClose.setSize(new Dimension(98,23));
		btnClose.setToolTipText(ButtonLabelsBundle.btn_Close_ToolTipText);
		btnClose.setIcon(new ImageIcon(ButtonImagesBundle.btn_Close));
		btnClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnClose.setMnemonic(java.awt.event.KeyEvent.VK_ENTER);
		btnClose.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            doClose();
	          }
	        });

	}
	
	/**
	 * se visualiza el objeto.
	 * cada subclase definir� qu� campos deben llenarse.
	 * 
	 * @return
	 */
	protected abstract void showObject();	
}
