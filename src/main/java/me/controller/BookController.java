/**
 * 
 */
package me.controller;

import me.bean.WsResponse;
import me.bean.WsUser;
import me.service.BookService;
import me.validator.PersonId;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MimeTypeUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gwendalmousset
 *
 */
@RestController
@RequestMapping("/books")
@Validated
public class BookController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "title/{title}", 
			produces = {MimeTypeUtils.APPLICATION_JSON_VALUE, MimeTypeUtils.APPLICATION_XML_VALUE})
	public WsResponse getBookByTitle(@PathVariable String title,
								 	 @RequestParam(value = "lang" , defaultValue = "fr") String language) throws Exception {
		LOGGER.info("call getBookByTitle [title=" + title + ",lang=" + language + "]");
		String title2 = this.bookService.getBookBy(title);
		WsUser u = new WsUser();
		u.setFirstname("Michel");
		u.setLastname("Dipietro");
		WsResponse response = new WsResponse(title2);
		response.setUser(u);
		return response;
	}
	
	//@Cacheable("books")
	@RequestMapping("isbn/{isbn}")
	public String getBookByISBN(@PathVariable String isbn, @RequestParam @PersonId String param) {
		LOGGER.info("call getBookByISBN");
		return this.bookService.getBookBy(isbn);
	}
	
//	private void draaaaame() {
//		String coupable = null;
//		coupable.charAt(0);
//	}
}
