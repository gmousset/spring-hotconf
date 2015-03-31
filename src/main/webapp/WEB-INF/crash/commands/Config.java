/**
 * 
 */
package commands;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import me.configurable.annotation.ConfigurableProperties;
import me.service.ConfService;

import org.crsh.cli.Argument;
import org.crsh.cli.Command;
import org.crsh.cli.Named;
import org.crsh.cli.Option;
import org.crsh.cli.Required;
import org.crsh.cli.Usage;
import org.crsh.command.BaseCommand;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.PropertySources;

/**
 * @author gwendalmousset
 *
 */
@Usage("Configuration properties")
public class Config extends BaseCommand {
	
	@Usage("Just say hello")
	@Command
	@Named("hello")
	public Object sayHello() {
		return "Hello";
	}
	
	@Usage("Configurable property list")
	@Command
	@Named("list")
	public Object list() {
		Map<String, Object> map = (Map<String, Object>) this.context.getAttributes().get("beans");
		ConfigurableProperties configurableProperties = (ConfigurableProperties) map.get("configurableProperties");
		Set<String> props = configurableProperties.getAllProperties();
		for (String prop : props) {
			this.out.println(prop);
		}
		return "\n";
	}
	
	@Usage("Print property value")
	@Command
	@Named("get")
	public Object getPropertyValue(@Usage("Property name") @Argument @Required String pName) {
		Map<String, Object> map = (Map<String, Object>) this.context.getAttributes().get("beans");
		ConfigurableProperties configurableProperties = (ConfigurableProperties) map.get("configurableProperties");
		Object value = configurableProperties.getPropertyValue(pName);
		return value;
	}
	
	@Usage("Set property value")
	@Command
	@Named("set")
	public void setPropertyValue(@Argument @Required String pName, @Argument @Required String pNewValue) {
		Map<String, Object> map = (Map<String, Object>) this.context.getAttributes().get("beans");
		ConfigurableProperties configurableProperties = (ConfigurableProperties) map.get("configurableProperties");
		configurableProperties.setPropertyValue(pName, Integer.valueOf(pNewValue));
	}
}
