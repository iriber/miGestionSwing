package com.migestion.swing.view.dialogs.adapters;



import javax.swing.JPanel;

import com.migestion.swing.controller.IControllerList;
import com.migestion.swing.model.UICollection;
import com.migestion.swing.search.criteria.UICriteria;
import com.migestion.swing.view.dialogs.DialogFindObject;
import com.migestion.swing.view.dialogs.ICriteriaPanel;
import com.migestion.swing.view.exceptions.ViewException;

/**
 * Diálogo para buscar una entity.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 11/10/2013
 *
 */
public class DialogFindAdapter extends DialogFindObject{

	
	ICriteriaPanel panel;
	IControllerList controller;
	UICollection defaultCollection;	
	
	//-----------------
	// CONSTRUCTORES
	//-----------------
	
	public DialogFindAdapter(String title, ICriteriaPanel panel, IControllerList controller, UICollection defaultCollection) {
		super(title);
		
		this.panel = panel;
		this.controller = controller;
		this.defaultCollection = defaultCollection;
	}
	
	
	//--------------------
	//M�TODOS PROTECTED
	//--------------------	


	/*
	 * (non-Javadoc)
	 * @see com.codnet.swing.view.dialogs.DialogFindObject#getCriteriaFromUI()
	 */
	protected UICriteria getCriteriaFromUI() throws ViewException {
//		UICategoriaProductoCriteria criteria = new UICategoriaProductoCriteria();
//		criteria.setNombre(getInputNombre());
//		criteria.setPaginable(true);
//		return criteria;
//		
		return panel.getCriteria();
	}

	/*
	 *  (non-Javadoc)
	 * @see com.ostrich.commonui.view.dialogs.DialogFindObject#validateInput()
	 */
	protected void validateInput() throws ViewException {
		// nada.		
	}

	/*
	 *  (non-Javadoc)
	 * @see com.ostrich.commonui.view.dialogs.DialogFindObject#createCriteriaPanel()
	 */
	protected JPanel createCriteriaPanel() {
		
//		JTextField txtNombre = JTextFieldFactory.getJTextField("",150);
//		PropertyInput prop1 = new PropertyInput("Nombre", I18nMessages.PRODUCTO_NOMBRE ,80,txtNombre);
//	
//		addPropertyInput(prop1);
//		
//		JPanel inputs = this.autoGenerateInfoPanel(1); 
//		JPanel inputsWithTip = new JPanel(new BorderLayout());
//		inputsWithTip.add(inputs, BorderLayout.NORTH);
//		return inputsWithTip;
		//panel = new UICategoriaProductoCriteriaPanel();
		return panel.getPanel();
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.codnet.swing.view.dialogs.DialogFindObject#getController()
	 */
	protected IControllerList getController() {
		return controller;
	}

	/*
	 * (non-Javadoc)
	 * @see com.codnet.swing.view.dialogs.DialogFindObject#getUICollectionDefault()
	 */
	protected UICollection getUICollectionDefault() {
		return defaultCollection;
	}


}