package com.migestion.swing.view.dialogs.adapters;

import java.awt.Container;

import com.migestion.swing.controller.IControllerView;
import com.migestion.swing.view.dialogs.DialogViewObject;

/**
 * Diálogo para visualizar objetos
 * 
 * @author Bernardo Iribarne
 *
 */
public class DialogViewAdapter extends DialogViewObject{

	/**
	 * contenedor para mostrar el objeto.
	 */
	private IDialogViewAdapter panel;
	
	public DialogViewAdapter(String title, IControllerView controller, IDialogViewAdapter panel) {
		super(title, controller);
		this.panel = panel;
		initialize(panel.getViewPanel());
	}

	@Override
	protected void showObject() {
		panel.showObject( this.objectToView );
	}

	@Override
	protected Container createInfoPanel() {
		if( panel!= null )
			return panel.getViewPanel();
		else
			return null;
	}

}
