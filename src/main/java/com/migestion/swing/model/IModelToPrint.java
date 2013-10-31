package com.migestion.swing.model;


/**
 * Interfaz que implementarán las UICollection para ser impresas.
 * La finalidad es que como una UICollection implementa TableModel,
 * pueda definir los mismos métodos para para ser reportada (html, excel)
 * pudiendo cambiar la visualización, por ejemplo, que en un listado
 * html se visualicen más columnas.
 * 
 * @author Bernardo Iribarne
 * @since 06/05/2012
 *
 */
public interface IModelToPrint {

	/**
	 * descripción del modelo.
	 */
	public String getDescription();
	
	/**
     * cantidad de filas que se imprimirán
     */
    public int getRownCountToPrint();
    
    /**
     * cantidad de columnas que se imprimirán
     */
    public int getColumnCountToPrint();    

    /**
     * retorna la clase de la columna indicada
     */
    public Class getColumnClassToPrint(int columnIndex);
    
    /**
     * retorna el nombre de la columna indicada
     */
    public String getColumnNameToPrint(int columnIndex);

    /**
     * retorna el valor de un "campo" (fila-columna).
     * 
     */
    public Object getValueAtToPrint(int rowIndex, int columnIndex) ;
    
    
    /**
     * footer del listado.
     */
	public String getFooter() ;

}
