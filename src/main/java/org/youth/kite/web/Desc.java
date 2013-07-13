package org.youth.kite.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * web配置
 * 
 * @author xusensen
 * @date 2013-7-13
 */
public class Desc {

	private static final long serialVersionUID = -1023855100882151250L;

	private String url;
	
	private String description;
	
//	private List<WrapService> services = new ArrayList<WrapService>();
	
	private ArgList args = new ArgList();
	
	/**
	 * display content format
	 */
	private String outputformat;

	/**
	 * input content format
	 */
	private String inputformat;
	
	/**
	 * display content charset
	 */
	private String charset;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public List<WrapService> getServices() {
//		return services;
//	}
//
//	public void setServices(List<WrapService> services) {
//		this.services = services;
//	}

	public ArgList getArgs() {
		return args;
	}

	public void setArgs(ArgList args) {
		this.args = args;
	}

	public String getOutputformat() {
		return outputformat;
	}

	public void setOutputformat(String outputformat) {
		this.outputformat = outputformat;
	}

	public String getInputformat() {
		return inputformat;
	}

	public void setInputformat(String inputformat) {
		this.inputformat = inputformat;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	@SuppressWarnings("rawtypes")
	public static class ArgList implements Serializable{
		private static final long serialVersionUID = -8046812937388691217L;
		private Class bindClass = Map.class;
		private String extension;
		private List<Arg> args = new ArrayList<Arg>();
		public Class getBindClass() {
			return bindClass;
		}
		public void setBindClass(Class bindClass) {
			this.bindClass = bindClass;
		}
		public String getExtension() {
			return extension;
		}
		public void setExtension(String extension) {
			this.extension = extension;
		}
		public List<Arg> getArgs() {
			return args;
		}
		public void setArgs(List<Arg> args) {
			this.args = args;
		}
		public void addArg(Arg arg) {
			this.args.add(arg);
		}
	}
	
	public static class Arg implements Serializable {
		private static final long serialVersionUID = 870384221413209340L;
		private String shortName;
		private String description;
		private String defaultValue;
		private boolean required; 
		private String type;
		public String getShortName() {
			return shortName;
		}
		public void setShortName(String shortName) {
			this.shortName = shortName;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getDefaultValue() {
			return defaultValue;
		}
		public void setDefaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
		}
		public boolean isRequired() {
			return required;
		}
		public void setRequired(boolean required) {
			this.required = required;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
	}
	
}
