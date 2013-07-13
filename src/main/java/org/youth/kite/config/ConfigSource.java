package org.youth.kite.config;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置信息的来源，简单的封装
 * @author xussen
 *
 */
public class ConfigSource {
	
	private final static String DEFAULT_PATH = "kite-context.xml";
	
	private final List<String> paths = new ArrayList<String>();
	
	{
		paths.add(DEFAULT_PATH);
	}
	
	public void addPath(String path) {
		paths.add(path);
	}

	public List<String> getPaths() {
		return paths;
	}

}
