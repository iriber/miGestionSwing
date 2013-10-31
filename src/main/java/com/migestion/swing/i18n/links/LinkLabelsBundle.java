package com.migestion.swing.i18n.links;

import java.util.ResourceBundle;

import com.migestion.swing.i18n.I18n;

/**
 * Clase que nos dar� los labels de los links de acuerdo
 * al idioma seteado en las properties.
 * 
 * @author Bernardo Iribarne
 *
 */
public class LinkLabelsBundle {

	
   private static ResourceBundle labels = ResourceBundle.getBundle( I18n.linkLabelsBundle );
    
   
   public static String link_Add = labels.getString("link.add"); 
   public static String link_Add_KeyStroke = labels.getString("link.add.KeyStroke");
   
   public static String link_Update = labels.getString("link.update");
   public static String link_Update_KeyStroke = labels.getString("link.update.KeyStroke");
   
   public static String link_Delete = labels.getString("link.delete");
   public static String link_Delete_KeyStroke = labels.getString("link.delete.KeyStroke");
   
   public static String link_View = labels.getString("link.view");
   public static String link_View_KeyStroke = labels.getString("link.view.KeyStroke");
   
   public static String link_Close = labels.getString("link.close");
   public static String link_Close_KeyStroke = labels.getString("link.close.KeyStroke");
   
   public static String link_Open = labels.getString("link.open");
   public static String link_Open_KeyStroke = labels.getString("link.open.KeyStroke");
   
   public static String link_Print = labels.getString("link.print");
   public static String link_Print_KeyStroke = labels.getString("link.print.KeyStroke");

   public static String link_PrintObject = labels.getString("link.printObject");
   public static String link_PrintObject_KeyStroke = labels.getString("link.printObject.KeyStroke");

   public static String link_Print_excel = labels.getString("link.print.excel");
   public static String link_Print_excel_KeyStroke = labels.getString("link.print.excel.KeyStroke");

   public static String link_CreateCriteria = labels.getString("link.createCriteria");
   public static String link_CreateCriteria_KeyStroke = labels.getString("link.createCriteria.KeyStroke");
   
   public static String link_List = labels.getString("link.list");
   public static String link_List_KeyStroke = labels.getString("link.list.KeyStroke");

   public static String link_Exit = labels.getString("link.exit");
   public static String link_Exit_KeyStroke = labels.getString("link.exit.KeyStroke");

   public static String link_FindObject = labels.getString("link.findObject");
   public static String link_FindObject_KeyStroke = labels.getString("link.findObject.KeyStroke");
   
}
