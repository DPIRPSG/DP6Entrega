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

import services.ItemService;
import domain.Item;

@Controller
@RequestMapping("/welcome")
public class WelcomeController extends AbstractController {
	
	// Services ----------------------------------------------------------

		@Autowired
		private ItemService itemService;

	// Constructors -----------------------------------------------------------
	
	public WelcomeController() {
		super();
	}
		
	// Index ------------------------------------------------------------------		

	@RequestMapping(value = "/index")
	public ModelAndView index(@RequestParam(required=false, defaultValue="John Doe") String name) {
		ModelAndView result;
		SimpleDateFormat formatter;
		String moment;
		
		Collection<Item> items;
		Item item;
		//Random rnd = new Random();
		//int r;
		//int i;
		
		items = itemService.findItemBestSelling();
		//r = (int) (Math.random() * items.size());
		//r = rnd.nextInt(items.size());
		//System.out.println(items.size());
		//System.out.println(r);
		//item = null;
		/*for(i=1;i<=r;i++) {
			item = items.iterator().next();
		}*/
		item = items.iterator().next();
		
		formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		moment = formatter.format(new Date());
		
		result = new ModelAndView("welcome/index");
		result.addObject("name", name);
		result.addObject("item", item);
		result.addObject("moment", moment);

		return result;
	}
}