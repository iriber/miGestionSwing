package com.migestion.swing.factories;

import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JList;
import javax.swing.ListModel;

/**
 * Proporciona m�todos para crear JList con las
 * propiedades m�s utilizadas.
 * 
 * @author Bernardo Iribarne
 *
 */
public class JListFactory {
	
	/**
	 * crea un jList con el modelo <code>model</code> y
	 * ancho <code>width</code>
	 * @param model
	 * @param width
	 * @return
	 */
	public static JList getJList(ListModel model, int width){
		JList list = new JList(model);
		list.setMaximumSize(new Dimension(width, 20));
		list.setPreferredSize(new Dimension(width, 20));
		list.setMinimumSize(new Dimension(width, 20));
		return list;
	}

	/**
	 * crea un jList con los elementos <code>elements</code> y
	 * ancho <code>width</code>
	 * @param model
	 * @param width
	 * @return
	 */
	public static JList getJList(Vector elements, int width){
		JList list = new JList(elements);
		list.setMaximumSize(new Dimension(width, 20));
		list.setPreferredSize(new Dimension(width, 20));
		list.setMinimumSize(new Dimension(width, 20));
		return list;
	}

}
