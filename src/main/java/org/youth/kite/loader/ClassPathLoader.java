package org.youth.kite.loader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;
import org.youth.kite.core.KiteScript;
import org.youth.kite.model.Kite;

public class ClassPathLoader {
	
	final static Logger LOGGER = Logger.getLogger(ClassPathLoader.class);
	
	public List<Kite> loadKites() {
		final List<Kite> kites = new ArrayList<Kite>();
		try {
			for(String cn : filterClazzNames(getClassNamesFromPackage(scanPackage))) {
				Class clazz = Class.forName(scanPackage+"."+cn);
				KiteScript kiteScript = (KiteScript)clazz.newInstance();
				JavaClass script = new JavaClass(kiteScript);
				Kite kite = new Kite(script, null);
				kites.add(kite);
			}
		} catch (IOException e) {
			LOGGER.error("scanPackage has trouble? ", e);
		} catch (ClassNotFoundException e) {
			LOGGER.error(e);
		} catch (InstantiationException e) {
			LOGGER.error(e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e);
		}
		return kites;
	}
	
	private List<String> filterClazzNames(List<String> classNamesFromPackage) {
		final List<String> KiteClazzNames = new ArrayList<String>();
		for (String cn : classNamesFromPackage) {
			if(cn.endsWith("Kite")) {
				KiteClazzNames.add(cn);
			}
		}
		return KiteClazzNames;
	}

	private String scanPackage = null;
	
	public void setScanPackage(String scanPackage) {
		this.scanPackage = scanPackage;
	}

	public static void main(String[] args) throws IOException {
		ClassPathLoader classPathLoader = new ClassPathLoader();
		for(String cn : classPathLoader.getClassNamesFromPackage("org.youth.kite")) {
			System.out.println(cn);
		}
	}
	public ArrayList<String> getClassNamesFromPackage(String packageName) throws IOException{
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
	        System.out.println(">"+jarFileName);
	        jf = new JarFile(jarFileName);
	        jarEntries = jf.entries();
	        while(jarEntries.hasMoreElements()){
	            entryName = jarEntries.nextElement().getName();
	            if(entryName.startsWith(packageName) && entryName.length()>packageName.length()+5){
	                entryName = entryName.substring(packageName.length(),entryName.lastIndexOf('.'));
	                names.add(entryName);
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
		            names.add(entryName);
	        	}
	        }
	    }
	    return names;
	}
}
