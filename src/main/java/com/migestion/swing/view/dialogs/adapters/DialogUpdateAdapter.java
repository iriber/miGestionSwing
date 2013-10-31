package com.migestion.swing.view.dialogs.adapters;

import java.awt.Container;
import java.util.List;

import com.migestion.swing.controller.IControllerUpdate;
import com.migestion.swing.view.dialogs.DialogUpdateObject;
import com.migestion.swing.view.exceptions.ViewException;
import com.migestion.swing.view.inputs.InputValidator;

/**
 * Diálogo para modificar objetos. 
 * 
 * @author Bernardo Iribarne
 *
 */

public class DialogUpdateAdapter extends DialogUpdateObject{
	
	/**
	 * panel para los inputs.
	 */
	private IDialogUpdateAdapter panel;

	
	public DialogUpdateAdapter(String title, IControllerUpdate controller, IDialogUpdateAdapter panel) {
		super(title, controller);
		this.panel = panel;

		initialize(panel.getInputPanel());
	}


	@Override
	protected Object getObjectFromUI() throws ViewException {
		return panel.getObjectFromUI();
	}


	@Override
	protected void validateInput() throws ViewException {

		List<InputValidator> validators =  panel.getValidators();

		for (InputValidator validator : validators) {
			validator.clean();
		}

		for (InputValidator validator : validators) {
			if( !validator.validate() )				
				throw new ViewException( validator.getMessage() );
		}		
	}


	@Override
	protected Container createInputPanel() {

		if( panel!= null )
			return panel.getInputPanel();
		else
			return null;
	}


	@Override
	protected void showObjectToUpdate() {

		panel.showObject( this.getObjectUpdated() );

	}
}
