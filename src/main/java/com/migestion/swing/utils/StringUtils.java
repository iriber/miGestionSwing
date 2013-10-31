package com.migestion.swing.utils;

/**
 * Provee m�todos para el manejo de strings.
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public class StringUtils {

	/**
	 * retorna true si el texto no est� vac�o.
	 * (no nulo y distinto de blanco).
	 * @param text
	 * @return
	 */
	public static boolean isEmpty(String text){
		return (text==null || text.equals(""));
	}

}
