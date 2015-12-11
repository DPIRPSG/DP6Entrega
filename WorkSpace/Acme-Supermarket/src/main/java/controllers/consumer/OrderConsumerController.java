package controllers.consumer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

import domain.Item;
import domain.Order;

import services.OrderService;

@Controller
@RequestMapping(value = "/order/consumer")
public class OrderConsumerController extends AbstractController {
	
	// Services ----------------------------------------------------------
	@Autowired
	private OrderService orderService;
	
	// Constructors ----------------------------------------------------------
	public OrderConsumerController(){
		super();
	}
	
	// Listing ----------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		
		ModelAndView result;
		Collection<Order> orders;
		
		/* Falta crear un servicio sin parámetros de entrada para que devuelva los order de un consumer determinado */
		orders = orderService.findAllByConsumer();
		
		result = new ModelAndView("order/list");
		result.addObject("orders", orders);
		result.addObject("requestURI", "order/consumer/list.do");
		
		return result;
	}
	
	//Edition ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int orderId){
		
		ModelAndView result;
		Order order;
		
		/* Falta crear un servicio con un parámetro de entrada para que devuelva el order a partir de su id */
		order = orderService.findOne(orderId);
		Assert.notNull(order);
		result = createEditModelAndView(order);
		
		return result;		
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Order order, BindingResult binding) {
		ModelAndView result;

		try {
			orderService.cancelOrder(order);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(order,
					"order.commit.error");
		}

		return result;
	}
	
	
	//Ancillary Methods ----------------------------------------------------------
	protected ModelAndView createEditModelAndView(Order order) {
		ModelAndView result;
		
		result = createEditModelAndView(order, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Order order, String message) {
		
		ModelAndView result;
		
		result = new ModelAndView("order/edit");
		result.addObject("order", order);
		result.addObject("message", message);
		
		return result;
	}
	
}
