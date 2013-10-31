package com.migestion.swing.factories;

import java.awt.Dimension;

import javax.swing.JTextField;

import com.migestion.swing.utils.LimitedStyledDocument;

/**
 * Proporciona m�todos para crear JTextField con las
 * propiedades m�s utilizadas.
 * 
 * @author Bernardo Iribarne
 *
 */
public class JTextFieldFactory {
	
	/**
	 * crea un jTextField con el texto <code>text</code>,
	 * un ancho "preferible" <code>width</code> y  con un l�mite 
	 * de caracteres de entrada definido por <code>maxCharacters</code>.
	 * 
	 * @param text
	 * @param width
	 * @param maxCharacters
	 * @return
	 */
	public static JTextField getJTextField(String text, int width, int maxCharacters){
		JTextField  field = getJTextField(text, width);		
		field.setDocument(new LimitedStyledDocument(maxCharacters));
		return field;
	}

	/**
	 * crea un jTextField con el texto <code>text</code>,
	 * un ancho "preferible" <code>width</code>.
	 * 
	 * @param text
	 * @param width
	 * @return
	 */
	public static JTextField getJTextField(String text, int width){
		JTextField  field = new JTextField();
		field.setText(text);				
		field.setMaximumSize(new Dimension(width, 20));
		field.setPreferredSize(new Dimension(width, 20));
		field.setMinimumSize(new Dimension(width, 20));		 		
		return field;
	}



}
