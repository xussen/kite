package org.youth.kite.loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.youth.kite.model.Kite;
import org.youth.kite.script.KiteScript;
import static org.youth.kite.util.PackageUtil.*;

/**
 * 
 * @author xusensen
 * @date 2013-7-19
 */
public class ClassPathLoader {
	
	final static Logger LOGGER = Logger.getLogger(ClassPathLoader.class);
	
	public static List<Kite> loadKites(String scanPackage) {
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
	
	public static Kite loadKite(String className) {
		Class clazz;
		try {
			clazz = Class.forName(className);
			KiteScript kiteScript = (KiteScript)clazz.newInstance();
			JavaClass script = new JavaClass(kiteScript);
			Kite kite = new Kite(script, null);
			return kite;
		} catch (ClassNotFoundException e) {
			LOGGER.error(e);
		} catch (InstantiationException e) {
			LOGGER.error(e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e);
		}
		return null;
	}
	
	private static List<String> filterClazzNames(List<String> classNamesFromPackage) {
		final List<String> KiteClazzNames = new ArrayList<String>();
		for (String cn : classNamesFromPackage) {
			if(cn.endsWith("Kite")) {
				KiteClazzNames.add(cn);
			}
		}
		return KiteClazzNames;
	}

}
