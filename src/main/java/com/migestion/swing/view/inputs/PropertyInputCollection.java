package com.migestion.swing.view.inputs;

import java.util.Vector;


/**
 * Colecci�n de Properties Input
 * 
 * @author Bernardo Iribarne
 *
 */
public class PropertyInputCollection extends Vector{

	  private int index=0;

	  public void add(PropertyInput propertyInput){
	    super.add(propertyInput);
	  }


	  public PropertyInput next(){
	    
		  if(hasNext()){  
			  return (PropertyInput)this.get(index++);
		  }
		  else
			  return null;
	  }

	  public boolean hasNext(){
	    return  this.index < this.size();
	  }

	  public void init(){
	    index = 0;
	  }
	  
	  /**
	   * retorna la property info con nombre <code>name</code>
	   * @param name
	   * @return
	   */
	  public PropertyInput get(String name){
		  PropertyInput prop=null;
		  this.init();
		  boolean ok = false;
		  while (this.hasNext() && !ok) {
			prop = this.next();
			ok = prop.getName().equals(name); 
		  }
		  if(!ok)
			  prop = null;
		  return prop;
	  }
	  
	  
	  
	  
}