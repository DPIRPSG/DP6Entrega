package controllers.consumer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import controllers.AbstractController;

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
	
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam int orderId){
		ModelAndView result;
		Order order;
		
		try{
			order = orderService.findOne(orderId);
			orderService.cancelOrder(order);
		
			result = new ModelAndView("redirect:list.do");
			result.addObject("message", "order.commit.ok");
		}catch(Throwable oops){
			result = new ModelAndView("redirect:list.do");
			result.addObject("message", "order.commit.error");
		}
		
		return result;
	}
	
	
	//Ancillary Methods ----------------------------------------------------------

	
}
