package org.youth.kite.test.container;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.youth.kite.NormalResponse;
import org.youth.kite.config.ConfigSource;
import org.youth.kite.model.Kite;
import org.youth.kite.sky.Container;
import org.youth.kite.test.AbstractTest;

public class ContainerTest extends AbstractTest {

	@Test
	public void testContainer() {
		ConfigSource configSource = new ConfigSource();
		Container container = new Container();
		container.init(configSource);
		Kite kite = container.getKite("");
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("request", null);
		NormalResponse response = new NormalResponse(System.out);
		paramMap.put("response", response);
		kite.fly(paramMap, System.out);
	}
}
