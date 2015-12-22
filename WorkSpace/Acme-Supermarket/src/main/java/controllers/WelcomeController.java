/* WelcomeController.java
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ExchangeRateService;
import services.ItemService;
import domain.ExchangeRate;
import domain.Item;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private ItemService itemService;

	@Autowired
	private ExchangeRateService exchangeRateService;
	

	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------

	@RequestMapping(value = "/index")
	public ModelAndView index(
			@RequestParam(required = false, defaultValue = "John Doe") String name,
			@RequestParam(required = false) Integer exchangeRateId,
			@CookieValue(value = "item", required = false) Item itemCookie,
			@RequestParam(required = false, defaultValue = "") String messageStatus,
			HttpServletResponse response) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		ExchangeRate exchangeRate;
		Collection<ExchangeRate> moneyList;

		exchangeRate = null;
		moneyList = exchangeRateService.findAll();

		if (exchangeRateId != null) {
			exchangeRate = exchangeRateService.findOne(exchangeRateId);
		} else {
			exchangeRate = exchangeRateService.findOneByName("Euros");
		}

		Collection<Item> items;
		Item item;

		items = itemService.findItemBestSelling();
		item = items.iterator().next();

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());

		result = new ModelAndView("welcome/index");
		result.addObject("name", name);
		result.addObject("item", item);
		result.addObject("moment", moment);
		result.addObject("moneyList", moneyList);
		result.addObject("exchangeRate", exchangeRate);
		
		if(itemCookie == null){
			itemCookie = itemService.findAll().iterator().next();
			response.addCookie(new Cookie("item", String.valueOf(itemCookie.getId())));
		}else{
			System.out.println("cookie recibida: ");
			System.out.println("ID: "+itemCookie.getId());
			System.out.println("Name: "+itemCookie.getName());

		}
		
		
		
		if(messageStatus != ""){
			result.addObject("messageStatus", messageStatus);
		}

		return result;
	}
}