package com.migestion.swing.view.dialogs;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.migestion.swing.factories.PropertyInputFactory;
import com.migestion.swing.i18n.buttons.ButtonImagesBundle;
import com.migestion.swing.i18n.buttons.ButtonLabelsBundle;
import com.migestion.swing.utils.StringUtils;
import com.migestion.swing.utils.UbicacionVentana;
import com.migestion.swing.view.inputs.PropertyInput;
import com.migestion.swing.view.inputs.PropertyInputCollection;

/**
 * Di�logo gen�rico de la forma Ok/Cancel.
 * 
 *  
 * @author Bernardo Iribarne
 * 
 */
public abstract class DialogOkCancel extends JDialog implements IDialogWithInput{

	//bot�n aceptar
	private JButton btnOk;
	//bot�n cancelar
	private JButton btnCancel;

	//variable para determinar si el usuario acept� o cancel�
	//la operaci�n
	protected boolean accepted = false;

	//panel donde estar� la informaci�n a mostrar
	//(lo definir� cada sublcase)
	protected Container infoPanel;
	
	//se guardar�n las properties inputs que
	//se mostrar�n en el di�logo.
	protected PropertyInputCollection propertiesInput = null;
	
	//path de la imagen a mostrar como fondo del di�logo
	private String pathImage;
	
	//------------------
	// CONSTRUCTORES
	//------------------
	public DialogOkCancel(String title) {
		super(new Frame(), title, true);
		initialize();			
	}

	public DialogOkCancel(String title, String pathImage) {
		super(new Frame(), title, true);
		this.pathImage = pathImage;
		initialize();			
	}	


	/**
	 * retorna true si el usuario acept� la operaci�n
	 * 
	 * @return
	 */
	public boolean isAcepted(){
		return this.accepted;
	}

	/**
	 * se inicializa el di�logo. (t�tulo, size, botones, etc).
	 */
	protected void initialize(){
		
        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doCancel();
                
            }
        });

		
		//inicializamos las properties input
		propertiesInput = new PropertyInputCollection();
		
		//obtenemos el panel para la entrada de la informaci�n.
		//este panel nos define el tama�o de la ventana
		infoPanel = createInfoPanel();

		if( infoPanel != null ){
			this.setBackground(infoPanel.getBackground());
			//si tiene definido un layout, definimos esta ventana
			//con layout, sino la inicializamos sin layout
			if(getInfoPanel().getLayout()==null){
				initializeWithoutLayout();
			}else{
				initializeWithLayout();
				pack();
			}
		}
	}

	protected void initialize(Container panel){
		
		infoPanel = panel;
		
		initialize();
		
		
	}
	/*
	 * se inicializa con un layout manager.
	 */
	private void initializeWithLayout() {
		getContentPane().setLayout(new BorderLayout());
		
		//si tenemos imagen de fondo instanciamos
		//un contenedor con la imagen.
		if(!StringUtils.isEmpty(pathImage)){			
			Container container = getContainerWithImage(new BorderLayout());
			container.add(getInfoPanel(), BorderLayout.NORTH);
			container.add(getButtonPanel(), BorderLayout.SOUTH);
			getContentPane().add(container, BorderLayout.CENTER);
			
		}else{			
			getContentPane().add(getInfoPanel(), BorderLayout.NORTH);
			getContentPane().add(getButtonPanel(), BorderLayout.SOUTH);
			
		}			

		
		
	}


	/*
	 * se inicializa sin layout manager.
	 */
	private void initializeWithoutLayout() {
		//si el ancho es menor a lo necesario para los botones
		//ok y cancel, lo cambiamos
		int width_dialog = (getInfoPanel().getWidth()>220)?getInfoPanel().getWidth():220;
		
		//construimos un panel con los botones ok y cancel
		int x_btnPanel = 0;
		int y_btnPanel = getInfoPanel().getHeight() + 10;
		int width_btnPanel = width_dialog;
		int height_btnPanel = 30;
		
		JPanel btnPanel = getButtonPanel(x_btnPanel, y_btnPanel, width_btnPanel, height_btnPanel);
		
		//agregamos en el panel principal el inputPanel y buttonPanel
		getContentPane().setLayout(null);
		
		if(!StringUtils.isEmpty(pathImage)){
			Container container = getContainerWithImage(null);
			container.add(getInfoPanel());
			container.add(btnPanel);
			container.setSize(new Dimension(width_dialog,getInfoPanel().getHeight() + 80));
			getContentPane().add(container);

//			JLabel label = new JLabel("");
//			label.setLayout(null);
//			label.setIcon(new CustomImageIcon(pathImage));
//			label.setSize(new Dimension(width_dialog,infoPanel.getHeight() + 80));
//			infoPanel.setOpaque(false);
//			btnPanel.setOpaque(false);
//			label.add(infoPanel);
//			label.add(btnPanel);
//			getContentPane().add(label);
		}else{
			getContentPane().add(getInfoPanel());
			getContentPane().add(btnPanel);
			
		}
		
		//seteamos el tama�o del di�logo
		setSize(width_dialog,getInfoPanel().getHeight() + 80);
	}
	
	/*
	 * retorna un contenedor con la imagen de fondo.
	 */
	private Container getContainerWithImage(LayoutManager layout){
		//getInfoPanel().setOpaque(false);
		JLabel label = new JLabel("");
		label.setLayout(layout);
		label.setIcon(new ImageIcon(pathImage));
		return label;
	}

	/**
	 * se abre el di�logo centrado en la pantalla.
	 */
	public void open() {
		UbicacionVentana.centrar(this, false);
		this.setVisible(true);
	}
	
	/**
	 * se cierra el di�logo.
	 */
	protected void doCancel(){
		this.accepted = false;
		this.dispose();
	}
	
	/**
	 *  se construye el panel para los botones Ok y Cancel,
	 *  utilizando un layout.
	 */
	protected JPanel getButtonPanel(){

		//inicializamos los botones
		initializeButtons();
		
		//creamos el panel para los botones
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(new FlowLayout());		
		btnPanel.add(btnOk);
		btnPanel.add(btnCancel);
		
		JPanel btnPanelSuperior = new JPanel();
		btnPanelSuperior.setLayout(new BorderLayout());
		btnPanelSuperior.add(btnPanel, BorderLayout.CENTER);
		btnPanelSuperior.setMinimumSize(new Dimension(220,50));
		btnPanelSuperior.setPreferredSize(new Dimension(220,50));
		
		btnPanel.setBackground( this.getBackground() );
		btnPanelSuperior.setBackground( this.getBackground() );
		
		return btnPanelSuperior;
	}
	/**
	 *  se construye el panel para los botones Ok y Cancel,
	 *  sin la utilizaci�n de un layout, tomando como referencia
	 *  las medidas del inputPanel.
	 */
	protected JPanel getButtonPanel(int x, int y, int width, int height){
		//creamos los botones
		initializeButtons();
		//determinamos la ubicaci�n de los botones
		int centroPanel = width / 2;
		int x_Ok = centroPanel - 100;
		int x_Cancel = centroPanel + 2;
		btnOk.setBounds(new Rectangle(x_Ok, 0, 98, 23));
		btnCancel.setBounds(new Rectangle(x_Cancel, 0, 98, 23));
		
		//creamos el panel para los botones
		JPanel btnPanel = new JPanel();
		btnPanel.setLayout(null);
		btnPanel.setBorder(null);
		btnPanel.setOpaque(false);
		btnPanel.add(btnOk);
		btnPanel.add(btnCancel);
		btnPanel.setBounds(new Rectangle(x, y, width, height));
		
		btnPanel.setBackground( this.getBackground() );
		return btnPanel;
	}

	/**
	 * se inicializan los botones Ok y Cancel. 
	 */
	private void initializeButtons() {
		
		//bot�n OK
		btnOk = new JButton(ButtonLabelsBundle.btn_Ok);
//		btnOk.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnOk.setMinimumSize(new Dimension(100, 23));
		btnOk.setToolTipText(ButtonLabelsBundle.btn_Ok_ToolTipText);
		
		//btnOk.setIcon(new ImageIcon(ButtonImagesBundle.btn_Ok));
		btnOk.setIcon(new ImageIcon(ButtonImagesBundle.btn_Ok));
		
		btnOk.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnOk.setMnemonic(java.awt.event.KeyEvent.VK_ENTER);
		btnOk.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            doOk();
	          }
	        });

		//bot�n Cancel
		btnCancel = new JButton(ButtonLabelsBundle.btn_Cancel);
//		btnCancel.setFont(new Font("Dialog", Font.PLAIN, 10));
		btnCancel.setMinimumSize(new Dimension(110, 23));
		btnCancel.setToolTipText(ButtonLabelsBundle.btn_Cancel_ToolTipText);
		btnCancel.setIcon(new ImageIcon(ButtonImagesBundle.btn_Cancel));
		btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancel.setMnemonic(java.awt.event.KeyEvent.VK_ESCAPE);
		btnCancel.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            doCancel();
	          }
	        });
	}
	
	/**
	 * se acepta la operaci�n. 
	 */
	protected abstract void doOk();
		
	/**
	 * retorna el panel donde se mostrar� la informaci�n deseada.
	 * cada subclase definir� qu� informaci�n se mostrar�.
	 */
	protected abstract Container createInfoPanel();
	
	
	/**
	 * retorna el panel donde se encuentra la informaci�n.
	 * @return
	 */
	protected Container getInfoPanel(){
		return this.infoPanel;
	}
	
	/**
	 * genera por default un panel donde se mostrar� la
	 * informaci�n definida en la colecci�n de properties
	 * input <code>propertiesInput</code>
	 *
	 */
	public JPanel autoGenerateInfoPanel(int columns){		
		return PropertyInputFactory.getJPanelProperty(this.propertiesInput, columns);
	}

	/**
	 * retorna el input asociado a la clave <code>key</code>
	 * @param key
	 * @return
	 */
	public Component getInput(String key){
		return this.propertiesInput.get(key).getInput();
	}
	
	/**
	 * se agrega un propertyInput
	 * @param propertyInput
	 */
	public void addPropertyInput(PropertyInput propertyInput){
		this.propertiesInput.add(propertyInput);
	}
	
	/*
	 *  (non-Javadoc)
	 * @see java.awt.Window#processWindowEvent(java.awt.event.WindowEvent)
	 */
	protected void processWindowEvent(WindowEvent e) {
	    //por si se cierra la ventana desde el �cono "X".
		super.processWindowEvent(e);
	    
	    if(e.getID()==WindowEvent.WINDOW_CLOSING){
	    	doCancel();	    	
	    }
	}
	
	public PropertyInputCollection getPropertyInputs() {
		return this.propertiesInput;
	}
	
	
	
}
