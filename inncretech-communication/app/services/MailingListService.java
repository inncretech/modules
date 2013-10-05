package services;

import org.springframework.stereotype.Component;
import play.Play;
import play.libs.WS;

@Component
public class MailingListService {

  private String applicationName;

  public MailingListService(){
    applicationName = Play.application().configuration().getString("application.name");
  }

  public void addEmailToList(String emailId){

    String defaultListName = applicationName+"-all";


  }
}
