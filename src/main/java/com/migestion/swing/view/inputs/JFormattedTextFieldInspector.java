package com.migestion.swing.view.inputs;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFormattedTextField;

/**
 * Inspector para un text field.
 * @author Bernardo Iribarne {Ostrich group}
 *
 */
public class JFormattedTextFieldInspector implements InputInspector{

	/*
	 * (non-Javadoc)
	 * @see com.ostrich.commonui.view.inputs.InputInspector#getValue(javax.swing.JComponent)
	 */
	public Object getValue(JComponent component) {
		return ((JFormattedTextField)component).getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see com.ostrich.commonui.view.inputs.InputInspector#hasValue(javax.swing.JComponent)
	 */
	public Boolean hasValue(JComponent component) {
		return getValue(component) != null;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ostrich.commonui.view.inputs.InputInspector#setColor(java.awt.Color, javax.swing.JComponent)
	 */
	public void setColor(Color color, JComponent component) {
		component.setBackground(color);
	}
}
