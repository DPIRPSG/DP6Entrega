package controllers.consumer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ContentService;

import controllers.AbstractController;
import domain.Content;
import domain.Storage;

@Controller
@RequestMapping(value = "/content/consumer")
public class ContentConsumerController extends AbstractController{
	
	// Services ----------------------------------------------------------
	@Autowired
	private ContentService contentService;
	
	// Constructors ----------------------------------------------------------

	public ContentConsumerController() {
		super();
	}
	
	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int shoppingCartId) {
		ModelAndView result;
		Collection<Content> contents;
		boolean byShoppingCart;
		
		byShoppingCart = true;

		/* Falta un servicio de findAllByShoppingCartId(shoppingCartId) como el de storageService */
		contents = contentService.findAllByShoppingCartId(shoppingCartId);
		result = new ModelAndView("content/list");
		result.addObject("requestURI", "content/consumer/list.do");
		result.addObject("contents", contents);
		result.addObject("byWarehouse", byShoppingCart);

		return result;
	}

}
