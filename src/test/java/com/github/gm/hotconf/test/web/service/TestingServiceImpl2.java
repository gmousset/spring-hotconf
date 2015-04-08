package com.github.gm.hotconf.test.web.service;

import org.springframework.stereotype.Service;

import com.github.gm.hotconf.annotations.HotConfigurableProperty;

/**
 * @author Gwendal Mousset
 *
 */
@Service
public class TestingServiceImpl2 implements TestingService2 {

  @HotConfigurableProperty("hotprop")
  private int prop;

  public int getProp() {
    return prop;
  }

  public void setProp(int prop) {
    this.prop = prop;
  }
}
