package com.migestion.swing.custom;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * JLabel que contendr� una imagen.
 * La imagen se mostrar� en su tama�o original o, en
 * caso de indicarlo, podemos verla en su totalidad
 * escalada dentro del label.
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public class JImageLabel extends JLabel{

	//para mostrar la imagen.
    private ImageIcon thumbnail = null;
    
    /**
     * seteamos la imagen a partir del �cono.
     * @param icon
     */
    public void setImage(ImageIcon icon){
    	//por defecto la imagen se muestra en su totalidad, escalada.
    	setImage(icon, true);
    }
    
    /**
     * seteamos la imagen a partir del �cono indicando 
     * si se quiere escalar o no.
     * 
     * @param icon
     * @param scaled
     */

    public void setImage(ImageIcon icon, boolean scaled){
    	
    	//si no viene nulo lo mostramos.
    	if(icon!=null){
    		if(scaled)
    			thumbnail = new ImageIcon(icon.getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_DEFAULT));
    		else
    			thumbnail = icon;
    	}
    	
    }

    /**
     * seteamos la imagen a partir de la url.
     * 
     * @param icon
     */
    public void setImage(String pathImage){
    	//por defecto la imagen se muestra en su totalidad, escalada.
    	setImage(new ImageIcon(pathImage), true);
    }

    /**
     * seteamos la imagen a partir de la url 
     * indicando si se quiere escalar o no.
     * 
     * @param pathImage
     * @param scaled
     */
    public void setImage(String pathImage, boolean scaled){    	
    	setImage(new ImageIcon(pathImage), scaled);
    }
    
    
    /**
     * redefinimos el paint para mostrar la imagen.
     */
    public void paint(Graphics g) { 
		super.paint(g); 
		if(thumbnail != null) { 
		    thumbnail.paintIcon(this, g, 0, 0); 
		} 
    }     
}
