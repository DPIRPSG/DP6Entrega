package controllers.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import domain.Consumer;
import domain.ShoppingCart;

import services.ConsumerService;
import services.ShoppingCartService;

@Controller
@RequestMapping(value = "/shopping-cart/consumer")
public class ShoppingCartConsumerController extends AbstractController{
	
	// Services ----------------------------------------------------------
	@Autowired
	private ShoppingCartService shoppingCartService;
	@Autowired
	private ConsumerService consumerService;
	
	// Constructors ----------------------------------------------------------

	public ShoppingCartConsumerController() {
		super();
	}
	
	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Consumer consumer;
		ShoppingCart shoppingCart;
		
		consumer = consumerService.findByPrincipal();
		shoppingCart = shoppingCartService.findByConsumer(consumer);
		
		result = new ModelAndView("shopping-cart/list");
		result.addObject("requestURI", "shopping-cart/consumer/list.do");
		result.addObject("shoppingCarts", shoppingCart);

		return result;
	}

}
