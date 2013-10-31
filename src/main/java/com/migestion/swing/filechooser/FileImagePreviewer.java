package com.migestion.swing.filechooser;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.border.BevelBorder;

/**
 * Utiliza un FileChooser para seleccionar una imagen
 * mostrando la previsualizaci�n en el mismo.
 * 
 * @author Bernardo Iribarne
 *
 */

public class FileImagePreviewer extends JComponent implements PropertyChangeListener { 
    ImageIcon thumbnail = null; 
    
    /**
     * Se instancia con un filechooser.
     * @param fc
     */
    public FileImagePreviewer(JFileChooser fc) {    	
		setPreferredSize(new Dimension(100, 50)); 
		fc.addPropertyChangeListener(this); 
		setBorder(new BevelBorder(BevelBorder.LOWERED)); 
    } 
     
    /**
     * carga la imagen desde el archivo.
     * @param f
     */
    public void loadImage(File f) { 
        if (f == null) { 
            thumbnail = null; 
        }else { 
		    	ImageIcon tmpIcon = new ImageIcon(f.getPath()); 
		    	if(tmpIcon.getIconWidth() > 90) { 
		    		thumbnail = new ImageIcon(tmpIcon.getImage().getScaledInstance(90, -1, Image.SCALE_DEFAULT)); 
		    	} else { 
		    		thumbnail = tmpIcon; 
		    	} 
        }	 
    } 
     
    /**
     * cuando se produce un cambio, se carga nuevamente
     * la imagen para la previsualizaci�n.
     * (implementaci�n de la interface PropertyChangeListener).
     */
    public void propertyChange(PropertyChangeEvent e) { 
		String prop = e.getPropertyName(); 
		if(prop == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) { 
		    if(isShowing()) { 
	                loadImage((File) e.getNewValue()); 
			repaint(); 
		    } 
		} 
    } 
     
    /**
     * redefinimos el paint para la previsualizaci�n.
     */
    public void paint(Graphics g) { 
		super.paint(g); 
		if(thumbnail != null) { 
		    int x = getWidth()/2 - thumbnail.getIconWidth()/2; 
		    int y = getHeight()/2 - thumbnail.getIconHeight()/2; 
		    if(y < 0) { 
		    	y = 0; 
		    } 
		     
		    if(x < 5) { 
		    	x = 5; 
		    } 
		    thumbnail.paintIcon(this, g, x, y); 
		} 
    } 
} 