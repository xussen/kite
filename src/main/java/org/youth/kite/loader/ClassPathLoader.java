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
import org.youth.kite.model.Kite;
import org.youth.kite.script.KiteScript;

public class ClassPathLoader {
	
	final static Logger LOGGER = Logger.getLogger(ClassPathLoader.class);
	
	public List<Kite> loadKites() {
		final List<Kite> kites = new ArrayList<Kite>();
		try {
			for(String cn : filterClazzNames(getClassNamesFromPackage(scanPackage))) {
				Class clazz = Class.forName(cn);
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
		classPathLoader.setScanPackage("org.youth.kite.test.loader.");
		for(Kite kite : classPathLoader.loadKites()) {
			System.out.println(kite);
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
	        jf = new JarFile(jarFileName);
	        jarEntries = jf.entries();
	        while(jarEntries.hasMoreElements()){
	        	JarEntry jarEntry = jarEntries.nextElement();
	            entryName = jarEntry.getName();
	            if(entryName.equals(packageName) || entryName.substring(0,entryName.lastIndexOf('/')+1).equals(packageName)) {
	            	if(entryName.endsWith(".class")) {
		                names.add(entryName.replaceAll("/", "."));
			            System.out.println(entryName.replaceAll("/", "."));
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
		            names.add((packageName + entryName).replaceAll("/", "."));
	        	}
	        }
	    }
	    return names;
	}
}
