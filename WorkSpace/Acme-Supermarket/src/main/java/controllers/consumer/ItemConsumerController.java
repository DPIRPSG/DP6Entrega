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
import services.ExchangeRateService;
import services.ItemService;
import services.ShoppingCartService;
import domain.Consumer;
import domain.ExchangeRate;
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
	
	@Autowired
	private ExchangeRateService exchangeRateService;

	// Constructors ----------------------------------------------------------

	public ItemConsumerController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, defaultValue="") String keyword, @RequestParam(required=false) Integer exchangeRateId) {
		ModelAndView result;
		Collection<Item> items;
		Collection<ExchangeRate> moneyList;
		String keywordToFind;
		ExchangeRate exchangeRate;

		exchangeRate = null;
		moneyList = exchangeRateService.findAll();
				
		items = itemService.findAll();
		
		if (!keyword.equals("")) {
			String[] keywordComoArray = keyword.split(" ");
			for (int i = 0; i < keywordComoArray.length; i++) {
				if (!keywordComoArray[i].equals("")) {
					keywordToFind = keywordComoArray[i];
					items = itemService.findBySingleKeyword(keywordToFind);
					break;
				}
			}
		}
		
		if(exchangeRateId != null) {
			exchangeRate = exchangeRateService.findOne(exchangeRateId);
		} else {
			exchangeRate = exchangeRateService.findOneByName("Euros");
		}

		result = new ModelAndView("item/list");
		result.addObject("requestURI", "item/consumer/list.do?");
		result.addObject("items", items);
		result.addObject("moneyList", moneyList);
		result.addObject("exchangeRate", exchangeRate);
		result.addObject("keyword", keyword);

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
