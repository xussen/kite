package org.youth.kite.sky;

import java.io.File;
import java.io.StringReader;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.youth.kite.config.ConfigSource;

/**
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public class ConfigXmlParser {
	
	public static Configuration parse(ConfigSource source) {
		for (String path : source.getPaths()) {
			parse(path);
		}
		return null;
	}

	public static void parse(String path) {
        SAXReader reader = new SAXReader();
        final Document document;
		try {
			document = reader.read(new File(path));
	        Element rootElement = document.getRootElement();
//	        parse(rootElement);
		} catch (DocumentException e) {
			throw new RuntimeException("parse desc xml error : can not parse desc xml " + e.getMessage(), e);
		}
	}

}
