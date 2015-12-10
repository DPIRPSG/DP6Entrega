package controllers.consumer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import services.ItemService;
import domain.Item;

@Controller
@RequestMapping(value = "/item/consumer")
public class ItemConsumerController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private ItemService itemService;

	// Constructors ----------------------------------------------------------

	public ItemConsumerController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam String keyword) {
		ModelAndView result;
		Collection<Item> items;

		if (keyword == "") {
			items = itemService.findAll();
		} else {
			items = itemService.findBySingleKeyword(keyword);
		}

		result = new ModelAndView("item/list");
		result.addObject("requestURI", "item/consumer/list.do");
		result.addObject("items", items);

		return result;
	}
}
