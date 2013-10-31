package com.migestion.swing.factories;


import java.awt.Dimension;

import javax.swing.JTextArea;

import com.migestion.swing.utils.LimitedStyledDocument;

/**
 * Proporciona m�todos para crear JTextArea con las
 * propiedades m�s utilizadas.
 * 
 * @author Bernardo Iribarne
 *
 */
public class JTextAreaFactory {
	
	/**
	 * crea un jTextArea con el texto <code>text</code>,
	 * un ancho "preferible" <code>width</code>, con un l�mite 
	 * de caracteres de entrada definido por <code>maxCharacters</code>.
	 * 
	 * @param text
	 * @param width
	 * @param maxCharacters
	 * @return
	 */
	public static JTextArea getJTextArea(String text, int width, int maxCharacters){
		JTextArea  field = getJTextArea(text, width);
		field.setDocument(new LimitedStyledDocument(maxCharacters));
		return field;
	}

	/**
	 * crea un jTextArea con el texto <code>text</code>,
	 * un ancho "preferible" <code>width</code>.
	 * 
	 * @param text
	 * @param width
	 * @return
	 */
	public static JTextArea getJTextArea(String text, int width){
		JTextArea  field = new JTextArea();
		field.setText(text);
		field.setMaximumSize(new Dimension(width, 20));
		field.setPreferredSize(new Dimension(200, 20));
		field.setMinimumSize(new Dimension(200, 20));
		return field;
	}

}
