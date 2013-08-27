package models;

import com.avaje.ebean.Ebean;
import play.db.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: pranab
 * Date: 8/27/13
 * Time: 10:27 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Template extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String name;
    public String templateText;
    public Date createdAt;
    public Date lastModifiedAt;

    public static Finder<Long,Template> find = new Finder<Long,Template>(
            Long.class, Template.class
    );
}
