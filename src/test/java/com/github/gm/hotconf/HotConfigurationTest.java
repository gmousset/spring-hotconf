/**
 * 
 */
package com.github.gm.hotconf;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.github.gm.hotconf.web.config.AppConfig;
import com.github.gm.hotconf.web.config.WebMvcConfig;
import com.github.gm.hotconf.web.service.TestingService;

/**
 * @author Gwendal Mousset
 *
 */
@WebAppConfiguration
@ContextHierarchy({
	@ContextConfiguration(classes = AppConfig.class),
	@ContextConfiguration(classes = WebMvcConfig.class),
})
@RunWith(SpringJUnit4ClassRunner.class)
public class HotConfigurationTest {

	private static final String TESTING_SERVICE_IMPL_STRING_PROP = "testingServiceImpl.stringProp";

	private static final String FLOAT_OBJ = "float.obj";

	private static final String FLOAT_PRIMITIVE = "float.primitive";

	private static final String DOUBLE_OBJ = "double.obj";

	private static final String DOUBLE_PRIMITIVE = "double.primitive";

	private static final String LONG_OBJ = "long.obj";

	private static final String LONG_PRIMITIVE = "long.primitive";

	private static final String INT_MAX = "int.max";

	private static final String INT_MIN = "int.min";

	private static final Logger LOGGER = LoggerFactory.getLogger(HotConfigurationTest.class);
	
	@Autowired
	private TestingService testingService;
	
	@Autowired
	private HotConfigurableProperties hotConfProps;
	
	@Test
	public void testPropertiesLoaded() {
		
		Set<String> allProps = this.hotConfProps.getAllProperties();
		assertNotNull(allProps);
		LOGGER.info("found props: " + allProps);
		
		// count
		assertEquals(9, allProps.size());
		
		// names
		assertTrue(allProps.contains(INT_MIN));
		assertTrue(allProps.contains(INT_MAX));
		assertTrue(allProps.contains(LONG_PRIMITIVE));
		assertTrue(allProps.contains(LONG_OBJ));
		assertTrue(allProps.contains(DOUBLE_PRIMITIVE));
		assertTrue(allProps.contains(DOUBLE_OBJ));
		assertTrue(allProps.contains(FLOAT_PRIMITIVE));
		assertTrue(allProps.contains(FLOAT_OBJ));
		assertTrue(allProps.contains(TESTING_SERVICE_IMPL_STRING_PROP));
		
		// @Value init
		assertEquals(10, this.testingService.getIntPrimProp());
		assertNotNull(this.testingService.getIntegerProp());
		assertEquals(100, this.testingService.getIntegerProp().intValue());
		
		// property values
		Object intMin = this.hotConfProps.getPropertyValue(INT_MIN);
		Object intMax = this.hotConfProps.getPropertyValue(INT_MAX);
		Object longPrm = this.hotConfProps.getPropertyValue(LONG_PRIMITIVE);
		Object longObj= this.hotConfProps.getPropertyValue(LONG_OBJ);
		Object doublePrm = this.hotConfProps.getPropertyValue(DOUBLE_PRIMITIVE);
		Object doubleObj = this.hotConfProps.getPropertyValue(DOUBLE_OBJ);
		Object floatPrm = this.hotConfProps.getPropertyValue(FLOAT_PRIMITIVE);
		Object floatObj = this.hotConfProps.getPropertyValue(FLOAT_OBJ);
		Object string = this.hotConfProps.getPropertyValue(TESTING_SERVICE_IMPL_STRING_PROP);
		assertEquals(10, (int) intMin);
		assertEquals(100, (int) intMax);
		assertEquals(0l, longPrm);
		assertEquals(null, longObj);
		assertEquals(0.0d, doublePrm);
		assertEquals(null, doubleObj);
		assertEquals(0.0f, floatPrm);
		assertEquals(null, floatObj);
		assertEquals(null, string);
	}
	
	@Test
	public void testChangePropertyValues() {
		// int, ok
		this.hotConfProps.setPropertyValue(INT_MIN, "12");
		int intMin = (int) this.hotConfProps.getPropertyValue(INT_MIN);
		assertEquals(12, intMin);
		
		// string, nok
		this.hotConfProps.setPropertyValue(INT_MIN, "toto");
	}
}
