package com.github.gm.hotconf;

import java.util.Properties;

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
    final Properties props = new Properties();
    
    // default values
    props.setProperty("crash.vfs.refresh_period", "1");
    props.setProperty("crash.ssh.auth_timeout", "300000");
    props.setProperty("crash.ssh.idle_timeout", "300000");
    props.setProperty("crash.auth", "simple");
    this.setConfig(props);
  }
  
  /**
   * Set the username for ssh access.
   * @param pUsername The username.
   */
  public final void setUsername(final String pUsername) {
    this.getConfig().setProperty("crash.auth.simple.username", pUsername);
  }
  
  /**
   * Set the password for ssh access.
   * @param pPassword The password.
   */
  public final void setPassword(final String pPassword) {
    this.getConfig().setProperty("crash.auth.simple.password", pPassword);
  }
  
  /**
   * Set the SSH listening port.
   * @param pPort The ssh port.
   */
  public final void setSshPort(final String pPort) {
    this.getConfig().setProperty("crash.ssh.port", pPort);
  }
}
