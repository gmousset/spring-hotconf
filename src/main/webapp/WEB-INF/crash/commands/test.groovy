import org.crsh.cli.Command;
import org.crsh.cli.Option;
import org.crsh.cli.Usage;

/**
 * Spring commands to list Customer in cache for the demo application.
 * @author drieu
 *
 */
class test {

	@Usage("Say coucou")
	@Command
	public Object coucou() {
		def bookService = context.attributes.beans["bookServiceImpl"];
		def msg = bookService.getBookBy("Toto")
		return "COUCOU";
	}
	
	@Usage("Print property value")
	@Command
	public Object get(@Usage("Property name") @Option(names = ["name", "n"]) String name) {
		def props = context.attributes.beans["props"];
		out.println(">>>");
		out.println(props);
	}
}