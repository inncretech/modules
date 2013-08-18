package models;

import com.avaje.ebean.Ebean;
import org.codehaus.jackson.map.ObjectMapper;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: pranab
 * Date: 25/7/13
 * Time: 6:26 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Communication extends Model{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String contactInfo;
    //captures the template to select
    public byte commType ;
    public String commData;
    public byte commMethod;
    public boolean sent;
    public Date sentAt;


    public static Finder<Long,Communication> find = new Finder<Long,Communication>(
            Long.class, Communication.class
    );

    public static void insert(Communication communication){
        Ebean.save(communication);
    }

    public void markAsSent(){
     this.sent = true;
     this.sentAt = new Date();
     Ebean.save(this);
    }

    public void setCommData(Event event)throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> data = new HashMap<String, Object>();
        if(event.getEventData() !=null)
            data.putAll(event.getEventData());

        data.put("firstName", event.getUser().getFirstName());
        data.put("lastName", event.getUser().getLastName());
        data.put("emailId", event.getUser().getEmailId());
        commData = mapper.writeValueAsString(data);
    }

    public Map getCommData()throws Exception{
        if(commData !=null){
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(commData, Map.class);
        }
        return null;
    }
}
