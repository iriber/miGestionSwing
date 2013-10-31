package com.migestion.swing.view.inputs;

import java.awt.Color;

import javax.swing.JComponent;

/**
 * Inspector para un jcalendar.
 * @author Bernardo Iribarne {Ostrich group}
 *
 */
public class JCalendarInspector implements InputInspector{

	/*
	 * (non-Javadoc)
	 * @see com.ostrich.commonui.view.inputs.InputInspector#getValue(javax.swing.JComponent)
	 */
	public Object getValue(JComponent component) {
		return ((com.toedter.calendar.JCalendar)component).getDate();
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
