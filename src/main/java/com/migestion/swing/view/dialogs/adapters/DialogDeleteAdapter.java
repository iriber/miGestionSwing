package com.migestion.swing.view.dialogs.adapters;

import java.awt.Container;

import javax.swing.JPanel;

import com.migestion.swing.controller.IControllerDelete;
import com.migestion.swing.controller.IControllerView;
import com.migestion.swing.view.dialogs.DialogDeleteObject;
import com.migestion.swing.view.dialogs.DialogViewObject;

/**
 * Diálogo para visualizar objetos
 * 
 * @author Bernardo Iribarne
 *
 */
public class DialogDeleteAdapter extends DialogDeleteObject{


	/**
	 * contenedor para mostrar el objeto.
	 */
	private IDialogDeleteAdapter panel;

	public DialogDeleteAdapter(String title, IControllerDelete controller, IDialogDeleteAdapter panel) {
		super(title, controller);
		this.panel = panel;
		initialize(panel.getDeleteMsgPanel());
	}

	@Override
	protected Container createInfoPanel() {
		if( panel!= null )
			return panel.getDeleteMsgPanel();
		else
			return null;
		}

	@Override
	protected void showObjectToDelete() {
		panel.showObject( this.objectToDelete );
	}
}
