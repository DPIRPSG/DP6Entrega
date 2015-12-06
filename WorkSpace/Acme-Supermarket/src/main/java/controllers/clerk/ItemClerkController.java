package controllers.clerk;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ItemService;


import controllers.AbstractController;
import domain.Item;

@Controller
@RequestMapping(value = "/item/clerk")
public class ItemClerkController extends AbstractController {

	//Services ----------------------------------------------------------
	
	@Autowired
	private ItemService itemService;

	//Constructors ----------------------------------------------------------

	public ItemClerkController() {
		super();
	}
	
	//Listing ----------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Item> items;
		
		items = itemService.findAll();
		result = new ModelAndView("item/list");
		result.addObject("requestURI", "item/clerk/list.do");
		result.addObject("items", items);
		
		return result;
	}
	
	//Creation ----------------------------------------------------------
	
	//Edition ----------------------------------------------------------
	
	//Ancillary Methods ----------------------------------------------------------
}
