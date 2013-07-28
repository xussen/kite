package org.youth.kite.loader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.youth.kite.model.JavaClass;
import org.youth.kite.model.JavaClass02;
import org.youth.kite.model.JavaView;
import org.youth.kite.model.Kite;
import org.youth.kite.model.Script;
import org.youth.kite.model.View;
import org.youth.kite.script.KiteOutPutStreamScript;
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
				final Kite kite = buildKite(cn);
				if(kite != null) {
					kites.add(kite);
				}
			}
		} catch (IOException e) {
			LOGGER.error("scanPackage has trouble? ", e);
		}
		return kites;
	}
	
	@SuppressWarnings("rawtypes")
	private static Kite buildKite(String clazzName) {
		try {
			final Kite kite;
			Class clazz = Class.forName(clazzName);
			Object scriptObject = clazz.newInstance();
			if(scriptObject instanceof KiteScript) {
				KiteScript kiteScript = (KiteScript)scriptObject;
				Script script = new JavaClass(kiteScript);
				String viewTemplateClassPath = clazzName.substring(0, clazzName.lastIndexOf(".")); 
				View view = new JavaView(viewTemplateClassPath);
				kite = new Kite(script, view);
			} else if (scriptObject instanceof KiteOutPutStreamScript) {
				KiteOutPutStreamScript kiteScript = (KiteOutPutStreamScript)scriptObject;
				JavaClass02 script = new JavaClass02(kiteScript);
				kite = new Kite(script, null);
			} else {
				kite = null;
			}
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
