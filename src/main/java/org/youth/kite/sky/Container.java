package org.youth.kite.sky;

import java.util.Map;

import org.youth.kite.cache.Cache;
import org.youth.kite.config.ConfigSource;
import org.youth.kite.model.Kite;

import static org.youth.kite.sky.ConfigXmlParser.*;

public class Container {
	
	private static Cache cache;
	
	/**
	 * 根据现有的配置，初始化整个容器
	 * @param configuration
	 */
	public static void init(ConfigSource configSource) {
		//parse 
		Configuration configuration = parse(configSource);
		//linking
		initKites(configuration);
		initInterceptors(configuration);
	}
	
	private static void initInterceptors(Configuration configuration) {
	}

	private static void initKites(Configuration configuration) {
		final Map<String, Kite> kites = configuration.kites();
		cache.add(kites);
	}

	public static Kite getKite(String name) {
		return cache.getKite(name);
	}
	
	public static void freshKite(String name) {
	}
	
	/**
	 * 更加现有的配置，重新加载一次容器，将原来所 包含的成员全部覆盖
	 */
	public static void reload() {
		
	}

	public static void main(String[] args) {
	}

}
