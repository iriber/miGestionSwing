package com.migestion.swing.custom;

import java.util.ArrayList;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 * Modelo para los combos.
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public class ComboModel implements ComboBoxModel{

  List elementos = new ArrayList();

  Object selectedItem;

  public void setElementos(List elementos){
    this.elementos = elementos;
  }

  /**
   * getSelectedItem
   *
   * @return Object
   */
  public Object getSelectedItem() {
    return selectedItem ;
  }

  /**
   * setSelectedItem
   *
   * @param anItem Object
   */
  public void setSelectedItem(Object anItem) {
     selectedItem = anItem;
  }

  /**
   * getSize
   *
   * @return int
   */
  public int getSize() {
    return elementos.size();
  }

  /**
   * getElementAt
   *
   * @param index int
   * @return Object
   */
  public Object getElementAt(int index) {
    return this.elementos.get(index);
  }

  /**
   * addListDataListener
   *
   * @param l ListDataListener
   */
  public void addListDataListener(ListDataListener l) {
  }

  /**
   * removeListDataListener
   *
   * @param l ListDataListener
   */
  public void removeListDataListener(ListDataListener l) {
  }

}
