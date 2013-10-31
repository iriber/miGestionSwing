package com.migestion.swing.view.inputs;

import javax.swing.JComponent;

import com.migestion.swing.utils.StringUtils;

/**
 * Colabora con las validaciones de los inputs
 * para validar integer.
 * 
 * @author Bernardo Iribarne
 *
 */
public class InputIntegerValidator extends InputValidator{

	public InputIntegerValidator(){
		setMarca("");
	}
	
	public Boolean validate( InputInspector inspector, JComponent component ){
		Object object = inspector.getValue(component);
		
		Boolean ok = Boolean.TRUE;
		
		String value=(String)object;
		
		if(!StringUtils.isEmpty( value )){
			
			try {
				Integer.parseInt( (String)object );
				ok = Boolean.TRUE;
			} catch (NumberFormatException nfe){
				ok = Boolean.FALSE;
			}
			
		}
		
		return ok;
		
	}
	
}
