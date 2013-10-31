package com.migestion.swing.factories;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;


/**
 * Provee m�todos para generar JPanel a partir de
 * pares key-value.
 * 
 * Pensado para cuando se desea mostrar algo de la forma:
 * 
 *    Descripci�n:  "componente de entrada" 
 *    (JLabel)     (JComboBox, JTextField, etc)
 *    
 * @author Bernardo Iribarne {Ostrich}
 *
 */

public class KeyValueLabelFactory {


	/**
	 * crea un panel con el par key-value, donde se especifica
	 * el componente del value.
	 * 
	 * @param key
	 * @param keyWidth
	 * @param value
	 * @return
	 */
	public static JPanel getJPanelKeyValue(String key, int keyWidth, Component value){
		
		//creamos el label del key
		JLabel lblKey = JLabelFactory.getJLabel(key, keyWidth);
		
		return getJPanelKeyValue(lblKey, value);
		
	}	
	
	/**
	 * crea un panel con el par key-value, donde se especifica
	 * el componente del key.
	 * 
	 * @param key
	 * @param value
	 * @param valueWidth
	 * @return
	 */
	public static JPanel getJPanelKeyValue(Component key, String value, int valueWidth){
		
		//creamos el label del key
		JLabel lblValue = JLabelFactory.getJLabel(value, valueWidth);
		
		return getJPanelKeyValue(key, lblValue);
		
	}
	
	/**
	 * crea un panel con el par key-value.
	 * al no especificar componentes, se muestran en labels.
	 * 
	 * @param key
	 * @param keyWidth
	 * @param value
	 * @param valueWidth
	 * @return
	 */
	public static JPanel getJPanelKeyValue(String key, int keyWidth, String value, int valueWidth){
		//como no se especifica un componente para el value, creamos
		//un JLabel por defecto.

		//creamos el label del value
		JLabel lblValue = JLabelFactory.getJLabel(value, valueWidth);
		
		return getJPanelKeyValue(key, keyWidth, lblValue);
	}	


	/**
	 * crea un panel con el par key-value, donde se especifica
	 * los componentes para key-value.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static JPanel getJPanelKeyValue(Component key, Component value){
//		//tomamos las medidas de los campos
//		int inputWidth = (int)value.getMinimumSize().getWidth();
//		int keyWidth = (int)key.getMinimumSize().getWidth();
//		
//		//creamos un panel con el label del key y
//		//el componente de entrada
//		JPanel panel = new JPanel();
//		panel.setLayout(new FlowLayout());		
//		panel.add(key);		
//		panel.add(value);
//		panel.setMinimumSize(new Dimension(keyWidth+inputWidth+20,30));
//		panel.setPreferredSize(new Dimension(keyWidth+inputWidth+20,30));
//		
//		JPanel panelSuperior = new JPanel(new BorderLayout());
//		panelSuperior.add(panel, BorderLayout.CENTER);
		
		return getJPanelKeyValue(key, value,30);
		
	}
	
	/**
	 * crea un panel con el par key-value, donde se especifica
	 * los componentes para key-value y el alto preferido.
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static JPanel getJPanelKeyValue(Component key, Component value, int panelHeight){
		//tomamos las medidas de los campos
		int inputWidth = (int)value.getPreferredSize().getWidth();
		int keyWidth = (int)key.getPreferredSize().getWidth();
		
		//creamos un panel con el label del key y
		//el componente de entrada
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());		
		panel.add(key);		
		panel.add(value);
		panel.setMinimumSize(new Dimension(keyWidth+inputWidth+20,panelHeight));
		panel.setPreferredSize(new Dimension(keyWidth+inputWidth+20,panelHeight));
		
		JPanel panelSuperior = new JPanel(new BorderLayout());
		panelSuperior.add(panel, BorderLayout.CENTER);
		
		return panelSuperior;
		
	}	
}
