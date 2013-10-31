package com.migestion.swing.utils;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Provee m�todos para el manejo de formatos.
 * 
 * @author Bernardo Iribarne
 *
 */
public class FormatUtils {

	/**
	 * formatea la fecha a un string
	 * @param date
	 * @return
	 */
	public static String format(Date date){
		SimpleDateFormat format = new SimpleDateFormat();
		return format.format(date);
	}

	/**
	 * formatea la fecha a un string
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern){
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
	
	/**
	 * formatea el float a un string
	 * @param number
	 * @return
	 */
	public static String format(Float number){
		DecimalFormat format = new DecimalFormat( "#,###,###,##0.00" );
		return format.format(number);
	}
	
	/**
	 * formatea el float a un string con el simbolo de la moneda
	 * @param number
	 * @return
	 */
	public static String formatMoneda(Float number, String symbol, Boolean symbolLeft){
		
		DecimalFormat format = new DecimalFormat( "#,###,###,##0.00" );
		
		if( symbolLeft )
			
			return symbol + " " + format.format(number);
		else
			return format.format(number)  + " " + symbol ;
	}
	
	/**
	 * formatea el float a un string
	 * @param number
	 * @return
	 */
	public static String format(Float number, String pattern){
		DecimalFormat format = new DecimalFormat( pattern );
		return format.format(number);
	}
	
	public static Float strToFloat(String number, String pattern){
		DecimalFormat format = new DecimalFormat( pattern );
		try {
			return new Float( format.parse(number).floatValue() );
		} catch (ParseException e) {
			return 0F;
		}
	}
	
	public static Float strToFloat(String number){
		DecimalFormat format = new DecimalFormat();
		try {
			return (Float) format.parse(number);
		} catch (ParseException e) {
			return 0F;
		}
	}
	
	public static Color getColor(int r, int g, int b){
		float hsb[]= new float[3];
		Color.RGBtoHSB(r, g, b, hsb );
		return Color.getHSBColor(hsb[0], hsb[1], hsb[2]);
	}
}
