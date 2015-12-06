package controllers.clerk;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.StorageService;
import controllers.AbstractController;
import domain.Storage;

@Controller
@RequestMapping(value = "/storage/clerk")
public class StorageClerkController extends AbstractController {

	// Services ----------------------------------------------------------
	@Autowired
	private StorageService storageService;

	// Constructors ----------------------------------------------------------

	public StorageClerkController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int warehouseId) {
		ModelAndView result;
		Collection<Storage> storages;

		storages = storageService.findAllByWarehouseId(warehouseId);
		result = new ModelAndView("storage/list");
		result.addObject("requestURI", "storage/clerk/list.do");
		result.addObject("storages", storages);

		return result;
	}

	// Creation ----------------------------------------------------------

	// Edition ----------------------------------------------------------

	// Ancillary Methods ----------------------------------------------------------

}
