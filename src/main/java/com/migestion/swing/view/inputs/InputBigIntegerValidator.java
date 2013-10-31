package com.migestion.swing.view.inputs;

import java.math.BigInteger;

import javax.swing.JComponent;

/**
 * Colabora con las validaciones de los inputs
 * para validar números biginteger.
 * 
 * @author Bernardo Iribarne
 *
 */
public class InputBigIntegerValidator extends InputValidator{

	
	
	public Boolean validate( InputInspector inspector, JComponent component ){
		Object object = inspector.getValue(component);
		
		Boolean ok = Boolean.TRUE;
		
		try {
			BigInteger.valueOf( Long.parseLong( (String)object ) );
			ok = Boolean.TRUE;
		} catch (NumberFormatException nfe){
			ok = Boolean.FALSE;
		}
		
		return ok;
		
	}
	
}
