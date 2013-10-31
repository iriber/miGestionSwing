package com.migestion.swing.utils;



import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

/**
 * Clase utilizada para reubicar ventanas en la pantalla
 * @author Bernardo
 */
public class UbicacionVentana {


  /*
  -----  JFrame
  */
  public static void centrar(JFrame frame, boolean resizable){
    //para que no se pueda modificar
    frame.setResizable(resizable);
    //Creo un objeto "Dimension" que almacene la dimensi�n de la pantalla del usuario.
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //Posiciono el frame
    frame.setLocation((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getHeight()) / 2);
  }

  public static void centrarTop(JFrame frame, boolean resizable){
    centrar(frame,resizable);
    int x = (int)frame.getBounds().getX();
    int width = frame.getWidth();
    int height = frame.getHeight();
    frame.setBounds(x,0,width,height);
  }


  /*
  -----  Frame
  */

  public static void centrar(Frame frame, boolean resizable){
    //para que no se pueda modificar
    frame.setResizable(resizable);
    //Creo un objeto "Dimension" que almacene la dimensi�n de la pantalla del usuario.
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //Posiciono el frame
    frame.setLocation((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getHeight()) / 2);
  }

  public static void centrarTop(Frame frame, boolean resizable){
    centrar(frame,resizable);
    int x = (int)frame.getBounds().getX();
    int width = frame.getWidth();
    int height = frame.getHeight();
    frame.setBounds(x,0,width,height);
  }

  /*
  -----  JDialog
  */

  public static void centrar(JDialog frame, boolean resizable){
    //para que no se pueda modificar
    frame.setResizable(resizable);
    //Creo un objeto "Dimension" que almacene la dimensi�n de la pantalla del usuario.
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //Posiciono el frame
    frame.setLocation((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getHeight()) / 2);
  }


  public static void centrarTop(JDialog frame, boolean resizable){
    centrar(frame,resizable);
    int x = (int)frame.getBounds().getX();
    int width = frame.getWidth();
    int height = frame.getHeight();
    frame.setBounds(x,0,width,height);
  }

  /*
  -----  Dialog
  */

  public static void centrar(Dialog frame, boolean resizable){
    //para que no se pueda modificar
    frame.setResizable(resizable);
    //Creo un objeto "Dimension" que almacene la dimensi�n de la pantalla del usuario.
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //Posiciono el frame
    frame.setLocation((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getHeight()) / 2);
  }

  public static void centrarTop(Dialog frame, boolean resizable){
    centrar(frame,resizable);
    int x = (int)frame.getBounds().getX();
    int width = frame.getWidth();
    int height = frame.getHeight();
    frame.setBounds(x,0,width,height);
  }

  /*
  -----  JInternalFrame
  */
  public static void centrar(JInternalFrame frame, boolean resizable){
    //para que no se pueda modificar
    frame.setResizable(resizable);
    //Creo un objeto "Dimension" que almacene la dimensi�n de la pantalla del usuario.
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //Posiciono el frame
    frame.setLocation((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getHeight()) / 2);
  }


}
