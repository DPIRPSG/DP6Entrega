package controllers.consumer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.ShoppingCart;
import domain.WareHouse;

import services.ShoppingCartService;

@Controller
@RequestMapping(value = "/shopping-cart/consumer")
public class ShoppingCartConsumerController {
	
	// Services ----------------------------------------------------------
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	// Constructors ----------------------------------------------------------

	public ShoppingCartConsumerController() {
		super();
	}
	
	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<ShoppingCart> shoppingCarts;

		/* Tengo que ver como pasarle el consumer. Quizás se pueda hacer para que el servicio lo tenga en cuenta */
		shoppingCarts = shoppingCartService.findByConsumer(consumer);
		result = new ModelAndView("shopping-cart/list");
		result.addObject("requestURI", "shopping-cart/consumer/list.do");
		result.addObject("shoppingCarts", shoppingCarts);

		return result;
	}

}
