package services;

import models.Communication;
import models.EventType;
import org.springframework.stereotype.Service;
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
    private STGroupDir groupDir;

    public TemplateService(){
        templateDataMap.put(EventType.LOGIN.getId() , "login.email.template");
        groupDir = new STGroupDir("templates/group");

    }
    public String createCommunicationBody(Communication comm) throws Exception{
        return groupDir.getInstanceOf(templateDataMap.get(comm.commType)).render();
    }
}
