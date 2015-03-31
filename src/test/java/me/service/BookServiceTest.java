/**
 * 
 */
package me.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import me.configuration.AppConfig;
import me.configuration.WebMvcConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;


/**
 * @author gwendalmousset
 *
 */
//@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@ContextHierarchy({
//@ContextConfiguration(classes = {AppConfig.class, WebMvcConfig.class})
	@ContextConfiguration(classes = AppConfig.class),
	@ContextConfiguration(classes = WebMvcConfig.class),
})
//@ContextConfiguration(classes = AppConfig.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceTest.class);
	
	/** Mock MVC*/
	private MockMvc mockMvc;

	/** Context */
	@Autowired
	private WebApplicationContext ctx;

	/**
	 * Called before every test execution
	 */
	@Before
	public void setUp() {
		this.mockMvc = webAppContextSetup(this.ctx).build();
	}
	
	@Autowired
	private BookService bookService;
	
	@Test
	public void test() throws Exception {
		LOGGER.info("start test");
		final MvcResult result = this.mockMvc.perform(
				get("/person/10pp")
				).andReturn();
		LOGGER.info("end test");
	}
}
