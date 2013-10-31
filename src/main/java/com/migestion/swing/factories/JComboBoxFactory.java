package com.migestion.swing.factories;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.JComboBox;

/**
 * Proporciona m�todos para crear combos con las
 * propiedades m�s utilizadas.
 * 
 * @author Bernardo Iribarne
 *
 */
public class JComboBoxFactory {
	
	/**
	 * crea un jComboBox con el modelo <code>model</code> y
	 * ancho <code>width</code>
	 * @param model
	 * @param width
	 * @return
	 */
	public static JComboBox getJComboBox(ComboBoxModel model, int width){
		JComboBox cmb = new JComboBox(model);
		cmb.setMaximumSize(new Dimension(width, 20));
		cmb.setPreferredSize(new Dimension(width, 20));
		cmb.setMinimumSize(new Dimension(width, 20));
		return cmb;
	}

	/**
	 * crea un jComboBox con los elementos <code>elements</code> y
	 * ancho <code>width</code>
	 * @param model
	 * @param width
	 * @return
	 */
	public static JComboBox getJComboBox(Vector elements, int width){
		JComboBox cmb = new JComboBox(elements);
		cmb.setMaximumSize(new Dimension(width, 20));
		cmb.setPreferredSize(new Dimension(width, 20));
		cmb.setMinimumSize(new Dimension(width, 20));
		return cmb;
	}

}
