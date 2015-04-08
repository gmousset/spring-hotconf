/**
 * 
 */
package com.github.gm.hotconf.test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.util.ReflectionUtils;

import com.github.gm.hotconf.Errors;
import com.github.gm.hotconf.HotConfigurableProperties;
import com.github.gm.hotconf.test.web.config.AppConfig;
import com.github.gm.hotconf.test.web.config.WebMvcConfig;
import com.github.gm.hotconf.test.web.service.TestingService;
import com.github.gm.hotconf.test.web.service.TestingService2;

/**
 * @author Gwendal Mousset
 *
 */
@WebAppConfiguration
@ContextHierarchy({ @ContextConfiguration(classes = AppConfig.class), @ContextConfiguration(classes = WebMvcConfig.class)})
@RunWith(SpringJUnit4ClassRunner.class)
public class HotConfigurationTest {

  /** */
  private static final String TESTING_SERVICE_IMPL_STRING_PROP = "testingServiceImpl.stringProp";

  /** */
  private static final String FLOAT_OBJ = "float.obj";

  /** */
  private static final String FLOAT_PRIMITIVE = "float.primitive";

  /** */
  private static final String DOUBLE_OBJ = "double.obj";

  /** */
  private static final String DOUBLE_PRIMITIVE = "double.primitive";

  /** */
  private static final String LONG_OBJ = "long.obj";

  /** */
  private static final String LONG_PRIMITIVE = "long.primitive";

  /** */
  private static final String INT_MAX = "int.max";

  /** */
  private static final String INT_MIN = "int.min";

  /** */
  private static final Logger LOGGER = LoggerFactory.getLogger(HotConfigurationTest.class);

  /** */
  @Autowired
  private TestingService testingService;
  
  /** */
  @Autowired
  private TestingService2 testingService2;

  /** */
  @Autowired
  private HotConfigurableProperties hotConfProps;

  /** */
  private ArrayList<String> unsupportedType;
  
  /** */
  @Test
  public final void testPropertiesLoaded() {

    Set<String> allProps = this.hotConfProps.getAllProperties();
    assertNotNull(allProps);
    LOGGER.info("found props: " + allProps);

    // count
    assertEquals(10, allProps.size()); // 9 in service1 + 1 in service 2

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
    Object longObj = this.hotConfProps.getPropertyValue(LONG_OBJ);
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
    
    // get unknown property
    Object fake1 = this.hotConfProps.getPropertyValue("COUCOUFAKE");
    assertTrue(((String) fake1).startsWith(Errors.UNKNOWN_PROPERTY.getMessage()));
  }

  @Test
  public void testChangePropertyValues() {
    // int, ok
    this.hotConfProps.setPropertyValue(INT_MIN, "12");
    int intMin1 = (int) this.hotConfProps.getPropertyValue(INT_MIN);
    assertEquals(12, intMin1);

    // int, nok
    this.hotConfProps.setPropertyValue(INT_MIN, "toto");
    int intMin2 = (int) this.hotConfProps.getPropertyValue(INT_MIN);
    assertEquals(12, intMin2);

    // Integer, ok
    this.hotConfProps.setPropertyValue(INT_MAX, "30");
    int intMax1 = (Integer) this.hotConfProps.getPropertyValue(INT_MAX);
    assertEquals(30, intMax1);

    // Integer, jok
    this.hotConfProps.setPropertyValue(INT_MAX, "30ggggg");
    int intMax2 = (Integer) this.hotConfProps.getPropertyValue(INT_MAX);
    assertEquals(30, intMax2);

    // long, ok
    this.hotConfProps.setPropertyValue(LONG_PRIMITIVE, "10000000");
    long longp1 = (long) this.hotConfProps.getPropertyValue(LONG_PRIMITIVE);
    assertEquals(10000000L, longp1);

    // long, nok
    this.hotConfProps.setPropertyValue(LONG_PRIMITIVE, "90000000ppppp");
    long longp2 = (long) this.hotConfProps.getPropertyValue(LONG_PRIMITIVE);
    assertEquals(10000000L, longp2);

    // Long, ok
    this.hotConfProps.setPropertyValue(LONG_OBJ, "774000");
    Long longo1 = (Long) this.hotConfProps.getPropertyValue(LONG_OBJ);
    assertEquals(774000L, longo1.longValue());

    // Long, nok
    this.hotConfProps.setPropertyValue(LONG_OBJ, "774000eeeeee");
    Long longo2 = (Long) this.hotConfProps.getPropertyValue(LONG_OBJ);
    assertEquals(774000L, longo2.longValue());

    // float, ok
    this.hotConfProps.setPropertyValue(FLOAT_PRIMITIVE, "100.98");
    float floatp1 = (float) this.hotConfProps.getPropertyValue(FLOAT_PRIMITIVE);
    assertEquals(100.98f, floatp1, 0.0);

    // float, nok
    this.hotConfProps.setPropertyValue(FLOAT_PRIMITIVE, "100.98mmmm");
    float floatp2 = (float) this.hotConfProps.getPropertyValue(FLOAT_PRIMITIVE);
    assertEquals(100.98f, floatp2, 0.0);

    // Float, ok
    this.hotConfProps.setPropertyValue(FLOAT_OBJ, "35.5");
    Float floato1 = (Float) this.hotConfProps.getPropertyValue(FLOAT_OBJ);
    assertEquals(35.5f, floato1.floatValue(), 0.0);

    // Float, nok
    this.hotConfProps.setPropertyValue(FLOAT_OBJ, "3.14ttt");
    Float floato2 = (Float) this.hotConfProps.getPropertyValue(FLOAT_OBJ);
    assertEquals(35.5f, floato2.floatValue(), 0.0);

    // double, ok
    this.hotConfProps.setPropertyValue(DOUBLE_PRIMITIVE, "320.12345");
    double doublep1 = (double) this.hotConfProps.getPropertyValue(DOUBLE_PRIMITIVE);
    assertEquals(320.12345d, doublep1, 0.0);

    // double, nok
    this.hotConfProps.setPropertyValue(DOUBLE_PRIMITIVE, "320.12345zzzz");
    double doublep2 = (double) this.hotConfProps.getPropertyValue(DOUBLE_PRIMITIVE);
    assertEquals(320.12345d, doublep2, 0.0);

    // Double, ok
    this.hotConfProps.setPropertyValue(DOUBLE_OBJ, "500.00001");
    Double doubleo1 = (Double) this.hotConfProps.getPropertyValue(DOUBLE_OBJ);
    assertEquals(500.00001, doubleo1.doubleValue(), 0.0);

    // Double, nok
    this.hotConfProps.setPropertyValue(DOUBLE_OBJ, "500.00001rrrrrrrr");
    Double doubleo2 = (Double) this.hotConfProps.getPropertyValue(DOUBLE_OBJ);
    assertEquals(500.00001, doubleo2.doubleValue(), 0.0);
    
    // String
    this.hotConfProps.setPropertyValue(TESTING_SERVICE_IMPL_STRING_PROP, "hop");
    String str = (String) this.hotConfProps.getPropertyValue(TESTING_SERVICE_IMPL_STRING_PROP);
    assertEquals("hop", str);
    
    // unknown property
    Object errorRet = this.hotConfProps.setPropertyValue("POUETPOUET", "hop");
    assertTrue(((String) errorRet).startsWith(Errors.UNKNOWN_PROPERTY.getMessage()));
    
    // not a number
    Object errorRet2 = this.hotConfProps.setPropertyValue(FLOAT_OBJ, "hop");
    assertTrue(((String) errorRet2).startsWith(Errors.NOT_A_NUMBER.getMessage()));
    
    
    // add unsupported type
    final Field uf = ReflectionUtils.findField(HotConfigurationTest.class, "unsupportedType");
    this.hotConfProps.addProperty(this, uf, "niiiii");
    // normally nothing append...
  }
  
  @Test
  public void testHooks() {
    // change int.min value and check hooks execution order
    this.hotConfProps.setPropertyValue(INT_MIN, "10");
    // get order list
    List<String> order = this.testingService.getHooksProof();
    assertEquals("4", order.get(0));
    assertEquals("3", order.get(1));
    assertEquals("2", order.get(2));
    assertEquals("1", order.get(3));
    assertEquals("8", order.get(4));
    assertEquals("4", order.get(5));
    assertEquals("2", order.get(6));
    assertEquals("1", order.get(7));
  }
}
