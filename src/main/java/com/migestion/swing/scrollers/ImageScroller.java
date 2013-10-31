package com.migestion.swing.scrollers;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

/**
 * JScroll para mostrar una imagen.
 * 
 * @author Bernardo Iribarne
 *
 */
public class ImageScroller extends JScrollPane { 
	  
	//label para mostrar la imagen.
	private JLabel lblImage;
	
	/**
	 * se construye el image scroller con una imagen.
	 * 
	 * @param icon
	 * @param background
	 */
	 public ImageScroller(Icon icon, Color background) { 
	     super(); 
	     JPanel p = new JPanel(); 
	     p.setBackground(background); 
	     p.setLayout(new BorderLayout() ); 
	      
	     lblImage = new JLabel(icon);
	     p.add(lblImage, BorderLayout.CENTER); 
	      
	     getViewport().add(p); 
	     getHorizontalScrollBar().setUnitIncrement(10); 
	     getVerticalScrollBar().setUnitIncrement(10); 
	     
	 } 
	  
		/**
		 * se construye el image scroller con una imagen y
		 * un texto.
		 * 
		 * @param icon
		 * @param text
		 * @param background
		 */
		 public ImageScroller(Icon icon, String text, Color background) { 
		     super(); 
		   
		     JPanel p = new JPanel(); 
		     p.setBackground(background); 
		     p.setLayout(new BorderLayout() ); 
		      
		     lblImage = new JLabel();
		     setTexAndImage(text, icon);
		     p.add(lblImage, BorderLayout.CENTER); 
		      
		     getViewport().add(p); 
		     getHorizontalScrollBar().setUnitIncrement(10); 
		     getVerticalScrollBar().setUnitIncrement(10); 
		     
		 } 

	 /**
	  * se construye el image scroller con un texto inicial.
	  * 
	  * @param text
	  * @param background
	  */
	 public ImageScroller(String text, Color background) { 
	     super(); 
	     JPanel p = new JPanel(); 
	     p.setBackground(background); 
	     p.setLayout(new BorderLayout() ); 
	     
	     lblImage = new JLabel(text);
	     lblImage.setHorizontalAlignment(SwingConstants.CENTER);
	     p.add(lblImage, BorderLayout.CENTER); 
	      
	     getViewport().add(p); 
	     getHorizontalScrollBar().setUnitIncrement(10); 
	     getVerticalScrollBar().setUnitIncrement(10); 
	 } 

	 /**
	  * se setea la imagen a mostrar.
	  * @param icon
	  */
	 public void setImage(Icon icon){
		 lblImage.setText("");
		 lblImage.setIcon(icon);
	 }
	 
	 /**
	  * borra la imagen mostrando un texto alternativo.
	  * @param text
	  */
	 public void cleanImage(String text){
		 lblImage.setText(text);
		 lblImage.setIcon(null);
	 }

	 /**
	  * se setea la imagen a mostrar.
	  * @param icon
	  */
	 public void setTexAndImage(String text, Icon icon){		 
		 lblImage.setVerticalAlignment(SwingConstants.TOP);
		 lblImage.setHorizontalAlignment(SwingConstants.CENTER);
		 lblImage.setText(text);
		 lblImage.setIcon(icon);
	 }
} 
	    