package com.migestion.swing.view.inputs;

import java.awt.Color;

import javax.swing.JComponent;

/**
 * Inspecciona el component para determinar el valor
 * asociado.
 * @author Bernardo Iribarne {Ostrich group}
 *
 */
public interface InputInspector {

	/**
	 * retorna el valor del input.
	 * @param component
	 * @return
	 */
	public Object getValue(JComponent component);

	/**
	 * retorna si el input tiene valor o no.
	 * @param component
	 * @return
	 */
	public Boolean hasValue(JComponent component);
	
	/**
	 * 
	 * @param color
	 * @param component
	 */
	public void setColor(Color color, JComponent component);
	
	
	
}
