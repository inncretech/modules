package services;

import org.springframework.stereotype.Component;
import play.libs.F;
import play.libs.WS;

import java.util.Map;

@Component
public class SendGridService {

  private static final String MARKETING_EMAIL_ENDPOINT ="https://sendgrid.com/api/newsletter/";

  public void setDefaultQueryParam(WS.WSRequestHolder wsRequestHolder){
    wsRequestHolder.setQueryParameter("api_user", "");
    wsRequestHolder.setQueryParameter("api_key", "");
  }

  public boolean isListExist(String name){
    WS.WSRequestHolder wsRequestHolder = WS.url(MARKETING_EMAIL_ENDPOINT + "/list/get.json");
    WS.Response response = wsRequestHolder.setQueryParameter("list", name).get().get();
    response.asJson();
    return true;
  }
  public void addEmailToDefaultList(String applicationName, String emailId, Map data){
    WS.url(MARKETING_EMAIL_ENDPOINT+"");
  }
}
