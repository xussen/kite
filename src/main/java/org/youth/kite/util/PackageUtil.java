package org.youth.kite.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 
 * @author xusensen
 * @date 2013-7-19
 */
public class PackageUtil {

	public static ArrayList<String> getClassNamesFromPackage(String packageName) throws IOException{
	    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
	    URL packageURL;
	    ArrayList<String> names = new ArrayList<String>();;

	    packageName = packageName.replace(".", "/");
	    packageURL = classLoader.getResource(packageName);

	    if(packageURL.getProtocol().equals("jar")){
	        String jarFileName;
	        JarFile jf ;
	        Enumeration<JarEntry> jarEntries;
	        String entryName;

	        // build jar file name, then loop through zipped entries
	        jarFileName = URLDecoder.decode(packageURL.getFile(), "UTF-8");
	        jarFileName = jarFileName.substring(5,jarFileName.indexOf("!"));
	        jf = new JarFile(jarFileName);
	        jarEntries = jf.entries();
	        while(jarEntries.hasMoreElements()){
	        	JarEntry jarEntry = jarEntries.nextElement();
	            entryName = jarEntry.getName();
	            if(entryName.equals(packageName) || entryName.substring(0,entryName.lastIndexOf('/')>0?entryName.lastIndexOf('/'):entryName.length()).equals(packageName)) {
	            	if(entryName.endsWith(".class")) {
		                names.add(entryName.replaceAll("/", ".").replaceFirst("\\.class", ""));
	            	}
	            }
	        }

	    // loop through files in classpath
	    }else{
	        File folder = new File(packageURL.getFile());
	        File[] contenuti = folder.listFiles();
	        String entryName;
	        for(File actual: contenuti){
	        	if(actual.isFile()) {
		            entryName = actual.getName();
		            entryName = entryName.substring(0, entryName.lastIndexOf('.'));
		            names.add((packageName + "/" + entryName).replaceAll("/", "."));
	        	}
	        }
	    }
	    return names;
	}
}
