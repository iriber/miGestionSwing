package com.migestion.swing.view.editors;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;

import com.migestion.swing.utils.StringUtils;

/**
 * Editor para integer
 */
public class IntegerEditor  extends DefaultCellEditor {
    
	private Boolean requerido ;
	
    public IntegerEditor(Boolean requerido) {
        super(new JFormattedTextField());
        
        this.requerido = requerido;
        
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected,
    	      int row, int column) {
    	    
    	JFormattedTextField editor = (JFormattedTextField) super.getTableCellEditorComponent(table, value, isSelected,
    	        row, column);

    	editor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));
    	editor.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
    	
    	if (value != null)
    		editor.setValue(value);
//    	      editor.setText(value.toString());
//    	
    	return editor;
    }
    
    @Override
	public Object getCellEditorValue() {
		
		JFormattedTextField editor = (JFormattedTextField)getComponent();
		
		//si es vacío y no requerido devolvemos null.
		if(!requerido && StringUtils.isEmpty(editor.getText()))
			return null;
		
		
		return new Integer( ((Number)editor.getValue()).intValue() );
		
		//return super.getCellEditorValue();
	}
}