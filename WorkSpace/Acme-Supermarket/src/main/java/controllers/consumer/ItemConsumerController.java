package controllers.consumer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import services.ConsumerService;
import services.ItemService;
import services.ShoppingCartService;
import domain.Consumer;
import domain.Item;
import domain.ShoppingCart;

@Controller
@RequestMapping(value = "/item/consumer")
public class ItemConsumerController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ConsumerService consumerService;

	// Constructors ----------------------------------------------------------

	public ItemConsumerController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam String keyword) {
		ModelAndView result;
		Collection<Item> items;
		String keywordToFind;

		if (keyword == "") {
			items = itemService.findAll();
		} else {
			String[] keywordComoArray = keyword.split(" ");
			keywordToFind = keywordComoArray[0];
			items = itemService.findBySingleKeyword(keywordToFind);
		}

		result = new ModelAndView("item/list");
		result.addObject("requestURI", "item/list.do");
		result.addObject("items", items);

		return result;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add(@RequestParam int itemId){
		ModelAndView result;
		ShoppingCart shoppingCart;
		Item item;
		Consumer consumer;
		
		try{
			consumer = consumerService.findByPrincipal();
			shoppingCart = shoppingCartService.findByConsumer(consumer);
			item = itemService.findOne(itemId);
			shoppingCartService.addItem(shoppingCart, item);
			result = new ModelAndView("redirect:list.do");
			result.addObject("message", "item.add.ok");
			result.addObject("keyword", "");
		}catch(Throwable oops){
			result = new ModelAndView("redirect:list.do");
			result.addObject("message", "item.commit.error");
			result.addObject("keyword", "");
		}
		
		return result;
	}
}
