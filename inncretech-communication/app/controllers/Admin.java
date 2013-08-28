package controllers;

import com.avaje.ebean.Ebean;
import models.Event;
import models.Template;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;
import views.html.*;

/**
 * Created with IntelliJ IDEA.
 * User: pranab
 * Date: 8/28/13
 * Time: 5:58 PM
 * To change this template use File | Settings | File Templates.
 */
@org.springframework.stereotype.Controller
public class Admin extends Controller{

    public Result index(){
        List<Template> templates = Template.find.findList();
        return ok(index.render(templates));
    }

    public Result saveTemplate()throws Exception{
        JsonNode json = request().body().asJson();
        if(json == null) {
            return badRequest("Expecting Json data");
        } else {
            try{
            ObjectMapper mapper = new ObjectMapper();
            Template template = mapper.readValue(json, Template.class);
            Ebean.beginTransaction();
            List<Template> templates =  Template.find.where().eq("name", template.getName()).findList();
            System.out.println(template.getTemplateText()) ;
            if(templates.size() > 0){
                System.out.println(template.getTemplateText()) ;
                Template t = templates.get(0);
                t.setTemplateText(template.getTemplateText());
                t.setSubject(template.getSubject());
                Ebean.update(t);
            }else{
                Ebean.save(template);
            }
            Ebean.commitTransaction();
            }finally{
                Ebean.endTransaction();
            }
            return ok("ok");
        }
    }
}
