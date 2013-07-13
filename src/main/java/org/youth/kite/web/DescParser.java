/*
 *    Copyright 2011-2012 19lou.com
 */
package org.youth.kite.web;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.youth.kite.web.Desc.Arg;
import org.youth.kite.web.Desc.ArgList;

/**
 * desc xml Parser
 * 
 * @author xusensen 2013-4-12 
 * E-mail:xussen1020@gmail.com xusensen@19lou.com
 *
 */
public class DescParser {
    public static final String ROOT_ELEMENT_NAME = "smartscript";

	
	public static Desc parse(String xmlString) {
        SAXReader reader = new SAXReader();
        Document document;
		try {
			document = reader.read(new StringReader(xmlString));
	        Element rootElement = document.getRootElement();
	        return parse(rootElement);
		} catch (DocumentException e) {
			throw new RuntimeException("parse desc xml error : can not parse desc xml " + e.getMessage(), e);
		}
	}

	private static Desc parse(Element rootElement) {
		Desc descDocument = new Desc();
		validateElement(rootElement, ROOT_ELEMENT_NAME);
		Element smartScript = rootElement;
		validateElementExsit(smartScript, "url");
		String url = getValue(smartScript.element("url"));
		descDocument.setUrl(url);
		
		String description = getValue(smartScript.element("description"));
		descDocument.setDescription(description);

		String outputformat = getElemDefaultValue(smartScript.element("outputformat"), "text/html");
		descDocument.setOutputformat(outputformat);

		String inputformat = getElemDefaultValue(smartScript.element("inputformat"), "keyvalue");
		descDocument.setInputformat(inputformat);

		String charset = getElemDefaultValue(smartScript.element("charset"), "GBK");
		descDocument.setCharset(charset);
		
		Element servicesEl = smartScript.element("services");
		parseServices(servicesEl, descDocument);
		
		Element argsEl = smartScript.element("args");
		parseArgs(argsEl, descDocument);
		
		return descDocument;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void parseArgs(Element argsEl, Desc descDocument) {
		ArgList argList = new ArgList();
		String className = getAttrDefaultValue(argsEl, "bindClass", null);
		String extension = getAttrDefaultValue(argsEl, "extension", "json");
		Class bindClazz = Map.class;
		if(className != null) {
			try {
				bindClazz = Class.forName(className);
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
		argList.setBindClass(bindClazz);
		argList.setExtension(extension);
		List<Element> argsEles = argsEl.elements("arg");
		for (Element argsEle : argsEles) {
			Arg arg = parseArg(argsEle);
			argList.addArg(arg);
		}
		descDocument.setArgs(argList);
	}

	private static Arg parseArg(Element argsEle) {
		validateElementExsit(argsEle, "shortname");
		String shortName = getValue(argsEle.element("shortname"));
		String description = getValue(argsEle.element("description"));
		String defaultValue = getValue(argsEle.element("default"));
		String required = getElemDefaultValue(argsEle.element("required"), "false");
		String type = getValue(argsEle.element("type"));
		Arg arg = new Arg();
		arg.setShortName(shortName);
		arg.setDescription(description);
		arg.setDefaultValue(defaultValue);
		arg.setRequired(required.equals("true")?true:false);
		arg.setType(type==null?"string":type);
		return arg;
	}

	@SuppressWarnings("unchecked")
	private static void parseServices(Element servicesEl, Desc descDocument) {
		if(servicesEl == null) {
			return;
		}
		List<Object> services = new ArrayList<Object>();
		List<Element> servicesElements = servicesEl.elements("service");
		for (Element servicesElement : servicesElements) {
			validateAttribute(servicesElement, "id");
			String id = getValue(servicesElement.attribute("id"));
			String extension = getAttrDefaultValue(servicesElement, "extension", "WithNoExtension");
			boolean readOnly = getAttrDefaultValue(servicesElement, "readOnly", "true").equals("true")?true:false;
//			WrapService wrapService = new WrapService(id, extension, readOnly);
//			services.add(wrapService);
		}
//		descDocument.setServices(services);
	}
	
	private static String getValue(Attribute attribute) {
		if(attribute != null) {
			return attribute.getStringValue().trim();
		} else {
			return null;
		}
	}
	
	private static String getAttrDefaultValue(Element element, String attrName, String defaultValue) {
		Attribute attribute = element.attribute(attrName);
		if(attribute == null) {
			return defaultValue;
		} else {
			return attribute.getStringValue().trim();
		}
	}

	private static String getElemDefaultValue(Element element, String defaultValue) {
		if(element == null) {
			return defaultValue;
		} else {
			String v = getValue(element);
			return v == null?defaultValue:v;
		}
	}
	
//	private static String getExsitValue(Element element) {
//		if(getValue(element)) {
//			
//		}
//		
//	}
	
	private static String getValue(Element element) {
		if(element != null) {
			Attribute attribute = element.attribute("value");
			if(attribute != null) {
				return attribute.getStringValue().trim();
			} else {
				return element.getTextTrim();
			}
		} else {
			return null;
		}
	}

	private static void validateAttribute(Element element, String attrName) {
		if(element.attribute(attrName)==null) {
			throw new RuntimeException("parse desc xml error : the attribute "+attrName+" is required in " + element.getName());
		}
	}

	private static void validateElement(Element element, String elementName) {
		if(!element.getName().equals(elementName)) {
			throw new RuntimeException("parse desc xml error : the element "+element.getName()+" is not valid with "+elementName);
		}
	}

	private static void validateElementExsit(Element parentElement, String elementName) {
		Element element = parentElement.element(elementName);
		if(element == null) {
			throw new RuntimeException("parse desc xml error : can not find the element "+elementName+" under "+parentElement.getName());
		}
	}
	
}