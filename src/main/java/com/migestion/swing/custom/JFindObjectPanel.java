package com.migestion.swing.custom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.migestion.swing.factories.JTextFieldFactory;
import com.migestion.swing.navigation.LinkFindObject;
import com.migestion.swing.navigation.interfaces.ILinkWindowFindObject;
import com.migestion.swing.navigation.listeners.LinkFindObjectListener;
import com.migestion.swing.utils.StringUtils;

/**
 * JPanel utilizado para buscar un objeto dentro
 * de un listado.
 *  
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public class JFindObjectPanel extends JPanel implements Serializable, LinkFindObjectListener{

	//text field para ingresar el c�digo del objeto a seleccionar.
	JTextField txtCodeObjectFound;
	
	//text field para visualizar el objeto seleccionado.
	JTextField txtObjectFound;
	Object objectFound;
	//link para buscar el objeta.
	protected LinkFindObject link ;
	protected String title;
	protected String description;
	protected ILinkWindowFindObject windowFindObject;
	protected IObjectFoundInspector inspector;
	protected LinkFindObjectListener listener;
	protected int textWidth;
	protected int codeWidth;
	protected IFinderObjectByCode finderByCode;
	protected JButton btnFind;
    private PropertyChangeSupport propertySupport;

    private JPanel input;
    public JFindObjectPanel() {
        this(null, null);
        propertySupport = new PropertyChangeSupport(this);
    }
    public Dimension getPreferredSize() {
        return new Dimension(75, 28);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
      //  propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
     //   propertySupport.removePropertyChangeListener(listener);
    }
    
	/**
	 * Construye el panel para buscar un objeto.
	 * 
	 * @param windowFindObject ventana utilizada para buscar el objeto.
	 * @param listener listener al que se avisar� y se le dar� el objeto encontrado.
	 */
	public JFindObjectPanel(ILinkWindowFindObject windowFindObject, LinkFindObjectListener listener){
		
		this(windowFindObject, listener,150);
	}
	
	/**
	 * Construye el panel para buscar un objeto.
	 * 
	 * @param windowFindObject ventana utilizada para buscar el objeto.
	 * @param listener listener al que se avisar� y se le dar� el objeto encontrado.
	 * @param textWidth ancho del texto donde se muestra el objeto.
	 */
	public JFindObjectPanel(ILinkWindowFindObject windowFindObject, LinkFindObjectListener listener, int textWidth){
		
		this.windowFindObject = windowFindObject;
		this.listener = listener;
		this.textWidth = textWidth;
		
		//seteamos el layout.
		setLayout(new BorderLayout());
		
		input = new JPanel(new BorderLayout());
		//text field para visualizar el objeto seleccionado.
		txtObjectFound = JTextFieldFactory.getJTextField("",this.textWidth);
		txtObjectFound.setEditable(false);
		input.add(txtObjectFound, BorderLayout.EAST);		
		
		txtCodeObjectFound = JTextFieldFactory.getJTextField("",10);
		txtCodeObjectFound.setEditable(true);
		txtCodeObjectFound.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodeObjectFoundFocusLost(evt);
            }
        });		
		input.add(txtCodeObjectFound, BorderLayout.WEST);		
		
		this.add(input, BorderLayout.WEST);
		
		
		//espacio en blanco para separar el texto del bot�n.
		JLabel blank = new JLabel();
		blank.setPreferredSize(new Dimension(5,10));
		this.add(blank,BorderLayout.CENTER);

		//link para buscar el objeto.
		link = new LinkFindObject(this.windowFindObject, "");		
		link.addListener(this.listener);
		//el panel tambi�n ser� un listener para poder mostrar la descripci�n
		//del objeto seleccionado.
		link.addListener(this);
		
		//construimos un bot�n para el link.
		btnFind = new JButton(link);		
		btnFind.setPreferredSize(new Dimension(20,28));
		btnFind.setText("");
		btnFind.setBorder(null);
		btnFind.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.add(btnFind, BorderLayout.EAST);		
		
		this.setPreferredSize(new Dimension(textWidth+40,28));
	}
	/*
	 *  (non-Javadoc)
	 * @see com.ostrich.commonui.navigation.listeners.LinkFindObjectListener#objectFinded(java.lang.Object)
	 */
	public void objectFound(Object objectFound) {
		this.objectFound = objectFound;
		if(objectFound!=null){
			
			String code="";
			String description = objectFound.toString();
			if(this.inspector!=null){
				code = inspector.getCode(objectFound);
				description = inspector.getDescription(objectFound);
			}
			
			txtObjectFound.setText( description);
			txtCodeObjectFound.setText(code);
		}
		else{
			txtObjectFound.setText("");
			txtCodeObjectFound.setText("");
		}
	}

	public void setListener(LinkFindObjectListener listener) {
		this.listener = listener;
		link.addListener(this.listener);
	}
	
	public void setTextWidth(int textWidth) {
		this.textWidth = textWidth;
		txtObjectFound.setMaximumSize(new Dimension(textWidth, 28));
		txtObjectFound.setPreferredSize(new Dimension(textWidth, 28));
		txtObjectFound.setMinimumSize(new Dimension(textWidth, 28));		 		

	}
	public void setCodeWidth(int codeWidth) {
		this.codeWidth = codeWidth;
		txtCodeObjectFound.setMaximumSize(new Dimension(codeWidth, 28));
		txtCodeObjectFound.setPreferredSize(new Dimension(codeWidth, 28));
		txtCodeObjectFound.setMinimumSize(new Dimension(codeWidth, 28));		 		
	}
	
	public void setBounds(Rectangle r){
		super.setBounds(r);
		
		int botonwidth = btnFind.getWidth();
		
		//calculamos un porcentaje para texto y code.
		int codewidth = txtCodeObjectFound.getWidth();
		int textwidth = (int)r.getWidth() - codewidth - botonwidth;
		setTextWidth(textwidth);
		setCodeWidth(codewidth);
	}
	
	public void setBounds(int x, int y, int width, int height){
		super.setBounds(x, y, width, height);
		
		int botonwidth = btnFind.getWidth();
		
		//calculamos un porcentaje para texto y code.
		int codewidth = this.codeWidth;
		int textwidth = width - codewidth - botonwidth;
		setTextWidth(textwidth);
		setCodeWidth(codewidth);
		
	}
	
	public void setWindowFindObject(ILinkWindowFindObject dialog) {
		this.windowFindObject = windowFindObject;
		link.setDialog(dialog);
	}
	public IObjectFoundInspector getInspector() {
		return inspector;
	}
	public void setInspector(IObjectFoundInspector inspector) {
		this.inspector = inspector;
	}
	
	public IFinderObjectByCode getFinderByCode() {
		return finderByCode;
	}
	public void setFinderByCode(IFinderObjectByCode finderByCode) {
		this.finderByCode = finderByCode;
	}
	/**
	 * cuando sale del txt para ingresar el c�digo, buscamos el
	 * objeto con el controlador indicado.
	 * @param evt
	 */
    private void txtCodeObjectFoundFocusLost(java.awt.event.FocusEvent evt) {
    	
    	String code = txtCodeObjectFound.getText();
    	if(!StringUtils.isEmpty(code) && getFinderByCode()!=null){
	        Object objectFinded = getFinderByCode().getObject(code);
	        //mostramos el objecto en el panel.
	        objectFound(objectFinded);
	        //le avisamos el listener.
	        if(listener!=null)
	        	listener.objectFound(objectFinded);
        }else{
	        //le avisamos el listener.
	        if(listener!=null)
	        	listener.objectFound(null);
        	objectFound(null);
        }
    }

    /**
     * path de la imagen para el bot�n de b�squeda.
     * @param path
     */
    public void setImagePath(String path){
    	if(!StringUtils.isEmpty(path)){
    		link.putValue(link.LARGE_ICON_KEY, new ImageIcon(path));
        	btnFind.setIcon(new ImageIcon(path));    		
    	}
    }

    /**
     * se setea editable o no el componente.
     * @param editable
     */
    public void setEditable(boolean editable){
        btnFind.setEnabled(editable);
        txtCodeObjectFound.setEditable(editable);
        txtObjectFound.setEditable(false);
    }
	public Object getObjectFound() {
		return objectFound;
	}
	public void setObjectFound(Object objectFound) {
		this.objectFound = objectFound;
	}
	public JTextField getTxtCodeObjectFound() {
		return txtCodeObjectFound;
	}
	public JTextField getTxtObjectFound() {
		return txtObjectFound;
	}
    
    public void clear(){
    	txtCodeObjectFound.setText("");
    	txtObjectFound.setText("");
    	objectFound = null;
    }
    
    public void setFont(Font f){
    	
    	super.setFont(f);
    	if( txtObjectFound!= null )
    		txtObjectFound.setFont(f);
    	if( txtCodeObjectFound!= null )
    		txtCodeObjectFound.setFont(f);
    	
    }
    
    public void setForeground(Color fg){
    	
    	super.setForeground( fg );
    	if( txtObjectFound!= null )
    		txtObjectFound.setForeground(fg);
    	if( txtCodeObjectFound!= null )
    		txtCodeObjectFound.setForeground(fg);
    }
    
    public void setBackground(Color bg){
    	
    	super.setBackground( bg );
    	if( txtObjectFound!= null )
    		txtObjectFound.setBackground(bg);
    	if( txtCodeObjectFound!= null )
    		txtCodeObjectFound.setBackground(bg);
    }
    
    public void hideCode(){
    	
    	input.remove( txtCodeObjectFound );

		//calculamos un porcentaje para texto y code.
		int codewidth = txtCodeObjectFound.getWidth();
		int textwidth = txtObjectFound.getWidth();
		setTextWidth(textwidth + codewidth);
    }
}
