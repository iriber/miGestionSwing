package com.migestion.swing.model;

import java.util.List;
import java.util.Vector;

import javax.swing.event.TableModelEvent;
import javax.swing.table.TableCellRenderer;

import com.migestion.swing.view.renderers.EntityFooterTableRenderer;


/**
 * Modelo de los objetos que serán mostrados en la ui.
 * Esta subclase permite mostrar una última fila como un footer summary
 * 
 * @author Bernardo Iribarne
 * @since 24/10/2013
 *
 */

public abstract class UIFooterCollection extends UICollection{

	
	
    public UIFooterCollection(String description, Vector elements){
       super(description, elements);
    }

    public UIFooterCollection(String description){
    	super(description);
    }

    
    /**
     * se setean los elementos del modelo.
     * se avisa el cambio a los suscriptores.
     * @param elements
     */
    public void setElements(Vector elements){
     
    	super.setElements(elements);
      
    	setFooter( elements );
    }

    /**
     * se setean los elementos del modelo.
     * se avisa el cambio a los suscriptores.
     * @param elements
     */
    public void setElements(List elements){
    	
    	super.setElements(elements);

    	setFooter( elements );
    }
    
    public void setElements(UICollection items) {
		
    	super.setElements(items.getElements());
    	
    	setFooter( items );
	}
    
    protected abstract void setFooter(UICollection items);
    
    protected abstract void setFooter(Vector items);
    
    protected abstract void setFooter(List items);
    
    protected abstract Object getObjectFooter();
    
    /*
     * (non-Javadoc)
     * @see com.migestion.swing.model.UICollection#getRowCount()
     */
    public int getRowCount() {
    	//retornamos uno más que será el footer.
        return super.getRowCount() + 1;
    }
    
    /*
     * (non-Javadoc)
     * @see com.migestion.swing.model.UICollection#getRealRowCount()
     */
    public int getRealRowCount() {
    	//descontamos el footer
        return getRowCount() - 1;
    }
   
    
    /**
     * retorna el objeto de la fila <code>row</code>
     * 
     * @param row
     * @return (si la fila no existe retorna null).
     */
    public Object getElement(int row){
      
    	Object object;
    	
    	if( row < getRealRowCount() ){
    		
	    	object = super.getElement(row);
	    		
    	}else{
    	
    		object = getObjectFooter();
    		
    	}
    	return object;
    }

    

	/*
     * (non-Javadoc)
     * @see com.migestion.swing.model.UICollection#deleteElement(int)
     */
    public void deleteElement (int fila)
    {
    	if( fila < getRealRowCount() ){
    		
    		super.deleteElement(fila);
    		
    		setFooter( getElements() );
    		
    	}else{
    		
    		//no se puede borrar el footer.
    	}
    }

    /**
     * agrega un objeto en el modelo.
     * avisa a los suscriptores el cambio.
     * @param element
     */
    public void addElement (Object element)
    {
    	//TODO chequear de que el footer siga siendo el último elemento.
        super.addElement(element);
        
        setFooter( getElements() );
        
    }


    /*
     * (non-Javadoc)
     * @see com.migestion.swing.model.UICollection#isCellEditable(int, int)
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
 
    	if( rowIndex < getRealRowCount() ){
    		
    		return isEntityCellEditable(rowIndex, columnIndex);
    	}else{
    		
    		return false;
    	}
 
    }


	/*
     * (non-Javadoc)
     * @see com.migestion.swing.model.UICollection#setValueAt(java.lang.Object, int, int)
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
    	
    	if( rowIndex < getRealRowCount() ){
    		
    		setEntityValueAt(aValue, rowIndex, columnIndex);
    		
    		setFooter( getElements() );
    		
    	}else{
    		
    		
    	}
    }

    

	/**
     * retorna el table cell renderer utilizado para 
     * mostrarse.
     * 
     * @return
     */
    public TableCellRenderer getTableCellRenderer(){
    	return new EntityFooterTableRenderer();
    }

    public Object getValueAt(int rowIndex, int columnIndex){
    	
    	if( rowIndex < getRealRowCount() ){
    		
    		return getEntityValueAt(rowIndex, columnIndex);
    	}else{
    		return getFooterValueAt( columnIndex);
    	}
    }

    public Object getValueAtShow(int rowIndex, int columnIndex){
    	
    	if( rowIndex < getRealRowCount() ){
    		
    		return getEntityValueAtShow(rowIndex, columnIndex);
    	}else{
    		return getFooterValueAt( columnIndex);
    	}
    }
	protected abstract Object getFooterValueAt(int columnIndex);

	protected abstract Object getEntityValueAt(int rowIndex, int columnIndex);
	
	protected abstract Object getEntityValueAtShow(int rowIndex, int columnIndex);
	
	protected abstract void setEntityValueAt(Object aValue, int rowIndex, int columnIndex);
	
	protected abstract boolean isEntityCellEditable(int rowIndex, int columnIndex);

}