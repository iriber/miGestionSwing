package com.migestion.swing.i18n.panels;

import java.util.ResourceBundle;

import com.migestion.swing.i18n.I18n;

/**
 * Clase que nos dará los labels para CriteriaFechaPanel
 * 
 * @author Bernardo Iribarne
 */

public class CriteriaFechaPanelMessagesBundle {

	private static ResourceBundle messages = ResourceBundle
			.getBundle(I18n.criteriaFechaPanelMessagesBundle);

	public static String criteriaFecha_rango = messages
			.getString("criteriaFecha.rango");

	public static String criteriaFecha_semana_actual = messages
			.getString("criteriaFecha.semana_actual");

	public static String criteriaFecha_mes_actual = messages
			.getString("criteriaFecha.mes_actual");

	public static String criteriaFecha_anio_actual = messages
			.getString("criteriaFecha.anio_actual");


}
