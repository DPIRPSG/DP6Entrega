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

import controllers.AbstractController;

import domain.Consumer;
import domain.Order;

import services.ConsumerService;
import services.OrderService;
import services.ShoppingCartService;

@Controller
@RequestMapping(value = "/order/consumer")
public class OrderConsumerController extends AbstractController {
	
	// Services ----------------------------------------------------------
	@Autowired
	private OrderService orderService;
	@Autowired
	private ShoppingCartService ShoppingCartService;
	@Autowired
	private ConsumerService consumerService;

	
	// Constructors ----------------------------------------------------------
	public OrderConsumerController(){
		super();
	}
	
	// Listing ----------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, defaultValue="") String messageStatus){
		
		ModelAndView result;
		Collection<Order> orders;
		
		/* Falta crear un servicio sin parámetros de entrada para que devuelva los order de un consumer determinado */
		orders = orderService.findAllByConsumer();
		
		result = new ModelAndView("order/list");
		result.addObject("orders", orders);
		result.addObject("requestURI", "order/consumer/list.do");
		
		if(messageStatus != ""){
			result.addObject("messageStatus", messageStatus);
		}
		
		return result;
	}
	
	//Creation ---------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Order order;
		
		order = ShoppingCartService.createCheckOut();
		result = createEditModelAndView(order);
		
		return result;
	}

	
	//Edition ----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Order order, BindingResult binding){
		ModelAndView result;
		Consumer actualConsumer;
		
		actualConsumer = order.getConsumer();
		
		Assert.isTrue(actualConsumer.equals(consumerService.findByPrincipal()), "Only the owner of the order can save it");
		
		boolean bindingError;
		
		if(binding.hasFieldErrors("orderItems")){
			bindingError = binding.getErrorCount() > 1;
		}else{
			bindingError = binding.getErrorCount() > 0;
		}
		
		if(bindingError){
			result = createEditModelAndView(order);
		} else {
			try {
				ShoppingCartService.saveCheckOut(order, order.getConsumer());
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				if(oops.getMessage().equals("order.commit.error.creditcard.date")){
					result = createEditModelAndView(order, "order.commit.error.creditcard.date");
				}else{
					result = createEditModelAndView(order, "order.commit.create.error");				
				}
			}
		}
		
		return result;
	}
	
	@RequestMapping(value = "/cancel", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam int orderId){
		ModelAndView result;
		Order order;
		Consumer actualConsumer;
		
		try{
			order = orderService.findOne(orderId);
			actualConsumer = order.getConsumer();
			Assert.isTrue(actualConsumer.equals(consumerService.findByPrincipal()), "Only the owner of the order can cancel it");
			orderService.cancelOrder(order);
			result = new ModelAndView("redirect:list.do");
			result.addObject("messageStatus", "order.commit.ok");
		}catch(Throwable oops){
			result = new ModelAndView("redirect:list.do");
			result.addObject("messageStatus", "order.commit.error");
		}
		
		return result;
	}
	
	
	//Ancillary Methods ----------------------------------------------------------
	protected ModelAndView createEditModelAndView(Order order){
		ModelAndView result;
		
		result = createEditModelAndView(order,null);
		
		return result;
	}

	protected ModelAndView createEditModelAndView(Order order, String message){
		ModelAndView result;
		
		result = new ModelAndView("order/create");
		result.addObject("order", order);
		result.addObject("message", message);
		
		return result;
	}
	
}
