package com.migestion.swing.view.dialogs;


import javax.swing.JOptionPane;

import com.migestion.swing.i18n.buttons.ButtonLabelsBundle;


/**
 * Proporciona métodos para mostrar mensajes al usuario.
 * 
 * @author Bernardo Iribarne
 *
 */
public class DialogMessage {

	
	/**
	 * mensaje informativo.
	 * 
	 * @param title
	 * @param message
	 */
	public static void showInformationMessage(String title, String message){
		Object[] options = {ButtonLabelsBundle.btn_Ok};
		JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_OPTION, JOptionPane.INFORMATION_MESSAGE,null,options,ButtonLabelsBundle.btn_Ok);
	}
	
	/**
	 * mensaje de error.
	 * 
	 * @param title
	 * @param message
	 */
	public static void showErrorMessage(String title, String message){
		Object[] options = {ButtonLabelsBundle.btn_Ok};
		JOptionPane.showOptionDialog(null, message, title, JOptionPane.YES_OPTION, JOptionPane.ERROR_MESSAGE,null,options,ButtonLabelsBundle.btn_Ok);
	}

}
