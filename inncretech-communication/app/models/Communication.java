package models;

import com.avaje.ebean.Ebean;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

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
}
