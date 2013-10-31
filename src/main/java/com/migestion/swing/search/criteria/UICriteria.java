package com.migestion.swing.search.criteria;

/**
 * Los criteria nos darán la información cargada
 * en la ventana destinada a la construcción del filtro
 * de búsqueda.
 * 
 * @author Bernardo Iribarne
 * @since 06/05/2012
 *
 */
public interface UICriteria {
	
	/**
	 * retorna la descripción del criterio de búsqueda.
	 * @return
	 */
	public String getDescription();
	
	
	/**
	 * offset para la paginación.
	 * @return
	 */
	public Integer getOffset();
	
	
	/**
	 * setea el offset para la paginación.
	 * @param offset
	 */
	public void setOffset(Integer offset);
	
	/**
	 * cantidad de filas a retornar en caso de paginar.
	 * @return
	 */
	public Integer getRowCount();

	/**
	 * setea el rowCount para la paginación.
	 * @param rowCount
	 */
	public void setRowCount(Integer rowCount);

	/**
	 * retorna true si se paginarán los resultados.
	 * @return
	 */
	public boolean isPaginable();
	
	/**
	 * determina si se paginarán los resultados.
	 * @param paginable
	 */
	public void setPaginable(boolean paginable);
}
