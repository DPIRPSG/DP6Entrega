package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.OrderService;

import controllers.AbstractController;
import domain.Order;

@Controller
@RequestMapping(value = "/order/administrator")
public class OrderAdministratorController extends AbstractController{

	//Services ----------------------------------------------------------

	@Autowired
	private OrderService orderService;
	
	//Constructors ----------------------------------------------------------
	
	public OrderAdministratorController(){
		super();
	}

	//Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
        ModelAndView result;
        Collection<Order> orders;
        
        orders = orderService.findAll();
        
        result = new ModelAndView("order/list");
        result.addObject("orders", orders);
        result.addObject("requestURI", "order/administrator/list.do");
        
        return result;
	}
	
	
	//Creation ----------------------------------------------------------

	
	//Edition ----------------------------------------------------------

	
	//Ancillary Methods ----------------------------------------------------------

}
