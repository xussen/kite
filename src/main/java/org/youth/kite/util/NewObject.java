package org.youth.kite.util;

import org.youth.kite.PaseClassNameException;

public class NewObject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static <T> T newObj(String className, Class<T> clazz) throws PaseClassNameException {
		try {
			return (T)Class.forName(className).newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			throw new PaseClassNameException("can not instant obj by className " + className, e);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new PaseClassNameException("can not access class by className " + className, e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new PaseClassNameException("can not find class by className " + className, e);
		}
	}

}
