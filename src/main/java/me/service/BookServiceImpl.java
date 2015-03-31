/**
 * 
 */
package me.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author gwendalmousset
 *
 */
@Service
public class BookServiceImpl implements BookService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);
	
	/* (non-Javadoc)
	 * @see me.service.BookService#getBookBy(java.lang.String)
	 */
	@Cacheable("books")
	public String getBookBy(String pTitle) {
		LOGGER.info("call getBook with title " + pTitle);
		return pTitle;
	}
	
	public native String getTruc();
}
