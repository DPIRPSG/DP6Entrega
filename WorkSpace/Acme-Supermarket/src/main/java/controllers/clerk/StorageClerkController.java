package controllers.clerk;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ItemService;
import services.StorageService;
import services.WareHouseService;
import controllers.AbstractController;
import domain.Item;
import domain.Storage;
import domain.WareHouse;

@Controller
@RequestMapping(value = "/storage/clerk")
public class StorageClerkController extends AbstractController {

	// Services ----------------------------------------------------------
	
	@Autowired
	private StorageService storageService;
	
	@Autowired
	private WareHouseService warehouseService;
	
	@Autowired
	private ItemService itemService;

	// Constructors ----------------------------------------------------------

	public StorageClerkController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listByWarehouse(@RequestParam Integer warehouseId, @RequestParam Integer itemId) {
		ModelAndView result;
		Collection<Storage> storages;
		boolean byWarehouse;
		boolean byItem;
		WareHouse warehouse;
		Item item;
		
		if(warehouseId != null) {
			byWarehouse = true;

			warehouse = warehouseService.findOne(warehouseId);
			storages = storageService.findAllByWarehouseId(warehouseId);
			result = new ModelAndView("storage/list");
			result.addObject("requestURI", "storage/clerk/list.do");
			result.addObject("storages", storages);
			result.addObject("byWarehouse", byWarehouse);
			result.addObject("warehouse", warehouse);
		} else {
			byItem = true;
			
			item = itemService.findOne(itemId);
			storages = storageService.findAllByItemId(itemId);
			result = new ModelAndView("storage/list");
			result.addObject("requestURI", "storage/clerk/list.do");
			result.addObject("storages", storages);
			result.addObject("byItem", byItem);
			result.addObject("item", item);
		}
		
		

		return result;
	}

	// Creation ----------------------------------------------------------

	// Edition ----------------------------------------------------------

	// Ancillary Methods ----------------------------------------------------------

}
