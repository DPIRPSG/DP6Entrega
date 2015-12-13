package controllers.clerk;

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

import services.ItemService;
import services.OrderItemService;
import services.WareHouseService;
import controllers.AbstractController;
import domain.Item;
import domain.Order;
import domain.OrderItem;

@Controller
@RequestMapping(value = "/order-item/clerk")
public class OrderItemClerkController extends AbstractController {

	//Services ----------------------------------------------------------
	
	@Autowired
	private OrderItemService orderItemService;
	
	@Autowired
	private WareHouseService warehouseService;
	
	@Autowired
	private ItemService itemService;

	//Constructors ----------------------------------------------------------

	public OrderItemClerkController() {
		super();
	}
	
	//Listing ----------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int orderId) {
		ModelAndView result;
		Collection<OrderItem> ordersItem;

		ordersItem = orderItemService.findAllByOrderId(orderId);
		
		result = new ModelAndView("order-item/list");
		result.addObject("requestURI", "order-item/list.do");
		result.addObject("orders-item", ordersItem);

		return result;
	}
	
	//Creation ----------------------------------------------------------
	
	@RequestMapping(value = "/serve", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int orderItemId) {
		ModelAndView result;
		OrderItem orderItem;
		
		orderItem = orderItemService.findOne(orderItemId);
		Assert.notNull(orderItem);
		result = createEditModelAndView(orderItem);
		
		return result;
	}
	
	//Edition ----------------------------------------------------------
	
	@RequestMapping(value = "/serve", method = RequestMethod.POST, params = "serve")
	public ModelAndView save(@Valid OrderItem orderItem, @RequestParam int unitsToServe, BindingResult binding) {
		ModelAndView result;
		Order order;
		Item item;
		String sku;
		int orderId;
		
		orderId = orderItem.getOrder().getId();
		sku = orderItem.getSku();
		
		order = orderItem.getOrder();
		item = itemService.findOneBySKU(sku);
		
		if (binding.hasErrors()) {
			result = createEditModelAndView(orderItem);
		} else {
			try {
				warehouseService.addItemToOrderItem(item, unitsToServe, order);	
				result = new ModelAndView("redirect:list.do?orderId="+orderId);
			} catch (Throwable oops) {
				result = createEditModelAndView(orderItem, "orderItem.commit.error");				
			}
		}

		return result;
	}
	
	//Ancillary Methods ----------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(OrderItem orderItem) {
		ModelAndView result;
		
		result = createEditModelAndView(orderItem, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(OrderItem orderItem, String message) {
		ModelAndView result;
		int units;
		int unitsServed;
		int orderId;
		
		units = orderItem.getUnits();
		unitsServed = orderItem.getUnitsServed();
		orderId = orderItem.getOrder().getId();
		
		result = new ModelAndView("order-item/serve");
		result.addObject("orderItem", orderItem);
		result.addObject("message", message);
		result.addObject("unitsNum", units);
		result.addObject("unitsServedNum", unitsServed);
		result.addObject("orderId", orderId);
		
		return result;
	}
}

