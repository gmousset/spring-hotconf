package com.github.gm.hotconf;

import org.crsh.spring.SpringWebBootstrap;

/**
 * @author Gwendal Mousset
 */
public class HotConfBootstrap extends SpringWebBootstrap {

  /**
   * Default constructor.
   */
  public HotConfBootstrap() {
    super();
    this.setCmdMountPointConfig("classpath:/crash/commands");
    this.setConfMountPointConfig("classpath:/crash");
  }
}
