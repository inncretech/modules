package services;

import models.Communication;
import models.EventType;
import models.Template;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STGroupFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class TemplateService {
    private Map<Byte, String> templateDataMap = new HashMap<Byte, String>();

    public TemplateService(){
        templateDataMap.put(EventType.LOGIN.getId() , "login");
        templateDataMap.put(EventType.SIGNUP.getId() , "signup");
        templateDataMap.put(EventType.FORGOTPWD.getId() , "forgotpwd");
    }
    public String createCommunicationBody(Communication comm) throws Exception{
        String templateText =  Template.find.where().eq("name", "login").findList().get(0).templateText;
        return new ST(templateText).add("data", comm.getCommData()).render();
    }
}
