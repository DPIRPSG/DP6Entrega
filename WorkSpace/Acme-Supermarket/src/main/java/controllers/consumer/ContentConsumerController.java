package controllers.consumer;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ContentService;

import controllers.AbstractController;
import domain.Content;

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
		
		/* Falta un servicio de findAllByShoppingCartId(shoppingCartId) como el de storageService */
		contents = contentService.findByShoppingCart(shoppingCartId);
		result = new ModelAndView("content/list");
		result.addObject("requestURI", "content/consumer/list.do");
		result.addObject("contents", contents);
		
		return result;
	}
	
	// Edition ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int contentId){
		ModelAndView result;
		Content content;
		
		content = contentService.findOneByContentId(contentId);
		Assert.notNull(content);
		result = createEditModelAndView(content);
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Content content, BindingResult binding){
		ModelAndView result;
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(content);
		} else {
			try {
				contentService.save(content);
				result = new ModelAndView("redirect:/shopping-cart/consumer/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(content, "content.commit.error");				
			}
		}
		
		return result;
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Content content, BindingResult binding){
		ModelAndView result;
		
		try{
			contentService.deleteComplete(content);
			result = new ModelAndView("redirect:/shopping-cart/consumer/list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(content, "content.commit.error");
		}
		
		return result;
	}
	
	// Ancilary methods -------------------------------------------------
	protected ModelAndView createEditModelAndView(Content content) {
		ModelAndView result;
		
		result = createEditModelAndView(content, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Content content, String message){
		ModelAndView result;
		
		result = new ModelAndView("content/edit");
		result.addObject("content", content);
		result.addObject("message", message);
		
		return result;
	}
	

}
