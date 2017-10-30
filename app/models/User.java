package models;

import com.fasterxml.jackson.databind.JsonNode;
import io.ebean.Finder;
import io.ebean.Model;
import play.libs.Json;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author jiang
 */
@Entity
@Table(name = "user")
public class User extends Model {
    @Id
    public Long id;
    public String name;

    private static Finder<Long, User> find = new Finder<Long, User>(User.class);

    public static JsonNode findById(long id){
        return Json.toJson(find.byId(id));
    }
}
