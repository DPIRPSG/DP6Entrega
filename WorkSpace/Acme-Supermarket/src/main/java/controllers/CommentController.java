package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CommentService;
import services.ItemService;
import domain.Comment;
import domain.Item;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController {
	
	// Services ----------------------------------------------------------
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ActorService actorService;
	
	
	// Constructors --------------------------------------------------------
	
	public CommentController() {
		super();
	}
	
	
	// Listing ------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int itemId) {
		ModelAndView result;
		Collection<Comment> comments;
		Item item;
		
		item = itemService.findOne(itemId);
		comments = commentService.findAllByItem(item);
		
		result = new ModelAndView("comment/list");
		result.addObject("comments", comments);
		result.addObject("requestURI", "comment/list.do");
		
		return result;
	}
	
	
	// Creation --------------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int itemId) {
		ModelAndView result;
		Comment comment;
		Item item;
		
		item = itemService.findOne(itemId);
		comment = commentService.createByItem(item);		
		result = createEditModelAndView(comment);
		
		return result;
	}
	
	
	// Ancillary methods ---------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Comment comment) {
		ModelAndView result;
		
		result = createEditModelAndView(comment, null);
		
		return result;
	}
	
	protected ModelAndView createEditModelAndView(Comment comment, String message) {
		ModelAndView result;
		
		if(!actorService.checkAuthority("ADMIN") && !actorService.checkAuthority("CONSUMER") && !actorService.checkAuthority("CLERK")){
			comment.setUserName("Anonymous");
		}else{
			comment.setUserName(actorService.findByPrincipal().getName());
		}
		
		result = new ModelAndView("comment/create");
		result.addObject("comment", comment);
		
		return result;
	}
}
