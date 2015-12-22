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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomizationInfoService;
import services.ExchangeRateService;
import services.ItemService;
import domain.CustomizationInfo;
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
	
	@Autowired
	private CustomizationInfoService customizationInfoService;

	// Constructors -----------------------------------------------------------

	public WelcomeController() {
		super();
	}

	// Index ------------------------------------------------------------------

	@RequestMapping(value = "/index")
	public ModelAndView index(
			@RequestParam(required = false, defaultValue = "John Doe") String name,
			@RequestParam(required = false) Integer exchangeRateId,
			@RequestParam(required = false, defaultValue = "") String messageStatus,
			@RequestParam(required=false, defaultValue="94") int customizationInfoId) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		ExchangeRate exchangeRate;
		Collection<ExchangeRate> moneyList;
		Collection<CustomizationInfo> customizations;
		CustomizationInfo customizationInfo;
		
		customizationInfo = customizationInfoService.findOne(customizationInfoId);
		customizations = customizationInfoService.findAll();

		exchangeRate = null;
		moneyList = exchangeRateService.findAll();

		if (exchangeRateId != null) {
			exchangeRate = exchangeRateService.findOne(exchangeRateId);
		} else {
			exchangeRate = exchangeRateService.findOneByName("Euros");
		}

		Collection<Item> items;
		Item item;
		// Random rnd = new Random();
		// int r;
		// int i;

		items = itemService.findItemBestSelling();
		// r = (int) (Math.random() * items.size());
		// r = rnd.nextInt(items.size());
		// System.out.println(items.size());
		// System.out.println(r);
		// item = null;
		/*
		 * for(i=1;i<=r;i++) { item = items.iterator().next(); }
		 */
		item = items.iterator().next();

		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());

		result = new ModelAndView("welcome/index");
		result.addObject("name", name);
		result.addObject("item", item);
		result.addObject("moment", moment);
		result.addObject("moneyList", moneyList);
		result.addObject("exchangeRate", exchangeRate);
		result.addObject("customizations", customizations);
		result.addObject("customizationInfo", customizationInfo);
		
		if(messageStatus != ""){
			result.addObject("messageStatus", messageStatus);
		}

		return result;
	}
}