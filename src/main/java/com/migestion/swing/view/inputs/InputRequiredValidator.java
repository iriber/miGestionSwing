package com.migestion.swing.view.inputs;

import javax.swing.JComponent;

/**
 * Colabora con las validaciones de los inputs
 * para valores requeridos.
 * 
 * @author Bernardo Iribarne
 *
 */
public class InputRequiredValidator extends InputValidator{

	public Boolean validate( InputInspector inspector, JComponent component ){
		return inspector.hasValue(component);
	}
	
}
