package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
        result.addObject("requestURI", "order/list.do")
        
        return result;
	}
	
	
	//Creation ----------------------------------------------------------

	
	//Edition ----------------------------------------------------------

	
	//Ancillary Methods ----------------------------------------------------------

}
