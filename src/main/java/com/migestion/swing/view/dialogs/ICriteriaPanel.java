package com.migestion.swing.view.dialogs;

import javax.swing.JPanel;

import com.migestion.swing.search.criteria.UICriteria;
import com.migestion.swing.view.exceptions.ViewException;

/**
 * Interface que implementarán los Panels para las búsquedas.
 * 
 * @author Bernardo Iribarne (ber.iribarne@gmail.com)
 * @since 11/10/2013
 *
 */
public interface ICriteriaPanel {

	public UICriteria getCriteria() throws ViewException;
	
	public void setCriteria(UICriteria criteria);
	
	public JPanel getPanel();
}
