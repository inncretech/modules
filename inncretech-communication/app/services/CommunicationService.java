package services;

import models.Communication;
import models.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stringtemplate.v4.AutoIndentWriter;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: pranab
 * Date: 25/7/13
 * Time: 6:25 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class CommunicationService {

    @Autowired
    private CommuncationSenderService communcationSenderService;

    public void handleEvent(Event event)throws Exception{
        Communication comm = createCommunicationToSend(event);
        communcationSenderService.sendCommunication(comm);
    }

    private Communication createCommunicationToSend(Event event)throws Exception{
        Communication comm = new Communication();
        comm.commType = event.getEventType().getId();
        comm.commMethod = 1;
        comm.setCommData(event);
        comm.contactInfo = event.getUser().getEmailId();
        Communication.insert(comm);
        return comm;
    }
}
