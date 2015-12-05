package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import domain.Consumer;

import services.ConsumerService;

@Controller
@RequestMapping(value = "/consumer")
public class RegisterController extends AbstractController{

	//Services ----------------------------------------------------------
	
	@Autowired
	private ConsumerService consumerService;
	
	//Constructors ----------------------------------------------------------
	
	public RegisterController(){
		super();
	}

	//Listing ----------------------------------------------------------

	//Creation ----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Consumer consu;
		
		consu = consumerService.create();
		result = createEditModelAndView(consu);
		
		return result;
	}
	
	//Edition ----------------------------------------------------------
	
	
	//Ancillary Methods ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(Consumer consumer){
		ModelAndView result;
		
		result = createEditModelAndView(consumer, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Consumer consumer, String message){
		ModelAndView result;
		
		result = new ModelAndView("consumer/create");
		result.addObject("consumer", consumer);
		result.addObject("message", message);
		
		return result;
	}
}
