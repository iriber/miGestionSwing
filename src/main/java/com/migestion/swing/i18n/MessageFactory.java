package com.migestion.swing.i18n;

import java.text.MessageFormat;

/**
 * Proporciona métodos para obtener mensajes parametrizados.
 * (facilita la utilización de MessageFormat).
 * 
 * @author Bernardo Iribarne
 *
 */
public class MessageFactory {

	/**
	 * A partir de un template y una lista de argumentos,
	 * genera un mensaje.<br>
	 * 
	 * El par�metro <code>template</code> debe ser de la forma:<br>
	 * 	 <b><i>mi casa es {0} y adem�s es {1} ... {N}.</i></b><br>
	 * 
	 * Mientras que <code>arguments</code> contendr� los objetos
	 * a ser reemplazados en el template. El orden de �stos determinar�
	 * su ubicaci�n en el template. As�, para el ejemplo, se necesitar�an
	 * N+1 argumentos:<br>
	 *   <b><i>{"linda", "agradable",...,"Mar�a"}. </i></b><br>
	 *        
	 * 
	 * Estar�a retornando el siguiente mensaje:<br>
	 *   <b><i>mi casa es linda y adem�s es agradable...Mar�a.</i></b><br>
	 * 
	 * @param template	String
	 * @param arguments Object[]
	 * @return
	 */
	public static String getMessage(String template, Object[] arguments){
        MessageFormat formatter = new MessageFormat("");        
        formatter.applyPattern(template);
        return formatter.format(arguments);
    }

}
