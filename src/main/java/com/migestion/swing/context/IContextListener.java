package com.migestion.swing.context;

import com.migestion.swing.navigation.listeners.LinkAddListener;
import com.migestion.swing.navigation.listeners.LinkDeleteListener;
import com.migestion.swing.navigation.listeners.LinkUpdateListener;

/**
 * Listener para el contexto de la aplicación.
 * 
 * @author bernardo
 *
 */
public interface IContextListener<T> extends LinkAddListener, LinkDeleteListener, LinkUpdateListener{

	
}
