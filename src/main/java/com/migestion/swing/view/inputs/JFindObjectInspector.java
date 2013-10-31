package com.migestion.swing.view.inputs;

import java.awt.Color;

import javax.swing.JComponent;

import com.migestion.swing.custom.JFindObjectPanel;

/**
 * Inspector para un text field.
 * @author Bernardo Iribarne {Ostrich group}
 *
 */
public class JFindObjectInspector implements InputInspector{

	/*
	 * (non-Javadoc)
	 * @see com.ostrich.commonui.view.inputs.InputInspector#getValue(javax.swing.JComponent)
	 */
	public Object getValue(JComponent component) {
		return ((JFindObjectPanel)component).getObjectFound();
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
		((JFindObjectPanel)component).getTxtObjectFound().setBackground(color);
		((JFindObjectPanel)component).getTxtCodeObjectFound().setBackground(color);
	}
}
