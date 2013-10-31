package com.migestion.swing.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.migestion.swing.view.editors.FloatEditor;
import com.migestion.swing.view.editors.IntegerEditor;
import com.migestion.swing.view.renderers.ElementTableRenderer;
import com.migestion.swing.view.renderers.TableHeaderRenderer;


/**
 * Modelo de los objetos que serán mostrados en la ui.
 * 
 * @author Bernardo Iribarne
 * @since 06/05/2012
 *
 */

public abstract class UICollection implements TableModel, IModelToPrint{

	//elementos que serán mostrados en el listado
	private Vector elements = new Vector();
	
	//colección de listeners que serán avisados cuando cambie la
	//colección de elementos del listado
	private Vector listeners = new Vector();
	
	//descripción de la colección.
	private String description;
	
	private Integer totalSize;
	
	//-----------------
	// CONSTRUCTORES
	//-----------------
	
    public UICollection(String description, Vector elements){
      this.elements = elements;
      this.description = description;
    }

    public UICollection(String description){
    	this.description = description;
    }

    
    /**
     * se setean los elementos del modelo.
     * se avisa el cambio a los suscriptores.
     * @param elements
     */
    public void setElements(Vector elements){
      this.elements = elements;
      alertListeners(new TableModelEvent(this, 0, 0,
            TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
      
      
    }

    /**
     * se setean los elementos del modelo.
     * se avisa el cambio a los suscriptores.
     * @param elements
     */
    public void setElements(List elements){
      this.elements = new Vector();
      for (Object entity : elements) {
			this.elements.add(entity);
		}
      alertListeners(new TableModelEvent(this, 0, 0,
            TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
    }
    
    public void setElements(UICollection items) {
		
    	setElements( items.getElements(), items.getDescription() );
		
	}
    
    /**
     * se setean los elementos del modelo y su descripci�n.
     * se avisa el cambio a los suscriptores.
     * @param elements
     */
    public void setElements(Vector elements, String description){
    	setElements(elements);
    	setDescription(description);
    }
    
    /**
     * retorna la cantidad de elementos del modelo
     */
    public int getRowCount() {
        return elements.size();
    }

    /**
     * pensado para cuando agregamos una fila más
     * con los totales.
     * @return
     */
    public int getRealRowCount() {
        return elements.size();
    }
    
    /**
     * retorna el objeto de la fila <code>row</code>
     * 
     * @param row
     * @return (si la fila no existe retorna null).
     */
    public Object getElement(int row){
      
    	Object object;
    	
    	try{
    		
    		object = elements.get(row);
    		
    	}catch(ArrayIndexOutOfBoundsException ex){
    		object = null;
    	}
    	return object;
    }


	/**
     * retorna la colecci�n de elementos
     * @return
     */
    public Vector getElements(){
      return elements;
    }


    /**
     * borra del modelo la fila indicada.
     * avisa a los suscriptores del cambio
     * @param fila
     */
    public void deleteElement (int fila)
    {
        // Se borra la fila
        elements.remove(fila);

        // Y se avisa a los suscriptores, creando un TableModelEvent...
        TableModelEvent evento = new TableModelEvent (this, fila, fila,
            TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

        // ... y pas�ndoselo a los suscriptores
        alertListeners (evento);
    }

    /**
     * borra del modelo el elemento indicado.
     * avisa a los suscriptores del cambio
     * @param fila
     */
    public void deleteElement (Object element)
    {
        // Se borra el objeto
        elements.remove(element);

        // Y se avisa a los suscriptores, creando un TableModelEvent...
        TableModelEvent evento = new TableModelEvent (this, 0, 0,
            TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);

        // ... y pas�ndoselo a los suscriptores
        alertListeners (evento);
    }

    /**
     * agrega un objeto en el modelo.
     * avisa a los suscriptores el cambio.
     * @param element
     */
    public void addElement (Object element)
    {
        elements.add (element);

        // Avisa a los suscriptores creando un TableModelEvent...
        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.INSERT);

        // ... y avisando a los suscriptores
        alertListeners (evento);
    }

    /**
     * actualiza un objeto en el modelo.
     * avisa a los suscriptores el cambio.
     * @param element
     */
    public void updateElement(Object element)
    {

        // Avisa a los suscriptores creando un TableModelEvent...
        TableModelEvent evento;
        evento = new TableModelEvent (this, this.getRowCount()-1,
            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS,
            TableModelEvent.UPDATE);

        // ... y avisando a los suscriptores
        alertListeners (evento);
    }

    /**
     * agrega un listener.
     */
    public void addTableModelListener(TableModelListener l) {
        listeners.add (l);
    }

    /**
     * define si una celda es editable o no.
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    /**
     * elimina el listener.
     */
    public void removeTableModelListener(TableModelListener l) {
        listeners.remove(l);
    }

    /**
     * setea el valor de un "campo" de la tabla.
     * (no se utilizar� este m�todo)
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
    }

    /**
     * avisa a los suscriptores sobre un evento que ocurri�
     * sobre los elementos del modelo
     * @param evento
     */
    protected void alertListeners (TableModelEvent evento)
    {
        for (int i=0; i<listeners.size(); i++)
            ((TableModelListener)listeners.get(i)).tableChanged(evento);
    }


    /*
     * m�todos a definir por las subclases
     */
    
    
    /**
     * cantidad de columnas que se visualizar�n
     */
    public abstract int getColumnCount();    

    /**
     * retorna la clase de la columna indicada
     */
    public abstract Class getColumnClass(int columnIndex);
    
    /**
     * retorna el nombre de la columna indicada
     */
    public abstract String getColumnName(int columnIndex);

    /**
     * retorna el nombre de la columna indicada
     */
    public Integer getColumnWidth(int columnIndex){
    	return 100;
    }

    /**
     * retorna el valor de un "campo" (fila-columna).
     * 
     */
    public abstract Object getValueAt(int rowIndex, int columnIndex) ;
 
    /**
     * retorna el valor de un "campo" (fila-columna).
     * 
     */
    public Object getValueAtShow(int rowIndex, int columnIndex) {
    	return getValueAt(rowIndex, columnIndex); 
    }
  
    /**
     * retorna una descripci�n de los objetos de modelo.
     * ser� utilizado, por ejemplo, como encabezado en los listados.
     * 
     */
    public String getDescription(){
    	return this.description;
    }

    /**
     * setea la descripci�n de los objetos de modelo.
     * ser� utilizado, por ejemplo, como encabezado en los listados.
     * 
     */
    public void setDescription(String description){
    	this.description = description;
    }

    /**
     * retorna el table cell renderer utilizado para 
     * mostrarse.
     * 
     * @return
     */
    public TableCellRenderer getTableCellRenderer(){
    	return new ElementTableRenderer();
    }

    /**
     * retorna el renderer para el header.
     * @return
     */
    public TableCellRenderer getTableHeaderRenderer(){
    	return new TableHeaderRenderer();
    }

	
	
	//--------------------------------------------------
	// Se definen los valores por defecto para imprimir.
	// Ser�n los mismos que se muestran en pantalla.
	//--------------------------------------------------
	/*
	 *  (non-Javadoc)
	 * @see com.ostrich.commonui.model.IModelToPrint#getColumnCountToPrint()
	 */
	public int getColumnCountToPrint() {
		return getColumnCount();
	}

	/*
	 *  (non-Javadoc)
	 * @see com.ostrich.commonui.model.IModelToPrint#getColumnClassToPrint(int)
	 */
	public Class getColumnClassToPrint(int columnIndex) {
		return getColumnClass(columnIndex);
	}

	/*
	 *  (non-Javadoc)
	 * @see com.ostrich.commonui.model.IModelToPrint#getColumnNameToPrint(int)
	 */
	public String getColumnNameToPrint(int columnIndex) {
		return getColumnName(columnIndex);
	}

	/*
	 *  (non-Javadoc)
	 * @see com.ostrich.commonui.model.IModelToPrint#getValueAtToPrint(int, int)
	 */
	public Object getValueAtToPrint(int rowIndex, int columnIndex) {
		return getValueAt(rowIndex, columnIndex);
	}
	
	/*
	 *  (non-Javadoc)
	 * @see com.ostrich.commonui.model.IModelToPrint#getRownCountToPrint()
	 */
	public int getRownCountToPrint() {		
		return getRowCount();
	}

    /*
     *  (non-Javadoc)
     * @see com.ostrich.commonui.model.IModelToPrint#getFooter()
     */
	public String getFooter() {		
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		String yearString = format.format(Calendar.getInstance().getTime());
		return "� Copyright 2005-"+ yearString +" MiGestion";
	}


	public TableRowSorter getTableSorter(){
//		TableRowSorter sorter = new TableRowSorter();
//		sorter.setModel(this);
//		return sorter;
		return null;
	}

	
	public Integer getColumnTextAlign(int columnIndex) {
		return SwingConstants.CENTER;
	}

	/**
	 * @return the totalSize
	 */
	public Integer getTotalSize() {
		return totalSize;
	}

	/**
	 * @param totalSize the totalSize to set
	 */
	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	public void initCellRenderers(JTable table){
		
		for(int columnIndex=0; columnIndex < this.getColumnCount(); columnIndex++){
			table.setDefaultRenderer(this.getColumnClass(columnIndex), this.getTableCellRenderer() );
		}		
		
	}

	public void initHeaderRenderers(JTable table){
		
		//table.setDefaultEditor(columnClass, editor)(Integer.class,  new IntegerEditor(0, 100));
		for(int columnIndex=0; columnIndex < this.getColumnCount(); columnIndex++){
			table.getColumnModel().getColumn(columnIndex).setHeaderRenderer( this.getTableHeaderRenderer() );
		}
	}
	
	public void initCellEditorsRenderers(JTable table){
		
		table.setDefaultEditor( Integer.class,  new IntegerEditor(0, null));
		table.setDefaultEditor( Float.class,  new FloatEditor(0,null, new DecimalFormat("#,###,###,##0.00")));
	}
	
	public void initColumnsWidth(JTable table){
		
		for(int columnIndex=0; columnIndex < this.getColumnCount(); columnIndex++){
			table.getColumnModel().getColumn(columnIndex).setPreferredWidth( getColumnWidth(columnIndex));
		}		
	}

	

	
}