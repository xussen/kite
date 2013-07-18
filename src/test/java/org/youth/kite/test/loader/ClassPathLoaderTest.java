package org.youth.kite.test.loader;

import org.junit.Test;
import org.youth.kite.loader.ClassPathLoader;
import org.youth.kite.model.Kite;
import org.youth.kite.test.AbstractTest;

public class ClassPathLoaderTest extends AbstractTest {
	
	@Test
	public void testLoadKites() {
		ClassPathLoader loader = new ClassPathLoader();
		for(Kite kite : loader.loadKites("org.junit")) {
			System.out.println(kite);
		}
		assertTrue("loadKites size:" + loader.loadKites("org.junit").size() + " != 1 ", loader.loadKites("org.junit").size()==1);
		
	}
	
}
