package com.migestion.swing.view.dialogs.adapters;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import com.migestion.swing.controller.IControllerAdd;
import com.migestion.swing.view.dialogs.DialogAddObject;
import com.migestion.swing.view.dialogs.DialogAddRelatedObject;
import com.migestion.swing.view.exceptions.ViewException;
import com.migestion.swing.view.inputs.InputRequiredValidator;
import com.migestion.swing.view.inputs.InputValidator;

/**
 * Diálogo para agregar objetos.
 * 
 * 
 * @author Bernardo Iribarne
 *
 */
public class DialogAddRelatedAdapter extends DialogAddRelatedObject{

	/**
	 * panel para los inputs.
	 */
	private IDialogAddRelatedAdapter panel;

	
	public DialogAddRelatedAdapter(String title, IControllerAdd controller, IDialogAddRelatedAdapter panel) {
		super(title, controller );
		
		this.panel = panel;

		initialize(panel.getInputPanel());
	}

	public void open(Object relatedObject) {
		
		this.panel.setRelatedObject(relatedObject);
		super.open( relatedObject );
	}
	
	@Override
	protected Object getObjectFromUI() throws ViewException {
		
		return panel.getObjectFromUI();
		
	}

	@Override
	protected void validateInput() throws ViewException {
		
		List<InputValidator> validators = panel.getValidators( );

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
	public void clearInputs() {
		panel.clearInputs();
		for (InputValidator validator : panel.getValidators()) {
			validator.clean();
		}
	}
}
