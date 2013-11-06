package com.migestion.swing.context;

import java.util.Vector;

/**
 * Observer para el contexto de la aplicación.
 * 
 * @author bernardo
 *
 */
public class ContextObserver<T> implements IContextListener<T>{

	/**
	 * listeners del contexto
	 */
	protected Vector<IContextListener<T>> listeners;
	
	public ContextObserver(){
		listeners = new Vector<IContextListener<T>>();
	}
	
	public void addListener( IContextListener<T> listener ){
		
		listeners.add( listener );
	}
	
	public void objectDeleted(Object objectDeleted) {
		
		for (IContextListener<T> listener : listeners) {
			listener.objectDeleted(objectDeleted);
		}
		
	}

	public void objectUpdated(Object objectUpdated) {
		
		for (IContextListener<T> listener : listeners) {
			listener.objectUpdated(objectUpdated);
		}
		
	}

	public void objectCreated(Object objectCreated) {
		for (IContextListener<T> listener : listeners) {
			listener.objectCreated(objectCreated);
		}
	}

}
