package com.migestion.swing.view.inputs;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JTable;

/**
 * Inspector para un jtable.
 * @author Bernardo Iribarne {Ostrich group}
 *
 */
public class JTableInspector implements InputInspector{

	/*
	 * (non-Javadoc)
	 * @see com.ostrich.commonui.view.inputs.InputInspector#getValue(javax.swing.JComponent)
	 */
	public Object getValue(JComponent component) {
		return ((JTable)component).getModel();
	}

	/*
	 * (non-Javadoc)
	 * @see com.ostrich.commonui.view.inputs.InputInspector#hasValue(javax.swing.JComponent)
	 */
	public Boolean hasValue(JComponent component) {
		return ((JTable)component).getModel().getRowCount()>0;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ostrich.commonui.view.inputs.InputInspector#setColor(java.awt.Color, javax.swing.JComponent)
	 */
	public void setColor(Color color, JComponent component) {
		((JTable)component).setBackground(color);
		((JTable)component).getParent().setBackground(color);
	}

	
}
