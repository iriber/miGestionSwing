package com.migestion.swing.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringBufferInputStream;

/**
 * Provee m�todos para el manejo de archivos
 *  
 * @author Bernardo Iribarne
 *
 */
public class FileUtils {
	
	private static final int BUFFER_SIZE = 8192;
	
	/**
	 * Mueve el archivo fileName ubicado en sourcePath hacia
	 * el directorio pathToCopy.
	 * 
	 * Si el directorio pathToCopy no existe, lo crea
	 * 
	 * @param sourcePath
	 * @param fileName
	 * @param pathToCopy
	 * @return
	 */
	public static boolean moveFile(String sourcePath, String fileName, String pathToCopy){
		//creamos el archivo fuente
		File sourceFile = new File(sourcePath + "/" + fileName);
		
		//creamos la ruta de directorios espec�fica
		File path = new File(pathToCopy);
		path.mkdirs();
 
		File destino = new File(pathToCopy +  "/" + fileName );
		
		return sourceFile.renameTo(destino);
	}
	
	/**
	 * Copia el archivo fileName ubicado en sourcePath hacia
	 * el directorio pathToCopy.
	 * 
	 * Si el directorio pathToCopy no existe, lo crea
	 * 
	 * @param sourcePath
	 * @param fileName
	 * @param pathToCopy
	 * @return
	 * @throws IOException 
	 */
	public static void copyFile(String sourcePath, String fileName, String pathToCopy) throws IOException{
			
			//creamos el archivo fuente
			File sourceFile = new File(sourcePath + "/" + fileName);		
			//creamos la ruta de directorios espec�fica
			File path = new File(pathToCopy);
			path.mkdirs();		
			//creamos el archivo destino
			File destino = new File(pathToCopy +  "/" + fileName );
			//si son los mismos archivos no copiamos nada
			if(!sourceFile.equals(destino))
				//copiamos el archivo
				copy(sourceFile,destino);
		
	}

	/**
	 * copy
	 *
	 * @param _srcFile
	 * @param _dstFile
	 * @throws FileUtilException
	 */
	public static void copy(final File _srcFile, final File _dstFile)
			throws IOException {
		
			final FileInputStream inputStream = new FileInputStream(_srcFile);
			final FileOutputStream outputStream = new FileOutputStream(_dstFile);
			copyStreams(inputStream, outputStream);
			inputStream.close();
			outputStream.close();
		
	}
	
	/**
	 * copyStreams
	 *
	 * @param _inputStream
	 * @param _outputStream
	 * @throws IOException
	 */
	private static void copyStreams(final InputStream _inputStream,
			final OutputStream _outputStream) throws IOException {
		int bytesRead;
		final byte[] buffer = new byte[BUFFER_SIZE];
		while ((bytesRead = _inputStream.read(buffer, 0, BUFFER_SIZE)) != -1) {
			_outputStream.write(buffer, 0, bytesRead);
		}
	}

	/**
	 * Retorna un texto que es leido de un archivo, cuyo nombre es pasado por
	 * parametro.
	 *
	 * @param fileName
	 * @return
	 * @throws IOException 
	 */
	public static String cargarTextoDesdeArchivo(String fileName) throws IOException{	
//			InputStream resourceAsStream = Thread.currentThread()
//					.getContextClassLoader().getResourceAsStream(fileName);

			InputStream resourceAsStream = new FileInputStream(fileName);
			Reader reader = new InputStreamReader(resourceAsStream);
			BufferedReader buffer = new BufferedReader(reader);
			String linea = buffer.readLine();
			StringBuffer buff = new StringBuffer();
			while (linea != null) {
				buff.append(linea);
				linea = buffer.readLine();
			}
			return buff.toString();
	
	}	

	/**
	 * Este metodo se encarga de escribir un archivo
	 *
	 * @param name
	 *            nombre del archivo, si no existe lo crea
	 * @param contenido
	 *            un Stream con el contenido del archivo
	 * @param append
	 *            flag que indica, que si el archivo existe agrega el contenido
	 * @throws IOException
	 */
	public static void crearArchivo(String path, String name,
			InputStream contenido, boolean append) throws IOException {
		File elPath = new File(path);
		File elArchivo = new File(elPath, name);

		if (!elPath.exists()) {
				elPath.mkdirs();
		}
		//Escribe el archivo
		if (!elArchivo.exists()) {
				elArchivo.createNewFile();
		}
		OutputStream bos = new FileOutputStream(elArchivo, append);
		copyStreams(contenido, bos);
		bos.close();
		contenido.close();
	}

	public static void crearArchivo(String path, String name,
			String contenido, boolean append) throws IOException {
		StringBufferInputStream input = new StringBufferInputStream(contenido);
		crearArchivo(path, name, input, false);
	}


	/**
	 * Este metodo se encarga de ejecutar un comando de sistema operativo
	 *
	 * @param comando
	 *            el comando a ejecutar (por ej. xxx.sh, xxx.bat, ls , mkdir ...
	 *            etc.)
	 * @param params
	 *            Los parametros para el comando (por ej. [-ltr] para ls , [-r, *]
	 *            para rm etc.)
	 * @param envVars
	 *            variables de entorno si son necesarias
	 *
	 * @return si el proceso termina normal true sino false
	 * @throws IOException 
	 * @throws InterruptedException 
	 *
	 */
	public static boolean ejecutaCommando(String comando, String[] params,
			String[] envVars) throws IOException, InterruptedException {
		boolean ret = false;
	
			Runtime run = Runtime.getRuntime();
			if (comando == null || comando.trim().equals("")) {
				return false;
			}

			StringBuffer aEjecutar = new StringBuffer();
			aEjecutar.append(comando);
			String separador = " ";
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					aEjecutar.append(separador + params[i]);
				}
			}
			aEjecutar.append(separador);

			Process prc;
			if (envVars == null || envVars.length == 0) {
				prc = run.exec(aEjecutar.toString());
			} else {
				prc = run.exec(aEjecutar.toString(), envVars);
			}

			InputStream err = prc.getErrorStream();
			InputStream std = prc.getInputStream();
			BufferedReader salidaStandar = new BufferedReader(
					new InputStreamReader(std));
			StringBuffer strd = new StringBuffer(), serr = new StringBuffer();
			String SALTO_LINEA = "\n";

			for (String str = salidaStandar.readLine(); str != null; str = salidaStandar
					.readLine()) {
				strd.append(str + SALTO_LINEA);
			}

			BufferedReader salidaError = new BufferedReader(
					new InputStreamReader(err));
			for (String str = salidaError.readLine(); str != null; str = salidaError
					.readLine()) {
				serr.append("Error : " + str + SALTO_LINEA);
			}

			int result = prc.waitFor();
			prc.destroy();
			if (result == 0) {
				ret = true;
			} else {
				ret = false;
			}
	
			return ret;
	}
	
	/**
	 * Abre el archivo dada su url.
	 * @param url
	 * @throws IOException
	 */
	public static void  abrirArchivo(String url) throws IOException{
		Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
		//Runtime.getRuntime().exec("C:\\Archivos de Programa\\Internet Explorer\\iexplore.exe \""  +url+"\"");
		
	}
}
