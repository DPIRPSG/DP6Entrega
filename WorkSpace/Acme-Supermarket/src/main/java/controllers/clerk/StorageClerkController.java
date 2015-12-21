package controllers.clerk;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ExchangeRateService;
import services.StorageService;
import controllers.AbstractController;
import domain.ExchangeRate;
import domain.Storage;

@Controller
@RequestMapping(value = "/storage/clerk")
public class StorageClerkController extends AbstractController {

	// Services ----------------------------------------------------------
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private ExchangeRateService exchangeRateService;

	// Constructors ----------------------------------------------------------

	public StorageClerkController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listByWarehouse(@RequestParam Integer warehouseId, @RequestParam Integer itemId, @RequestParam(required=false) Integer exchangeRateId) {
		ModelAndView result;
		Collection<Storage> storages;
		boolean byWarehouse;
		boolean byItem;
		ExchangeRate exchangeRate;
		Collection<ExchangeRate> moneyList;
        
        exchangeRate = null;
		moneyList = exchangeRateService.findAll();
		
		if(exchangeRateId != null) {
			exchangeRate = exchangeRateService.findOne(exchangeRateId);
		} else {
			exchangeRate = exchangeRateService.findOneByName("Euros");
		}
		
		if(warehouseId != null) {
			byWarehouse = true;

			storages = storageService.findAllByWarehouseId(warehouseId);
			result = new ModelAndView("storage/list");
			result.addObject("requestURI", "storage/clerk/list.do");
			result.addObject("storages", storages);
			result.addObject("byWarehouse", byWarehouse);
			result.addObject("moneyList", moneyList);
			result.addObject("exchangeRate", exchangeRate);
			result.addObject("warehouseId", warehouseId);
		} else {
			byItem = true;
			
			storages = storageService.findAllByItemId(itemId);
			result = new ModelAndView("storage/list");
			result.addObject("requestURI", "storage/clerk/list.do");
			result.addObject("storages", storages);
			result.addObject("byItem", byItem);
		}
		
		

		return result;
	}

	// Creation ----------------------------------------------------------

	// Edition ----------------------------------------------------------

	// Ancillary Methods ----------------------------------------------------------

}
