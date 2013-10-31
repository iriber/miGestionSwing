package com.migestion.swing.view.renderers;

import javax.swing.JLabel;

import com.migestion.swing.utils.FormatUtils;

/**
 * Define la visualización de un objeto en la tabla que se mostrará en los
 * listados
 * 
 * @author Bernardo Iribarne
 * @since 06/05/2012
 * 
 */
public class MontoTableRenderer extends ElementTableRenderer {

	private String pattern;
	
	public MontoTableRenderer(String pattern){
	
		this.pattern = pattern;
	}
	
	protected void setLabel(JLabel label, int column, int row,
			Object propertyElement) {
		if (propertyElement != null)
			label.setText(FormatUtils.format((Float) propertyElement, pattern));
		else
			label.setText("");
	}
}
