package controllers.clerk;

import java.util.Collection;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ClerkService;
import services.OrderService;

import controllers.AbstractController;
import domain.Clerk;
import domain.Order;

@Controller
@RequestMapping(value = "/order/clerk")
public class OrderClerkController extends AbstractController{
	
	//Services ----------------------------------------------------------
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ClerkService clerkService;

	//Constructors ----------------------------------------------------------

	public OrderClerkController(){
		super();
	}
	
	//Listing ----------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=false, defaultValue="") String messageStatus){
		ModelAndView result;
		Collection<Order> orders;
		String messageResult;
		
		orders = orderService.findAllNotAssigned();
		orders.addAll(orderService.findAllByClerk());
		
		result = new ModelAndView("order/list");
		result.addObject("requestURI", "order/clerk/list.do");
		result.addObject("orders", orders);
		
		if(messageStatus != ""){
			result.addObject("messageStatus", messageStatus);
		}
		
		return result;
	}
	
	//Assign ----------------------------------------------------------

	@RequestMapping(value = "/self-assign", method = RequestMethod.GET)
	public ModelAndView assign(@RequestParam int orderId){
		ModelAndView result;
		Clerk clerk ;
		Order order;
		
		try{
			order = orderService.findOne(orderId);
			Assert.notNull(order);
			clerk = clerkService.findByprincipal();
			Assert.notNull(clerk);
		
			orderService.assignToClerkManual(clerk, order);
			result = new ModelAndView("redirect: list.do");
			result.addObject("messageStatus", "order.self-assign.ok");
		} catch (Throwable ops){
			result = new ModelAndView("redirect: list.do");
			result.addObject("messageStatus", "order.self-assign.error");
			
			
		}
		return result;
	}
	
	//Edition ----------------------------------------------------------

	//Ancillary Methods ----------------------------------------------------------
}
