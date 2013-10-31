package com.migestion.swing.view.inputs;

import javax.swing.JComponent;

/**
 * Colabora con las validaciones de los inputs
 * para longitudes permitidas.
 * 
 * @author Bernardo Iribarne
 *
 */
public class InputLengthValidator extends InputValidator{

	private Integer maxSize;
	private Integer minSize;
	
	public InputLengthValidator(){
		maxSize = null;
		minSize = null;
	}
	
	public InputLengthValidator(Integer maxSize, Integer minSize){
		this.maxSize = maxSize;
		this.minSize = minSize;
	}
	
	public Boolean validate( InputInspector inspector, JComponent component ){
		Object object = inspector.getValue(component);
		
		Boolean ok = Boolean.TRUE;
		
		if( maxSize != null)
			ok = ok && ((String)object).length() <= maxSize;
		if( minSize != null)
			ok = ok && ((String)object).length() >= maxSize;
		
		return ok;
		
	}
	
}
