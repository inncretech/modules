package controllers;

import models.Event;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import play.*;
import play.mvc.*;

import play.mvc.Controller;
import services.CommunicationService;
import views.html.*;

@org.springframework.stereotype.Controller
public class Application extends Controller {

    @Autowired
    private CommunicationService communicationService;

    public Result index(){
        return ok("ok");
    }
  
    public  Result handleEvent()throws Exception {
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        } else {
            ObjectMapper mapper = new ObjectMapper();
            Event event = mapper.readValue(json, Event.class);
            communicationService.handleEvent(event);
            return ok(index.render("Your new application is readyn."));
        }
    }
  
}
