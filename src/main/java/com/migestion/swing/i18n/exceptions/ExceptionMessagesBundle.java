package com.migestion.swing.i18n.exceptions;

import java.util.ResourceBundle;

import com.migestion.swing.i18n.I18n;

/**
 * Clase que nos dará los mensajes de las exceptiones de acuerdo
 * al idioma seteado en las properties.
 * 
 * @author Bernardo Iribarne {Ostrich}
 *
 */
public class ExceptionMessagesBundle {

	
   private static ResourceBundle messages = ResourceBundle.getBundle( I18n.exceptionMessagesBundle );
    
   //LINKS
   public static String link_disable = messages.getString("link.disable"); 
   public static String link_Close_windowNotDefined = messages.getString("link.close.windowNotDefined");
   public static String link_Open_windowNotDefined = messages.getString("link.open.windowNotDefined");
   public static String link_Update_objectNotDefined = messages.getString("link.update.objectNotDefined");
   public static String link_Delete_objectNotDefined = messages.getString("link.delete.objectNotDefined");
   public static String link_View_objectNotDefined = messages.getString("link.view.objectNotDefined");
   public static String link_View_cannotReadObject = messages.getString("link.view.cannotReadObjet");
   public static String link_Print_collectionNotDefined = messages.getString("link.print.collectionNotDefined");
   public static String link_Print_printerNotDefined = messages.getString("link.print.printerNotDefined");
   public static String link_Print_listenerNotDefined = messages.getString("link.print.listenerNotDefined");
   public static String link_Print_objectNotDefined = messages.getString("link.print.objectNotDefined");
   
}
