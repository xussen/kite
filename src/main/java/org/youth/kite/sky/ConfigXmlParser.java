package org.youth.kite.sky;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.youth.kite.PaseClassNameException;
import org.youth.kite.config.ConfigSource;
import org.youth.kite.loader.ClassPathLoader;
import org.youth.kite.loader.KiteLoader;
import org.youth.kite.model.Kite;
import static org.youth.kite.util.NewObject.*;

/**
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public class ConfigXmlParser {
    public static final String ROOT_ELEMENT_NAME = "context";

	
	public static Configuration parse(ConfigSource source) {
		for (String path : source.getPaths()) {
			parse(path);
		}
		return null;
	}

	public static Configuration parse(String path) {
        SAXReader reader = new SAXReader();
        final Document document;
		try {
			document = reader.read(new File(path));
	        Element rootElement = document.getRootElement();
	        return parse(rootElement);
		} catch (DocumentException e) {
			throw new RuntimeException("parse desc xml error : can not parse desc xml " + e.getMessage(), e);
		}
	}

	@SuppressWarnings("unchecked")
	private static Configuration parse(Element rootElement) {
		validateElement(rootElement, ROOT_ELEMENT_NAME);
		final Element context = rootElement;
		final Map<String, Kite> kites = new HashMap<String, Kite>();
		//类路径下装入Kite
		List<Element> kiteClassesEles = context.elements("kite");
		for (Element kiteClassEle : kiteClassesEles) {
			Kite kite = ClassPathLoader.loadKite(kiteClassEle.attribute("class").getValue());
			kites.put(kiteClassEle.attributeValue("id"), kite);
		}
		//扩展方式载入Kite
		List<Element> kitesEles = context.elements("kites");
		for (Element kitesEle : kitesEles) {
			final Map<String, Kite> importKites = new HashMap<String, Kite>();
			try {
				KiteLoader loader = newObj(kitesEle.attribute("class").getValue(), KiteLoader.class);
				for( Kite kite : loader.loadKites()) {
					importKites.put(kite.toString(), kite);
				}
			} catch (PaseClassNameException e) {
				throw new RuntimeException("can not new kites loader ", e);
			}
			kites.putAll(importKites);
		}
		Configuration configuration = new Configuration();
		configuration.addKites(kites);
		
		return configuration;
	}

	/**
	 * validate ele name 
	 * @param element
	 * @param elementName
	 */
	private static void validateElement(Element element, String elementName) {
		if(!element.getName().equals(elementName)) {
			throw new RuntimeException("parse desc xml error : the element "+element.getName()+" is not valid with "+elementName);
		}
	}

}
