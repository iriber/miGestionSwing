package com.migestion.swing.view.frames;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

import org.jdesktop.swingx.JXTaskPaneContainer;

import com.migestion.swing.navigation.Link;
import com.migestion.swing.navigation.LinkListCollection;
import com.migestion.swing.swingx.custom.StackedBox;


/**
 * Frame utilizado como contenedor de frames internos (JInternalFrame).
 * Contiene una toolbar y un desktop. La idea es que desde la toolbar
 * se ejecutan las distintas acciones del sistema, donde el ideal es
 * que las acciones sean objetos de la jerarqu�a de Link.
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */

public class JFrameContainer extends JFrame{

	//toolbar que contendr� los botones para abrir las ventanas.
	protected JToolBar toolbar;
	
	//desktop para contener los frames internos.
	protected JDesktopPane desktop;
	
	
	protected JXTaskPaneContainer taskpanecontainer;	
	protected StackedBox box;   
	protected JScrollPane scrollTaskpane;
	
	protected MenuDialog menuDialog;
	
	//-----------------
	//CONSTRUCTORES	
	//-----------------
	
	/**
	 * se contruye con un t�tulo.
	 * 
	 * @param title
	 */
	public JFrameContainer(String title){
		super(title);
		//creamos la ui
		createUI("");
	}
	
	/**
	 * se construye con un t�tulo y una imagen de fondo.
	 * 
	 * @param title
	 * @param pathImage
	 */
	public JFrameContainer(String title, String pathImage){
		super(title);
		//creamos la ui
		createUI(pathImage);
	}
	
	//--------------------
	//M�TODOS P�BLICOS	
	//--------------------
	
	/**
	 * se abre la ventana centrada en la pantalla.
	 */
	public void open() {
		//UbicacionVentana.centrar(this, true);
		this.setVisible(true);
	}	
	
    /**
     * agrega un link en la toolbar.
     * 
     * @param link
     */
	public void addLink(LinkListCollection link){
		JButton btnLink = new JButton(link.getDescription() ,link.getIcon());
		btnLink.addActionListener(link);
		btnLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLink.setMnemonic(link.getKeyEvent());
		btnLink.setMargin(new Insets(0,0,0,0));
		btnLink.setToolTipText(link.getDescription());
	    toolbar.add(btnLink);
	    //link.getDialog().addToJFrameContainer(this); 
    }
	
    /**
     * agrega un link en la toolbar.
     * 
     * @param link
     */
	public void addLink(Link link){
		JButton btnLink = new JButton(link.getDescription() ,link.getIcon());
		btnLink.addActionListener(link);
		btnLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLink.setMnemonic(link.getKeyEvent());
		btnLink.setToolTipText(link.getDescription());
	    toolbar.add(btnLink);	     
	    
	    
    }

	/**
	 * agrega el componente en el desktop.
	 * @param component
	 */
	public void addToDesktop(Component component){
		desktop.add(component);
	}
	
	
	//-------------------
	//M�TODOS PRIVADOS
	//-------------------
	
	/*
	 * inicializaci�n de la pantalla.
	 */
	private void createUI(String pathImage){
		
		setLayout( new BorderLayout() );		
		
		//inicializamos la toolbar.
		toolbar = new JToolBar();
		toolbar.setLayout( new FlowLayout() );
		
		getContentPane().add(toolbar, BorderLayout.NORTH);
		taskpanecontainer = new JXTaskPaneContainer();

		box = new StackedBox();   

		//box.addBox("Tool bar",toolbar);
		box.addBox("Panel de control", taskpanecontainer);
		
		scrollTaskpane  = new JScrollPane(taskpanecontainer);
		getContentPane().add( scrollTaskpane, BorderLayout.WEST);
		
//		menuDialog = new MenuDialog(this, false);
//		menuDialog.add( scrollTaskpane, BorderLayout.CENTER);
//		menuDialog.pack();
		//getContentPane().add(box, BorderLayout.WEST);
		
		//inicializamos el desktop.		
		desktop = new JDesktopPane();
		desktop.setBackground(Color.DARK_GRAY);
		getContentPane().add(desktop, BorderLayout.CENTER);
		
		setPreferredSize(new Dimension(450, 400));
	    pack();
	}
	
	public void toogleTaskPane(){
//		
		scrollTaskpane.setVisible( !scrollTaskpane.isVisible() );
        taskpanecontainer.setVisible(scrollTaskpane.isVisible() );
        getLayout().layoutContainer(getContentPane());
        this.pack();

//		menuDialog.setVisible( !menuDialog.isVisible() );
	}

	public void replaceToolbar(Component component){
		toolbar.setVisible(false);
		getContentPane().add(component, BorderLayout.NORTH);
		this.pack();
	}
}
