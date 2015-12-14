package controllers.actor;

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

import services.ActorService;
import services.FolderService;
import services.MessageService;

import controllers.AbstractController;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Controller
@RequestMapping(value = "/message/actor")
public class MessageActorController extends AbstractController{

	//Services ----------------------------------------------------------

	@Autowired
	private MessageService messageService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private ActorService actorService;
	
	//Constructors ----------------------------------------------------------
	
	public MessageActorController(){
		super();
	}

	//Listing ----------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int folderId){
        ModelAndView result;
        Collection<Message> messages;
        Folder folder;
        
        folder = folderService.findOne(folderId);
        
        folderService.checkActor(folder);
        
        messages = messageService.findAllByFolder(folder);
        
        result = new ModelAndView("message/list");
        result.addObject("messa", messages);
        result.addObject("requestURI", "messages/actor/list.do");
        
        return result;
	}
	
	// Creation ----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Message message;
		
		message = messageService.create();
		
		message.setSender(actorService.findByPrincipal());
		
		result = createEditModelAndView(message);
		
		return result;
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "send")
	public ModelAndView save(@Valid Message message, BindingResult binding) {
		ModelAndView result;
		int sendId, actId;
		
		
		sendId = message.getSender().getUserAccount().getId();
		actId = actorService.findByPrincipal().getUserAccount().getId();
		
		Assert.isTrue(sendId == actId);
		

		if (binding.hasErrors()) {
			result = createEditModelAndView(message);
		} else {
			try {
				messageService.firstSave(message);
				result = new ModelAndView("redirect:../../folder/actor/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(message, "message.commit.error");				
			}
		}

		return result;
	}

	// Edition ----------------------------------------------------------
	
	

	// Ancillary Methods ----------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Message input) {
		ModelAndView result;
		
		result = createEditModelAndView(input, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Message input, String message){
		ModelAndView result;
		Collection<Actor> actors;
		
		actors = actorService.findAll();
		
		result = new ModelAndView("message/create");
		result.addObject("messa", input);
		result.addObject("actors", actors);
		result.addObject("message", message);
		
		return result;
	}
	
}
