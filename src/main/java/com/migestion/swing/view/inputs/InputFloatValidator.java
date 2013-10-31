package com.migestion.swing.view.inputs;

import javax.swing.JComponent;

/**
 * Colabora con las validaciones de los inputs
 * para validar números float.
 * 
 * @author Bernardo Iribarne
 *
 */
public class InputFloatValidator extends InputValidator{

	public InputFloatValidator(){
		setMarca("");
	}
	
	public Boolean validate( InputInspector inspector, JComponent component ){
		Object object = inspector.getValue(component);
		
		Boolean ok = Boolean.TRUE;
		
		try {
			Float.parseFloat( (String)object );
			ok = Boolean.TRUE;
		} catch (NumberFormatException nfe){
			ok = Boolean.FALSE;
		}
		
		return ok;
		
	}
	
}
