package com.migestion.swing.factories;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * Proporciona m�todos para crear labels con las
 * propiedades m�s utilizadas.
 * 
 * @author Bernardo Iribarne
 *
 */
public class JLabelFactory {
	
	/**
	 * crea un jLabel con el texto <code>text</code> y
	 * el ancho <code>width</code>
	 * @param text
	 * @param width
	 * @return
	 */
	public static JLabel getJLabel(String text, int width){
		JLabel label = new JLabel();
		label.setMinimumSize(new Dimension(width, 16));
		label.setMaximumSize(new Dimension(width, 16));
		label.setPreferredSize(new Dimension(width, 16));
		label.setText(text);
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		return label;
	}

	/**
	 * crea un jLabel con el texto <code>text</code>,
	 * el ancho <code>width</code> y alineaci�n 
	 * horizontal <code>horizontalAligment</code>
	 * @param text
	 * @param width
	 * @return
	 */
	public static JLabel getJLabel(String text, int width, int horizontalAligment){
		JLabel label = getJLabel(text, width);
		label.setHorizontalAlignment(horizontalAligment);
		return label;
	}

}
