package com.migestion.swing.view.inputs;

import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JPasswordField;

import com.migestion.swing.utils.StringUtils;

/**
 * Inspector para un password field.
 * @author Bernardo Iribarne
 *
 */
public class JPasswordFieldInspector implements InputInspector{

	/*
	 * (non-Javadoc)
	 * @see com.ostrich.commonui.view.inputs.InputInspector#getValue(javax.swing.JComponent)
	 */
	public Object getValue(JComponent component) {
		return new String ( ((JPasswordField)component).getPassword() ) ;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ostrich.commonui.view.inputs.InputInspector#hasValue(javax.swing.JComponent)
	 */
	public Boolean hasValue(JComponent component) {
		return !StringUtils.isEmpty( getValue(component).toString() );
	}

	/*
	 * (non-Javadoc)
	 * @see com.ostrich.commonui.view.inputs.InputInspector#setColor(java.awt.Color, javax.swing.JComponent)
	 */
	public void setColor(Color color, JComponent component) {
		component.setBackground(color);
	}
}
